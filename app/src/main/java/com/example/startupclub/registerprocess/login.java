package com.example.startupclub.registerprocess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.startupclub.bottomnavigation.MainActivity;
import com.example.startupclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {


    TextInputEditText mUsername , mPassword ;
    Button mSignIn ,mSignUp;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mUsername = findViewById(R.id.edit_email);
        mPassword = findViewById(R.id.edit_password);
        mSignIn = findViewById(R.id.Sign_in);
        mSignUp = findViewById(R.id.Sign_up);
        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,register.class));
            }
        });



        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(login.this);
                progressDialog.setMessage("Please Wait ...");
                progressDialog.show();


                String Email = mUsername.getText().toString();
                String Password = mPassword.getText().toString();
                if(TextUtils.isEmpty(Email)){
                    mUsername.setError("Can't leave the space empty");
                    return;
                }

                if(TextUtils.isEmpty(Password)){
                    mPassword.setError("Can't leave the space empty");
                    return;
                }

                if(Password.length() < 6) {
                    mPassword.setError("Password must be greater than 6 characters");
                    return;
                }

                mAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("SignIn", "signInWithEmail:success");
                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
                                    databaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            progressDialog.dismiss();

                                            Intent intent = new Intent(login.this,MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            Toast.makeText(login.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                                            Log.d("SignIn", "signInWithEmailDataAdded:success");
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    progressDialog.dismiss();
                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });


    }
}