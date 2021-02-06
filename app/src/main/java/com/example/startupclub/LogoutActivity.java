package com.example.startupclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.startupclub.registerprocess.login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    FirebaseAuth mAuth;
    MaterialButton btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);


        mAuth = FirebaseAuth.getInstance();
        btn_logout = findViewById(R.id.btn_logout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.custom_toolbar);

        // selecting the menu and highlighting the menu in the bottom navigation bar
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        // switching activity using bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(LogoutActivity.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);// to avoid the transition happening between the activity
                        break;
                    case R.id.event:
                        intent = new Intent(LogoutActivity.this,EventsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);// to avoid the transition happening between the activity
                        break;
                    case R.id.profile:
                        intent = new Intent(LogoutActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);// to avoid the transition happening between the activity
                        break;
                    case R.id.teams:
                        intent = new Intent(LogoutActivity.this,TeamActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);// to avoid the transition happening between the activity
                        break;
                    case R.id.logout:
                        break;

                }


                return false;
            }
        });

        // button for logout
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);// to avoid the transition happening between the activity
    }

    // function for logout
    private void logout() {
        mAuth.getInstance().signOut();
        startActivity(new Intent(LogoutActivity.this, login.class));
        finish();
        overridePendingTransition(0,0);// to avoid the transition happening between the activity
    }


}