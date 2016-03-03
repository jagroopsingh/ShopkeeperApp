package com.apps.gill.shopkeeperapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apps.gill.shopkeeperapp.R;
import com.apps.gill.shopkeeperapp.models.shopkeeper.ShopkeeperData;
import com.apps.gill.shopkeeperapp.retrofit.RestClient;
import com.apps.gill.shopkeeperapp.utils.AlertBox;
import com.apps.gill.shopkeeperapp.utils.CommonData;
import com.apps.gill.shopkeeperapp.utils.ProgressBar;
import com.apps.gill.shopkeeperapp.utils.RetrofitErrorHandler;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ShopkeeperLogin extends BaseActivity implements View.OnClickListener {
    Button signIn;
    TextView signUp;
    Intent intent;
    private EditText etEmail,etPassword;
    String email,password;
    String message=null;

    public void init()
    {
        signIn=(Button) findViewById(R.id.bt_signin);
        signUp=(TextView) findViewById(R.id.tv_signup);
        etEmail=(EditText) findViewById(R.id.login_email);
        etPassword=(EditText) findViewById(R.id.login_password);
    }

    public void getData()
    {
        email=etEmail.getText().toString();
        password=etPassword.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopkeeper_login);
        init();
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private boolean onValidate()
    {
        Boolean isValid = true ;
        if (!(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            AlertBox.alertDialogShow(ShopkeeperLogin.this, "enter valid email");
            return false ;
        }
        if(password.length()<6)
        {
            AlertBox.alertDialogShow(ShopkeeperLogin.this,"password length short");
            return false ;
        }
       return isValid ;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_signin:
                getData();
                if(onValidate())
                putData();
                break;
            case R.id.tv_signup:
                intent=new Intent(this,SignUp.class);
                startActivity(intent);
                finish();
                break;
        }

    }
    public void putData()
    {
        ProgressBar.showProgressDialog(ShopkeeperLogin.this,message);
        RestClient.getApiService().shopLogin(email, password, new Callback<ShopkeeperData>() {
            @Override
            public void success(ShopkeeperData shopkeeperData, Response response) {
                ProgressBar.dismissProgressDialog();
                CommonData.setShopkeeperData(getApplicationContext(), shopkeeperData);
                Log.v("success", shopkeeperData.getData().get(0).getEmail());
                intent=new Intent(getApplicationContext(),Customers.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("failure", error.getLocalizedMessage());
                ProgressBar.dismissProgressDialog();
                RetrofitErrorHandler.checkCode(ShopkeeperLogin.this, error);
            }
        });
    }
}
