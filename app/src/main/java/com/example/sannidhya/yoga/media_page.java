package com.example.sannidhya.yoga;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class media_page extends AppCompatActivity  {
    public static final int recovery_dialogue_request = 1;
   // declare the playlist list
    public ArrayList<playlist_lists> the_playlist;
    String category_name;
    public YouTubePlayerFragment youTubePlayerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize the playlist
        the_playlist = new ArrayList<>();
        //set the content view to media page
        setContentView(R.layout.activity_media_page);
        //get the category from last activity
        Intent intent = getIntent();
        category_name = intent.getExtras().getString("name");
        Log.d("category name", "onCreate: "+category_name);

        //add relevant category to playlist
        search(category_name);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs_media);
        tabs.addTab(tabs.newTab().setText("List"));
        tabs.addTab(tabs.newTab().setText("Comments"));
        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeFragment);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_media);
        setupViewPager(viewPager,youTubePlayerFragment);
        tabs.setupWithViewPager(viewPager);

         //videoView= (YouTubePlayerView) findViewById(R.id.videoView);
        //Log.d("Try","initialized youtube frame");
        //videoView.initialize(Dev_key,new YoutubeActivity() );
        //Log.d("Try","checking for listadapter");

        /*playlistAdapter listadapter = new playlistAdapter(media_page.this, the_playlist);
        //Log.d("Try","listAdapter initialized");
        ListView listView = (ListView) findViewById(R.id.playlist_list_view);
        //Log.d("Try","List view linked");
        listView.setAdapter(listadapter);
        //Log.d("Try","list adapter linked to listview");
        video_id = intent.getExtras().getString("vid_id");
        if (video_id == null)
            video_id = the_playlist.get(0).getVideo_id();
        //Log.d("Try","got the relevant video id");


        //Log.d("Try","started the video");
        //creating an onclicklistner to check if any other item selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the video id of the selected item
                video_id = (the_playlist.get(position).getVideo_id());
                //videoView.onSaveInstanceState();
                /*if (youTubePlayer.getDurationMillis()- youTubePlayer.getCurrentTimeMillis() >= 0 ) {
                    //destroy the current activity and re-initialize

                }*/

                /*if (youTubePlayer.isPlaying() == true){
                    timer = youTubePlayer.getCurrentTimeMillis();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                    date = new Date();
                    globals = Singleton.getInstance();
                    old_video_ids old_video_ids = new old_video_ids(video_id, timer, formatter.format(date));
                    old_video_ids.setCategory(category_name);
                    if (globals.check_id(old_video_ids) != true) {
                        globals.resune_list.add(old_video_ids);
                        Collections.sort(globals.resune_list, new Sortbydate());
                    }

                    for (int i = 0; i < globals.resune_list.size(); i++){
                        Log.d("resume list","video id :"+ globals.resune_list.get(i).getVideo_id()+" Date: " + globals.resune_list.get(i).getWhenDidYouWatch());
                    }
                }

                youTubePlayer.loadVideo(video_id);
            }
        });*/
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager, YouTubePlayerFragment youTubePlayerFragment) {
        mAdapter adapter = new mAdapter( getSupportFragmentManager());
        adapter.addFragment(new Video_list_frag(the_playlist,category_name, youTubePlayerFragment),"List");
        adapter.addFragment(new Comment_Frag(), "Comments");
        viewPager.setAdapter(adapter);
    }
    //setting up fragment page adapter
    private static class mAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public mAdapter(FragmentManager manager) {
            super( manager );
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get( position );
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add( fragment );
            mFragmentTitleList.add( title );
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get( position );
        }
    }
   /* @Override
    public void sendText(String text){
        // Get Fragment B
        Comment_Frag frag = (Comment_Frag)getSupportFragmentManager().getFragments().get( 1 );
        frag.updateText(text);
    }*/


    /*@Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        //when the frame is successfully initialized, this method is called
        youTubePlayer.setPlaybackEventListener(playerEventChangeListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListner);
        //if boolean b is true then something is broken and thus we wanna look for false b
        if (!b){
            this.youTubePlayer = youTubePlayer;

            //load the video
            youTubePlayer.loadVideo(video_id);
        }
    }

    private YouTubePlayer.PlaybackEventListener playerEventChangeListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };


    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListner = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    @Override
    protected void onStart() {

        super.onStart();
    }
    @Override
    public void onSaveInstanceState(Bundle saveYoutubeVals) {

        saveYoutubeVals.putString("message", "This is my message to be reloaded");
        super.onSaveInstanceState(saveYoutubeVals);
    }*/

    /*@Override
    protected void onDestroy() {
        if (youTubePlayer != null){
            timer = youTubePlayer.getCurrentTimeMillis();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            date = new Date();
            globals = Singleton.getInstance();
            old_video_ids old_video_ids = new old_video_ids(video_id, timer, formatter.format(date));
            old_video_ids.setCategory(category_name);
            if (globals.check_id(old_video_ids) != true) {
                globals.resune_list.add(old_video_ids);
            }
        }
        super.onDestroy();
    }*/

    /*@Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        //if initialization fails then you check for the type of error, if it's classified as an user recoverable error and show it in a dialogue box in the youtube view
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,recovery_dialogue_request).show();
        }
        // else you put a text on the screen, problems like null pointer exception
        else {
            String errorMessage = String.format("Error initializing youtube ", youTubeInitializationResult.toString());
            Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
        }
    }*/
    //method to add relevant videos to p[aylist
    //pass the category name as a parameter to use it in switch
    public void search(String name) {
        Log.d("in search", "search for: "+name);
        //converting everything to uppercase so that all characters match, since the comaprision is case sensitive
        String name_ucase = name.toUpperCase(Locale.forLanguageTag("en"));
        switch (name_ucase) {
            case ("RELAX"):
                the_playlist.clear();
                the_playlist.add(new playlist_lists(1,"wqVEvhjrmk0"));
                the_playlist.add(new playlist_lists(2,"rB02UW8cN3Q"));
                the_playlist.add(new playlist_lists(3,"kBu9fbtbsUc"));
                the_playlist.add(new playlist_lists(4,"3pGC0oCs4fA"));
                the_playlist.add(new playlist_lists(5,"EgD7gqTAZQE"));
                the_playlist.add(new playlist_lists(6,"Djln3DP57OM"));
                the_playlist.add(new playlist_lists(7,"m--IGxnFPmk"));
                the_playlist.add(new playlist_lists(8,"vr24XsCDDEM"));
                break;
            case "MEDITATION":
                the_playlist.clear();
                the_playlist.add(new playlist_lists(1,"XtChAFxSI3w"));
                the_playlist.add(new playlist_lists(2,"kQY07hhYifg"));
                the_playlist.add(new playlist_lists(3,"OMfF9cvfmYo"));
                the_playlist.add(new playlist_lists(4,"pHdmBV0BoOY"));
                the_playlist.add(new playlist_lists(5,"rpSbU7B2tPk"));
                the_playlist.add(new playlist_lists(6,"5eFvtP6X4pc"));
                the_playlist.add(new playlist_lists(7,"3EBBPvTy1ac"));
                the_playlist.add(new playlist_lists(8,"xYYgh4nSxXU"));
                break;
            case "ROUNTINELY":
                the_playlist.clear();
                the_playlist.add(new playlist_lists(1,"vr24XsCDDEM"));
                the_playlist.add(new playlist_lists(2,"EZVHjVbUP40"));
                the_playlist.add(new playlist_lists(3,"TwXilp2mUtE"));
                the_playlist.add(new playlist_lists(4,"F8M16BgKnfs"));
                the_playlist.add(new playlist_lists(5,"5oAPihoIiNY"));
                the_playlist.add(new playlist_lists(6,"W4duEjQEaOc"));
                the_playlist.add(new playlist_lists(7,"2OEL4P1Rz04"));
                the_playlist.add(new playlist_lists(8,"fn1sLTb39RU"));
                break;
            case "FOCUS":
                the_playlist.clear();
                the_playlist.add(new playlist_lists(1,"EFi-pxsKWzE"));
                the_playlist.add(new playlist_lists(2,"WPni755-Krg"));
                the_playlist.add(new playlist_lists(3,"xpMlKSZAIks"));
                the_playlist.add(new playlist_lists(4,"9ArN2_dnHQg"));
                the_playlist.add(new playlist_lists(5,"mg7netw1JuM"));
                the_playlist.add(new playlist_lists(6,"ArwcHjmsw3A"));
                the_playlist.add(new playlist_lists(7,"yttwjrDnAxI"));
                the_playlist.add(new playlist_lists(8,"7TO_oHxuk6c"));
                break;
                default:
                    break;

        }
    }
}



