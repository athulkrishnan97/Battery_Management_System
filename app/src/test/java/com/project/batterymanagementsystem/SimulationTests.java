package com.project.batterymanagementsystem;

import org.junit.Test;

import static org.junit.Assert.*;

import com.project.batterymanagementsystem.enums.ChargeType;
import com.project.batterymanagementsystem.simulation.Simulator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SimulationTests {
    private static final int DEFAULT_DELAY = 1500;
    private static final int DEFAULT_HEALTH = 65;
    Simulator simulator = new Simulator();
//test
    /*
    @Test
    public void ifTempNeg() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ChargeType> future = new CompletableFuture<>();
        new Thread(() -> {
            simulator.setChangeListener((temp, voltage, health, type, increment) -> {
                future.complete(type);
            });
            simulator.simulateData(DEFAULT_DELAY, -30, DEFAULT_HEALTH);
        }).start();
        ChargeType result = future.get(3, TimeUnit.SECONDS);
        //
    }
    */
//test
    @Test
    public void ifTemp30ThenChargeRateShouldBeFast() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ChargeType> future = new CompletableFuture<>();
        new Thread(() -> {
            simulator.setChangeListener((temp, voltage, health, type, increment) -> {
                future.complete(type);
            });
            simulator.simulateData(DEFAULT_DELAY, 30, DEFAULT_HEALTH);
        }).start();
        ChargeType result = future.get(3, TimeUnit.SECONDS);
        assertEquals(ChargeType.FAST, result);
    }
//test

    @Test
    public void ifTemp34ThenChargeRateShouldBeAverage() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ChargeType> future = new CompletableFuture<>();
        new Thread(() -> {
            simulator.setChangeListener((temp, voltage, health, type, increment) -> {
                future.complete(type);
            });
            simulator.simulateData(DEFAULT_DELAY, 34, DEFAULT_HEALTH);
        }).start();
        ChargeType result = future.get(3, TimeUnit.SECONDS);
        assertEquals(ChargeType.AVERAGE, result);
    }
//test
    @Test
    public void ifTemp35ThenChargeRateShouldBeSlow() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ChargeType> future = new CompletableFuture<>();
        new Thread(() -> {
            simulator.setChangeListener((temp, voltage, health, type, increment) -> {
                future.complete(type);
            });
            simulator.simulateData(DEFAULT_DELAY, 35, DEFAULT_HEALTH);
        }).start();
        ChargeType result = future.get(3, TimeUnit.SECONDS);
        assertEquals(ChargeType.SLOW, result);
    }
//test
    /*
    @Test
    public void ifTempVeryHigh() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ChargeType> future = new CompletableFuture<>();
        new Thread(() -> {
            simulator.setChangeListener((temp, voltage, health, type, increment) -> {
                future.complete(type);
            });
            simulator.simulateData(DEFAULT_DELAY, 300, DEFAULT_HEALTH);
        }).start();
        ChargeType result = future.get(3, TimeUnit.SECONDS);
        //
    }
    CompletableFuture<ChargeType> future = new CompletableFuture<>();
        new Thread(() -> {
            simulator.setChangeListener((temp, voltage, health, type, increment) -> {
                future.complete(type);
            });
            simulator.simulateData(DEFAULT_DELAY, 300, DEFAULT_HEALTH);
        }).start();
        ChargeType result = future.get(3, TimeUnit.SECONDS);
    */
}
