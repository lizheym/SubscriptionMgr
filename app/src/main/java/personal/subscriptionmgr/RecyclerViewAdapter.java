package personal.subscriptionmgr;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Liz on 9/5/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> itemTexts = new ArrayList<>();
    private ArrayList<String> itemCategories = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> itemTexts, ArrayList<String> itemCategories, Context context) {
        this.itemTexts = itemTexts;
        this.itemCategories = itemCategories;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called.");

        //TODO: different cases for different categories
        String category = itemCategories.get(position);
        if(category == "weekly"){
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.smiley);
            holder.image.setImageBitmap(bm);
        }else if(category == "monthly"){
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.smiley);
            holder.image.setImageBitmap(bm);
        }else if(category == "annual"){
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.smiley);
            holder.image.setImageBitmap(bm);
        }else{
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.smiley);
            holder.image.setImageBitmap(bm);
        }

        holder.text.setText(itemTexts.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = holder.text.getText().toString();

                // start new fragment
                SingleSubFragment newFragment = new SingleSubFragment();
                Bundle args = new Bundle();
                args.putString("name", itemName);
                newFragment.setArguments(args);
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_left);
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
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