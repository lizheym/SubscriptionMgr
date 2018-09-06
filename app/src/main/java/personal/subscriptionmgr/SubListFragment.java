package personal.subscriptionmgr;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liz on 9/4/2018.
 */

public class SubListFragment extends Fragment {
    AppDatabase db;
    View v;
    private ArrayList<String> itemTexts;
    private ArrayList<String> itemCategories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_sub_list, container, false);

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "subscription").allowMainThreadQueries().build();

        Button restartButton = v.findViewById(R.id.create_new_sub_btn);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Replace self fragment with AddSubFragment
                AddSubFragment newFragment = new AddSubFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
            }
        });

        populate();

        return v;
    }

    private void populate(){
        List<Subscription> subList = db.subscriptionDao().getAll();

        itemTexts = new ArrayList<>();
        itemCategories = new ArrayList<>();

        for(Subscription item : subList){
            itemTexts.add(item.getName());
            String category = item.getCategory();
            //TODO: change image based on frequency
            itemCategories.add(category);
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemTexts, itemCategories, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
