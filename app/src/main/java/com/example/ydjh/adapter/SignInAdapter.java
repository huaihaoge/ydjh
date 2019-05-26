package com.example.ydjh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ydjh.R;
import com.example.ydjh.view.activity.SignInActivity;
import com.example.ydjh.vo.response.news.ListNewsVO;
import com.example.ydjh.vo.response.signin.SignInVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInAdapter {

    private Context context;
    private String data;
    private NewsItemListAdapter.OnItemClickListener listener;


    public SignInAdapter(Context context, SignInVO response) {
        this.context = context;

        if (response != null && response.getRet() != null) {
            this.data = (String) response.getData();
        }

    }

    public String getData() {
        return data;
    }

}
