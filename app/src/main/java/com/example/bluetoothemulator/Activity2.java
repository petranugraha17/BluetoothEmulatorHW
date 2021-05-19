package com.example.bluetoothemulator;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private boolean isScanning = false;
    public ArrayList<Data> foundDevice = new ArrayList<>();
    Button button2;
    Info info;
    com.example.bluetoothemulator.Adaptor Adaptor;
    private int count = 1;
    RecyclerView recyclerView;
    NavController controller;
    BluetoothLeScanner mBluetoothLeScanner =mBluetoothAdapter.getBluetoothLeScanner();

    @Override
    @Nullable
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
        }
        final BluetoothManager bluetoothManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
                button2 = findViewById(R.id.button2);
                Adaptor = new Adaptor(onItemClick);
                recyclerView = findViewById(R.id.abc);
                recyclerView.setLayoutManager(new LinearLayoutManager(Activity2.this));
                recyclerView.setAdapter(Adaptor);
                button2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if (isScanning) {
                            isScanning = false;
                            button2.setText("Start scanning");
                            mBluetoothLeScanner.stopScan(startScanCallback);
                        }else{
                            isScanning = true;
                            button2.setText("Stop scanning");
                            Adaptor.clearDevice();
                            foundDevice.clear();
                            mBluetoothLeScanner.startScan(startScanCallback);
                        }
                    }
                });
        }


    private final ScanCallback startScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice device = result.getDevice();
                ScanRecord mScanRecord = result.getScanRecord();
                String address = device.getAddress();
                byte[] content = mScanRecord.getBytes();
                int mRssi = result.getRssi();
                foundDevice.add(new Data(address, String.valueOf(mRssi), String.valueOf(content)));
                Adaptor.addDevice(foundDevice);
        }
    };
    com.example.bluetoothemulator.Adaptor.OnItemClick onItemClick = new com.example.bluetoothemulator.Adaptor.OnItemClick() {
        @Override
        public void onItemClick(Data selectedDevice) {
            Toast.makeText(Activity2.this, selectedDevice.getAddress(),Toast.LENGTH_SHORT)
                    .show();
            Bundle bundle = new Bundle();
            bundle.putString("byte",selectedDevice.getAddress());
            bundle.putString("NAC",selectedDevice.getNAC());
            bundle.putString("rssi",selectedDevice.getRSSI());
            controller.navigate(R.id.action_info2_to_fragment2,bundle);
            if(isScanning){
                isScanning = false;
                mBluetoothLeScanner.stopScan(startScanCallback);
                button2.setText("開始掃描");
            }
        }
    };

}