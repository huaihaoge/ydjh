package com.example.ydjh.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ydjh.R;
import com.example.ydjh.adapter.SignInAdapter;
import com.example.ydjh.config.ApiConfig;
import com.example.ydjh.service.httpApi.HttpApi;
import com.example.ydjh.service.httpProvider.HttpProvider;
import com.example.ydjh.vo.request.signin.SignInResVO;
import com.example.ydjh.vo.response.signin.SignInVO;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInActivity extends AppCompatActivity {

    private SignInAdapter signInAdapter;
    private SignInVO signInVO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        findViewById(R.id.singInButton).setOnClickListener(onClickListener);
        findViewById(R.id.singUpButton).setOnClickListener(onClickListenerSignUp);

//        SignInResVO signInResVO;
//
//        Intent intent = getIntent();
//        String userNameMessage = intent.getStringExtra(NavigationActivity.USER_NAME_SINGIN);
//        String userPasswdMessage = intent.getStringExtra(NavigationActivity.USER_PASSWD_SINGIN);
//
//        TextView editTextUserName = findViewById(R.id.editTextUserName);
//        TextView editTextPasswd = findViewById(R.id.editTextUserPasswd);
//        editTextUserName.setText(userNameMessage);
//        editTextPasswd.setText(userPasswdMessage);

    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v){

            TextView editTextUserName = findViewById(R.id.editTextUserName);
            TextView editTextPasswd = findViewById(R.id.editTextUserPasswd);

            SignInResVO signInResVO = new SignInResVO();
            signInResVO.setName(editTextUserName.getText().toString());
            signInResVO.setPasswd(editTextPasswd.getText().toString());

            if((signInResVO.getName().length()!=0)&&(signInResVO.getPasswd().length()!=0)){

                HttpApi httpApi = HttpProvider.http(ApiConfig.SIGN_IN_URL).create(HttpApi.class);
                httpApi.user(signInResVO.getName(),signInResVO.getPasswd())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SignInVO>() {
                                       @Override
                                       public void onSubscribe(Disposable d) {

                                       }

                                       @Override
                                       public void onNext(SignInVO resp) {
                                           signInVO = resp;
                                           signInAdapter = new SignInAdapter(SignInActivity.this,resp);
                                           Toast.makeText(SignInActivity.this,signInAdapter.getData(),Toast.LENGTH_LONG).show();
                                       }

                                       @Override
                                       public void onError(Throwable e) {

                                       }

                                       @Override
                                       public void onComplete() {

                                       }
                                   }

                        );
            }else {
                Toast.makeText(SignInActivity.this,"输入完整的用户名或密码",Toast.LENGTH_LONG).show();
            }



//            Toast.makeText(SignInActivity.this,"Button点击事件1",Toast.LENGTH_LONG).show();
        }
    };
    private View.OnClickListener onClickListenerSignUp=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(SignInActivity.this,SignUpActivity.class);
            startActivity(intent);
        }
    };

}
