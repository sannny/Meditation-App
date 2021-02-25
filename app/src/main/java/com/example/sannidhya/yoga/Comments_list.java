package com.example.sannidhya.yoga;

public class Comments_list {
    String textDisplay,AuthorName,ChannelURL,profileImageURL;
    int LikeCount,numberOfReplies,viewerRating;
    String publishDate;



    //ArrayList<> replies;
    public  Comments_list(String textDisplay,
                              String AuthorName,
                              String ChannelURL,
                              String profileImageURL,
                              int LikeCount,
                              int numberOfReplies,
                              //int viewerRating,
                              String publishDate){
        this.textDisplay = textDisplay;
        this.AuthorName = AuthorName;
        this.ChannelURL = ChannelURL;
        this.profileImageURL = profileImageURL;
        this.LikeCount = LikeCount;
        this.numberOfReplies = numberOfReplies;
        //this.viewerRating = viewerRating;
        this.publishDate = publishDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public int getLikeCount() {
        return LikeCount;
    }

    public int getNumberOfReplies() {
        return numberOfReplies;
    }

    public int getViewerRating() {
        return viewerRating;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public String getChannelURL() {
        return ChannelURL;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public String getTextDisplay() {
        return textDisplay;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public void setChannelURL(String channelURL) {
        ChannelURL = channelURL;
    }

    public void setLikeCount(int likeCount) {
        LikeCount = likeCount;
    }

    public void setNumberOfReplies(int numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setTextDisplay(String textDisplay) {
        this.textDisplay = textDisplay;
    }

    public void setViewerRating(int viewerRating) {
        this.viewerRating = viewerRating;
    }
}
