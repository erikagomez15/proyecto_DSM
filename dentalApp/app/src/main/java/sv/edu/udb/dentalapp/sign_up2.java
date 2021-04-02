package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class sign_up2 extends AppCompatActivity {

    private ImageView imvback;
    private TextView txtvTitle;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        //Components hooks
        imvback = findViewById(R.id.btnBack);
        txtvTitle = findViewById(R.id.txtvTitle);
        btnNext = findViewById(R.id.btnNext);
    }

    //To make the transition to sign_up2 activity
    public void nextScreen2(View view){
        Intent intent = new Intent(this, sign_up3.class);
        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(imvback, "btnBack_transition");
        pairs[1] = new Pair<View, String>(txtvTitle, "title_transition");
        pairs[2] = new Pair<View, String>(btnNext, "btnLogin_transition");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(sign_up2.this, pairs);
            startActivity(intent,options.toBundle());
        }else{
            startActivity(intent);
            finish();
        }

    }

    public void toSignUp1(View view){
        Intent intent = new Intent(this, signUp .class);
        startActivity(intent);
        finish();
    }
}