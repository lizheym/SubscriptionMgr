package personal.subscriptionmgr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Liz on 9/4/2018.
 */

public class MainActivity extends AppCompatActivity {
    Button statsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new SubListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        statsButton = findViewById(R.id.stats_button);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statsButton.getText().equals("Show stats")){
                    StatsFragment newFragment = new StatsFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_bottom, R.anim.out_to_bottom);
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.commit();
                    statsButton.setText("Hide stats");
                }else{
                    SubListFragment newFragment = new SubListFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_bottom, R.anim.out_to_bottom);
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.commit();
                    statsButton.setText("Show stats");
                }

            }
        });

    }

}
