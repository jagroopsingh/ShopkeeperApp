package com.apps.gill.shopkeeperapp.retrofit;

import com.apps.gill.shopkeeperapp.config.Config;
import com.apps.gill.shopkeeperapp.retrofit.ApiService;

import retrofit.RestAdapter;



/**
 * Rest client
 */
public class RestClient {
    private static ApiService apiService = null;

    public static ApiService getApiService() {
        if (apiService == null) {

           // For object response which is default
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Config.getBaseURL()).build();

//            //For type string response
//            RestAdapter restAdapter = new RestAdapter.Builder()
//                    .setEndpoint(Config.getBaseURL())
//                    .setConverter(new StringConverter())    //converter for response type
//                    .build();


            apiService = restAdapter.create(ApiService.class);
        }
        return apiService;
    }


}
