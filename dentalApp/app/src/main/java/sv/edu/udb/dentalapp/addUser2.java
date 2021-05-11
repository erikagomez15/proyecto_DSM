package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addUser2 extends AppCompatActivity {

    private EditText edtEmail,edtPassword,edtUser;
    private String usuario, tipo, nombre,apellido,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user2);

        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("user");
        tipo = bundle.getString("type");
        nombre = bundle.getString("name");
        apellido = bundle.getString("apellido");
        telefono = bundle.getString("telefono");

        edtEmail = findViewById(R.id.txtAddUserEmail);
        edtPassword = findViewById(R.id.txtAddUserPassword);
        edtUser = findViewById(R.id.txtAddUserUsuario);
    }

    public void nextAddUser3(View view){
        if(edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(),"Rellena los campos",Toast.LENGTH_LONG).show();
        }

        else {
            if(edtPassword.getText().length()<=6){
                Toast.makeText(getApplicationContext(),"La contraseña debe contener mas de 6 caracteres",Toast.LENGTH_LONG).show();
            }
            else{
                Intent intent = new Intent(addUser2.this,addUser3.class);
                intent.putExtra("name",nombre);
                intent.putExtra("apellido",apellido);
                intent.putExtra("telefono",telefono);
                intent.putExtra("type",tipo);
                intent.putExtra("user",usuario);
                intent.putExtra("email",edtEmail.getText().toString());
                intent.putExtra("password",edtPassword.getText().toString());
                intent.putExtra("usuario",edtUser.getText().toString());
                startActivity(intent);
                finish();
            }
        }
    }

    public void backAddUser1(View view){
        Intent intent = new Intent(addUser2.this, addUser.class);
        intent.putExtra("type",tipo);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }
}

