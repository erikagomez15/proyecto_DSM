package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class userProfile extends AppCompatActivity {

    ImageButton btnBack;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //hooks
        btnBack = findViewById(R.id.btnBackDash);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    public void updateData(View view){

    }

    public void backDash(View view){
        Intent intent = new Intent(userProfile.this, dashboard.class);
        startActivity(intent);
    }
}