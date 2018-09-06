package personal.subscriptionmgr;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Liz on 9/6/2018.
 */

public class StatsFragment extends Fragment {
    Button statsButton;
    TextView totalAnnual;
    TextView totalMonthly;
    AppDatabase db;
    double annualCounter = 0;
    double monthlyCounter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "subscription").allowMainThreadQueries().build();
        List<Subscription> subList = db.subscriptionDao().getAll();

        for(Subscription sub : subList){
            String frequency = sub.getCategory();
            if(frequency.equals("annual")){
                annualCounter += sub.getCost();
                monthlyCounter += sub.getCost()/12;
            }else if(frequency.equals("monthly")){
                annualCounter += 12*sub.getCost();
                monthlyCounter += sub.getCost();
            }else if(frequency.equals("weekly")){
                annualCounter += 52*sub.getCost();
                monthlyCounter += 4*sub.getCost();
            }
        }

        totalAnnual = v.findViewById(R.id.total_annual);
        totalMonthly = v.findViewById(R.id.total_monthly);
        totalAnnual.setText("You spend $" + String.format("%.2f", annualCounter) + " per year on subscription services.");
        totalMonthly.setText("You spend $ " + String.format("%.2f", monthlyCounter) + " per month on subscription services");

        return v;
    }
}
