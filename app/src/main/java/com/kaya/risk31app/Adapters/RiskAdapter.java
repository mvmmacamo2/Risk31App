package com.kaya.risk31app.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kaya.risk31app.Models.Risk;
import com.kaya.risk31app.R;

import java.util.List;

public class RiskAdapter extends RecyclerView.Adapter<RiskAdapter.MyViewHolder> {

    Context mContext;
    List<Risk> mData;
    ProgressBar progressBar;

    public RiskAdapter(Context mContext, List<Risk> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView risk_name, risk_estado, risk_username, risk_action, risk_classification, risk_score,risk_likelihood, risk_impact, risk_cor;

        public MyViewHolder(View itemView) {
            super(itemView);
            risk_name = itemView.findViewById(R.id.risk_name);
            risk_estado = itemView.findViewById(R.id.risk_estado);
//            risk_action = itemView.findViewById(R.id.risk_action);
//            risk_classification = itemView.findViewById(R.id.risk_classification);
//            risk_score = itemView.findViewById(R.id.risk_score);
//            risk_impact = itemView.findViewById(R.id.risk_impact);
//            risk_likelihood = itemView.findViewById(R.id.risk_likelihood);
//            risk_username = itemView.findViewById(R.id.risk_username);
//            risk_cor = itemView.findViewById(R.id.risk_classification);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.row_risk_item, parent, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.risk_name.setText(mData.get(position).getName());
       holder.risk_estado.setText(mData.get(position).getEstado());
       holder.risk_estado.setBackgroundColor(Color.parseColor(mData.get(position).getColor()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
