package com.example.startupclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Toolbar toolbar;
        BottomNavigationView bottomNavigationView;



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.custom_toolbar);


        // selecting the menu and highlighting the menu in the bottom navigation bar
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);


        // switching activity using bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.home:
                        intent = new Intent(TeamActivity.this,MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);// to avoid the transition happening between the activity
                        break;
                    case R.id.event:
                        intent = new Intent(TeamActivity.this,EventsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);// to avoid the transition happening between the activity
                        break;
                    case R.id.profile:
                        intent = new Intent(TeamActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);// to avoid the transition happening between the activity
                        break;
                    case R.id.teams:
                        break;
                    case R.id.logout:
                        intent = new Intent(TeamActivity.this,LogoutActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);// to avoid the transition happening between the activity
                        break;

                }


                return false;
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);// to avoid the transition happening between the activity
    }
}