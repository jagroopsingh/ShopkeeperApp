package com.apps.gill.shopkeeperapp.utils;

import android.content.Context;

import com.apps.gill.shopkeeperapp.models.GetCustomer.CustomerData;
import com.apps.gill.shopkeeperapp.models.RegisterCustomer.RegisterCustomer;
import com.apps.gill.shopkeeperapp.models.shopkeeper.ShopkeeperData;
import com.apps.gill.shopkeeperapp.sharedpreferences.Prefs;
import com.apps.gill.shopkeeperapp.sharedpreferences.SharedPreferencesName;

/**
 * Created by gill on 11-02-2016.
 */
public class CommonData {
   private static ShopkeeperData shopkeeperData;
    //private static RegisterCustomer registerCustomer=new RegisterCustomer();
    private static CustomerData customerData;

    public static CustomerData getCustomerData(Context context) {
        if (customerData == null) {
            customerData = Prefs.with(context).getObject(SharedPreferencesName.APP_CUSTOMER, CustomerData.class);
        }
        return customerData;
    }

    public static void setCustomerData(Context context,CustomerData Data) {
        customerData = Data;
        Prefs.with(context).save(SharedPreferencesName.APP_CUSTOMER, customerData);
    }

    public static ShopkeeperData getShopkeeperData(Context context) {
        if (shopkeeperData == null) {
            shopkeeperData = Prefs.with(context).getObject(SharedPreferencesName.APP_USER, ShopkeeperData.class);
        }

        return shopkeeperData;
    }

    public static void setShopkeeperData(Context context,ShopkeeperData Data) {
        shopkeeperData=Data;
        Prefs.with(context).save(SharedPreferencesName.APP_USER, shopkeeperData);
    }

    public static void clearAllAppData(Context context) {
        shopkeeperData=null;
        customerData=null;
        Prefs.with(context).removeAll();
    }
}
