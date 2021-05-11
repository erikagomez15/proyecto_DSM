package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import sv.edu.udb.dentalapp.Adapters.AdapterService;
import sv.edu.udb.dentalapp.Models.Appointment;
import sv.edu.udb.dentalapp.Models.Service;

public class editAppointment extends AppCompatActivity {

    ArrayList<Service> listServices;
    RecyclerView recycler;
    AdapterService adapter;
    Service servicio;
    Appointment appointment;
    TextView txtvServiceRequired, typeuser, User;
    //date & time picker's variables
    TextView hour_date;
    Button btnSelectTime_Date, btnEditAppointment;
    EditText edtUser, edtDescription;
    String date, description,key,service,user;

    com.google.android.material.textfield.TextInputLayout mask;
    private String tipoUsuario;
    private String usuario;

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refAppointments = database.getReference("Appointments");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        Bundle bundle = getIntent().getExtras();
        tipoUsuario = bundle.getString("type");
        usuario = bundle.getString("usuario");
        date = bundle.getString("date");
        description = bundle.getString("description");
        key = bundle.getString("key");
        service = bundle.getString("service");
        user = bundle.getString("user");

        //hooks
        recycler = findViewById(R.id.listServicesAppointmentEdit);
        typeuser = (TextView) findViewById(R.id.txtTypeUserEdit);
        mask = findViewById(R.id.maskEdit);
        User = (TextView) findViewById(R.id.txtUserAEdit);


        txtvServiceRequired = findViewById(R.id.txtvServiceRequiredEdit);
        hour_date = findViewById(R.id.hour_dateEdit);
        btnSelectTime_Date = findViewById(R.id.btnSelectTime_Date);
        btnEditAppointment = findViewById(R.id.btnEditAppointment);
        hour_date.setInputType(InputType.TYPE_NULL);
        edtUser = findViewById(R.id.userEdit);
        edtDescription = findViewById(R.id.observationEdit);


        edtDescription.setText(description);
        hour_date.setText(date);
        txtvServiceRequired.setText(service);
        typeuser.setText(tipoUsuario);
        User.setText(usuario);
        edtUser.setText(user);
        edtUser.setEnabled(false);

        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        listServices = new ArrayList<Service>();
        adapter = new AdapterService(listServices);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtvServiceRequired.setText(listServices.get(recycler.getChildAdapterPosition(v)).getName());
            }
        });
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

        btnSelectTime_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog( hour_date);
            }
        });
    }

    //Time & Date Picker's method
    private void showDateTimeDialog(final TextView date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yy HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(editAppointment.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(editAppointment.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void EditAppointment(View view){
        String Usuario="";
        if(tipoUsuario.equals("cliente")){Usuario = usuario;}
        else{
            Usuario = edtUser.getText().toString();
        }

        String descripcion = edtDescription.getText().toString();
        Appointment A = new Appointment(Usuario,descripcion,hour_date.getText().toString(),txtvServiceRequired.getText().toString(), key);
        refAppointments.child(key).setValue(A);
        Toast.makeText(getApplicationContext(),"Cita Modificada",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(editAppointment.this, dashboard.class);
        intent.putExtra("type",tipoUsuario);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }

    public void back(View view){
        Intent intent = new Intent(editAppointment.this, dashboard.class);
        intent.putExtra("type",tipoUsuario);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }
}