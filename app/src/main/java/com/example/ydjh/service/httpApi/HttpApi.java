package com.example.ydjh.service.httpApi;


import com.example.ydjh.vo.response.news.ListNewsVO;
import com.example.ydjh.vo.response.signin.SignInVO;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface HttpApi {
    @POST("toutiao/index")
    Observable<ListNewsVO> newsList(@Query("type") String type, @Query("key") String key);

    @GET("/demo/exists")
    Observable<SignInVO> user(@Query("name") String name,@Query("passwd") String passwd);

    @GET("/demo/add")
    Observable<SignInVO> addUser(@Query("name") String name , @Query("passwd") String passwd,@Query("email") String email);

}
