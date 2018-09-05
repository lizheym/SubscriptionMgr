package personal.subscriptionmgr;

import android.arch.persistence.room.Room;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Liz on 9/4/2018.
 */

public class AddSubFragment extends Fragment {
    AppDatabase db;
    private EditText subNameField;
    private EditText subCostField;
    private EditText subNotifyField;
    private EditText subEmailField;
    private Spinner categorySpinner;
    private Spinner monthSpinner;
    private Spinner dayOfMonthSpinner;
    private Spinner dayOfWeekSpinner;
    private Button addToDbBtn;

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

        //instantiate widgets
        subNameField = (EditText) v.findViewById(R.id.sub_name_field);
        subCostField = (EditText) v.findViewById(R.id.sub_cost_field);
        subNotifyField = (EditText) v.findViewById(R.id.sub_notify_field);
        subEmailField = (EditText) v.findViewById(R.id.sub_email_field);
        categorySpinner = (Spinner) v.findViewById(R.id.category_spinner);
        monthSpinner = (Spinner) v.findViewById(R.id.month_spinner);
        dayOfMonthSpinner = (Spinner) v.findViewById(R.id.day_of_month_spinner);
        dayOfWeekSpinner = (Spinner) v.findViewById(R.id.day_of_week_spinner);
        addToDbBtn = v.findViewById(R.id.add_to_db_btn);

        //set adapters for spinners
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(getContext(), R.array.category_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(getContext(), R.array.month_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> dayOfMonthAdapter = ArrayAdapter.createFromResource(getContext(), R.array.day_of_month_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> dayOfWeekAdapter = ArrayAdapter.createFromResource(getContext(), R.array.day_of_week_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayOfMonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayOfWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        categorySpinner.setAdapter(categoryAdapter);
        monthSpinner.setAdapter(monthAdapter);
        dayOfMonthSpinner.setAdapter(dayOfMonthAdapter);
        dayOfWeekSpinner.setAdapter(dayOfWeekAdapter);

        /*categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });*/

        addToDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subscription newSub = new Subscription();

                newSub.setName(subNameField.getText().toString());
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
