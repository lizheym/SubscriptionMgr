package personal.subscriptionmgr;

import android.arch.persistence.room.Room;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Liz on 9/4/2018.
 */

public class AddSubFragment extends Fragment {
    AppDatabase db;
    private EditText subName;
    //TODO: add rest of edit texts

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_sub, container, false);

        //instantiate database
        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "subscription").allowMainThreadQueries().build();

        subName = (EditText) v.findViewById(R.id.sub_name_field);
        //TODO: add rest of edit texts

        Button addToDbBtn = v.findViewById(R.id.add_to_db_btn);
        addToDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subscription newSub = new Subscription();

                newSub.setName(subName.getText().toString());
                newSub.setCategory("Music");
                newSub.setFrequency("Monthly");
                newSub.setChargeMonth(0);
                newSub.setChargeDay(12);
                newSub.setCost(12.99);
                newSub.setEmail("lizizzle27@gmail.com");
                newSub.setNotification(2);

                db.subscriptionDao().insertAll(newSub);

                //Replace self fragment with SubListFragment
                SubListFragment newFragment = new SubListFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();

                //TODO: remove (for testing)
                Toast.makeText(getActivity(), "Pressed button", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
