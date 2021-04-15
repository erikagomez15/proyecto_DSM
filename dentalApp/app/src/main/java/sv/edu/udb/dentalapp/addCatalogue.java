package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class addCatalogue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catalogue);
    }

    public void back(View view){
        Intent intent = new Intent(addCatalogue.this, dashboard.class);
        startActivity(intent);
    }
}