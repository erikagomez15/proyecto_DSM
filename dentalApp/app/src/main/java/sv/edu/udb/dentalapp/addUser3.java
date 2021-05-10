package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class addUser3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user3);
    }
    public void backAddUser2(View view){
        Intent intent = new Intent(addUser3.this, addUser2.class);
        startActivity(intent);
    }

    //To create user account
    public void createUserAccount(View view){

    }
}