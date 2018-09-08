package personal.subscriptionmgr;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Liz on 9/6/2018.
 */

public class SingleSubFragment extends Fragment {
    AppDatabase db;
    TextView nameView;
    TextView chargeInfoView;
    TextView notificationInfoView;
    TextView emailInfoView;
    Button backToListButton;

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
        chargeInfoView = v.findViewById(R.id.sub_charge_info);
        notificationInfoView = v.findViewById(R.id.sub_notification_info);
        emailInfoView = v.findViewById(R.id.sub_email_info);
        backToListButton = v.findViewById(R.id.back_to_list_btn);

        backToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Replace self fragment with SubListFragment
                SubListFragment newFragment = new SubListFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_left);
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.commit();
            }
        });

        Subscription sub = db.subscriptionDao().findByName(subName);

        nameView.setText(subName);
        String category = sub.getCategory();
        String chargeMonth = sub.getChargeMonth();
        String chargeDOMonth = sub.getChargeDayOfMonth();
        String chargeDOWeek = sub.getChargeDayOfWeek();
        Double cost = sub.getCost();
        String notification = sub.getNotification();
        String email = sub.getEmail();

        if (category.equals("annual")) {
            chargeInfoView.setText("Charges $" + String.format("%.2f", cost) + " annually on " + chargeMonth + " " + chargeDOMonth);
        }else if(category.equals("monthly")) {
            chargeInfoView.setText("Charges $" + String.format("%.2f", cost) + " monthly on " + chargeDOMonth + " of the month.");
        }else if(category.equals("weekly")){
            chargeInfoView.setText("Charges $" + String.format("%.2f", cost) + " weekly on " + chargeDOWeek);
        }

        notificationInfoView.setText("App will notify you " + notification + " days in advance of charge.");

        emailInfoView.setText("Email " + email + " used to sign up for subscription.");
        
        return v;
    }

}
