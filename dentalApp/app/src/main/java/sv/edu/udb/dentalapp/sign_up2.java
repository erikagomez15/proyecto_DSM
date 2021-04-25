package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class sign_up2 extends AppCompatActivity {

    private ImageView imvback;
    private TextView txtvTitle;
    private Button btnNext;
    private EditText edtuser, edtemail,edtpassword,edtpasswordAgain;
    String name, lastname,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        //Components hooks
        imvback = findViewById(R.id.btnBack);
        txtvTitle = findViewById(R.id.txtvTitle);
        btnNext = findViewById(R.id.btnNext);
        edtuser = findViewById(R.id.RegistrerUser);
        edtemail = findViewById(R.id.RegistrerEmail);
        edtpassword = findViewById(R.id.RegistrerPassword);
        edtpasswordAgain = findViewById(R.id.RegistrerPasswordAgain);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        lastname = bundle.getString("lastname");
        phone = bundle.getString("phone");
    }

    //To make the transition to sign_up2 activity
    public void nextScreen2(View view){

        if(edtpassword.getText().toString().isEmpty() || edtemail.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Rellena los campos",Toast.LENGTH_LONG).show();}
        else
        {
            if(edtpassword.getText().length()<=6){Toast.makeText(getApplicationContext(),"La contraseña debe contener mas de 6 caracteres",Toast.LENGTH_LONG).show();}
            else{
                Intent intent = new Intent(this, sign_up3.class);

                intent.putExtra("name",name);
                intent.putExtra("lastname",lastname);
                intent.putExtra("phone",phone);
                intent.putExtra("user",edtuser.getText().toString());
                intent.putExtra("email",edtemail.getText().toString());
                intent.putExtra("password",edtpassword.getText().toString());
                startActivity(intent);
                finish();
            }

        }
    }

    public void toSignUp1(View view){
        Intent intent = new Intent(this, signUp .class);
        startActivity(intent);
        finish();
    }
}