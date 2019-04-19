package com.kaya.risk31app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaya.risk31app.Activities.ActionDetailsActivity;
import com.kaya.risk31app.Models.Actions;
import com.kaya.risk31app.R;

import java.util.List;

public class ActionsAdapter extends RecyclerView.Adapter<ActionsAdapter.MyViewHolder> {

    Context mContext;
    List<Actions> mData;
    public ProgressBar progressBarLoading;

    public ActionsAdapter(Context mContext, List<Actions> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.row_myactions_item, parent, false);

        return new ActionsAdapter.MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.textViewAction.setText(mData.get(position).getAction());
      holder.textViewEstado.setText(mData.get(position).getEstado());
      holder.textViewEstado.setBackgroundColor(Color.parseColor(mData.get(position).getColor()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAction, textViewEstado;
        private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
        boolean isLoading;
        int visibleThreshold = 5;
        int lastVisibleItem, totalItemCount;
        public MyViewHolder(final View itemView) {
            super(itemView);
            textViewAction = itemView.findViewById(R.id.action_name);
            textViewEstado = itemView.findViewById(R.id.action_estado);
            progressBarLoading = itemView.findViewById(R.id.progressBarLoding_id);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent actionDetails = new Intent(mContext, ActionDetailsActivity.class);

                    int position = getAdapterPosition();
                    actionDetails.putExtra("risk", mData.get(position).getName_risk());
                    actionDetails.putExtra("action", mData.get(position).getAction());
                    actionDetails.putExtra("admin", mData.get(position).getAdmin());
                    actionDetails.putExtra("created_at", mData.get(position).getCreated_at());
                    actionDetails.putExtra("prazo", mData.get(position).getPrazo());
                    actionDetails.putExtra("tipo", mData.get(position).getTipo());

                    mContext.startActivity(actionDetails);
                }
            });

        }
    }

}
