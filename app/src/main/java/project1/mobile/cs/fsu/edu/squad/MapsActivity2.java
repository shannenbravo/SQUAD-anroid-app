package project1.mobile.cs.fsu.edu.squad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        FirebaseDatabase.getInstance().getReference().child("Users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            SquadUser user = snapshot.getValue(SquadUser.class);
                            LatLng user1 = new LatLng(user.getuLat(), user.getuLonge());
                            String test = String.valueOf(user.getuLat());
                            showAlert(test);
                            mMap.addMarker(new MarkerOptions().position(user1).title(user.getName()));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });

    }
    public void showAlert(String errMessage){
        AlertDialog.Builder formNotComplete = new AlertDialog.Builder(MapsActivity2.this);
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