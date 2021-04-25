package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sv.edu.udb.dentalapp.Adapters.AdapterAppointment;
import sv.edu.udb.dentalapp.Models.Appointment;

public class listOfAppointments extends AppCompatActivity {

    ArrayList<Appointment> listAppointments;
    RecyclerView recycler;
    AdapterAppointment adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_appointments);

        recycler = (RecyclerView) findViewById(R.id.listAppointments);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listAppointments = new ArrayList<Appointment>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new AdapterAppointment(listAppointments);
        recycler.setAdapter(adapter);
        database.getReference("Appointments").addValueEventListener(new ValueEventListener() {
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

    public void back(View view){
        Intent intent = new Intent(listOfAppointments.this, dashboard.class);
        startActivity(intent);
    }
}