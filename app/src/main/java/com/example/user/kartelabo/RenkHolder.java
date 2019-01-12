package com.example.user.kartelabo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RenkHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public LinearLayout linearLayout;
    public RelativeLayout relativeLayout;
    public TextView textView;

    public RenkHolder(View itemView, final ColorAdapter.OnItemClickListener listener) {
        super(itemView);
        cardView=itemView.findViewById(R.id.cardView);
        linearLayout=itemView.findViewById(R.id.linearLayout);
        relativeLayout=itemView.findViewById(R.id.relativeLayout);
        textView=itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    int position=getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){

                        listener.onItemClick(position);
                    }

                }

            }
        });

    }
}