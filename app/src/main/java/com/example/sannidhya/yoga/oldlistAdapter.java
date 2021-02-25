package com.example.sannidhya.yoga;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class oldlistAdapter extends RecyclerView.Adapter<ViewHolder> {
    Singleton globals = Singleton.getInstance();
    public oldlistAdapter(ArrayList<old_video_ids> arrayList) {

    }


    /*@NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.oldstuff_smoke, parent, false);
        }
        // Get the {@link AndroidFlavor} object located at this position in the list
        old_video_ids oldVideoIds = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.old_story_name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText("Forca barca");

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        iconView.setImageResource(R.drawable.ic_launcher_foreground);

        return listItemView;

    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oldstuff_smoke, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,   int position) {
        holder.titleTextView.setText(globals.resune_list.get(position).getCategory()+" "+globals.resune_list.get(position).getVideo_id());
        holder.coverImageView.setImageResource(R.drawable.ic_launcher_foreground);

    }

    @Override
    public int getItemCount() {
        return globals.resune_list.size();
    }
}
