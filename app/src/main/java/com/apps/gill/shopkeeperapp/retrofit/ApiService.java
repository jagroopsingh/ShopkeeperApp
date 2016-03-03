package com.apps.gill.shopkeeperapp.retrofit;

import com.apps.gill.shopkeeperapp.models.GetCustomer.CustomerData;
import com.apps.gill.shopkeeperapp.models.RegisterCustomer.RegisterCustomer;
import com.apps.gill.shopkeeperapp.models.shopkeeper.ShopkeeperData;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 *Define all server calls here
 */
public interface ApiService {

//    /**
//     *
//     * @param accessToken
//     * @param deviceToken
//     * @param regType
//     * @param callback
//     */
//    @FormUrlEncoded
//    @POST("/login_through_accesstoken")
//    public void login(@Field("access_token") String accessToken, @Field("device_token") String deviceToken, @Field("registration_type") String regType, Callback<String> callback);


    /**
     *
     * @param name
     * @param email
     * @param mobile
     * @param password
     * @param callback
     */
    @FormUrlEncoded
    @POST("/api/admin/createSupplier")
    public void login(@Field("name") String name, @Field("email") String email,@Field("phoneNo") String mobile,@Field("password") String password, Callback<ShopkeeperData> callback);
   @FormUrlEncoded
    @POST("/api/admin/registerDriver")
    public void customerRegister(@Header("authorization") String accessToken,@Field("name") String name, @Field("email") String email,@Field("phoneNo") String mobile,@Field("address") String password,Callback<RegisterCustomer> customerCallback);
    @FormUrlEncoded
    @POST("/api/admin/supplierLogin")
    public void shopLogin(@Field("email") String email,@Field("password") String password,Callback<ShopkeeperData> callback);
    @GET("/api/admin/getAlldriver")
    public void getCustomer(@Header("authorization") String accessToken,Callback<CustomerData> callback);
}