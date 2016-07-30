package com.testproject.amit.snapchat_new;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    protected TextView mSignUp;
    protected Button mLoginButton;
    protected EditText mUsername, mPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSignUp = (TextView) findViewById(R.id.SignUp);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            };

        });


        mLoginButton = (Button) findViewById(R.id.Login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String User,Pass;

                mUsername = (EditText) findViewById(R.id.Username);
                mPassword = (EditText) findViewById(R.id.Password);

                User = mUsername.getText().toString();
                Pass = mPassword.getText().toString();

                if(User.trim().equals("") || Pass.trim().equals(""))
                {
                    //Error Dialog
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setTitle("OOPS!");
                    alert.setMessage("Please Fill All The Credentials");
                    alert.setPositiveButton("OK",null);

                    AlertDialog ref = alert.create() ;
                    ref.show();
                }
                else
                {
                    //Login successfull
                }
            }
        });

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
