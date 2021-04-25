package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetPassword extends AppCompatActivity {
    Button recuperarbtn;
    EditText recorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        recorreo = findViewById(R.id.rcorreo);
        recuperarbtn = findViewById(R.id.rbtn);
        recuperarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(recorreo.getText().toString());

            }
        });


    }

    @Override
    public void onBackPressed(){

        super.onBackPressed();
        Intent intent = new Intent(forgetPassword.this, login.class);
        startActivity(intent);
        finish();
    }
    public void sendEmail(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailaddress = email;

        auth.sendPasswordResetEmail(emailaddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText( forgetPassword.this, "Correo enviado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(forgetPassword.this, login.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText( forgetPassword.this, "Correo invalido", Toast.LENGTH_SHORT).show();


                }
                }


        });
    }



    //back to Login
    public void backLogin(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        finish();
    }
}