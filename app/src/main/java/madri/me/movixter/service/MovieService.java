package madri.me.movixter.service;

import madri.me.movixter.movie.Movie;
import madri.me.movixter.movie.MovieResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by badri on 5/13/17.
 */

public interface MovieService {

    @GET("/3/movie/{search_type}")
    Call<MovieResults> getMovieResults(@Path("search_type") String searchType,
                                       @Query("api_key") String apiKey,
                                       @Query("page") int page);

}
