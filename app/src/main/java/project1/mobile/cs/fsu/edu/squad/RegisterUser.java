package project1.mobile.cs.fsu.edu.squad;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class RegisterUser extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    EditText cPassword;
    Button reggiNow;
    FirebaseAuth mAuth;
    private String TAG;
    String uemail;
    String uName;
    String uPassword;
    float lon;
    float lat;


    public float getRandomNumberLat(){
        Random r = new Random();
        float randomLat = -80 + r.nextFloat() * (80 - (-80));
        return randomLat;
    }

    public float getRandomNumberLon(){
        Random r = new Random();
        float randomLon = -80 + r.nextFloat() * (80 - (-80));
        return randomLon;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        reggiNow= (Button)findViewById(R.id.registerButt);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.editPassword);

        mAuth = FirebaseAuth.getInstance();

        reggiNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });


    }

    public void sendData(){
        //TODO checks for the input

        uemail = email.getText().toString();
        uName = name.getText().toString();
        uPassword = password.getText().toString();

        lon = getRandomNumberLon();
        lat = getRandomNumberLat();

        mAuth.createUserWithEmailAndPassword(uemail, uPassword)
                .addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            final FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("Users");
                            FirebaseUser user = mAuth.getCurrentUser();
                            ref.child(user.getUid()).setValue(new SquadUser(uName,uemail,uPassword, lon, lat ));
                            Intent intent = new Intent(RegisterUser.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterUser.this, "Create user failed.",
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                });

    }
}