package madri.me.movixter.movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by badri on 5/13/17.
 */

public class MovieResults {
    @SerializedName("page")
    private int mPage;
    @SerializedName("results")
    private List<Movie> mMovieList;
    @SerializedName("status_message")
    private String mStatusMessage;
    @SerializedName("status_code")
    private int mStatusCode;

    public int getPage() {
        return mPage;
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public int getStatusCode() {
        return mStatusCode;
    }
}
