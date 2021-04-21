package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class forgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    //back to Login
    public void backLogin(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        finish();
    }
}