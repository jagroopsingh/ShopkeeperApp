package com.apps.gill.shopkeeperapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.apps.gill.shopkeeperapp.R;
import com.apps.gill.shopkeeperapp.utils.CommonData;

public class Splash extends BaseActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(CommonData.getShopkeeperData(getApplicationContext())==null) {
                    mainIntent = new Intent(getApplicationContext(), ShopkeeperLogin.class);
                }
                else {
                    mainIntent = new Intent(getApplicationContext(), Customers.class);
                }
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
