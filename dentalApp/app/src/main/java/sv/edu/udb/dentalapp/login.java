package sv.edu.udb.dentalapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import  android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import sv.edu.udb.dentalapp.Models.Service;
import sv.edu.udb.dentalapp.Models.User;

public class login extends AppCompatActivity {
    private Button BtnLogin,btnLogin;
    private EditText edtUser, edtPassword;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView recuperacion;
    private FirebaseAuth mAuth;
    int RC_SIGN_IN = 1;
    String TAG = "GoogleSignIn";
    private FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        BtnLogin= findViewById(R.id.BtnGoogle);
        btnLogin=findViewById(R.id.btnLogin);
        edtUser=findViewById(R.id.edtUser);
        edtPassword=findViewById(R.id.edtPass);
        recuperacion =findViewById(R.id.recuperarcontra);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
        recuperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, forgetPassword.class);
                startActivity(intent);
                finish();

            }
        });
    }




    //LOGIN GOOGLE

    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "LOGIN DE GOOGLE FALLIDO", e);
                // ...
            }
        }

    }
    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "CREDENCIALES EXITOSAS");


                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                mAuth=FirebaseAuth.getInstance();
                                FirebaseUser currentUser= mAuth.getCurrentUser();
                                String usuario = currentUser.getEmail();
                                Intent dashboardActivity = new Intent(login.this,dashboard.class);
                                dashboardActivity.putExtra("type","cliente");
                                dashboardActivity.putExtra("user",usuario);
                                startActivity(dashboardActivity);
                                login.this.finish();

                            }} else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });


    }



    //************************************************************************+

//LOGIN CON CORREO Y CONTRASEÑA

    private void loginUserAccount() {

        String email, password;
        email = edtUser.getText().toString();
        password = edtPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "¡Login Exitoso!", Toast.LENGTH_LONG).show();

                            VerificarTipo(email);

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "No se logró iniciar sesión. Intente más tarde", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    public void VerificarTipo(String email){


        database = FirebaseDatabase.getInstance();
        Query query = database.getReference("Usuarios").orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            int count = 0;
            String TipoUsuario ="";
            String correo ="";
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                    for(DataSnapshot snapshot: datasnapshot.getChildren()){
                        User u = snapshot.getValue(User.class);
                        TipoUsuario = u.getType();
                        correo = u.getEmail();
                        count++;
                    }
                Toast.makeText(getApplicationContext(),"Encontramos " + count + " Registros",Toast.LENGTH_LONG).show();
                //if(count>0){ TipoUsuario = "cliente";}
                //else {TipoUsuario = "admin";}
                Intent intent = new Intent(login.this, dashboard.class);
                intent.putExtra("type",TipoUsuario);
                intent.putExtra("user",correo);
                startActivity(intent);
                 //TipoUsuario = datasnapshot.child("type").getValue().toString();
                //TipoUsuario = datasnapshot.getValue(User.class).getType();
                //Toast.makeText(getApplicationContext(),"Registro tipo "+TipoUsuario,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    //****************************************************************************************************++







    public void forgetPassword(View view ){
        Intent intent = new Intent(this,forgetPassword.class);
        startActivity(intent);
        finish();
    }

    public void signUp(View view){
        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);
        finish();
    }



}