package com.example.bluetoothemulator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nac;
    TextView RSSI;
    Button detail;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nac = itemView.findViewById(R.id.NAC);
        RSSI = itemView.findViewById(R.id.rssi);
        detail = itemView.findViewById(R.id.detail);
    }
}

