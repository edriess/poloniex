package com.demo.ticker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Utils {

    public static String readFile(String filePath) {
        String line = null;
        StringBuilder buffer = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }

            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");
        } catch(IOException ex) {
            System.out.println( "Error reading file '" + filePath + "'");
            ex.printStackTrace();
        }

        return buffer.toString();
    }


    public static boolean isEmpty(String stringPair) {
        return stringPair == null || stringPair.trim().equals("");
    }

    public static void printInstructions(String text) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println(text);
        System.out.println("----------------------------------------------------------------------");
    }

    public static void printPairs(Map<String, PoloniexTickerBean> tickerBeanMap) {
        final Set<String> keySet = tickerBeanMap.keySet();
        final TreeSet<String> treeSet = new TreeSet(keySet);

        Utils.printInstructions(" Pairs listed (in alpha-order) are the ones which may be input.");

        int rowMax = 10;
        int count = 0;
        for (String next : treeSet) {
            if (count < rowMax) {
                System.out.print(next + " ");
            } else {
                count = 0;
                System.out.println(next);
            }
            count++;
        }

        System.out.println();
        System.out.println();
    }


}
