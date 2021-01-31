package com.example.startupclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    String UserID;
    TextView fname;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        viewPager2 = findViewById(R.id.main_viewpager);
        tabLayout = findViewById(R.id.main_tab);
        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events");



        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView = findViewById(R.id.nav_menu);
        fname = navigationView.getHeaderView(0).findViewById(R.id.name_text);


        viewPager2.setAdapter(new PagerAdapter(this));
        synctab();
        logout();
        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.courses:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Course_fragment()).commit();
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new profile_fragment()).commit();
                        break;

                    case R.id.logout:
                        mAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this,login.class));
                        finish();
                        // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new logout_fragment()).commit();
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });*/

       /* UserID=mAuth.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(UserID).child("Name");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                fname.setText(data);
                //Userdata data = snapshot.getValue(Userdata.class);
                //fname.setText(data.getName());
                Log.d("Email","the key is "+ data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/







    }

    private void synctab() {
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 :
                        tab.setText("Previous");
                        break;
                    case 1 :
                        tab.setText("Ongoing");
                        break;
                    case 2 :
                        tab.setText("Upcoming");
                        break;
                }

            }
        });tabLayoutMediator.attach();
    }

    private void logout() {
        UserID=mAuth.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(UserID).child("Name");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.getValue(String.class);
                fname.setText(data);
                //Userdata data = snapshot.getValue(Userdata.class);
                //fname.setText(data.getName());
                Log.d("Email","the key is "+ data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}