package com.example.reallabassigment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private TextView tv2;
    private EditText name;
    private EditText Address;
    private Button save;

    FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv2 = findViewById(R.id.tv2);
        name = findViewById(R.id.edit_addName);
        Address = findViewById(R.id.edit_address);
        save = findViewById(R.id.btn_save);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        save.setOnClickListener(this);
    }

    private void saveUserInformation(){

        String nam = name.getText().toString().trim();
        String add = Address.getText().toString().trim();

        UserInformation userInformation = new UserInformation(nam , add);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this, "Information Saved..", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {

        if (view == tv2){
            firebaseAuth.signOut();
            finish();
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
        }

        if (view == save){
            saveUserInformation();
        }

    }
}
