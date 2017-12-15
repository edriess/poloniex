package com.demo.ticker;

import java.util.LinkedList;
import java.util.List;

public class MovingAverage {

    private List<Double> priceList = new LinkedList();
    private int maxValues = 60; //default to 60, for one per second for a minute

    public int getMaxValues() {
        return maxValues;
    }

    public void setMaxValues(int maxValues) {
        this.maxValues = maxValues;
    }

    public Double calculateOneMinAvg(Double latestPrice) {
        Double average = null;
        priceList.add(latestPrice);

        if (priceList.size() == maxValues) {
            //ready to calculate moving average
            average = getAverage();
        } else if (priceList.size() > maxValues) {
            //need to trim off the first value, to be a moving average
            priceList.remove(0);
            average = getAverage();
        }
        return average;
    }

    private Double getAverage() {
        Double total = 0d;
        for (Double price : priceList) {
            total += price;
        }
        return total / priceList.size();
    }


}
