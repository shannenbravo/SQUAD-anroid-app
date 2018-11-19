package project1.mobile.cs.fsu.edu.squad;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class showSquad extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String test;
    FirebaseDatabase database1;
    DatabaseReference ref;
    Button goBack;
    ListView mySquad;
    ArrayList<String> info = new ArrayList<String>();
    SquadUser user;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_squad);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //database1 = FirebaseDatabase.getInstance();

        goBack = findViewById(R.id.returnBack);
        mySquad = (ListView) findViewById(R.id.displayFriends);

        //FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("friends")
        //ref = database1.getReference("Users").child(mUser.getUid());

        test = mUser.getUid();
        //info.add(mUser.getUid());


        FirebaseDatabase.getInstance().getReference().child("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            showAlert("first part passed" + i);
                            i = i + 1;


                            SquadUser user = snapshot.getValue(SquadUser.class);
                            String test1 = String.valueOf(user.getName());
                            String test = String.valueOf(user.getEmail());
                            //Can't add to array because of async task, could use callbacks
                            info.add(test1 + " - " + test);

                            //SquadUser user = snapshot.getValue(SquadUser.class);
                            //LatLng user1 = new LatLng(user.getuLat(), user.getuLonge());
                            //info.add(user.getEmail());
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        showAlert("first part failed");
                    }
                });


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, R.id.label,info);
        mySquad.setAdapter(adapter);

        goBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(showSquad.this,GridMenu.class);
                startActivity(intent);
            }
        });
    }


    public void showAlert(String errMessage){
        AlertDialog.Builder formNotComplete = new AlertDialog.Builder(showSquad.this);
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
