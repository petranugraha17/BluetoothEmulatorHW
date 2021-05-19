package com.example.bluetoothemulator;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.text.style.BulletSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    private static final String ARG_PARAMETER1 = "parameter1";
    private static final String ARG_PARAMETER2 = "parameter2";

    private String mParameter1;
    private String mParameter2;

    public SecondFragment(){

    }

    public static SecondFragment newInstance(String parameter1, String parameter2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAMETER1, parameter1);
        args.putString(ARG_PARAMETER2, parameter2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParameter1 = getArguments().getString(ARG_PARAMETER1);
            mParameter2 = getArguments().getString(ARG_PARAMETER2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            TextView byteTV = view.findViewById(R.id.TV);
            byteTV.setText("byte results : " + bundle.getString("byte"));
            TextView MAC = view.findViewById(R.id.NAC);
            MAC.setText("NAC : " + bundle.getString("MAC"));
            TextView rssi = view.findViewById(R.id.rssi);
            rssi.setText("RSSI info : " + bundle.getString("rssi"));
        }
        Button button = view.findViewById(R.id.getback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_fragment2_to_info2);
            }
        });

    }
}
