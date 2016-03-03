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

public class SignUp extends BaseActivity implements View.OnClickListener {

    Button signUp;
    TextView signIn;
    Intent intent;
    private EditText etName,etEmail,etMobile,etPassword;
    String name,email,mobile,password,message=null;

    public void init()
    {
        signUp=(Button) findViewById(R.id.bt_signup);
        signIn=(TextView) findViewById(R.id.tv_signin);
        etName=(EditText) findViewById(R.id.et_name);
        etEmail=(EditText) findViewById(R.id.et_email);
        etMobile=(EditText) findViewById(R.id.et_mobile);
        etPassword=(EditText) findViewById(R.id.et_password);
    }

    public void get()
    {
        name=etName.getText().toString();
        email=etEmail.getText().toString();
        mobile=etMobile.getText().toString();
        password=etPassword.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);
    }

    private boolean onValidate()
    {
        boolean isValid=true;
        if(name.length()==0)
        {
            AlertBox.alertDialogShow(SignUp.this, "enter name");
            return false;
        }
         if (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            AlertBox.alertDialogShow(SignUp.this, "enter valid email");
            return false;
        }
         if(mobile.length()<10)
        {
            AlertBox.alertDialogShow(SignUp.this, "Incorrect mobile number");
            return false;
        }
         if(password.length()<6)
        {
            AlertBox.alertDialogShow(SignUp.this, "enter valid password");
            return false;
        }
        return isValid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_signup:
                get();
                if(onValidate())
                    putData();
                break;
            case R.id.tv_signin:
                intent=new Intent(this,ShopkeeperLogin.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    public void putData()
    {
        ProgressBar.showProgressDialog(SignUp.this, message);
        RestClient.getApiService().login(name ,email ,mobile,password, new Callback<ShopkeeperData>() {
            @Override
            public void success(ShopkeeperData shopkeeperData, Response response) {
                ProgressBar.dismissProgressDialog();
                CommonData.setShopkeeperData(getApplicationContext(), shopkeeperData);
                Log.v("success ", shopkeeperData.getData().get(0).getAccessToken());
                intent=new Intent(getApplicationContext(),Customers.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("failure",error.getLocalizedMessage());
                ProgressBar.dismissProgressDialog();
                RetrofitErrorHandler.checkCode(SignUp.this,error);
            }
        });
    }
}
