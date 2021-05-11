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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import sv.edu.udb.dentalapp.Adapters.AdapterAppointment;
import sv.edu.udb.dentalapp.Models.Appointment;

public class listOfAppointments extends AppCompatActivity {

    ArrayList<Appointment> listAppointments;
    RecyclerView recycler;
    AdapterAppointment adapter;
    private String tipoUser, user;
    TextView type, Usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_appointments);

        Bundle bundle = getIntent().getExtras();
        tipoUser = bundle.getString("type");
        user = bundle.getString("user");

        recycler = (RecyclerView) findViewById(R.id.listAppointments);
        type = (TextView) findViewById(R.id.txtTipoUsuario);
        Usuario = (TextView) findViewById(R.id.txtuser);
        type.setText(tipoUser);
        Usuario.setText(user);

        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listAppointments = new ArrayList<Appointment>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new AdapterAppointment(listAppointments);
        recycler.setAdapter(adapter);

        if(tipoUser.equals("cliente")){
            Query query = database.getReference("Appointments").orderByChild("user").equalTo(user);
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

        else{
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


    }

    public void back(View view){
        Intent intent = new Intent(listOfAppointments.this, dashboard.class);
        intent.putExtra("type",tipoUser);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}