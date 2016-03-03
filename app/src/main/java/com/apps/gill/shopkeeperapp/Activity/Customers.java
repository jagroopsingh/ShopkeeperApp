package com.apps.gill.shopkeeperapp.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.apps.gill.shopkeeperapp.Adapters.RecyclerAdapter;
import com.apps.gill.shopkeeperapp.R;
import com.apps.gill.shopkeeperapp.models.GetCustomer.CustomerData;
import com.apps.gill.shopkeeperapp.models.GetCustomer.CustomerDetails;
import com.apps.gill.shopkeeperapp.retrofit.RestClient;
import com.apps.gill.shopkeeperapp.utils.CommonData;
import com.apps.gill.shopkeeperapp.utils.ProgressBar;
import com.apps.gill.shopkeeperapp.utils.RetrofitErrorHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Customers extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    Button clickToAdd;
    Button customerLogout;
    Intent intent;
    List<CustomerDetails> storeCustomers=new ArrayList<>();

    public void init()
    {
        clickToAdd = (Button) findViewById(R.id.bt_customer_add);
        customerLogout = (Button) findViewById(R.id.bt_logout);
        if(CommonData.getCustomerData(getApplicationContext())!=null && CommonData.getCustomerData(getApplicationContext()).getData().size()>0)
        {
            storeCustomers=CommonData.getCustomerData(getApplicationContext()).getData() ;
        }
        recyclerView = (RecyclerView) findViewById(R.id.json_recycler);
        adapter = new RecyclerAdapter(getApplicationContext(), storeCustomers);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        init();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        putData();
        clickToAdd.setOnClickListener(this);
        customerLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_customer_add:
                intent = new Intent(this, AddCustomers.class);
                startActivityForResult(intent, requestCodeValue);
                break;
            case R.id.bt_logout:
                displayLogOutAlert();
                break;
        }
    }

    public void putData() {

        String token = "bearer " + CommonData.getShopkeeperData(getApplicationContext()).getData().get(0).getAccessToken();
        if(CommonData.getCustomerData(getApplicationContext())==null||CommonData.getCustomerData(getApplicationContext()).getData().size()==0)
            ProgressBar.showProgressDialog(Customers.this,"Loading Customers");
        RestClient.getApiService().getCustomer(token, new Callback<CustomerData>() {
            @Override
            public void success(CustomerData customerData, Response response) {
                CommonData.setCustomerData(Customers.this,customerData);
                ProgressBar.dismissProgressDialog();
                adapter.resetData(customerData.getData());
            }

            @Override
            public void failure(RetrofitError error) {
                RetrofitErrorHandler.checkCode(Customers.this, error);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCodeValue) {
            if (resultCode == RESULT_OK) {
                putData();
            }
        }
    }

    private void displayLogOutAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Customers.this);
        alert.setTitle("Alert!!");
        alert.setMessage("Sure to Log Out");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                CommonData.clearAllAppData(getApplicationContext());
                Intent intent = new Intent(Customers.this, ShopkeeperLogin.class);
                startActivity(intent);
                finish();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        alert.show();
    }
}
