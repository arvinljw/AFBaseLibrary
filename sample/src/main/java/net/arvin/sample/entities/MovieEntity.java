package net.arvin.sample.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by arvinljw on 18/3/4 11:46
 * Function：
 * Desc：
 */
public class MovieEntity implements Parcelable {
    /**
     * articleUrl : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg
     * movieName : 肖申克的救赎
     * scoreNumber : 9.6
     * style : 犯罪,剧情
     */

    private String articleUrl;
    private String movieName;
    private String scoreNumber;
    private String style;
    private String quote;
    private String detailUrl;

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(String scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public MovieEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.articleUrl);
        dest.writeString(this.movieName);
        dest.writeString(this.scoreNumber);
        dest.writeString(this.style);
        dest.writeString(this.quote);
        dest.writeString(this.detailUrl);
    }

    protected MovieEntity(Parcel in) {
        this.articleUrl = in.readString();
        this.movieName = in.readString();
        this.scoreNumber = in.readString();
        this.style = in.readString();
        this.quote = in.readString();
        this.detailUrl = in.readString();
    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
