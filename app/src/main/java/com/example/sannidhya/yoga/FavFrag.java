package com.example.sannidhya.yoga;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavFrag extends Fragment {

    //public static final String Dev_key ="AIzaSyCOcRykvb1FIm3s2S6XGElZFtDAQrB-4t0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycle_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_list, parent, false));
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        Singleton globals;
        //private static final int LENGTH = 18;
        private ArrayList<old_video_ids> old_vid_resume;
        public ContentAdapter(Context context) {
            globals = Singleton.getInstance();
            //Resources resources = context.getResources();
            old_vid_resume = globals.resune_list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Bundle info = new Bundle(  );
                    //info.putString( "category",old_vid_resume.get(holder.getAdapterPosition()).getCategory());
                    //info.putString( "id", );
                    globals.setCurrent_v_id( old_vid_resume.get(holder.getAdapterPosition()).getVideo_id());
                    startActivity(new Intent(getContext(),media_page.class).putExtra("name",old_vid_resume.get(holder.getAdapterPosition()).getCategory()));
                }
            } );
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            if (old_vid_resume.size() == 0){
                holder.name.setText("Nothing to continue");
                holder.description.setText("");

            }
            else {
                try {

                    Log.e("VideoId is->","" + old_vid_resume.get( position % old_vid_resume.size() ).getVideo_id());

                    String img_url = getYoutubeThumbnailUrlFromVideoUrl( old_vid_resume.get( position % old_vid_resume.size() ).getVideo_id() ); // this is link which will give u thumnail image of that video

                    // picasso jar file download image for u and set image in imagview

                    Picasso.get().load( img_url ).centerCrop().onlyScaleDown().fit().into( holder.avator);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //holder.avator.setImageDrawable(getYoutubeThumbnailUrlFromVideoUrl(old_vid_resume.get(position % old_vid_resume.size()).getVideo_id()));
                holder.name.setText( old_vid_resume.get( position % old_vid_resume.size() ).getVideo_id() );
                holder.description.setText( "Continue " + old_vid_resume.get( position % old_vid_resume.size() ).getCategory() );
            }
        }

        @Override
        public int getItemCount() {
            return old_vid_resume.size();
        }
    }
    public static String getYoutubeThumbnailUrlFromVideoUrl(String videoId) {
        return "http://img.youtube.com/vi/"+videoId+ "/0.jpg";
    }

}
