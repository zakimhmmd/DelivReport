package com.example.zaki.delivreport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.zaki.delivreport.Decar.DeCarFragment;
import com.example.zaki.delivreport.Deexpress.DeExpressFragment;
import com.example.zaki.delivreport.Defood.DeFoodFragment;
import com.example.zaki.delivreport.Depayment.DePaymentFragment;
import com.example.zaki.delivreport.Deride.DeRideFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CircleImageView profilecircleImageView;
    SharedPrefManager sharedPrefManager;
    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPrefManager = new SharedPrefManager(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.app_name);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        profilecircleImageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        Glide.with(MainActivity.this)
                .load(R.drawable.ic_person)
                .into(profilecircleImageView);

        if (savedInstanceState == null){
            Fragment currentFragment = new DashboardFragment();
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
        if (id == R.id.action_logout) {
            new AlertDialog.Builder(this)
                    .setMessage("Apakah anda ingin Logout?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                            startActivity(new Intent(MainActivity.this, LoginActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }
                    }).setNegativeButton("No", null).show();
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
            fragment = new DashboardFragment();
        } else if (id == R.id.navigation_ride){
            title = "DeRide";
            fragment = new DeRideFragment();
        } else if (id == R.id.navigation_express){
            title = "DeExpress";
            fragment = new DeExpressFragment();
        } else if (id == R.id.navigation_food){
            title = "DeFood";
            fragment = new DeFoodFragment();
        } else if (id == R.id.navigation_car){
            title = "DeCar";
            fragment = new DeCarFragment();
        } else if (id == R.id.navigation_payment){
            title = "DePayment";
            fragment = new DePaymentFragment();
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
