package com.kaya.risk31app.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaya.risk31app.Models.Actions;
import com.kaya.risk31app.R;

import java.util.List;

public class TypeActionsAdapter extends RecyclerView.Adapter<TypeActionsAdapter.MyViewHolder> {
    Context mContext;
    List<Actions> mData;

    public TypeActionsAdapter(Context mContext, List<Actions> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_myactions_item, viewGroup, false);

        return new TypeActionsAdapter.MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.textViewAction.setText(mData.get(position).getAction());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewAction;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewAction = itemView.findViewById(R.id.action_name);


        }
    }
}

