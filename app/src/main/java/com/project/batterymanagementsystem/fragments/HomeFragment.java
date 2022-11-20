package com.project.batterymanagementsystem.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.batterymanagementsystem.MainActivity;
import com.project.batterymanagementsystem.R;
import com.project.batterymanagementsystem.adapters.BatteryRVAdapter;
import com.project.batterymanagementsystem.ui.BatteryCard;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private MainActivity mActivity;
    private ArrayList<BatteryCard> mBatteryCards;
    private Thread mLocalThread;

    private BatteryRVAdapter mAdapter;

    private TextView mBatteryPercentage;

    private TextView mBatteryCurrentNow;

    private TextView mBatteryCurrentMin;

    private TextView mBatteryCurrentMax;

    private ProgressBar mBatteryCircleBar;

    private double mMin;

    private double mMax;

    private Handler mHandler;

    private String mActivePower;


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
        final View view =  inflater.inflate(R.layout.fragment_home, container, false);
        mContext = view.getContext();
        mActivity = (MainActivity) getActivity();

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

        mBatteryCurrentNow = view.findViewById(R.id.batteryCurrentNow);
        mBatteryCurrentMin = view.findViewById(R.id.batteryCurrentMin);
        mBatteryCurrentMax = view.findViewById(R.id.batteryCurrentMax);

        mMin = Integer.MAX_VALUE;
        mMax = 0;
        mHandler = new Handler();
        //mHandler.postDelayed(mRunnable, 5000);
        loadData();
        setBatteryLevel(88);
        return view;

    }

    private void setBatteryLevel(int percentage){
        mBatteryCircleBar.setProgress(65);
        mBatteryPercentage.setText(""+percentage);

    }

    private void loadData() {
        mLocalThread = new Thread(new Runnable() {
            public void run() {
                mBatteryCards = new ArrayList<>();
                String value;
                int color = Color.GREEN;

                // Temperature
                float temperature = 31;
                value = temperature + " ºC";
                if (temperature > 45) {
                    color = Color.RED;
                } else if (temperature <= 45 && temperature > 35) {
                    color = Color.YELLOW;
                }
                mBatteryCards.add(
                        new BatteryCard(
                                R.drawable.ic_thermometer_black_18dp,
                                getString(R.string.battery_summary_temperature),
                                value,
                                color
                        )
                );

                // Voltage
                value = 3.7 + " V";
                mBatteryCards.add(
                        new BatteryCard(
                                R.drawable.ic_flash_black_18dp,
                                getString(R.string.battery_summary_voltage),
                                value
                        )
                );

                // Health
                value = "Dead"; //estimator.getHealthStatus(mContext);
                //color = value.equals(mContext.getString(R.string.battery_health_good)) ?
                        //Color.GREEN : Color.RED;
                mBatteryCards.add(
                        new BatteryCard(
                                R.drawable.ic_heart_black_18dp,
                                getString(R.string.battery_summary_health),
                                value,
                                Color.GREEN
                        )
                );

                mBatteryCards.add(
                        new BatteryCard(
                                R.drawable.ic_wrench_black_18dp,
                                getString(R.string.battery_summary_technology),
                                "Li-ion",
                                color
                        )
                );
            }
        });

        mLocalThread.start();
        setAdapter();
    }

    private void setAdapter() {
        try {
            mLocalThread.join();
            if (mAdapter == null) {
                mAdapter = new BatteryRVAdapter(mBatteryCards);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.swap(mBatteryCards);
            }
            mRecyclerView.invalidate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}