package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.w3c.dom.Text;
import sv.edu.udb.dentalapp.Models.User;

public class userProfile extends AppCompatActivity {
    FirebaseDatabase database;
    ImageButton btnBack;
    Button btnUpdate;
    private TextView userp, typeuserp;
    private EditText edtNombre, edtApellido, edtTelefono, edtContra,edtCorreo;
    private String tipo, usuario, nombres, apellidos, telefono, contra,fecha,genero, user,key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //hooks
        btnBack = findViewById(R.id.btnBackDash);
        btnUpdate = findViewById(R.id.btnUpdate);

        Bundle bundle = getIntent().getExtras();
        tipo = bundle.getString("type");
        usuario = bundle.getString("user");

        userp=(TextView)findViewById(R.id.userp);
        typeuserp=(TextView)findViewById(R.id.typeuserp);
        userp.setText(usuario);
        typeuserp.setText(tipo);

        edtNombre = findViewById(R.id.txtNameProfile);
        edtApellido = findViewById(R.id.txtLasNameProfile);
        edtTelefono = findViewById(R.id.txtPhoneProfile);
        edtContra = findViewById(R.id.txtPasswordProfile);
        edtCorreo = findViewById(R.id.txtEmailProfile);

        database = FirebaseDatabase.getInstance();
        Query query = database.getReference("Usuarios").orderByChild("email").equalTo(usuario);
        query.addValueEventListener(new ValueEventListener() {
            int count = 0;
            String TipoUsuario ="";
            String correo ="";
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for(DataSnapshot snapshot: datasnapshot.getChildren()){
                    User u = snapshot.getValue(User.class);
                    fecha = u.getBirthday();
                    correo = u.getEmail();
                    genero = u.getGender();
                    nombres = u.getName();
                    apellidos = u.getLastname();
                    contra = u.getPassword();
                    telefono = u.getPhone();
                    user = u.getUser();
                    key = u.getKey();
                    count++;
                }
                edtNombre.setText(nombres);
                edtApellido.setText(apellidos);
                edtCorreo.setText(correo);
                edtContra.setText(contra);
                edtTelefono.setText(telefono);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateData(View view){
        User u = new User();
        u.setBirthday(fecha);
        u.setEmail(edtCorreo.getText().toString());
        u.setGender(genero);
        u.setName(edtNombre.getText().toString());
        u.setLastname(edtApellido.getText().toString());
        u.setPassword(edtContra.getText().toString());
        u.setPhone(edtTelefono.getText().toString());
        u.setType(tipo);
        u.setUser(user);
        u.setKey(key);
        DatabaseReference refUsuarios = database.getReference("Usuarios");
        refUsuarios.child(u.getKey()).setValue(u);
        Toast.makeText(this,"Datos Actualizados", Toast.LENGTH_LONG).show();
    }

    public void backDash(View view){
        Intent intent = new Intent(userProfile.this, dashboard.class);
        intent.putExtra("type",tipo);
        intent.putExtra("user",usuario);
        startActivity(intent);
    }
}


