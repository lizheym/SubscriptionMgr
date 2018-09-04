package personal.subscriptionmgr;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Liz on 9/4/2018.
 */

public class SubListFragment extends Fragment {
    AppDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sub_list, container, false);

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "subscription").allowMainThreadQueries().build();

        TextView testView = v.findViewById(R.id.testing);
        Subscription sub = db.subscriptionDao().findByName("Spotify");
        if(sub!=null){
            testView.setText("Found");
        }

        Button restartButton = v.findViewById(R.id.create_new_sub_btn);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Pressed button", Toast.LENGTH_LONG).show();

                //Replace self fragment with AddSubFragment
                AddSubFragment newFragment = new AddSubFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
            }
        });

        return v;
    }
}
