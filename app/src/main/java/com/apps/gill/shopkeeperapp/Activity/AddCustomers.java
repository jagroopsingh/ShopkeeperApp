package com.apps.gill.shopkeeperapp.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apps.gill.shopkeeperapp.R;
import com.apps.gill.shopkeeperapp.models.RegisterCustomer.RegisterCustomer;
import com.apps.gill.shopkeeperapp.retrofit.RestClient;
import com.apps.gill.shopkeeperapp.utils.AlertBox;
import com.apps.gill.shopkeeperapp.utils.CommonData;
import com.apps.gill.shopkeeperapp.utils.ProgressBar;
import com.apps.gill.shopkeeperapp.utils.RetrofitErrorHandler;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddCustomers extends BaseActivity implements View.OnClickListener {

    Button btAddCustomer;
    Button btToolbar;
    EditText etCustomerName,etCustomerEmail,etCustomerMobile,etCustomerAddress;
    String customerName,customerEmail,customerMobile,customerAddress,message=null;

    public void init()
    {
        btAddCustomer=(Button) findViewById(R.id.bt_add);
        btToolbar=(Button) findViewById(R.id.bt_toolbar);
        etCustomerName=(EditText) findViewById(R.id.et_customer_name);
        etCustomerEmail=(EditText) findViewById(R.id.et_customer_email);
        etCustomerMobile=(EditText) findViewById(R.id.et_customer_mobile);
        etCustomerAddress=(EditText) findViewById(R.id.et_customer_address);
    }

    public void get()
    {
        customerName=etCustomerName.getText().toString();
        customerEmail=etCustomerEmail.getText().toString();
        customerMobile=etCustomerMobile.getText().toString();
        customerAddress=etCustomerAddress.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customers);
       init();
        btAddCustomer.setOnClickListener(this);
        btToolbar.setOnClickListener(this);
    }

    private boolean onValidate()
    {
        boolean isValid=true;
        if(customerName.length()==0)
        {
            AlertBox.alertDialogShow(AddCustomers.this, "enter name");
            return false;
        }
        if (!(!TextUtils.isEmpty(customerEmail) && android.util.Patterns.EMAIL_ADDRESS.matcher(customerEmail).matches()))
        {
            AlertBox.alertDialogShow(AddCustomers.this, "enter valid email");
            return false;
        }
        if(customerMobile.length()<10)
        {
            AlertBox.alertDialogShow(AddCustomers.this, "Incorrect mobile number");
            return false;
        }
        if(customerAddress.length()==0)
        {
            AlertBox.alertDialogShow(AddCustomers.this, "enter address");
            return false;
        }
        return isValid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_add:
                get();
                if(onValidate())
                    putData();
                 break;
            case R.id.bt_toolbar:
                onBackPressed();
                finish();
                break;
        }
    }
    public void putData()
    {
        String accessToken="bearer "+CommonData.getShopkeeperData(AddCustomers.this).getData().get(0).getAccessToken();
        Log.v("accessToken", accessToken);
        ProgressBar.showProgressDialog(AddCustomers.this, message);
        RestClient.getApiService().customerRegister(accessToken,customerName, customerEmail, customerMobile, customerAddress, new Callback<RegisterCustomer>() {
            @Override
            public void success(RegisterCustomer registerCustomer, Response response) {
                AlertDialog alert = new AlertDialog.Builder(AddCustomers.this).create();
                ProgressBar.dismissProgressDialog();
                alert.setMessage("Customer is added");
                alert.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(AddCustomers.this, Customers.class);
                        setResult(RESULT_OK, intent1);
                        finish();
                    }
                });
                alert.show();

            }
            @Override
            public void failure(RetrofitError error) {
                ProgressBar.dismissProgressDialog();
                RetrofitErrorHandler.checkCode(AddCustomers.this, error);

            }
        });
    }
}
