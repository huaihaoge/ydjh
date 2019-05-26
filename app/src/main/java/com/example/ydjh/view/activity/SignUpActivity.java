package com.example.ydjh.view.activity;

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
import com.example.ydjh.vo.request.signin.SignUpResVO;
import com.example.ydjh.vo.response.signin.SignInVO;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignUpActivity extends AppCompatActivity {


    private SignInAdapter signInAdapter;
    private SignInVO signInVO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        findViewById(R.id.SignUp).setOnClickListener(onClickListener);

//        TextView textView = findViewById(R.id.editTextUserEmailSingUp);
//        textView.setText("1231323");
//        Intent intent = getIntent();
//        String userNameMessage = intent.getStringExtra(NavigationActivity.USER_NAME_SINGIN);
//        String userPasswdMessage = intent.getStringExtra(NavigationActivity.USER_PASSWD_SINGIN);
//
//        TextView editTextUserName = findViewById(R.id.editTextUserName);
//        TextView editTextPasswd = findViewById(R.id.editTextUserPasswd);
//        editTextUserName.setText(userNameMessage);
//        editTextPasswd.setText(userPasswdMessage);

    }
    private View.OnClickListener onClickListener=new View.OnClickListener(){
        public void onClick(View v){

            TextView editTextUserName = findViewById(R.id.editTextUserNameSignUp);
            TextView editTextPasswd = findViewById(R.id.editTextUserPasswdSignUp);
            TextView editTextEamil = findViewById(R.id.editTextUserEmailSignUp);

            SignUpResVO signUpResVO = new SignUpResVO();
            signUpResVO.setName(editTextUserName.getText().toString());
            signUpResVO.setPasswd(editTextPasswd.getText().toString());
            signUpResVO.setEmail(editTextEamil.getText().toString());

            if((signUpResVO.getName().length()!=0)&&(signUpResVO.getPasswd().length()!=0)&&(signUpResVO.getEmail().length()!=0)){

                HttpApi httpApi = HttpProvider.http(ApiConfig.SIGN_IN_URL).create(HttpApi.class);
                httpApi.addUser(signUpResVO.getName(),signUpResVO.getPasswd(),signUpResVO.getEmail())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SignInVO>() {
                                       @Override
                                       public void onSubscribe(Disposable d) {

                                       }

                                       @Override
                                       public void onNext(SignInVO resp) {
                                           signInVO = resp;
                                           signInAdapter = new SignInAdapter(SignUpActivity.this,resp);
                                           Toast.makeText(SignUpActivity.this,signInAdapter.getData(),Toast.LENGTH_LONG).show();
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
                Toast.makeText(SignUpActivity.this,"请输入完整的注册信息",Toast.LENGTH_LONG).show();
            }



//            Toast.makeText(SignInActivity.this,"Button点击事件1",Toast.LENGTH_LONG).show();
        }
    };
}
