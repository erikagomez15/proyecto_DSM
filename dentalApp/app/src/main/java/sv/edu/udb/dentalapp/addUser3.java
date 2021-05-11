package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;
import sv.edu.udb.dentalapp.Models.User;

public class addUser3 extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refUsuarios = database.getReference("Usuarios");
    FirebaseAuth mAuth;

    private String tipo, usuario, name, lastname, phone, email, password, gender, date, type, user;
    private RadioButton rbtnAdmin, rbtnClient, rbtnMale, rbtnFemale;
    private DatePicker fechaNac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user3);
        mAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();
        tipo = bundle.getString("type");
        usuario = bundle.getString("user");
        name = bundle.getString("name");
        lastname = bundle.getString("apellido");
        phone = bundle.getString("telefono");
        email = bundle.getString("email");
        password = bundle.getString("password");
        user = bundle.getString("usuario");

        rbtnAdmin = findViewById(R.id.rdbTypeAdmin);
        rbtnClient = findViewById(R.id.rdbTypeClient);
        rbtnMale = findViewById(R.id.rbtnAddUserMale);
        rbtnFemale = findViewById(R.id.rbtnAddUserFemale);
        fechaNac = findViewById(R.id.fechaAddUser);
    }

    public void backAddUser2(View view){
        Intent intent = new Intent(addUser3.this, addUser2.class);
        intent.putExtra("name",name);
        intent.putExtra("apellido", lastname);
        intent.putExtra("telefono",phone);
        intent.putExtra("type",tipo);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }

    //To create user account
    public void createUserAccount(View view){

        if (rbtnAdmin.isChecked()==true) type = "Admin";
        if (rbtnClient.isChecked()==true) type ="cliente";

        if (rbtnMale.isChecked()==true) gender = "Masculino";
        if (rbtnFemale.isChecked()==true) gender = "Femenino";

        date = String.valueOf(fechaNac.getDayOfMonth()) + "-"+String.valueOf(fechaNac.getMonth())+"-"+String.valueOf(fechaNac.getYear());

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User u = new User(name,lastname,phone,user,email,password,date,gender,type);
                    u.setKey(UUID.randomUUID().toString());
                    refUsuarios.push().setValue(u);
                    Toast.makeText(getApplicationContext(),"Usuario Registrado",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(addUser3.this, dashboard.class);
                    intent.putExtra("type",tipo);
                    intent.putExtra("user",usuario);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Fallo de registro, intente de nuevo",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
