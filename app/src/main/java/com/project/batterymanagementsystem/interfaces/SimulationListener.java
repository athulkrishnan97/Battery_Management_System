package com.project.batterymanagementsystem.interfaces;

import com.project.batterymanagementsystem.enums.ChargeType;

public interface SimulationListener {

    void onStatusChange(double temp, double voltage, int health, ChargeType type, double increment);
}
