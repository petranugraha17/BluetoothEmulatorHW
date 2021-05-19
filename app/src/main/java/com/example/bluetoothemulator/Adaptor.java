package com.example.bluetoothemulator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Adaptor extends RecyclerView.Adapter<MyViewHolder> {
    private OnItemClick OnItemClick;
    private int pos;
    ArrayList<Data> arraylist = new ArrayList<Data>();
    private HashMap<String, Data> hashMap = new HashMap<>();
    public Adaptor(OnItemClick OnItemClick) { this.OnItemClick = OnItemClick; }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bluetoothdetail,parent,false);
        return new MyViewHolder(view);
    }

    public void clearDevice(){
        this.arraylist.clear();
        notifyDataSetChanged();
    }
    public void addDevice(ArrayList<Data> arraylist){
        this.arraylist = arraylist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.nac.setText("NAC address"+arraylist.get(position).getNAC());
       holder.RSSI.setText("RSSI"+arraylist.get(position).getRSSI());
    }

    @Override
    public int getItemCount() { return arraylist.size(); }
    interface OnItemClick{
        void onItemClick(Data currentDevice);
    }
}
