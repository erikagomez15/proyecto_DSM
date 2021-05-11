package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sv.edu.udb.dentalapp.Adapters.AdapterService;
import sv.edu.udb.dentalapp.Models.Service;

public class listService extends AppCompatActivity {

    ArrayList<Service> listServices;
    TextView type, user;
    RecyclerView recycler;
    AdapterService adapter;
    private String tipoUser, usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service);

        Bundle bundle = getIntent().getExtras();
        tipoUser = bundle.getString("type");
        usuario = bundle.getString("user");

        recycler = (RecyclerView) findViewById(R.id.listServices);
        type = (TextView) findViewById(R.id.textType);
        user = (TextView) findViewById(R.id.textUserLS);
        type.setText(tipoUser);
        user.setText(usuario);

        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listServices = new ArrayList<Service>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new AdapterService(listServices);
        recycler.setAdapter(adapter);
        database.getReference("Services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                listServices.removeAll(listServices);
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    Service s = snapshot.getValue(Service.class);
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
        intent.putExtra("type",tipoUser);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }
}