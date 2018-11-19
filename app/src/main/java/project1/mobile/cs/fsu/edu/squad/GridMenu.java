package project1.mobile.cs.fsu.edu.squad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GridMenu extends AppCompatActivity {
//
    private Intent intent;
    private Intent gIntent;
    private LinearLayout myLocation;
    private LinearLayout otherLoacation;
    private LinearLayout growSquad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_menu);
        myLocation = (LinearLayout) findViewById(R.id.myLocation);
        otherLoacation = (LinearLayout)findViewById(R.id.findFriends);
        growSquad = (LinearLayout) findViewById(R.id.growSquad);

        //able to access current user's information
        /*FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser thisUser = mAuth.getCurrentUser();
        Toast.makeText(GridMenu.this, thisUser.getUid(), Toast.LENGTH_SHORT).show();*/

        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GridMenu.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        growSquad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GridMenu.this,growSquad.class);
                startActivity(intent);
            }
        });

        otherLoacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GridMenu.this,MapsActivity2.class);
                startActivity(intent);
            }
        });
    }
}
