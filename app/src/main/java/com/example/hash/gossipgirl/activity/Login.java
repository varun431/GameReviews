package com.example.hash.gossipgirl.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hash.gossipgirl.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button login, createaccount;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        login = (Button) findViewById(R.id.login);
        if (login != null) {
            login.setOnClickListener(this);
        }
        createaccount = (Button) findViewById(R.id.createaccount);
        if (createaccount != null) {
            createaccount.setOnClickListener(this);
        }
//        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus) {
//                    email.setHint("");
//                }
//                else {
//                    email.setHint("Email Address");
//                }
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.createaccount:
                //Show registration screen for new users
                Intent i = new Intent(this, Register.class);
                startActivity(i);
//                Log.i("Account Created", "account created!!!!!!");

            case R.id.login:
                //If user has already created account then Mainactivity is shown
                email = (EditText)findViewById(R.id.email);
                password = (EditText)findViewById(R.id.password);
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                emailString = emailString.trim();
                passwordString = passwordString.trim();

                if(emailString.isEmpty() || passwordString.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    mFirebaseAuth.signInWithEmailAndPassword(emailString, passwordString)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle(R.string.login_error_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                }
        }
    }
}
