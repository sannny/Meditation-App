package com.example.sannidhya.yoga;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTextView;
    public ImageView coverImageView;
    LinearLayout linearLayout;
    public ViewHolder(@NonNull View v) {
        super(v);
        final Singleton globals = Singleton.getInstance();
        linearLayout = (LinearLayout) v.findViewById(R.id.clickable);
        titleTextView = (TextView) v.findViewById(R.id.old_story_name);
        coverImageView = (ImageView) v.findViewById(R.id.old_image_view);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    old_video_ids clickedDataItem = globals.resune_list.get(pos);
                    Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getVideo_id(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}
