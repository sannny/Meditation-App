package com.example.sannidhya.yoga;

public class old_video_ids {
    private String video_id;
    private int Timer;
    private String whenDidYouWatch;
    private String category = "";
    Singleton globals;

    public old_video_ids(String video_id,int Timer, String whenDidYouWatch){
        this.Timer = Timer;
        this.video_id = video_id;
        this.whenDidYouWatch = whenDidYouWatch;
    }

    public String getWhenDidYouWatch() {
        return whenDidYouWatch;
    }

    public int getTimer() {
        return Timer;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public void setTimer(int timer) {
        Timer = timer;
    }

    public void setWhenDidYouWatch(String whenDidYouWatch) {
        this.whenDidYouWatch = whenDidYouWatch;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

