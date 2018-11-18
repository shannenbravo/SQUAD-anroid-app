package project1.mobile.cs.fsu.edu.squad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    Button loginbutt;
    //private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();*/

        /*mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(MainActivity.this, this /* OnConnectionFailedListener )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/

        intent = new Intent(this,GridMenu.class);
        loginbutt = (Button)findViewById(R.id.loginButton);
        loginbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
