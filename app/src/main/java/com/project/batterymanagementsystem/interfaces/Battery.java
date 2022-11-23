
/*
public class Battery {

    /**
     * Method to obtain current battery voltage and return it
     *

    public static double getBatteryVoltage(final Context context) {
        Intent receiver = context.registerReceiver(
                null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        );

        if (receiver == null) return 0;

        double voltage = receiver.getIntExtra(BatteryCharger.EXTRA_VOLTAGE, 0);

        return (voltage == 0) ? voltage : voltage / 1000;
    }

    /**
     * Get the battery capacity and return as percentage
     *
    public static double getBatteryCapacity(final Context context) {
        double value = 0;

        BatteryCharger manager = (BatteryCharger)
                context.getSystemService(Context.BATTERY_SERVICE);
        if (manager != null) {
            value = manager.getProperty(BatteryCharger.BATTERY_PROPERTY_CAPACITY);
        }

        if (value != 0 && value != Integer.MIN_VALUE) {
            return value;
        }

        return 0;
    }

    /**
     * Get the battery full capacity (charge counter) in mAh.
     * Since Power (W) = (Current (A) * Voltage (V)) <=> Power (Wh) = (Current (Ah) * Voltage (Vh)).
     * Therefore, Current (mA) = Power (mW) / Voltage (mV)
     *
     *
    public static int getBatteryChargeCounter(final Context context) {
        int value = 0;

        BatteryCharger manager = (BatteryCharger) context.getSystemService(Context.BATTERY_SERVICE);
        if (manager != null) {
            value = manager.getIntProperty(BatteryCharger.BATTERY_PROPERTY_CHARGE_COUNTER);
        }

        return value;

    }


    public static double getBatteryFactoryCapacity(final Context context) {
        Object PowerProfileA;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";

        try {
            PowerProfileA = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class)
                    .newInstance(context);

            batteryCapacity = (double) Class
                    .forName(POWER_PROFILE_CLASS)
                    .getMethod("getBatteryCapacity")
                    .invoke(PowerProfileA);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (double) batteryCapacity;
    }


    public static double getBatteryCurrentAverage(final Context context) {
        double value = 0;

        BatteryCharger manager = (BatteryCharger)
                context.getSystemService(Context.BATTERY_SERVICE);
        if (manager != null) {
            value = manager.getIntProperty(BatteryCharger.BATTERY_PROPERTY_CURRENT_AVERAGE);
        }

        return (value != 0 && value != Integer.MIN_VALUE) ? value : 0;
    }



    /**
     * Calculate current battery usage
     *
    public static double getBatteryCapacityConsumed(final double batteryUsage, final Context context) {
        double current = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BatteryCharger manager = (BatteryCharger)
                    context.getSystemService(Context.BATTERY_SERVICE);
            current = manager.getProperty(BatteryCharger.BATTERY_PROPERTY_CURRENT_AVERAGE);
        }

        return (current * batteryUsage) / 1000;
    }

    /**
     * Calculates the battery's remaining energy capacity
     *
    public static double getBatteryRemainingCapacity(final Context context) {
        double remainingCapacity;
        double capacity = getBatteryFactoryCapacity(context);  // in %
        if (capacity <= -1) {
            capacity = 0;
        }

        long chargeCounter = getBatteryChargeCounter(context);
        if (chargeCounter <= -1) {
            chargeCounter = abs(getBatteryDesignCapacity(context));  // in mAh
        }

        if (capacity > 0 && chargeCounter > 0) {
            remainingCapacity = (double) ((chargeCounter * capacity) / 100);
        } else {
            double voltageNow = max(1, getBatteryVoltage(context));
            long energyCounter = getBatteryEnergyCounter(context);
            if (energyCounter <= -1) {
                energyCounter = 0;
            }
            return (double) (energyCounter / voltageNow);
        }

        return (double) remainingCapacity;
    }



}

*/