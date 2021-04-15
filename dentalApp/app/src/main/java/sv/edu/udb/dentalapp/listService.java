package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class listService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service);
    }

    public void back(View view){
        Intent intent = new Intent(listService.this, dashboard.class);
        startActivity(intent);
    }
}