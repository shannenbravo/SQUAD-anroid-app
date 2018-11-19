package project1.mobile.cs.fsu.edu.squad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    String cPassWord;
    double lon;
    double lat;


    public double getRandomNumberLat(){
        Random r = new Random();
        double randomLat = -80 + r.nextDouble() * (80 - (-80));
        return randomLat;
    }

    public double getRandomNumberLon(){
        Random r = new Random();
        double randomLon = -80 + r.nextDouble() * (80 - (-80));
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
        cPassword = (EditText)findViewById(R.id.cPassword);

        mAuth = FirebaseAuth.getInstance();

        reggiNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uemail = email.getText().toString().trim();
                uName = name.getText().toString().trim();
                uPassword = password.getText().toString().trim();
                cPassWord = cPassword.getText().toString().trim();
                if(uemail.matches("") || uName.matches("") || uPassword.matches("") || cPassWord.matches("")){

                    showAlert("Incomplete Form");

                    if(uemail.matches(""))
                        email.setError("Incomplete");
                    if(uName.matches(""))
                        name.setError("Incomplete");

                    if(uPassword.matches(""))
                        password.setError("Incomplete");

                    if(cPassWord.matches(""))
                        cPassword.setError("Incomplete");

                }else {

                    if(!uPassword.matches(cPassWord)){
                        showAlert("password des not match");
                        cPassword.setError("Password does not match");
                    }else {
                        sendData();
                    }

//                    sendData();
                }

            }
        });


    }

    public void sendData(){
        //TODO checks for the input

//        uemail = email.getText().toString().trim();
//        uName = name.getText().toString().trim();
//        uPassword = password.getText().toString().trim();

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
                            Intent intent = new Intent(RegisterUser.this, GridMenu.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(RegisterUser.this, "Create user failed.",
//////                                    Toast.LENGTH_SHORT).show();
                            showAlert(task.getException().toString());


                        }
                    }
                });

    }


    public void showAlert(String errMessage){
        AlertDialog.Builder formNotComplete = new AlertDialog.Builder(RegisterUser.this);
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