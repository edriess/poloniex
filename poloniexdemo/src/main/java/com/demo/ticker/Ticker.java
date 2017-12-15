package com.demo.ticker;

import java.util.Map;

public class Ticker {

    private PoloniexManager poloniexManager;
    private MovingAverage movingAverage;

    public Ticker(PoloniexManager poloniexManager, MovingAverage movingAverage) {
        this.poloniexManager = poloniexManager;
        this.movingAverage = movingAverage;
    }

    public void startCalculating(String stringPair)
            throws Exception {
        int count = 0;

        do {
            //wait 1 second between calls to Poloniex
            Thread.sleep(1000);
            Double latestPrice = getLatestPrice(stringPair);
            System.out.print(count + " latestPrice = " + latestPrice + "  " + stringPair + "    ");

            final Double oneMinAvg = movingAverage.calculateOneMinAvg(latestPrice);
            if (oneMinAvg == null) {
                int secondsLeft = movingAverage.getMaxValues() - count - 1;
                System.out.println("Wait for it...." + secondsLeft + " seconds left. ");
            } else {
                System.out.println("oneMinAvg = " + oneMinAvg);
            }

            count++;
        } while (true);

    }

    public Double getLatestPrice(String stringPair)
            throws Exception {
        String tickerJson = poloniexManager.loadTickerJson();
        Map<String, PoloniexTickerBean> tickerBeanMap = poloniexManager.convertToObjects(tickerJson);
        final PoloniexTickerBean bean = tickerBeanMap.get(stringPair);
        return bean.getLast();

    }

}
