package madri.me.movixter.movie;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

/**
 * Created by badri on 5/13/17.
 */

public class Movie {
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("adult")
    private boolean mIsAdult;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("genre_ids")
    private List<Integer> mGenreIds;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("id")
    private int mId;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("popularity")
    private BigDecimal mPopularity;
    @SerializedName("vote_count")
    private int mVoteCount;
    @SerializedName("video")
    private boolean isVideoAvailable;
    @SerializedName("vote_average")
    private BigDecimal mVoteAverage;

    public String getPosterPath() {
        String scheme = "https";
        String host = "image.tmdb.org";
        Uri uri = new Uri.Builder()
                .scheme(scheme)
                .authority(host)
                .appendPath("t")
                .appendPath("p")
                .appendPath("w185")
                .appendPath(mPosterPath.substring(1))
                .build();
        return uri.toString();
    }

    public boolean isAdult() {
        return mIsAdult;
    }

    public String getOverview() {
        return mOverview;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public int getId() {
        return mId;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public BigDecimal getPopularity() {
        return mPopularity;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public boolean isVideoAvailable() {
        return isVideoAvailable;
    }

    public BigDecimal getVoteAverage() {
        return mVoteAverage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

}
