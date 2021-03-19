package com.example.sannidhya.yoga;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;
import static android.text.Html.fromHtml;

/**
 * Sample Java code for youtube.commentThreads.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/guides/code_samples#java
 */

public class Comment_Frag extends Fragment {
    public String video_id="3pGC0oCs4fA";
    private ProgressDialog pDialog;
    final protected String Dev_key="<enter your API key>";
    public ArrayList<Comments_list> Comments;
    RecyclerView recyclerView;
    Context mcontext;
    Singleton globals;
    ContentAdapter adapter;
    /* // You need to set this value for your code to compile.
     // For example: ... DEVELOPER_KEY = "YOUR ACTUAL KEY";
     private static final String DEVELOPER_KEY = "AIzaSyCOcRykvb1FIm3s2S6XGElZFtDAQrB-4t0";
     private static final String APPLICATION_NAME = "API code samples";
     private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();*/
    /*public void updateText(String text){
        // updating video id
        video_id = text;
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        globals = Singleton.getInstance();

        video_id = globals.getCurrent_v_id();
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycle_view, container, false );
        setUserVisibleHint(false);
        setUserVisibleHint(true);
        return recyclerView;

    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser == true) {
            new GetComments().execute();
            adapter = new ContentAdapter( mcontext );
            recyclerView.setAdapter( adapter );
            recyclerView.setHasFixedSize( true );
            recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        }
        super.setUserVisibleHint( isVisibleToUser );


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avator;
        public TextView name;
        public TextView comment;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super( inflater.inflate( R.layout.comment_layout, parent, false ) );
            avator = (ImageView) itemView.findViewById( R.id.Author_image );
            name = (TextView) itemView.findViewById( R.id.Author_name );
            comment = (TextView) itemView.findViewById( R.id.Author_comment );
        }
    }

    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        //Singleton globals;


        public ContentAdapter(Context context) {
            mcontext = context;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            int position = 0;
            if (holder.getAdapterPosition() != -1){
                position = holder.getAdapterPosition();
            }
            holder.name.setClickable(true);
            holder.name.setMovementMethod( LinkMovementMethod.getInstance());
                String text = "<a href='"+Comments.get( position ).getChannelURL()+"'>"+ Comments.get(position).getAuthorName()+ "</a>";
            holder.name.setText( fromHtml(text));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            try {

                //Log.e("VideoId is->","" + videoId);

                String img_url = Comments.get( position % Comments.size() ).getProfileImageURL(); // this is link which will give u thumnail image of that video

                // picasso jar file download image for u and set image in imagview

                Picasso.get().load( img_url ).centerCrop().onlyScaleDown().fit().into( holder.avator);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //holder.avator.setImageDrawable(getYoutubeThumbnailUrlFromVideoUrl(old_vid_resume.get(position % old_vid_resume.size()).getVideo_id()));
            holder.name.setText( Comments.get( position % Comments.size() ).getAuthorName() );
            holder.comment.setText( "Continue " + Comments.get( position % Comments.size() ).getTextDisplay() );
        }

        @Override
        public int getItemCount() {
            return Comments.size();
        }
    }
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetComments extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Comments = new ArrayList<Comments_list>();

            String url = "https://www.googleapis.com/youtube/v3/commentThreads?key=AIzaSyCOcRykvb1FIm3s2S6XGElZFtDAQrB-4t0&part=id,snippet,replies&videoId="+globals.getCurrent_v_id();
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray comments = jsonObj.getJSONArray("items");

                    // looping through All Contacts
                    for (int i = 0; i < comments.length(); i++) {
                        JSONObject c = comments.getJSONObject(i);

                        // Snippet node is JSON Object
                        JSONObject snippet = c.getJSONObject("snippet");
                        JSONObject topLevelComment = snippet.getJSONObject("topLevelComment");
                        JSONObject snippet2 = topLevelComment.getJSONObject("snippet");
                        Comments.add(new Comments_list(snippet2.getString("textDisplay"),
                                snippet2.getString("authorDisplayName"),
                                snippet2.getString("authorChannelUrl"),
                                snippet2.getString("authorProfileImageUrl"),
                                //topLevelComment.getInt("viewerRating"),
                                snippet2.getInt("likeCount"),
                                snippet.getInt("totalReplyCount"),
                                snippet2.getString("publishedAt")));

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing()) {
                recyclerView.setAdapter( adapter );
                recyclerView.setHasFixedSize( true );
                recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
                pDialog.dismiss();
            }
            /**
             * Updating parsed JSON data into ListView
             * */

            /*ListAdapter adapter = new SimpleAdapter(
                    getActivity(), contactList,
                    R.layout.comment_layout, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            lv.setAdapter(adapter);*/
        }

    }
}
