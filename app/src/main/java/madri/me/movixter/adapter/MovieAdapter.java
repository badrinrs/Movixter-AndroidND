package madri.me.movixter.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import madri.me.movixter.R;
import madri.me.movixter.activity.MovieDetailActivity;
import madri.me.movixter.movie.Movie;

/**
 * Created by badri on 5/13/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String TAG = MovieAdapter.class.getSimpleName();

    private List<Movie> mMovieList;
    private Context mContext;

    public MovieAdapter(Context context, List<Movie> movieList) {
        mContext = context;
        mMovieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        holder.mMovieProgressBar.setVisibility(View.VISIBLE);
        Picasso.with(mContext)
                .load(mMovieList.get(position).getPosterPath())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.mMovieImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.mMovieProgressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {
                        holder.mMovieProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mMovieList ==null ? 0 : mMovieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mMovieImageView;
        ProgressBar mMovieProgressBar;

        MovieViewHolder(View itemView) {
            super(itemView);
            mMovieProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_movie_image);
            mMovieImageView = (ImageView) itemView.findViewById(R.id.iv_movie_image);
            mMovieImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra(mContext.getString(R.string.movie_key), new Gson().toJson(mMovieList.get(getAdapterPosition())));
            mContext.startActivity(intent);
        }
    }
}
