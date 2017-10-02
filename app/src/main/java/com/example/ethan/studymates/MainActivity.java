package com.example.ethan.studymates;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private EditText mName;
    private EditText mPassword;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mButton = (Button) findViewById(R.id.button);
        mName = (EditText) findViewById(R.id.name);
        mPassword = (EditText) findViewById(R.id.password);

        mButton.setOnClickListener(this);


    }

    private void registerUser(){
        String name = mName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            // name is empty
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            // password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        mFirebaseAuth.createUserWithEmailAndPassword(name, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Done!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Didn't Work!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    @Override
    public void onClick(View view) {
        if(view == mButton){
            registerUser();
        }
    }
}
