package com.walidhelaoui.gomycode;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String ServerAddress = "http://192.168.0.100/";
    private static final String TAG = "LoginActivity";
    EditText login_edt,password_edt;
    Button loginBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_edt = findViewById(R.id.edtUsername);
        password_edt = findViewById(R.id.edtPassword);
        loginBtn = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startSignIn();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void startSignIn(){
         String login = login_edt.getText().toString().trim();
         String password = password_edt.getText().toString().trim();

         mAuth.signInWithEmailAndPassword(login,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     Toast.makeText(LoginActivity.this,"sign in",Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(LoginActivity.this,MainActivity.class));
                     finish();
                 }else {
                     Toast.makeText(LoginActivity.this,"sign in problem",Toast.LENGTH_SHORT).show();
                 }
             }
         });
    }
}
