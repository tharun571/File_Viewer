package com.example.file_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.lang.*;
import java.io.*;
import java.util.*;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {



    ArrayList<String> fileList=new ArrayList<String>();
    ArrayList<String> fileList1=new ArrayList<String>();
    ArrayList<String> fileList2=new ArrayList<String>();
    Context context;


    public Adapter(  Context context) {
        this.context = context;
    }

    public void add(ArrayList<String> file){
        fileList.addAll(file);
    }


    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(view);

    }

    private RecyclerView recyclerView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onBindViewHolder(@NonNull final Adapter.MyViewHolder holder, int position) {

        File[] files = new File(fileList.get(position)).listFiles();
        fileList2.clear();
        if (files != null) {
            for (File file : files) {
                fileList2.add(file.getPath());
                visible(holder);
            }


        }





        String text=fileList.get(position);

       StringBuilder sb=new StringBuilder(text);
       sb.reverse();
       text=sb.toString();

       int i=text.indexOf('/');

       text=text.substring(0,i);
        StringBuilder sb1=new StringBuilder(text);
        sb1.reverse();
        text=sb1.toString();

        holder.textView.setText(text);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                Log.w("QWE","ASD");

                if(holder.r.getVisibility()==View.GONE&&holder.d.getVisibility()==View.GONE){

                }

                else if(holder.r.getVisibility() == View.VISIBLE ){

                    holder.r.setVisibility(View.GONE);
                    holder.d.setVisibility(View.VISIBLE);
                    fileList1.add("lololol");

                    File[] files = new File(fileList.get(position)).listFiles();
                    fileList1.clear();
                    if (files != null) {
                        for (File file : files) {
                            fileList1.add(file.getPath());
                        }
                        recyclerView=(RecyclerView)view.findViewById(R.id.secondrecycler);
                        recyclerView.setHasFixedSize(true);
                        layoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(layoutManager);
                        mAdapter = new Adapter(context);
                        mAdapter.add(fileList1);
                        recyclerView.setAdapter(mAdapter);
                    }



                }
                else{
                    holder.d.setVisibility(View.GONE);
                    holder.r.setVisibility(View.VISIBLE);
                    fileList1.clear();
                    recyclerView=(RecyclerView)view.findViewById(R.id.secondrecycler);
                    recyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(layoutManager);
                    mAdapter = new Adapter(context);
                    mAdapter.add(fileList1);
                    recyclerView.setAdapter(mAdapter);
                }


            }
        });

    }

    public void visible(@NonNull final Adapter.MyViewHolder holder){

        holder.r.setVisibility(View.VISIBLE);


    }
    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        ImageView r,d;
        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.filename);
            r=itemView.findViewById(R.id.right1);
            d=itemView.findViewById(R.id.down1);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            itemClickListener.OnClick(v,getAdapterPosition());
        }
    }
}
