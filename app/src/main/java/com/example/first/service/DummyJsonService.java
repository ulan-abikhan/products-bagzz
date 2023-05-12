package com.example.first.service;

import com.example.first.LoginModel;
import com.example.first.model.Product;
import com.example.first.model.Todo;
import com.example.first.model.User;
import com.example.first.repository.TodoRepository;
import com.example.first.response.ProductsResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DummyJsonService {

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int id);

    @GET("todos/{id}")
    Call<JSONObject> getTodo(@Path("id") int id );

    @GET("products/category/{category}")
    Call<ProductsResponse> getProducts(@Path("category") String category);

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id);

    @GET("todos")
    Call<TodoRepository> getTodos(@Header("Authorization") String token, @Query("limit") int limit);

    @POST("auth/login")
    Call<LoginModel> login(@Body Map<String, String> body);


}
