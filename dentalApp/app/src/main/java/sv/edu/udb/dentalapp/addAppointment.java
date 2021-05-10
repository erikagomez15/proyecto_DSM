package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class addAppointment extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> services;
    TextView txtvServiceRequired;
    ImageButton btnBackDash;
    //date & time picker's variables
    TextView hour_date;
    Button btnSelectTime_Date;

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

        hour_date.setInputType(InputType.TYPE_NULL);

        //Aqui se obtendrian los servicios del catalogo
        services = new ArrayList<String>();
        services.add("Servicio 1");
        services.add("Servicio 2");
        services.add("Servicio 3");
        services.add("Servicio 4");
        services.add("Servicio 5");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,services);
        listView.setAdapter(adapter);

        //Get item selected
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                txtvServiceRequired.setText(services.get(position));
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

                new TimePickerDialog(addAppointment.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(addAppointment.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void back(View view){
        Intent intent = new Intent(addAppointment.this, dashboard.class);
        startActivity(intent);
    }
}