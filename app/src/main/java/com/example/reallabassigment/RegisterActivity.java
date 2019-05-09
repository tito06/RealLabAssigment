package com.example.reallabassigment;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edit_email;
    private  EditText edot_pass;

    private Button btn;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        edit_email = findViewById(R.id.email);
        final String email = edit_email.getText().toString().trim();
        edot_pass = findViewById(R.id.pass);
        final String pass = edot_pass.getText().toString().trim();

        btn = findViewById(R.id.btn_sub);
        btn.setOnClickListener(this);

    }

    private void registerUser(){
        String email = edit_email.getText().toString().trim();
        String pass = edot_pass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "email empty", Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "pass empty", Toast.LENGTH_LONG).show();
        }

        progressDialog.setMessage("Registering....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Seuccesfull", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == btn){
            registerUser();
        }

    }
}
