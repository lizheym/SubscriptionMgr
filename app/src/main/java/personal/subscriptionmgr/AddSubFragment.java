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
    private String category;
    private String chargeMonth;
    private String chargeDayOfMonth;
    private String chargeDayOfWeek;

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
        final ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(getContext(), R.array.month_array, android.R.layout.simple_spinner_item);
        final ArrayAdapter<CharSequence> dayOfMonthAdapter = ArrayAdapter.createFromResource(getContext(), R.array.day_of_month_array, android.R.layout.simple_spinner_item);
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

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int index = parentView.getSelectedItemPosition();
                //annual
                if(index == 0){
                    category = "annual";
                    monthSpinner.setVisibility(View.VISIBLE);
                    dayOfMonthSpinner.setVisibility(View.VISIBLE);
                    dayOfWeekSpinner.setVisibility(View.INVISIBLE);
                }
                //monthly
                else if(index == 1){
                    category = "monthly";
                    monthSpinner.setVisibility(View.INVISIBLE);
                    dayOfMonthSpinner.setVisibility(View.VISIBLE);
                    dayOfWeekSpinner.setVisibility(View.INVISIBLE);
                }
                //weekly
                else if(index == 2){
                    category = "weekly";
                    monthSpinner.setVisibility(View.INVISIBLE);
                    dayOfMonthSpinner.setVisibility(View.INVISIBLE);
                    dayOfWeekSpinner.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                category = "annual";
                monthSpinner.setVisibility(View.INVISIBLE);
                dayOfMonthSpinner.setVisibility(View.INVISIBLE);
                dayOfWeekSpinner.setVisibility(View.INVISIBLE);
            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int index = parentView.getSelectedItemPosition();
                chargeMonth = getActivity().getResources().getStringArray(R.array.month_array)[index];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                chargeMonth = "January";
            }
        });

        dayOfMonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int index = parentView.getSelectedItemPosition();
                chargeDayOfMonth = getActivity().getResources().getStringArray(R.array.day_of_month_array)[index];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                chargeDayOfMonth = "1";
            }
        });

        dayOfWeekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int index = parentView.getSelectedItemPosition();
                chargeDayOfWeek = getActivity().getResources().getStringArray(R.array.day_of_week_array)[index];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                chargeDayOfWeek = "Sunday";
            }
        });

        addToDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subscription newSub = new Subscription();
                String name = subNameField.getText().toString();
                String costString = subCostField.getText().toString();
                String notificationString = subNotifyField.getText().toString();
                String email = subEmailField.getText().toString();

                //first, check to see if all fields have been filled, and in the correct format
                if(name.length() == 0 || costString.length() == 0 || notificationString.length() == 0 || email.length() == 0){
                    Toast.makeText(getActivity(), "Must enter all information", Toast.LENGTH_LONG).show();
                }else if(!isDouble(costString)){
                    Toast.makeText(getActivity(), "Cost must be in #.## format", Toast.LENGTH_LONG).show();
                }else if(!isInt(notificationString)){
                    Toast.makeText(getActivity(), "\"Days in advance of charge to notify\" must be an integer", Toast.LENGTH_LONG).show();
                }else if(db.subscriptionDao().findByName(name) != null){
                    Toast.makeText(getActivity(), "Entry for this service already exists.", Toast.LENGTH_LONG).show();
                }else{
                    newSub.setName(name);
                    Double cost = Double.parseDouble(costString);
                    newSub.setCategory(category);
                    //TODO: change category to ENUM
                    if (category == "annual") {
                        newSub.setChargeMonth(chargeMonth);
                        newSub.setChargeDayOfMonth(chargeDayOfMonth);
                        newSub.setChargeDayOfWeek("0");
                    } else if (category == "monthly") {
                        newSub.setChargeMonth("0");
                        newSub.setChargeDayOfMonth(chargeDayOfMonth);
                        newSub.setChargeDayOfWeek("0");
                    } else if (category == "weekly") {
                        newSub.setChargeMonth("0");
                        newSub.setChargeDayOfMonth("0");
                        newSub.setChargeDayOfWeek(chargeDayOfWeek);
                    }

                    newSub.setCost(cost);
                    newSub.setNotification(notificationString);
                    newSub.setEmail(email);

                    db.subscriptionDao().insertAll(newSub);

                    //Replace self fragment with SubListFragment
                    SubListFragment newFragment = new SubListFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.commit();
                }
            }
        });

        return v;
    }

    public boolean isDouble( String str ){
        try {
            Double.parseDouble(str);
            return true;
        }catch( Exception e ){
                return false;
        }
    }

    public boolean isInt( String str ){
        try {
            Integer.parseInt(str);
            return true;
        }catch( Exception e ){
                return false;
        }
    }
}
