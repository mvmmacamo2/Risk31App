package com.kaya.risk31app.Storage;

import com.kaya.risk31app.Models.Actions;
import com.kaya.risk31app.Service.ActionsResponse;
import com.kaya.risk31app.Service.RiskResponse;
import com.kaya.risk31app.Service.UserProfileService;
import com.kaya.risk31app.Service.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("registar")
    Call<AccessToken> registar(
            @Field("name") String Name,
            @Field("email") String Email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login")
    Call<AccessToken> login(
            @Field("email") String email, @Field("password") String password
    );

    @FormUrlEncoded
    @POST("get-my-actions-by-status")
    Call<Actions> getActionsByStatus (
            @Field("estado") String estado, @Field("id") int id
    );


    @GET("users")
    Call<UserResponse> getUsers();

    @GET("user")
    Call<UserProfileService> getUserProfile();

    @GET("get-riscos-avaliados")
    Call<RiskResponse> getRisks();

    @GET("get-my-actions/{id}")
    Call<ActionsResponse> getActions(@Path("id") int id);

    @FormUrlEncoded
    @POST("save-close-action")
    Call<Actions> saveCloseAction(@Field("id") int id, @Field("descricao") String descricao);

}
