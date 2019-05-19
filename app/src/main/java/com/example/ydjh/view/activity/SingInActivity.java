package com.example.ydjh.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ydjh.R;

public class SingInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singin);

        Intent intent = getIntent();
        String userNameMessage = intent.getStringExtra(NavigationActivity.USER_NAME_SINGIN);
        String userPasswdMessage = intent.getStringExtra(NavigationActivity.USER_PASSWD_SINGIN);

        TextView editTextUserName = findViewById(R.id.editTextUserName);
        TextView editTextPasswd = findViewById(R.id.editTextUserPasswd);
        editTextUserName.setText(userNameMessage);
        editTextPasswd.setText(userPasswdMessage);

    }
}
