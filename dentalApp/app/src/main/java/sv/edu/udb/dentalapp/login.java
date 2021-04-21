package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import  android.content.Intent;
public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
    }

    public void forgetPassword(View view ){
        Intent intent = new Intent(this,forgetPassword.class);
        startActivity(intent);
        finish();
    }

    public void signUp(View view){
        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);
        finish();
    }

    public void Login(View view){
        //Aqui iria el codigo del Login
        Intent intent = new Intent(login.this, dashboard.class);
        startActivity(intent);
    }

}