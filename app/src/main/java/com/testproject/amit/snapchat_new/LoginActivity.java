package com.testproject.amit.snapchat_new;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    protected TextView mSignUp;
    protected Button mLoginButton;
    protected EditText mEmail, mPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        //By BB
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LoginActivity.java", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("LoginActivity.java", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

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

                String Email,Pass;

                mEmail = (EditText) findViewById(R.id.Email);
                mPassword = (EditText) findViewById(R.id.Password);

                Email = mEmail.getText().toString();
                Pass = mPassword.getText().toString();

                if(Email.trim().equals("") || Pass.trim().equals(""))
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
                    // [START sign_in_with_email]
                    setProgressBarIndeterminateVisibility(true);

                    mAuth.signInWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("LoginActivity.java", "signInWithEmail:onComplete:" + task.isSuccessful());

                                    setProgressBarIndeterminateVisibility(false);


                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Log.w("LoginActivity.java", "signInWithEmail:failed", task.getException());
                                        Toast.makeText(LoginActivity.this,"Sign In Failed",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK);
                                        i.addFlags(i.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        Toast.makeText(LoginActivity.this,"Sign In Succesfull",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                    // [END sign_in_with_email]



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
