package project1.mobile.cs.fsu.edu.squad;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String test;
    FirebaseDatabase database1;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        database1 = FirebaseDatabase.getInstance();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("friends")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            test = snapshot.getValue(String.class);
                            ref = database1.getReference("Users").child(test);

                            // Attach a listener to read the data at our posts reference
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    SquadUser post = dataSnapshot.getValue(SquadUser.class);
                                    LatLng user1 = new LatLng(post.getuLat(), post.getuLonge());
                                    mMap.addMarker(new MarkerOptions().position(user1).title(post.getName()));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    System.out.println("The read failed: " + databaseError.getCode());
                                }
                            });

                            /*SquadUser user = snapshot.getValue(SquadUser.class);
                            LatLng user1 = new LatLng(user.getuLat(), user.getuLonge());
                            mMap.addMarker(new MarkerOptions().position(user1).title(user.getName()));

                            test = dataSnapshot.child(emailTrim).getValue(String.class);
                            ref = database1.getReference("Users").child(mUser.getUid()).child("friends");

                            Map<String, Object> updates = new HashMap<String,Object>();
                            updates.put(emailTrim, test);
                            ref.updateChildren(updates);
                            */
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });

    }
}
