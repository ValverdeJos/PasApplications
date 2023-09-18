package com.example.pasapplication.View;


import android.os.Bundle;
import android.text.Layout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.pasapplication.R;

import com.example.pasapplication.View.Fragments.HomeFragmentDirections;
import com.example.pasapplication.databinding.ActivityMainBinding;
import com.example.pasapplication.databinding.FragmentHomeBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private FloatingActionButton addProduct;

    private NavHostFragment navHostFragment;
    private NavController navController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        addProduct = binding.floatingActionButton;
        addProduct.setOnClickListener(v -> {
            Toast.makeText(this, "Frame add Product", Toast.LENGTH_SHORT).show();
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToAddProductFragment();
            navController.navigate(action);
        });

        initNavigation();

        initBadge();
    }


    private void  initNavigation(){
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation,navController);

        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if(navDestination.getId() == R.id.updateFragment){
                BadgeDrawable badgeDrawable = binding.bottomNavigation.getBadge(R.id.updateFragment);
                if(badgeDrawable != null){
                    badgeDrawable.setVisible(false);
                    badgeDrawable.clearNumber();
                }
            }
        });

    }

    private void initBadge() {
        BadgeDrawable badge = binding.bottomNavigation.getOrCreateBadge(R.id.updateFragment);
        badge.setVisible(true);

        badge.setNumber(5);

        badge.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        badge.setBadgeTextColor(ContextCompat.getColor(this,R.color.black));
        badge.setVerticalOffset(15);
        badge.setHorizontalOffset(-5);
    }
}