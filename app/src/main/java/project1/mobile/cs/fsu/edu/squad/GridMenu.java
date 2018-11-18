package project1.mobile.cs.fsu.edu.squad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class GridMenu extends AppCompatActivity {
//
    private Intent intent;
    private LinearLayout myLocation;
    private LinearLayout otherLoacation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_menu);
        myLocation = (LinearLayout) findViewById(R.id.myLocation);
        otherLoacation = (LinearLayout)findViewById(R.id.findFriends);
        myLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(GridMenu.this,MapsActivity.class);
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
