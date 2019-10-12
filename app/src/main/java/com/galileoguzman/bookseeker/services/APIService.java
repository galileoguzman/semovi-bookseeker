package com.galileoguzman.bookseeker.services;

import com.galileoguzman.bookseeker.models.ApiToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/api-token-auth/")
    @FormUrlEncoded
    Call<ApiToken> doLogin(@Field("username") String email,
                           @Field("password") String password);
}
