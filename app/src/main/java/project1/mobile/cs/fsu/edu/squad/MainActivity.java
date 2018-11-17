package project1.mobile.cs.fsu.edu.squad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    Button loginbutt;
    Button reggi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbutt = (Button)findViewById(R.id.loginButton);
        reggi = (Button)findViewById(R.id.reggi);
        loginbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,GridMenu.class);
                startActivity(intent);
            }
        });

        reggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,RegisterUser.class);
                startActivity(intent);
            }
        });
    }
}
