package project1.mobile.cs.fsu.edu.squad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class growSquad extends AppCompatActivity{

    Button addFriend;
    EditText findByEmail;
    public String TAG;
    String test;
    DatabaseReference eRef;
    DatabaseReference ref;
    FirebaseDatabase database1;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grow_squad);

        addFriend = (Button) findViewById(R.id.add);
        findByEmail = (EditText) findViewById(R.id.findEmail);

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = findByEmail.getText().toString();

                mAuth = FirebaseAuth.getInstance();
                mUser = mAuth.getCurrentUser();

                //FirebaseDatabase database = FirebaseDatabase.getInstance();
                database1 = FirebaseDatabase.getInstance();

                final String emailTrim = email.replaceAll(".com", "");

                eRef = database1.getReference("EmailToUID");


                eRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        test = dataSnapshot.child(emailTrim).getValue(String.class);
                        ref = database1.getReference("Users").child(mUser.getUid()).child("friends");

                        Map<String, Object> updates = new HashMap<String,Object>();
                        updates.put(emailTrim, test);
                        ref.updateChildren(updates);

                        Intent intent = new Intent(growSquad.this,GridMenu.class);
                        startActivity(intent);
                        //showAlert("Email found: " + test);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                        showAlert("Email not found");
                    }
                });


            }
        });
    }

    public void showAlert(String errMessage){
        AlertDialog.Builder formNotComplete = new AlertDialog.Builder(growSquad.this);
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
