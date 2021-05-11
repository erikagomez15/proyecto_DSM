package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Element;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

import sv.edu.udb.dentalapp.Adapters.AdapterAppointment;
import sv.edu.udb.dentalapp.Adapters.AdapterAppointmentDashboard;
import sv.edu.udb.dentalapp.Models.Appointment;
import sv.edu.udb.dentalapp.Models.Service;
import sv.edu.udb.dentalapp.Models.User;
import android.view.View;
import android.widget.ImageButton;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import android.view.View;
import android.widget.ImageButton;
import android.widget.QuickContactBadge;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Appointment> listAppointments;
    RecyclerView recycler;
    AdapterAppointmentDashboard adapter;
    DatabaseReference refAppointments = FirebaseDatabase.getInstance().getReference("Appointments");

    //Menu Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    private FirebaseAuth mAuth;
    private TextView NombreUsuario;
    private String tipo;
    private String usuario;

    ImageButton sheetButton, sheetButton2, sheetButton3, sheetButton4, sheetButton5, sheetButton6;
    private int widget_appCompat_imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        NombreUsuario=findViewById(R.id.txtUsuario);
        recycler = (RecyclerView) findViewById(R.id.listAppointmentsDashboard);

        Bundle bundle = getIntent().getExtras();
        tipo = bundle.getString("type");
        usuario = bundle.getString("user");
        NombreUsuario.setText(usuario);

        //Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        sheetButton = findViewById(R.id.sheetBottom1);



        if(tipo.equals("cliente")){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.customer_menu);
        }
        else{
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.main_menu);
        }

        //Toolbar
        setSupportActionBar(toolbar);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_hom);

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        listAppointments = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new AdapterAppointmentDashboard(listAppointments);

        adapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appointment app = new Appointment();
                app.setDate(listAppointments.get(recycler.getChildAdapterPosition(v)).getDate());
                app.setDescription(listAppointments.get(recycler.getChildAdapterPosition(v)).getDescription());
                app.setKey(listAppointments.get(recycler.getChildAdapterPosition(v)).getKey());
                app.setService(listAppointments.get(recycler.getChildAdapterPosition(v)).getService());
                app.setUser(listAppointments.get(recycler.getChildAdapterPosition(v)).getUser());
                BottomSheet bottomSheet = new BottomSheet(app, tipo, usuario);
                bottomSheet.show(getSupportFragmentManager(), "TAG");

            }
        });

        recycler.setAdapter(adapter);
        Query query = database.getReference("Appointments").orderByChild("user").equalTo(usuario);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                listAppointments.removeAll(listAppointments);
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    Appointment a = snapshot.getValue(Appointment.class);
                    listAppointments.add(a);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    //method to open BottomSheet
    public void openBottomSheet(View view){
        //BottomSheet bottomSheet = new BottomSheet();
        //bottomSheet.show(getSupportFragmentManager(), "TAG");
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
                Intent intent5 = new Intent(dashboard.this, userProfile.class);
                intent5.putExtra("type",tipo);
                intent5.putExtra("user",usuario);
                startActivity(intent5);
                break;
            case R.id.nav_help:
                String URL = "https://firebasestorage.googleapis.com/v0/b/dentalapp-710ed.appspot.com/o/MANUAL%20DE%20USUARIO%20DENTALAPP.pdf?alt=media&token=245584d9-4e3a-4695-a244-711c74e434ee";
                Uri uri = Uri.parse(URL);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case R.id.nav_addUser:
                Intent intent6 = new Intent(dashboard.this,addUser.class);
                intent6.putExtra("type",tipo);
                intent6.putExtra("user",usuario);
                startActivity(intent6);
                break;
            case R.id.nav_addAppointment:
                Intent intent7= new Intent(dashboard.this, addAppointment.class);
                intent7.putExtra("type",tipo);
                intent7.putExtra("user",usuario);
                startActivity(intent7);
                break;
            case R.id.nav_seeAppointments:
                Intent intent2 = new Intent(dashboard.this, listOfAppointments.class);
                intent2.putExtra("type",tipo);
                intent2.putExtra("user",usuario);
                startActivity(intent2);
                break;
            case R.id.nav_addServices:
                Intent intent3 = new Intent(dashboard.this, addCatalogue.class);
                intent3.putExtra("type",tipo);
                intent3.putExtra("user",usuario);
                startActivity(intent3);
                break;
            case R.id.nav_seeServices:
                Intent intent4 = new Intent(dashboard.this, listService.class);
                intent4.putExtra("type",tipo);
                intent4.putExtra("user",usuario);
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