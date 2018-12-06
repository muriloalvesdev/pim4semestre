package pim.alves.murilo.projetointegradomultidisciplinar;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pim.alves.murilo.projetointegradomultidisciplinar.view.LoginActivity;
import pim.alves.murilo.projetointegradomultidisciplinar.view.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                finish();
                startActivity(i);
            }
        }, SPLASH_TIME_OUT);
    }
}
