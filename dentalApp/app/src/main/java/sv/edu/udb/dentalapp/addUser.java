package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class addUser extends AppCompatActivity {

    private String tipo, usuario;
    private TextView type, user;
    private EditText Nombre,Apellido,Telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Bundle bundle = getIntent().getExtras();
        tipo = bundle.getString("type");
        usuario = bundle.getString("user");

        Nombre = findViewById(R.id.txtName);
        Apellido = findViewById(R.id.txtLastName);
        Telefono = findViewById(R.id.txtPhone);
        type = findViewById(R.id.txtUserAccount);
        user = findViewById(R.id.txtTypeUserAccount);
        type.setText(tipo);
        user.setText(usuario);
    }

    public void nextAddUser(View view){
        Intent intent = new Intent(addUser.this, addUser2.class);
        intent.putExtra("name",Nombre.getText().toString());
        intent.putExtra("apellido",Apellido.getText().toString());
        intent.putExtra("telefono",Telefono.getText().toString());
        intent.putExtra("type",tipo);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }
    //Back to dashboard
    public void backDash1(View view){
        Intent intent = new Intent(addUser.this, dashboard.class);
        intent.putExtra("type",tipo);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }
}

