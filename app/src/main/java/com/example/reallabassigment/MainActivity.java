package com.example.reallabassigment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btn_register;
    private EditText edit_email;
    private EditText edit_pass;
    private Button btn_login;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();


        btn_register = findViewById(R.id.tv1);

        edit_email = findViewById(R.id.edit_email);
        edit_pass = findViewById(R.id.edit_pass);
        btn_login = findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));

        }

        btn_login.setOnClickListener(this);
    }

    private void userLogin(){

        String email = edit_email.getText().toString().trim();
        String password = edit_pass.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(MainActivity.this, "email empty", Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "password empty", Toast.LENGTH_LONG).show();
        }
        progressDialog.setMessage("Loging in..");
        progressDialog.show();



        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), Profile.class));
                        }
                    }
                });


    }

    @Override
    public void onClick(View view) {

    if (view == btn_register){

        Intent intent =new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    if (view == btn_login){
        userLogin();
    }
    }
}
