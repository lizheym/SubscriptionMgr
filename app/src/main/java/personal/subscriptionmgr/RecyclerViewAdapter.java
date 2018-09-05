package personal.subscriptionmgr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Liz on 9/5/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> itemTexts = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> itemTexts, ArrayList<String> images, Context context) {
        this.itemTexts = itemTexts;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called.");
        Glide.with(context).asBitmap().load(images.get(position)).into(holder.image);
        holder.text.setText(itemTexts.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: start new activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemTexts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView text;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.item_image);
            text = itemView.findViewById(R.id.item_text);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}