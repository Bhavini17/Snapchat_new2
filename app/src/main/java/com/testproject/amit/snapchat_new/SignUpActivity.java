package com.testproject.amit.snapchat_new;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    protected EditText mUsername,mPassword,mEmail;
    protected Button mSignUp;
    private String mUrl="https://snapchat-38018.firebaseio.com/";
    private Firebase mRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //By BB
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in


                        // TODO handle the callback
                    Log.d("SignUpActivity.java", "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(SignUpActivity.this, "Callback  signed in", Toast.LENGTH_LONG).show();
                } else {
                    // User is signed out
                    Log.d("SignUpActivity.java", "onAuthStateChanged:signed_out");
                    Toast.makeText(SignUpActivity.this, "callback signed out", Toast.LENGTH_LONG).show();
                }
                // ...
            }
        };
//        getActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        mSignUp = (Button) findViewById(R.id.SignUp);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User,Pass,Email;

                mUsername = (EditText) findViewById(R.id.Name_SignUp);
                mPassword = (EditText) findViewById(R.id.Password_SignUp);
                mEmail = (EditText) findViewById(R.id.Email_SignUp);

                User = mUsername.getText().toString();
                Pass = mPassword.getText().toString();
                Email = mEmail.getText().toString();

                if(User.trim().equals("") || Pass.trim().equals("") || Email.trim().equals(""))
                {
                    //Error Dialog
                    AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
                    alert.setTitle("OOPS!");
                    alert.setMessage("Please Fill All The Credentials");
                    alert.setPositiveButton("OK",null);

                    AlertDialog ref = alert.create() ;
                    ref.show();
                }
                else
                {
                   /* mRef = new Firebase(mUrl);
                    SignUp_firebase obj = new SignUp_firebase( User, Pass, Email);

                    mRef.push().setValue(obj); */



                    mAuth.createUserWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("SignUpActivity.java", "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {

                                        Log.d("SignUpActivity.java","Unsuccessful signup");
                                        Toast.makeText(SignUpActivity.this, "Unsuccessfull SignUp",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {

                                        Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                                        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK);
                                        i.addFlags(i.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);

                                        Log.d("SignUpActivity.java","Successful signup");
                                        Toast.makeText(SignUpActivity.this, "Successfully Sign Up", Toast.LENGTH_LONG).show();
                                        finish();

                                    }

                                }
                            });

                    //Clearing the rest of the text.
                    mUsername.setText("");
                    mPassword.setText("");
                    mEmail.setText("");
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
