package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sv.edu.udb.dentalapp.Models.Service;

public class addCatalogue extends AppCompatActivity {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refServicios = database.getReference("Services");
    EditText edtName, edtDescription, edtPrice, edtTime;
    TextView user, typeUser;
    String tipo, usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catalogue);

        Bundle bundle = getIntent().getExtras();
        tipo = bundle.getString("type");
        usuario = bundle.getString("user");

        edtName = findViewById(R.id.txtServiceName);
        edtDescription = findViewById(R.id.txtDescription);
        edtPrice = findViewById(R.id.txtPrice);
        edtTime = findViewById(R.id.txtTime);
        user = findViewById(R.id.txtUserAS);
        typeUser = findViewById(R.id.txtTypeUserAS);
        user.setText(usuario);
        typeUser.setText(tipo);
    }

    public void back(View view){
        Intent intent = new Intent(addCatalogue.this, dashboard.class);
        intent.putExtra("type",tipo);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }

    public void CleanInputs(){
        edtName.setText("");
        edtDescription.setText("");
        edtPrice.setText("");
        edtTime.setText("");
    }

    public void add(View view) {
        String nombre = edtName.getText().toString();
        String descripcion = edtDescription.getText().toString();
        String precio = edtPrice.getText().toString();
        String tiempo = edtTime.getText().toString();

            Service servicio = new Service();
            servicio.setName(nombre);
            servicio.setDescription(descripcion);
            servicio.setPrice(precio);
            servicio.setTime(tiempo);
            refServicios.push().setValue(servicio);
            CleanInputs();

    }
}