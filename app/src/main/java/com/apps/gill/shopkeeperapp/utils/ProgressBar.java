package com.apps.gill.shopkeeperapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.apps.gill.shopkeeperapp.R;


/**
 * Created by gill on 16-02-2016.
 */
public class ProgressBar {
    private static ProgressDialog loading=null;
    public static void showProgressDialog(Context context, String message) {

            loading = new ProgressDialog(context);

        if (message == null) {
            loading.setMessage("Please wait...");
        } else {
            loading.setMessage(message);
        }

        //loading.setIndeterminate(true);
        loading.setCancelable(false);
        loading.show();
        loading.setContentView(R.layout.custom_progress);
    }

    public static void dismissProgressDialog() {
        if (loading != null && loading.isShowing())
            loading.dismiss();
    }
    }
