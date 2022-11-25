package com.project.batterymanagementsystem.simulation;

import com.project.batterymanagementsystem.enums.ChargeType;
import com.project.batterymanagementsystem.interfaces.SimulationListener;

public class Simulator {

    private SimulationListener mListener = null;

    public void setChangeListener(SimulationListener listener) {
        this.mListener = listener;
    }
    
    //To simulate working logic of the system and simulate temperature sensor and voltmeter readings

    public void simulateData(int updateDelay, double temp, int health){
        while (true) {
            try {
                while (temp < 33) { // Logic to simulate Fast charge
                    temp = temp + Math.random(); // To simulate temperature sensor readings
                    Thread.sleep(updateDelay);
                    if (mListener != null)
                        mListener.onStatusChange(temp, 15, health, ChargeType.FAST,1);
                }
                while (temp > 33 && temp < 35) { //Logic to simulate Average Charging
                    temp = temp + (Math.random() / 2);
                    Thread.sleep(updateDelay);
                    if (mListener != null)
                        mListener.onStatusChange(temp, 9, health, ChargeType.AVERAGE,0.5);
                }
                while (temp > 27) { //Logic to simulate Slow Charging
                    temp = temp - Math.random();
                    Thread.sleep(updateDelay);
                    if (mListener != null)
                        mListener.onStatusChange(temp, 5, health, ChargeType.SLOW,0.3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
