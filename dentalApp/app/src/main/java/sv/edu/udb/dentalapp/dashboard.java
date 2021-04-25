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
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Menu Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private FirebaseAuth mAuth;
    private TextView NombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        NombreUsuario=findViewById(R.id.txtUsuario);

        //FIrebase
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser= mAuth.getCurrentUser();
        NombreUsuario.setText(currentUser.getDisplayName());

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
    public void SignOut(){
        mAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
        finish();
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
            case R.id.navSesion:
                SignOut();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}