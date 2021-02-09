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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    TextInputEditText mName,mphone,memail,mpassword;
    Button msignIn,msignUp;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String UserID;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.edit_name);
        mphone = findViewById(R.id.edit_phone);
        memail = findViewById(R.id.edit_email);
        mpassword = findViewById(R.id.edit_password);
        msignIn = findViewById(R.id.signinbtn);
        msignUp = findViewById(R.id.signupbtn);
        mAuth = FirebaseAuth.getInstance();





        msignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,login.class));
            }
        });



        msignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(register.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();


                final String email = memail.getText().toString();
                final String name = mName.getText().toString();
                final String phone = mphone.getText().toString();
                String password = mpassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Can't leave the space empty");
                    return;
                }

                if(TextUtils.isEmpty(name)){
                    mName.setError("Can't leave the space empty");
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    mphone.setError("Can't leave the space empty");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Can't leave the space empty");
                    return;
                }

                if(password.length() < 6){
                    mpassword.setError("Password must be greater than 6 characters");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    firebaseUser = mAuth.getCurrentUser();
                                    UserID=mAuth.getUid();
                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(UserID);
                                    Map<String,Object> map= new HashMap<>();
                                    map.put("Name",name);
                                    map.put("Email",email);
                                    map.put("Phone Number",phone);
                                    map.put("id",UserID);
                                    databaseReference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                progressDialog.dismiss();
                                                Intent intent = new Intent(register.this, MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                            }

                                        }
                                    });
                                } else {
                                    // If sign in fails, display a message to the user.
                                    progressDialog.dismiss();
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });



            }
        });


    }
}