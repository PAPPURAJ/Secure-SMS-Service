package com.example.securesmsservice.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.securesmsservice.R;

import java.util.ArrayList;

public class MainMsgAdapter extends RecyclerView.Adapter<MainMsgAdapter.MainMsgHolder> {

    private Context context;
    private ArrayList<MessageData> arrayList;

    public MainMsgAdapter(Context context, ArrayList<MessageData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainMsgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainMsgHolder mainMsgHolder;
        View view=LayoutInflater.from(context).inflate(R.layout.single_msg,parent,false);
        mainMsgHolder=new MainMsgHolder(view);
        return mainMsgHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainMsgHolder holder, int position) {

        holder.textView.setText(arrayList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MainMsgHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public MainMsgHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.singleMsgTv);
        }
    }
}
