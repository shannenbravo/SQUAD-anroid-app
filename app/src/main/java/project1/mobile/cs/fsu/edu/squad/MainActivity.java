package project1.mobile.cs.fsu.edu.squad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    Button loginbutt;
    Button reggi;
    private FirebaseAuth mAuth;
    EditText mEmail;
    EditText mPassword;
    String email;
    String password;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText) findViewById(R.id.editEmail);
        mPassword = (EditText) findViewById(R.id.editPassword);

        loginbutt = (Button) findViewById(R.id.loginButton);
        reggi = (Button) findViewById(R.id.reggi);

        loginbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                if(email.matches("") || password.matches("")){
                    showAlert("Incomplete form");

                    if(email.matches(""))
                        mEmail.setError("Incomplete");
                    if (password.matches(""))
                        mPassword.setError("Incomplete");
                }else {

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        intent = new Intent(MainActivity.this, GridMenu.class);
                                        startActivity(intent);
                                    } else {
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        showAlert("Authentication failed");
                                    }
                                }
                            });

                }
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "signInWithEmail:success");
//                                    FirebaseUser currentUser = mAuth.getCurrentUser();
//                                    intent = new Intent(MainActivity.this, GridMenu.class);
//                                    startActivity(intent);
//                                } else {
//                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
////                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                                    showAlert("Authentication failed");
//                                }
//                            }
//                        });
            }
        });

        reggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);
            }
        });
    }

    public void showAlert(String errMessage){
        AlertDialog.Builder formNotComplete = new AlertDialog.Builder(MainActivity.this);
        formNotComplete.setMessage(errMessage).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert = formNotComplete.create();
        alert.setTitle("Alert!");
        alert.show();
    }
}