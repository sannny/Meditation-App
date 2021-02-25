package com.example.sannidhya.yoga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
// maintaining a global array list of watched videos
public class Singleton {
    private static Singleton ourInstance = null;

    static Singleton getInstance() {
        if(ourInstance == null) {
            ourInstance = new Singleton();
        }
        return ourInstance;
    }

    protected Singleton() {
    }

    ArrayList<old_video_ids> resune_list = new ArrayList<>() ;
    String current_v_id= new String(  );

    public String getCurrent_v_id() {
        return current_v_id;
    }

    public void setCurrent_v_id(String current_v_id) {
        this.current_v_id = current_v_id;
    }

    public void add(String video_id, int timer, String date){
        resune_list.add(new old_video_ids(video_id,timer,date));
        Collections.sort(resune_list , new Sortbydate());
    }


    public void Com(String date1,String date2){

    }
    //comparator function
    public boolean check_id(old_video_ids oldVideoIds){
        int i=0;

        while (resune_list.size()>i){
            if(resune_list.get(i).getVideo_id() == oldVideoIds.getVideo_id()) {
                resune_list.get(i).setTimer(oldVideoIds.getTimer());
                resune_list.get(i).setWhenDidYouWatch(oldVideoIds.getWhenDidYouWatch());
                Collections.sort(resune_list, new Sortbydate());
                return true;
            }
            i++;
        }
        return false;
    }
}


class Sortbydate implements Comparator<old_video_ids>
{
    // Used for sorting in ascending order of time and date
    public int compare(old_video_ids a, old_video_ids b)
    {
        return Integer.parseInt(a.getWhenDidYouWatch()) - Integer.parseInt(b.getWhenDidYouWatch());
    }
}