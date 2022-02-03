package com.example.securesmsservice.message;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.securesmsservice.R;

import java.util.ArrayList;
import java.util.Collections;

public class MainMsgAdapter extends RecyclerView.Adapter<MainMsgAdapter.MainMsgHolder> {

    private Context context;
    private ArrayList<MessageData> arrayList;
    private RecyclerView recyclerView;

    public MainMsgAdapter(Context context, RecyclerView recyclerView,ArrayList<MessageData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.recyclerView=recyclerView;
    }

    @NonNull
    @Override
    public MainMsgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainMsgHolder mainMsgHolder;
        View view;
        if(viewType==1)
            view=LayoutInflater.from(context).inflate(R.layout.single_send,parent,false);
        else
            view=LayoutInflater.from(context).inflate(R.layout.single_receive,parent,false);
        mainMsgHolder=new MainMsgHolder(view);
        return mainMsgHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainMsgHolder holder, int position) {
        holder.textView.setText(arrayList.get(getRevPos(position)).getMessage());
        recyclerView.smoothScrollToPosition(position);
        holder.itemView.setOnLongClickListener(view -> {

            EditText inputEt=new EditText(context);
            inputEt.setHint("Enter secret");

            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("Recover message")
                    .setView(inputEt)
                    .setNegativeButton("Cancel",null)
                    .setPositiveButton("Decrypt", (dialogInterface, i) -> {

                        String decryptStr=inputEt.getText().toString();
//                        if(decryptStr.equals(""))
//                        {
//                            Toast.makeText(context, "Input secret key!", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
                        arrayList.get(position).setMessage(AESUtils.decrypt(arrayList.get(position).getMessage(), decryptStr));
                        if(arrayList.get(position).getMessage()==null){
                            holder.textView.setTextColor(Color.parseColor("#FF0000"));
                            holder.textView.setText("Parsing error!");
                        }

                        else{
                            holder.textView.setText(arrayList.get(position).getMessage());
                        }

                        recyclerView.smoothScrollToPosition(position);
                    }).create().show();

            return true;
        });
    }


    void addData(MessageData msgData){
        arrayList.add(msgData);
        recyclerView.smoothScrollToPosition(arrayList.size()-1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
       return arrayList.get(getRevPos(position)).isSend()?1:0;
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

    int getRevPos(int pos){
        return pos;
    }
}
