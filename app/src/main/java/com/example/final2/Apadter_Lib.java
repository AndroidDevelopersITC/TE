package com.example.final2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class Apadter_Lib extends RecyclerView.Adapter<Apadter_Lib.View_Holder_Libs> {
    ArrayList<LibContans> arrayList;
    Context context;

    public Apadter_Lib(ArrayList<LibContans> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View_Holder_Libs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.libs_items,null,false);
        View_Holder_Libs viewHolderLibs = new View_Holder_Libs(v);


        return viewHolderLibs;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder_Libs holder, int position) {
        holder.tv_name.setText(arrayList.get(position).getName_book());
        holder.tv_id.setText( Integer.toString(arrayList.get(position).getId_book()));
        holder.tv_price.setText(arrayList.get(position).getPrice_book()+"");
        holder.tv_amount.setText(arrayList.get(position).getAmount_book()+" ");


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class View_Holder_Libs extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_amount;
        TextView tv_price;



        public View_Holder_Libs(@NonNull View itemView) {
            super(itemView);
            tv_id=itemView.findViewById(R.id.items_tv_id);
            tv_name=itemView.findViewById(R.id.items_tv_name);
            tv_amount=itemView.findViewById(R.id.items_tv_amount);
            tv_price=itemView.findViewById(R.id.items_tv_price);



        }
    }
}
