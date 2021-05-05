package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.QuickContactBadge;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Menu Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //Sheet's buttons variables
    ImageButton sheetButton, sheetButton2, sheetButton3, sheetButton4, sheetButton5, sheetButton6;
    private int widget_appCompat_imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        sheetButton = findViewById(R.id.sheetBottom1);
        sheetButton2 = findViewById(R.id.sheetButton2);
        sheetButton3 = findViewById(R.id.sheetButton3);
        sheetButton4 = findViewById(R.id.sheetButton4);
        sheetButton5 = findViewById(R.id.sheetButton5);
        sheetButton6 = findViewById(R.id.sheetButton6);

        //Toolbar
        setSupportActionBar(toolbar);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_hom);


    }

    public void openBottomSheet(View view){
        BottomSheet bottomSheet = new BottomSheet();
        bottomSheet.show(getSupportFragmentManager(), "TAG");
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    //go to other acts
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_hom:
                break;
            case R.id.nav_config:
                break;
            case R.id.nav_help:
                break;
            case R.id.nav_addAppointment:
                Intent intent= new Intent(dashboard.this, addAppointment.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_seeAppointments:
                Intent intent2 = new Intent(dashboard.this, listOfAppointments.class);
                startActivity(intent2);
                break;
            case R.id.nav_addServices:
                Intent intent3 = new Intent(dashboard.this, addCatalogue.class);
                startActivity(intent3);
                break;
            case R.id.nav_seeServices:
                Intent intent4 = new Intent(dashboard.this, listService.class);
                startActivity(intent4);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}