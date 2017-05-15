package madri.me.movixter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import madri.me.movixter.R;
import madri.me.movixter.movie.Movie;
import madri.me.movixter.utils.Utils;

public class MovieDetailActivity extends AppCompatActivity {
    private RelativeLayout mProgressBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        Intent intent = getIntent();
        mProgressBarLayout = (RelativeLayout) findViewById(R.id.progress_bar_layout);

        String movieJson = intent.getStringExtra(getString(R.string.movie_key));
        if(movieJson!=null) {
            Gson gson = new Gson();
            Movie movie = gson.fromJson(movieJson, Movie.class);
            TextView movieName = (TextView) findViewById(R.id.movie_title);
            TextView releaseDate = (TextView) findViewById(R.id.movie_release_date);
            TextView voteAverage = (TextView) findViewById(R.id.avg_vote);
            TextView plotDescription = (TextView) findViewById(R.id.plot_description);
            ImageView movieImage = (ImageView) findViewById(R.id.movie_poster);

            movieName.setText(movie.getTitle());
            releaseDate.setText(movie.getReleaseDate());
            voteAverage.setText(String.valueOf(movie.getVoteAverage().doubleValue()));
            plotDescription.setText(movie.getOverview());
            Picasso.with(this).load(movie.getPosterPath()).placeholder(android.R.drawable.ic_menu_gallery).into(movieImage, new Callback() {
                @Override
                public void onSuccess() {
                    mProgressBarLayout.setVisibility(View.GONE);
                }

                @Override
                public void onError() {
                    mProgressBarLayout.setVisibility(View.GONE);
                }
            });
        } else {
            mProgressBarLayout.setVisibility(View.GONE);
            Utils.showError(this, false, "Error", "Movie not Found. Please try again later!");
        }
    }


}
