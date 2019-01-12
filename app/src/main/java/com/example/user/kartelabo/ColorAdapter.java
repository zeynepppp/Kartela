package com.example.user.kartelabo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<RenkHolder> {
    private List<Renk> renkList;
    private Context context;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ColorAdapter(Context context,List<Renk> list){

        this.context=context;
        this.renkList=list;

    }

    @NonNull
    @Override
    public RenkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.renk_gorunumu,null);

        return new RenkHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RenkHolder holder, int position) {
//holder.ivResim.setImageResource(resId);
        int renk= Color.parseColor(renkList.get(position).getRenkKodu());
       // holder.cardView.setCardBackgroundColor(renk);
       // holder.linearLayout.setBackgroundColor(renk);
        holder.relativeLayout.setBackgroundColor(renk);
        holder.textView.setText(renkList.get(position).getRenkKodu());

    }

    @Override
    public int getItemCount() {
        return renkList.size();
    }
}
