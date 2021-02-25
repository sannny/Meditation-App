package com.example.sannidhya.yoga;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CardFrag extends Fragment {
    public Singleton globals;

    public CardFrag(Singleton globals) {
        this.globals = globals;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //setting up recycler view
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycle_view, container, false);
        //View recycleView;
        //binding adapter to recycler view
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }
    //customizing recycler view for each fragment here by changing ViewHolders's layout file
    public static class ViewHolder extends RecyclerView.ViewHolder  {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
        }
    }

     // Adapter to display recycler view.
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // initialize lists for RecyclerView.
        private final String[] mCategory;
        public ContentAdapter(Context context) {
            Resources resources;
            resources = context.getResources();
            mCategory = resources.getStringArray(R.array.category_list);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            //setting up click listener for items in recycler view
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(),media_page.class).putExtra("name",mCategory[holder.getAdapterPosition()% mCategory.length]));
                }
            } );
            return holder;
        }
        //This functions handles what to show in the card item
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //holder.picture.setImageDrawable(mPlacePictures[position % mPlacePictures.length]);
            //holder.picture.setImageDrawable(R.drawable.ic_launcher_background);
            holder.name.setText(mCategory[position % mCategory.length]);
            holder.description.setText("Videos carefully picked just for you to "+ mCategory[position % mCategory.length]);

        }
        //this function returns item count
        @Override
        public int getItemCount() {
            return mCategory.length;
        }
    }
}
