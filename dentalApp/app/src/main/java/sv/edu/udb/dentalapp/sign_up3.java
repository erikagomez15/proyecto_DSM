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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import sv.edu.udb.dentalapp.Models.User;

public class sign_up3 extends AppCompatActivity {

    private String name, lastname,phone,email,password, user, date, gender;
    private RadioButton radioM, radioF;
    DatePicker fecha;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refUsuarios = database.getReference("Usuarios");
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_sign_up3);
        radioM = findViewById(R.id.rbtnMale);
        radioF = findViewById(R.id.rbtnFemale);
        fecha = findViewById(R.id.DPfecha);

        if (radioM.isChecked()==true) gender = radioM.getText().toString();
        if (radioF.isChecked()==true) gender = radioF.getText().toString();


        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        lastname = bundle.getString("lastname");
        phone = bundle.getString("phone");
        email = bundle.getString("email");
        password = bundle.getString("password");
        user = bundle.getString("user");
        date = String.valueOf(fecha.getDayOfMonth()) + "-"+String.valueOf(fecha.getMonth())+"-"+String.valueOf(fecha.getYear());
    }

    public void createUserAccount(View view){

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        User u = new User(name,lastname,phone,user,email,password,date,gender);
                        refUsuarios.push().setValue(u);
                        Toast.makeText(getApplicationContext(),"Usuario Registrado",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(sign_up3.this,login.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Fallo de registro, intente de nuevo",Toast.LENGTH_LONG).show();
                    }
                }
            });

    }

    public void toSignUp2(View view){
        Intent intent = new Intent(this, sign_up2 .class);
        startActivity(intent);
        finish();
    }
}