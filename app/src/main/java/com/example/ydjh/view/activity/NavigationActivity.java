package com.example.ydjh.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.ydjh.MainActivity;
import com.example.ydjh.R;
import com.example.ydjh.bean.User;


public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static final String USER_NAME_SINGIN = "com.example.ydjh.username";
    public static final String USER_PASSWD_SINGIN = "com.example.ydjh.passwd";
    public static final String MESSAGE = "com.example.ydjh.NEWS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent intent = new Intent(this, MainActivity.class);
        Intent login = new Intent(this, LoginActivity.class);
        Intent singin = new Intent(this, SignInActivity.class);
        Intent singup = new Intent(this, SignUpActivity.class);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            intent.putExtra(MESSAGE, "FISH");
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
//            intent.putExtra(MESSAGE,"NEWS");
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            intent.putExtra(MESSAGE,"NEWS");
            startActivity(intent);
        } else if (id == R.id.nav_longin) {
            startActivity(singin);
        } else if (id == R.id.nav_singup) {
            startActivity(singup);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void singIn(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        User user = new User();
        user.setName(((EditText) findViewById(R.id.editTextUserName)).getText().toString());
        user.setPasswd(((EditText) findViewById(R.id.editTextUserPasswd)).getText().toString());

        intent.putExtra(USER_NAME_SINGIN, user.getName());
        intent.putExtra(USER_PASSWD_SINGIN, user.getPasswd());
        startActivity(intent);
    }

    public void singUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
//        User user = new User();
////        user.setName(((EditText) findViewById(R.id.editTextUserName)).getText().toString());
////        user.setPasswd(((EditText) findViewById(R.id.editTextUserPasswd)).getText().toString());
////
////        intent.putExtra(USER_NAME_SINGIN, user.getName());
////        intent.putExtra(USER_PASSWD_SINGIN,user.getPasswd());
        startActivity(intent);
    }

}
