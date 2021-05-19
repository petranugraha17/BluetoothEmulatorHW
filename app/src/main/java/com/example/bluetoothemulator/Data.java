package com.example.bluetoothemulator;

public class Data {
    private String NAC;
    private String rssi;
    private String address;
    public Data(String address, String NAC, String RSSI){
        this.NAC = NAC;
        this.rssi = RSSI;
        this.address = address;
    }

    public void setNAC(String NAC) {
        this.NAC = NAC;
    }

    public void setRSSI(String RSSI) {
        this.rssi = RSSI;
    }

    public String getNAC() {
        return NAC;
    }

    public String getRSSI() {
        return rssi;
    }

    public String getAddress() {
        return address;
    }
}
