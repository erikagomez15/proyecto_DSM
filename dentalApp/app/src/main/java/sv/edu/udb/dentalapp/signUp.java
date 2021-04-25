package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

public class signUp extends AppCompatActivity {
    //Componentes Variables
    private ImageView imvback;
    private TextView txtvTitle;
    private Button btnNext, btnLogin;
    private EditText edtname, edtlastname,edtphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Components hooks
        imvback = findViewById(R.id.btnBack);
        txtvTitle = findViewById(R.id.txtvTitle);
        btnLogin = findViewById(R.id.btnLogin);
        btnNext = findViewById(R.id.btnNext);
        edtname = findViewById(R.id.Registrername);
        edtlastname = findViewById(R.id.Registrerlastname);
        edtphone = findViewById(R.id.Registrerphone);
    }

    //To make the transition to sign_up2 activity
    public void nextScreen1(View view){
        Intent intent = new Intent(getApplicationContext(), sign_up2.class);

        intent.putExtra("name",edtname.getText().toString());
        intent.putExtra("lastname",edtlastname.getText().toString());
        intent.putExtra("phone",edtphone.getText().toString());

        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(imvback, "btnBack_transition");
        pairs[1] = new Pair<View, String>(txtvTitle, "title_transition");
        pairs[2] = new Pair<View, String>(btnNext, "btnLogin_transition");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signUp.this, pairs);
            startActivity(intent,options.toBundle());
        }else{
            startActivity(intent);
            finish();
        }

    }

    public void toLogin(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
        finish();
    }
}