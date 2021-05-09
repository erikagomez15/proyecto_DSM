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
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import sv.edu.udb.dentalapp.Adapters.AdapterService;
import sv.edu.udb.dentalapp.Models.Appointment;
import sv.edu.udb.dentalapp.Models.Service;

public class addAppointment extends AppCompatActivity {

    ArrayList<Service> listServices;
    RecyclerView recycler;
    AdapterService adapter;
    private Service servicio;
    TextView txtvServiceRequired;
    ImageButton btnBackDash;
    //date & time picker's variables
    TextView hour_date;
    Button btnSelectTime_Date , btnAddAppointment;
    EditText edtUser, edtDescription;

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refAppointments = database.getReference("Appointments");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        //hooks
        btnBackDash = findViewById(R.id.btnBackDash);
        listView= findViewById(R.id.listServices);
        txtvServiceRequired = findViewById(R.id.txtvServiceRequired);
        hour_date = findViewById(R.id.hour_date);
        btnSelectTime_Date = findViewById(R.id.btnSelectTime_Date);
        btnAddAppointment = findViewById(R.id.btnaddAppointment);
        hour_date.setInputType(InputType.TYPE_NULL);
        edtUser = findViewById(R.id.user);
        edtDescription = findViewById(R.id.observation);



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

        btnAddAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAppointment();
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

                new TimePickerDialog(addAppointment.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(addAppointment.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void back(View view){
        Intent intent = new Intent(addAppointment.this, dashboard.class);
        startActivity(intent);
    }

    public void AddAppointment(){
        String usuario = edtUser.getText().toString();
        String descripcion = edtDescription.getText().toString();
        Appointment A = new Appointment(usuario,descripcion,hour_date.getText().toString(),txtvServiceRequired.getText().toString());
        refAppointments.push().setValue(A);
        Toast.makeText(getApplicationContext(),"Cita Realizada",Toast.LENGTH_LONG).show();
        CleanInputs();
    }

    public void CleanInputs(){
        txtvServiceRequired.setText("Selección");
        hour_date.setText("");
        edtDescription.setText("");
        edtUser.setText("");
    }
}