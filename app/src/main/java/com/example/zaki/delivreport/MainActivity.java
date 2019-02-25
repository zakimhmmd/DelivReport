package com.example.zaki.delivreport;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CircleImageView profilecircleImageView;

    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.app_name);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        profilecircleImageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        Glide.with(MainActivity.this)
                .load(R.drawable.ic_person_black_24dp)
                .into(profilecircleImageView);

        if (savedInstanceState == null){
            Fragment currentFragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, currentFragment)
                    .commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        drawer.removeDrawerListener(toggle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Fragment fragment = null;
        String title = "";

        if (id == R.id.navigation_dashboard){
            title = "Dashboard";
            fragment = new HomeFragment();
        } else if (id == R.id.navigation_ride){
            title = "DeRide";
            fragment = new PageFragment();
            bundle.putString(PageFragment.EXTRAS, "DeRide");
            fragment.setArguments(bundle);
        } else if (id == R.id.navigation_express){
            title = "DeExpress";
            fragment = new PageFragment();
            bundle.putString(PageFragment.EXTRAS, "DeExpress");
            fragment.setArguments(bundle);
        } else if (id == R.id.navigation_food){
            title = "DeFood";
            fragment = new DeFoodFragment();
        } else if (id == R.id.navigation_car){
            title = "DeCar";
            fragment = new PageFragment();
            bundle.putString(PageFragment.EXTRAS, "DeCar");
            fragment.setArguments(bundle);
        } else if (id == R.id.navigation_payment){
            title = "DePayment";
            fragment = new PageFragment();
            bundle.putString(PageFragment.EXTRAS,"DePayment");
            fragment.setArguments(bundle);
        }

        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();
        }

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
