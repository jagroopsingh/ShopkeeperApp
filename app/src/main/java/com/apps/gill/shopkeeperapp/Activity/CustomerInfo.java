package com.apps.gill.shopkeeperapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apps.gill.shopkeeperapp.R;
import com.apps.gill.shopkeeperapp.models.GetCustomer.CustomerDetails;

public class CustomerInfo extends BaseActivity implements View.OnClickListener {

    TextView tvCustomerName,tvCustomerPhone,tvCustomerEmail,tvCustomerAddress;
    CustomerDetails customerDetails;
    Button toolbarButton;
    public void init()
    {
        tvCustomerName=(TextView) findViewById(R.id.tv_display_name);
        tvCustomerPhone=(TextView) findViewById(R.id.tv_display_mobile);
        tvCustomerEmail=(TextView) findViewById(R.id.tv_display_email);
        tvCustomerAddress=(TextView) findViewById(R.id.tv_display_address);
        toolbarButton=(Button) findViewById(R.id.toolbar_detail);
    }

    public void setText()
    {
        tvCustomerName.setText("Name : "+customerDetails.getName());
       tvCustomerPhone.setText("Phone No : "+customerDetails.getPhoneNo());
        tvCustomerEmail.setText("Email : "+customerDetails.getEmail());
        tvCustomerAddress.setText("Address : "+customerDetails.getAddress());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        init();
        customerDetails=(CustomerDetails)getIntent().getSerializableExtra("Detail");
        setText();
        toolbarButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.toolbar_detail:
                onBackPressed();
                finish();
                break;
        }
    }
}
