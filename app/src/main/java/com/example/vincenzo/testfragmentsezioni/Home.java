package com.example.vincenzo.testfragmentsezioni;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Vincenzo on 03/02/2017.
 */


public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_app);


        Button login = (Button) findViewById(R.id.login_home);
        Button registrati = (Button) findViewById(R.id.registrati_home);
        Button info = (Button) findViewById(R.id.info_home);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (
                        Home.this,
                        MainActivity.class
                );
                startActivity(intent);
            }
        });

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (
                        Home.this,
                        Registrazione.class
                );
                startActivity(intent);
            }
        });

    }

}
