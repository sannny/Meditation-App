package com.example.sannidhya.yoga;

public class playlist_lists {
    private String video_id="";
    private int list_position=-1;

    public  playlist_lists(int list_position, String video_id){
        this.list_position = list_position;
        this.video_id = video_id;
    }

    public int getList_position() {
        return list_position;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setList_position(int list_position) {
        this.list_position = list_position;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }
}
