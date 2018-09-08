package personal.subscriptionmgr;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
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

        PieChart pieChart = (PieChart) v.findViewById(R.id.piechart);

        List<PieEntry> dataValues = new ArrayList<PieEntry>();

        float annualCost = 0;
        for(Subscription sub : subList){
            String frequency = sub.getCategory();
            if(frequency.equals("annual")){
                annualCost = (float)sub.getCost();
                annualCounter += annualCost;
                monthlyCounter += sub.getCost()/12;
            }else if(frequency.equals("monthly")){
                annualCost = (float)(12*sub.getCost());
                annualCounter += annualCost;
                monthlyCounter += sub.getCost();
            }else if(frequency.equals("weekly")){
                annualCost = (float)(52*sub.getCost());
                annualCounter += annualCost;
                monthlyCounter += 4*sub.getCost();
            }
            dataValues.add(new PieEntry(annualCost, sub.getName()));
        }

        PieDataSet dataSet = new PieDataSet(dataValues, "Annual Subscription Spending");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);

        totalAnnual = v.findViewById(R.id.total_annual);
        totalMonthly = v.findViewById(R.id.total_monthly);
        totalAnnual.setText("You spend $" + String.format("%.2f", annualCounter) + " per year on subscription services.");
        totalMonthly.setText("You spend $" + String.format("%.2f", monthlyCounter) + " per month on subscription services");

        return v;
    }
}
