package com.demo.ticker;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TickerTest extends TestCase {

    @Mock
    private PoloniexManager mockedPoloniexManager;
    private Ticker ticker;
    private MovingAverage movingAverage = new MovingAverage();

    private Map<String, PoloniexTickerBean> tickerBeanMap = new HashMap();
    private Double latestPrice = 0.0045d;
    private String pair = "BTC_ETH";


    @Before
    public void setUp() throws Exception {
        super.setUp();
        PoloniexTickerBean poloniexTickerBean = new PoloniexTickerBean();
        poloniexTickerBean.setLast(latestPrice);

        tickerBeanMap.put(pair, poloniexTickerBean);

        final String jsonFilePath = "src/test/java/resources/returnTickerData.txt";
        final String fileContents = Utils.readFile(jsonFilePath);

        ticker = new Ticker(mockedPoloniexManager,movingAverage);

        when(mockedPoloniexManager.loadTickerJson()).thenReturn(fileContents);
        when(mockedPoloniexManager.convertToObjects(any(String.class))).thenReturn(tickerBeanMap);
    }


    @Test
    public void testLatestPrice() throws Exception {
        final Double price = ticker.getLatestPrice(pair);
        assertEquals(latestPrice,price);
    }
}