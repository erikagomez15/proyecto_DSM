package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class addUser2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user2);
    }

    public void nextAddUser3(View view){

    }

    public void backAddUser1(View view){
        Intent intent = new Intent(addUser2.this, addUser.class);
        startActivity(intent);
    }
}