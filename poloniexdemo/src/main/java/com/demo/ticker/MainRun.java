package com.demo.ticker;

import java.util.*;

public class MainRun {

    private static Map<String, PoloniexTickerBean> tickerBeanMap = new HashMap();

    public static void main(String[] args) {

        try {
            PoloniexManager poloniexManager = new PoloniexManager();

            String tickerJson = poloniexManager.loadTickerJson();

            tickerBeanMap = poloniexManager.convertToObjects(tickerJson);

            Utils.printPairs(tickerBeanMap);

            Utils.printInstructions("Enter (by typing) one of the crypto currency pairs shown above to start the 1-min moving avg. \n\n " +
                    "It will take 1 min for the values to start appearing on the screen, as we'll get a new price value 1x per second.");

            String stringPair = getInputPair();
            System.out.println("You entered: " + stringPair);
            stringPair = checkEntry(stringPair);

            Utils.printInstructions("Now calulating 1-min moving average. " +
                    "\n Please wait 1 minute. " +
                    "\n Kill the running program when you've seen enough ;) ");

            MovingAverage movingAverage = new MovingAverage();
            movingAverage.setMaxValues(60);

            Ticker ticker = new Ticker(poloniexManager, movingAverage);
            ticker.startCalculating(stringPair);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String checkEntry(String stringPair) {
        //check in case the user entered lower case.
        if (! Utils.isEmpty(stringPair)) {
            stringPair = stringPair.toUpperCase();
        }

        final PoloniexTickerBean bean = tickerBeanMap.get(stringPair);

        if (bean == null) {
            System.out.println("You must enter a valid pair");
        } else {
            final double last = bean.getLast();
            System.out.println("Price right now: " + last);
        }
        return stringPair;
    }

    private static String getInputPair() {
        Scanner scanner = new Scanner(System.in);
        String stringPair = scanner.next();
        scanner.close();
        return stringPair;
    }


}
