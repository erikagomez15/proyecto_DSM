package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class addUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    public void nextAddUser(View view){
        Intent intent = new Intent(addUser.this, addUser2.class);
        startActivity(intent);
    }
    //Back to dashboard
    public void backDash1(View view){
        Intent intent = new Intent(addUser.this, dashboard.class);
        startActivity(intent);
    }
}