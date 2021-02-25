package com.example.sannidhya.yoga;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.sannidhya.yoga.media_page.recovery_dialogue_request;


public class Video_list_frag extends Fragment {
    VideoClicked mCallback;



    private YouTubePlayerFragment youTubePlayerFragment;
    Comment_Frag comment_frag;
    private int timer;
    Date date;
    Singleton globals;
    public String video_id ="jwrjkjarsH0";
    ArrayList<playlist_lists>  playlist;
    final protected String Dev_key="AIzaSyCOcRykvb1FIm3s2S6XGElZFtDAQrB-4t0";
    YouTubePlayer myouTubePlayer;
    String Category;
    public Video_list_frag(ArrayList<playlist_lists> playlist,String Category, YouTubePlayerFragment youTubePlayerFragment){
        this.playlist = playlist;
        this.Category = Category;
        this.youTubePlayerFragment = youTubePlayerFragment;
    }


    public interface VideoClicked{
        public void sendText(String text);
    }
   /* @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (VideoClicked) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement TextClicked");
        }

    }*/

   /* @Override
    public void onDetach() {
        mCallback = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //comment_frag = new Comment_Frag();
        globals = Singleton.getInstance();
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
        public ContentAdapter(Context context) {

            //Resources resources = context.getResources();
            //old_vid_resume = ;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            final ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            //final YouTubePlayerView videoView = (YouTubePlayerView) getActivity().findViewById(R.id.videoView);
            youTubePlayerFragment.initialize( Dev_key, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            //if boolean b is true then something is broken and thus we wanna look for false b
                            if (!b){
                                myouTubePlayer = youTubePlayer;
                                //load the video
                                youTubePlayer.loadVideo(video_id);
                                //update the video id of playing video
                                //mCallback.sendText(video_id);
                                globals.setCurrent_v_id(playlist.get( holder.getAdapterPosition()).getVideo_id());
                                //Log.d( "video id", globals.getCurrent_v_id( ));
                            }
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                            //if initialization fails then you check for the type of error, if it's classified as an user recoverable error and show it in a dialogue box in the youtube view
                            if (youTubeInitializationResult.isUserRecoverableError()){
                                youTubeInitializationResult.getErrorDialog(getActivity(),recovery_dialogue_request).show();
                            }
                            // else you put a text on the screen, problems like null pointer exception
                            else {
                                String errorMessage = String.format("Error initializing youtube ", youTubeInitializationResult.toString());
                                Toast.makeText(getContext(),errorMessage,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //Log.d("Try","initialized youtube frame");
                    holder.itemView.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (myouTubePlayer.isPlaying() == true) {
                                timer = myouTubePlayer.getCurrentTimeMillis();
                                SimpleDateFormat formatter = new SimpleDateFormat( "yyyyMMdd" );
                                date = new Date();

                                old_video_ids old_video_ids = new old_video_ids( playlist.get( holder.getAdapterPosition() ).getVideo_id(), timer, formatter.format( date ) );
                                old_video_ids.setCategory( Category );
                                if (globals.check_id( old_video_ids ) != true) {
                                    globals.resune_list.add( old_video_ids );
                                    Collections.sort( globals.resune_list, new Sortbydate() );
                                }

                                for (int i = 0; i < globals.resune_list.size(); i++) {
                                    Log.d( "resume list", "video id :" + globals.resune_list.get( i ).getVideo_id() + " Date: " + globals.resune_list.get( i ).getWhenDidYouWatch() );
                                }
                            }
                            //mCallback.sendText( playlist.get( holder.getAdapterPosition() ).getVideo_id());
                            globals.setCurrent_v_id(playlist.get( holder.getAdapterPosition()).getVideo_id());
                            Log.d( "video id", globals.getCurrent_v_id( ));
                            myouTubePlayer.loadVideo( playlist.get( holder.getAdapterPosition() ).getVideo_id() );
                            //startActivity(new Intent(getContext(),media_page.class).putExtra("name",playlist.get(holder.getAdapterPosition())));
                            //update the video id of playing video

                        }
                    } );
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
                try {

                    //Log.e("VideoId is->","" + videoId);

                    String img_url = getYoutubeThumbnailUrlFromVideoUrl( playlist.get( position % playlist.size() ).getVideo_id() ); // this is link which will give u thumnail image of that video

                    // picasso jar file download image for u and set image in imagview

                    Picasso.get().load( img_url ).centerCrop().onlyScaleDown().fit().into(holder.avator);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //holder.avator.setImageDrawable(getYoutubeThumbnailUrlFromVideoUrl(old_vid_resume.get(position % old_vid_resume.size()).getVideo_id()));
                holder.name.setText( "Part "+playlist.get( position % playlist.size() ).getList_position() );
                holder.description.setText( "Our selected video for" + Category+" Part " +playlist.get( position % playlist.size() ).getList_position() );
        }

        @Override
        public int getItemCount() {
            return playlist.size();
        }
    }
    public static String getYoutubeThumbnailUrlFromVideoUrl(String videoId) {
        return "http://img.youtube.com/vi/"+videoId+ "/0.jpg";
    }
}
