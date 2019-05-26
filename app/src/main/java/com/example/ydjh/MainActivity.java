package com.example.ydjh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ydjh.adapter.NewsItemListAdapter;
import com.example.ydjh.config.ApiConfig;
import com.example.ydjh.service.httpApi.HttpApi;
import com.example.ydjh.service.httpProvider.HttpProvider;
import com.example.ydjh.view.activity.NavigationActivity;
import com.example.ydjh.view.activity.NewsDetailsActivity;
import com.example.ydjh.vo.request.news.ListNewsResVO;
import com.example.ydjh.vo.response.news.ListNewsVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;


    private NewsItemListAdapter newsItemListAdapter;
    private ListNewsVO listNewsVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        initView();

        ListNewsResVO resVO = new ListNewsResVO();

        Intent intent = getIntent();
        String message = intent.getStringExtra(NavigationActivity.MESSAGE);
        String url = ApiConfig.BASE_URL;

        if (message != null) {
            if (message.equals("FISH")) {
              getFishsList(url);
            } else if (message.equals("NEWS")) {
                getNewsList(url);
            } else {

            }
        }
    }

    private void initData() {
        listNewsVO = new ListNewsVO();
    }

    private void initView() {
        rvNews.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initListener() {
        if (newsItemListAdapter != null) {
            newsItemListAdapter.setListener(new NewsItemListAdapter.OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    String detailsUrl = listNewsVO.getResult().getData().get(position).getUrl();
                    startActivity(new Intent(MainActivity.this, NewsDetailsActivity.class)
                            .putExtra("url", detailsUrl));
                }
            });
        }
    }

    private void getNewsList(String url) {
        HttpApi httpApi = HttpProvider.http(url).create(HttpApi.class);
        httpApi.newsLists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListNewsVO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListNewsVO resp) {
                        listNewsVO = resp;
                        newsItemListAdapter = new NewsItemListAdapter(MainActivity.this, resp);
                        rvNews.setAdapter(newsItemListAdapter);
                        initListener();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getFishsList(String url) {
        HttpApi httpApi = HttpProvider.http(url).create(HttpApi.class);
        httpApi.newsFish()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListNewsVO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListNewsVO resp) {
                        listNewsVO = resp;
                        newsItemListAdapter = new NewsItemListAdapter(MainActivity.this, resp);
                        rvNews.setAdapter(newsItemListAdapter);
                        initListener();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
