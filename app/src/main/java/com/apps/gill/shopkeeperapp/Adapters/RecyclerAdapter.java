package com.apps.gill.shopkeeperapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.gill.shopkeeperapp.Activity.CustomerInfo;
import com.apps.gill.shopkeeperapp.R;
import com.apps.gill.shopkeeperapp.models.GetCustomer.CustomerData;
import com.apps.gill.shopkeeperapp.models.GetCustomer.CustomerDetails;

import java.util.List;

/**
 * Created by gill on 11-02-2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements View.OnClickListener{
    List<CustomerDetails> detailsList=null;
    Context context;
    private LayoutInflater inflater;
    public RecyclerAdapter(Context context,List<CustomerDetails> detailses)
    {
        inflater=LayoutInflater.from(context);
        this.context=context;
        this.detailsList=detailses;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recycler_data,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     holder.customer_name.setText(detailsList.get(position).getName());
        holder.pos=position;
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(this);
        //holder.customer_image.setImageResource((Integer) Data.getData().get(position).getCustomerPicURL().getOriginal());
    }

    @Override
    public int getItemCount() {
        return detailsList.size() ;
    }

    public void resetData(List<CustomerDetails> customerDetailses)
    {
        this.detailsList=customerDetailses;
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        MyViewHolder holder=(MyViewHolder) v.getTag();
        Intent intent=new Intent(context, CustomerInfo.class);
        intent.putExtra("Detail",detailsList.get(holder.pos));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView customer_image;
        TextView customer_name;
        int pos;
        public MyViewHolder(View itemView) {
            super(itemView);
            customer_image=(ImageView) itemView.findViewById(R.id.iv_customer);
            customer_name=(TextView) itemView.findViewById(R.id.tv_customer);
        }
    }
}
