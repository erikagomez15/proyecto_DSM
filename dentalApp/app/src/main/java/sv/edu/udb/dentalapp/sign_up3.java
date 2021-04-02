package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sign_up3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);
    }

    public void createUserAccount(View view){

    }

    public void toSignUp2(View view){
        Intent intent = new Intent(this, sign_up2 .class);
        startActivity(intent);
        finish();
    }
}