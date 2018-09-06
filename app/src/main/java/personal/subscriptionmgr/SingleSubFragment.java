package personal.subscriptionmgr;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Liz on 9/6/2018.
 */

public class SingleSubFragment extends Fragment {
    AppDatabase db;
    TextView nameView;
    TextView categoryView;
    TextView chargeMonthView;
    TextView chargeDOMonthView;
    TextView chargeDOWeekView;
    TextView costView;
    TextView notifyView;
    TextView emailView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_single_sub, container, false);

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "subscription").allowMainThreadQueries().build();
        
        String subName = getArguments().getString("name");
        nameView = v.findViewById(R.id.sub_name);
        categoryView = v.findViewById(R.id.sub_category);
        chargeMonthView = v.findViewById(R.id.sub_charge_month);
        chargeDOMonthView = v.findViewById(R.id.sub_charge_day_of_month);
        chargeDOWeekView = v.findViewById(R.id.sub_charge_day_of_week);
        costView = v.findViewById(R.id.sub_cost);
        notifyView = v.findViewById(R.id.sub_notify);
        emailView = v.findViewById(R.id.sub_email);
        
        Subscription sub = db.subscriptionDao().findByName(subName);
        
        nameView.setText(subName);
        categoryView.setText(sub.getCategory());
        chargeMonthView.setText(sub.getChargeMonth());
        chargeDOMonthView.setText(sub.getChargeDayOfMonth());
        chargeDOWeekView.setText(sub.getChargeDayOfWeek());
        costView.setText(String.valueOf(sub.getCost()));
        notifyView.setText(sub.getNotification());
        emailView.setText(sub.getEmail());
        
        return v;
    }

}
