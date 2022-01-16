package com.example.securesmsservice.conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.securesmsservice.R;

import java.util.ArrayList;

public class ConversationAdapter  extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {
    private Context context;
    private ArrayList<String> arrayList;

    public ConversationAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversationViewHolder(LayoutInflater.from(context).inflate(R.layout.single_conversation,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        holder._idTv.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ConversationViewHolder extends RecyclerView.ViewHolder {
        public TextView _idTv;
        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);
            _idTv=itemView.findViewById(R.id.singleConversationIDTv);
        }
    }
}
