package com.project.batterymanagementsystem.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.project.batterymanagementsystem.R;
import com.project.batterymanagementsystem.adapters.BatteryRVAdapter;
import com.project.batterymanagementsystem.enums.ChargeType;
import com.project.batterymanagementsystem.interfaces.SimulationListener;
import com.project.batterymanagementsystem.simulation.Simulator;
import com.project.batterymanagementsystem.ui.BatteryCard;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private ArrayList<BatteryCard> mBatteryCards;
    private BatteryRVAdapter mAdapter;
    private TextView mBatteryPercentage;
    private ProgressBar mBatteryCircleBar;
    private TextView mBatteryCurrentNow;
    private ConstraintLayout mRelativeLayout;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = view.getContext();
        mRecyclerView = view.findViewById(R.id.rv);


        LinearLayoutManager layout = new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setHasFixedSize(true);

        mBatteryPercentage = view.findViewById(R.id.batteryCurrentValue);
        mBatteryCircleBar = view.findViewById(R.id.batteryProgressbar);
        mRelativeLayout = view.findViewById(R.id.constraintLayout);


        mBatteryCurrentNow = view.findViewById(R.id.batteryCurrentNow);
        loadData(30, 5, 65, ChargeType.SLOW);
        setAdapter();
        simulateData(1500,27,86);

        return view;
    }
    double batteryLevel = 35;

    private void simulateData(int updateDelay, double temp, int health) {
        new Thread(new SimulationRunnable(updateDelay, temp, health) {
        }).start();
    }

    public void updateUI(double increment, ChargeType newType) {
        requireActivity().runOnUiThread(() -> {
            if (batteryLevel < 95) {
                batteryLevel = batteryLevel + increment;
            }
            setBatteryLevel((int)batteryLevel);
            mAdapter.swap(mBatteryCards);
            if(currentChargeType!= newType){
                notifyChargeType(currentChargeType, newType);
                currentChargeType = newType;
            }
        });
    }

    private void notifyChargeType(ChargeType currentChargeType, ChargeType newType) {
        if(currentChargeType == ChargeType.FAST && newType == ChargeType.AVERAGE){
            showSnackBar(getString(R.string.fast_to_average));
        }
        else if(currentChargeType == ChargeType.AVERAGE && newType == ChargeType.SLOW){
            showSnackBar(getString(R.string.average_to_slow));
        }
        else if(currentChargeType == ChargeType.SLOW && newType == ChargeType.FAST){
            showSnackBar(getString(R.string.slow_to_fast));
        }
    }

    private void showSnackBar(String text) {
        Snackbar snackbar = Snackbar
                .make(mRelativeLayout, text, Snackbar.LENGTH_LONG)
                .setDuration(3000);
        snackbar.show();
    }

    private void setBatteryLevel(int percentage) {
        mBatteryCircleBar.setProgress(percentage);
        mBatteryPercentage.setText("" + percentage);

    }
    
    private void setAdapter() {

        if (mAdapter == null) {
            mAdapter = new BatteryRVAdapter(mBatteryCards);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.swap(mBatteryCards);
        }
        mRecyclerView.invalidate();
    }

    ChargeType currentChargeType = ChargeType.FAST;
    public class SimulationRunnable implements Runnable{
        int updateDelay;
        double temp;
        int health;

        public SimulationRunnable(int updateDelay, double temp, int health) {
            this.updateDelay = updateDelay;
            this.temp = temp;
            this.health = health;
        }

        @Override
        public void run() {
            Simulator simulator = new Simulator();
            simulator.setChangeListener(new SimulationListener() {
                @Override
                public void onStatusChange(double temp, double voltage, int health, ChargeType type, double increment) {
                    loadData(temp, voltage, health, type);
                    updateUI(increment,type);
                }
            });
            simulator.simulateData(updateDelay,temp,health);
        }
    }
    private double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}

    private void loadData(double temp, double voltage, int health, ChargeType type) {
        mBatteryCards = new ArrayList<>();
        String outTemp = round(temp,1) + " ºC";
        int color = Color.GREEN;
        if (temp > 35) {
            color = Color.RED;
        } else if (temp > 33 && temp < 35) {
            color = Color.YELLOW;
        }
        mBatteryCards.add(
                new BatteryCard(
                        R.drawable.ic_thermometer_black_18dp,
                        getString(R.string.battery_summary_temperature),
                        outTemp,
                        color
                )
        );

        // Voltage
        String voltageOut = voltage + " V";
        int voltageColor = Color.BLACK;
        if (voltage == 15) {
            voltageColor = Color.GREEN;
        } else if (voltage == 9) {
            voltageColor = Color.YELLOW;
        } else if (voltage == 5) {
            voltageColor = Color.RED;
        }
        mBatteryCards.add(
                new BatteryCard(
                        R.drawable.ic_flash_black_18dp,
                        getString(R.string.battery_summary_voltage),
                        voltageOut,
                        voltageColor
                )
        );

        // Health
        String healthOut = health + " %";
        int healthColor;
        if (health > 85) {
            healthColor = Color.GREEN;
        } else {
            healthColor = Color.RED;
        }

        mBatteryCards.add(
                new BatteryCard(
                        R.drawable.ic_heart_black_18dp,
                        getString(R.string.battery_summary_health),
                        healthOut,
                        healthColor
                )
        );

        mBatteryCards.add(
                new BatteryCard(
                        R.drawable.ic_wrench_black_18dp,
                        getString(R.string.battery_summary_technology),
                        "Li-ion",
                        Color.GREEN
                )
        );
        int rateColor = Color.BLACK;
        if (voltage == 15) {
            rateColor = Color.GREEN;
        } else if (voltage == 9) {
            rateColor = Color.YELLOW;
        } else if (voltage == 5) {
            rateColor = Color.RED;
        }
        mBatteryCards.add(
                new BatteryCard(
                        R.drawable.ic_charging_black,
                        getString(R.string.charge_rate),
                        type.name,
                        rateColor
                )
        );
    }

    
