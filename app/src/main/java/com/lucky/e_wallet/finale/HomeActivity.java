package com.lucky.e_wallet.finale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mactionBarDrawerToggle;
    private NavigationView mNavigationView;
    private Toolbar mtoolbar;

    private ImageView profileImage;
    private TextView userName;
    private TextView userEmail;

    private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // User is not signed in, go to login activity
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mDrawerLayout = findViewById(R.id.drawer_layout);

        mactionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, mtoolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mactionBarDrawerToggle);

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.nav_home){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(id == R.id.nav_reports){
                    Intent intent = new Intent(getApplicationContext(), ReportsActivity.class);
                    startActivity(intent);

                }
                else if(id == R.id.nav_savings){
                    Intent intent = new Intent(getApplicationContext(), SavingsActivity.class);
                    startActivity(intent);

                }
                else if(id == R.id.nav_expenses){
                    Intent intent = new Intent(getApplicationContext(), ExpensesActivity.class);
                    startActivity(intent);

                }
                else if(id == R.id.nav_goals){
                    Intent intent = new Intent(getApplicationContext(), GoalsActivity.class);
                    startActivity(intent);

                }
                else if(id == R.id.nav_logout){
                    mAuth.signOut();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        //mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


        //});




        mDrawerLayout.addDrawerListener(mactionBarDrawerToggle);
        mactionBarDrawerToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mactionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}