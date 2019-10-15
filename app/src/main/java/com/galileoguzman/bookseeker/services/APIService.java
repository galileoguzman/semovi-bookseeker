package com.galileoguzman.bookseeker.services;

import com.galileoguzman.bookseeker.models.ApiToken;
import com.galileoguzman.bookseeker.models.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @POST("/api-token-auth/")
    @FormUrlEncoded
    Call<ApiToken> doLogin(@Field("username") String email,
                           @Field("password") String password);

    @GET("/api/v1/books/")
    Call<List<Book>> doGetAllBooks(@Header("Authorization") String token);
}
