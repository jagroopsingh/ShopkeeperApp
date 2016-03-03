package com.apps.gill.shopkeeperapp.utils;

import android.app.Activity;
import android.util.Log;

import org.json.JSONObject;

import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;

public class RetrofitErrorHandler {


    private static String errorMessage = "";

    /**
     * @param context
     * @param error
     */
    public static void checkCode(final Activity context, RetrofitError error) {
        try {
            errorMessage = "";
            Log.v("error bundle", error.toString() + "");

            switch (error.getKind()) {
                case NETWORK:
                    errorMessage = "Failed to connect. Please check your internet connection.";

                    break;
                case CONVERSION:
                    errorMessage = "An error was procured while parsing.";
                    break;
                case HTTP: {
                    errorMessage = "Application server could not respond. Please try later.";

                    try {
                        String str = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                        JSONObject json = new JSONObject(str);
                        errorMessage = json.getString("message");

                    } catch (Exception e) {
                        errorMessage = "Something went wrong. Please try later.";
                    }
                }
                break;

                case UNEXPECTED:
                    errorMessage = "An unexpected error occurred. Please try later.";
                    break;
            }

            if (!errorMessage.isEmpty())
                AlertBox.alertDialogShow(context, errorMessage);


        } catch (Exception e) {
            AlertBox.alertDialogShow(context, "Something went wrong. Please try later");

        }
    }




}
