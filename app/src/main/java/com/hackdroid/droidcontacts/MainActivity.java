package com.hackdroid.droidcontacts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d(TAG, "onCreate: ");
//        navController = Navigation.findNavController(savedInstanceStat);'bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        navController = Navigation.findNavController(this, R.id.callLogs);

        setUpNavigation();


    }

    private void setUpNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mainHolder);
        NavigationUI.setupWithNavController(bottomNavigationView,
                navHostFragment.getNavController());

    }


}
