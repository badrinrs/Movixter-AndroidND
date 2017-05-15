package madri.me.movixter.activity;

import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import madri.me.movixter.R;
import madri.me.movixter.adapter.MovieAdapter;
import madri.me.movixter.movie.Movie;
import madri.me.movixter.movie.MovieResults;
import madri.me.movixter.service.MovieService;
import madri.me.movixter.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieGridActivity extends AppCompatActivity {
    private static final String TAG = MovieGridActivity.class.getSimpleName();

    private RecyclerView mMovieGridView;
    private MovieAdapter mMovieAdapter;
    private List<Movie> mMovieList;
    private RelativeLayout mProgressBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        mMovieGridView = (RecyclerView) findViewById(R.id.rv_movie_list);
        mProgressBarLayout = (RelativeLayout) findViewById(R.id.progress_bar_layout);

        getMovies("popular");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        Switch aSwitch = (Switch) menu.findItem(R.id.action_switch_movie_type).getActionView().findViewById(R.id.switch_movie);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.getText().toString().contains("Popular")) {
                    compoundButton.setText(R.string.top_rated_movies);
                    getMovies(getString(R.string.top_rated));
                } else {
                    compoundButton.setText(R.string.popular_movies);
                    getMovies(getString(R.string.popular));
                }
            }
        });
        return true;
    }



    private void getMovies(String type) {
        if(!Utils.isOnline(this)) {
            Utils.showError(this, false, "Network Error", "No Internet Connection! Please try again later!");
        } else {
            mProgressBarLayout.setVisibility(View.VISIBLE);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MovieService service = retrofit.create(MovieService.class);
            Call<MovieResults> movieResultsCall = service.getMovieResults(type, getString(R.string.tmdb_api_key), 1);
            movieResultsCall.enqueue(new Callback<MovieResults>() {
                @Override
                public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                    mProgressBarLayout.setVisibility(View.INVISIBLE);
                    MovieResults movieResults = response.body();
                    if (movieResults.getStatusCode() == 404 || movieResults.getStatusCode() == 201) {
                        Utils.showError(MovieGridActivity.this, false, "Error", movieResults.getStatusMessage());
                    } else {
                        mMovieList = movieResults.getMovieList();
                        mMovieAdapter = new MovieAdapter(MovieGridActivity.this, mMovieList);
                        GridLayoutManager layoutManager = new GridLayoutManager(MovieGridActivity.this, 2);
                        mMovieGridView.setAdapter(mMovieAdapter);
                        mMovieGridView.setLayoutManager(layoutManager);
                        mMovieAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<MovieResults> call, Throwable t) {
                    mProgressBarLayout.setVisibility(View.INVISIBLE);
                    Log.v(TAG, "Error: " + t);
                    Log.v(TAG, "Request: " + call.request().url().toString());
                    Utils.showError(MovieGridActivity.this, false, "Error", t.getMessage());
                }
            });
        }
    }
}
