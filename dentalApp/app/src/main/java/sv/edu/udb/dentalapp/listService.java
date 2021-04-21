package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sv.edu.udb.dentalapp.Adapters.AdapterServices;
import sv.edu.udb.dentalapp.models.Services;

public class listService extends AppCompatActivity {
    ArrayList<Services> listServices;
    RecyclerView recycler;
    AdapterServices adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service);

        recycler = (RecyclerView) findViewById(R.id.listServices);

        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listServices = new ArrayList<Services>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new AdapterServices(listServices);
        recycler.setAdapter(adapter);
        database.getReference("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                listServices.removeAll(listServices);
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    Services s = snapshot.getValue(Services.class);
                    listServices.add(s);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void back(View view){
        Intent intent = new Intent(listService.this, dashboard.class);
        startActivity(intent);
    }
}