package project1.mobile.cs.fsu.edu.squad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText password;
    EditText cPassword;
    Button reggiNow;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("Squad");
//        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        reggiNow= (Button)findViewById(R.id.registerButt);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.editPassword);
        reggiNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });


    }

    public void sendData(){
        //TODO checks for the input
        DatabaseReference usersRef = ref.child("users");
        String uemail = email.getText().toString().trim();
        String uName = name.getText().toString().trim();
        String uPassword = password.getText().toString().trim();
        usersRef.child(uemail).setValue(new SquadUser(uemail,uemail,uPassword));

    }
}
