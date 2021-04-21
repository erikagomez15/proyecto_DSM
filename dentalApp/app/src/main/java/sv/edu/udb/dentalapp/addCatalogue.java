package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sv.edu.udb.dentalapp.models.Services;

public class addCatalogue extends AppCompatActivity {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refServicios = database.getReference("Services");
    EditText edtName, edtDescription, edtPrice, edtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catalogue);

        edtName = findViewById(R.id.txtServiceName);
        edtDescription = findViewById(R.id.txtDescription);
        edtPrice = findViewById(R.id.txtPrice);
        edtTime = findViewById(R.id.txtTime);
    }

    public void add(View view){
        String nombre = edtName.getText().toString();
        String descripcion = edtDescription.getText().toString();
        String precio = edtPrice.getText().toString();
        String tiempo = edtTime.getText().toString();

        if(nombre ==null || descripcion==null ||precio ==null || tiempo==null){
            Toast.makeText(getApplicationContext(),"Completa todos los campos",Toast.LENGTH_SHORT).show();
        }
        else {
            Services servicio = new Services();
            servicio.setNombre(nombre);
            servicio.setDescripcion(descripcion);
            servicio.setPrecio(precio);
            servicio.setTiempo(tiempo);
            refServicios.push().setValue(servicio);
            CleanInputs();
        }

    }

    public void CleanInputs(){
        edtName.setText("");
        edtDescription.setText("");
        edtPrice.setText("");
        edtTime.setText("");
    }
    public void back(View view){
        Intent intent = new Intent(addCatalogue.this, dashboard.class);
        startActivity(intent);
    }
}