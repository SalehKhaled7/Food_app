package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ChipNavigationBar chipNavigationBar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //getSupportActionBar().hide();// hide the action bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //hide the status bar

        toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle("Food Donation App");
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_home,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        bottomMenu();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                toolbar.setTitle("Profile");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment2()).commit();
                break;
            case R.id.nav_address:
                toolbar.setTitle("My address");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddressFragment()).commit();
                break;
            case R.id.nav_settings:
                toolbar.setTitle("Settings");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SettingsFragment()).commit();
                break;
            case R.id.nav_about_us:
                toolbar.setTitle("About us");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AboutUsFragment()).commit();
                break;
            case R.id.nav_share:

                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment =null;
                switch (i){
                    case R.id.bottom_nav_home:
                        fragment= new HomeFragment();
                        toolbar.setTitle("Food Donation App");
                        break;
                    case R.id.bottom_nav_categories:
                        fragment= new CategoryFragment();
                        toolbar.setTitle("Categories");
                        break;
                    case R.id.bottom_nav_basket:
                        fragment= new MyOrdersFragment();
                        toolbar.setTitle("My orders");
                        break;
                    case R.id.bottom_nav_profile:
                        fragment= new ProfileFragment();
                        toolbar.setTitle("Profile");
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

            }
        });
    }


}