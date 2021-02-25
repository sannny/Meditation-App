package com.example.sannidhya.yoga;


import androidx.appcompat.app.AppCompatActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

public class home extends AppCompatActivity{
    String mCategory_name = null;
    int mImagesource_id = 0;
    //constructor to initialize variables
    public home(String mCategory_name, int mImagesource_id){
        this.mCategory_name = mCategory_name;
        this.mImagesource_id = mImagesource_id;
    }

    //method to get and set variables


    public int getmImagesource_id() {
        return mImagesource_id;
    }

    public String getmCategory_name() {
        return mCategory_name;
    }

    public void setmCategory_name(String mCategory_name) {
        this.mCategory_name = mCategory_name;
    }

    public void setmImagesource_id(int mImagesource_id) {
        this.mImagesource_id = mImagesource_id;
    }
}
