package sv.edu.udb.dentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //variables for animation
    Animation topAnim, bottomAnim;
    //components' variables
    ImageView imgvAppLogo;
    TextView txtvAppName, txtvSlogan;
    //variable to pass actyvity
    private static  int SPLASH_SCREEN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        imgvAppLogo = (ImageView) findViewById(R.id.imgvAppLogo);
        txtvAppName = (TextView)findViewById(R.id.txtvAppName);
        txtvSlogan = (TextView)findViewById(R.id.txtvSlogan);
        //setting animation to components
        imgvAppLogo.setAnimation(topAnim);
        txtvAppName.setAnimation(bottomAnim);
        txtvSlogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, dashboard.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}