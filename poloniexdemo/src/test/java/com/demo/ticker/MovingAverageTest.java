package com.demo.ticker;

import junit.framework.TestCase;

public class MovingAverageTest extends TestCase {


    private MovingAverage movingAverage = new MovingAverage();

    public void setUp() throws Exception {
        super.setUp();
        movingAverage.setMaxValues(3);
    }

    public void testOnlyCalculateWhenEnoughValues() throws Exception {
        final Double afterOneInput = movingAverage.calculateOneMinAvg(1.0d);
        final Double afterTwoInput = movingAverage.calculateOneMinAvg(2.0d);
        final Double afterThreeInput = movingAverage.calculateOneMinAvg(5.0d);

        assertNull(afterOneInput);
        assertNull(afterTwoInput);
        assertNotNull(afterThreeInput);
    }

    public void testCorrectAverageCalculatedWhenExactlyEnoughValues() throws Exception {
        final Double afterOneInput = movingAverage.calculateOneMinAvg(5.0d);
        final Double afterTwoInput = movingAverage.calculateOneMinAvg(2.3d);
        final Double afterThreeInput = movingAverage.calculateOneMinAvg(9.333d);

        assertEquals(5.544333333333333d, afterThreeInput);
    }

    public void testCorrectAverageCalculatedWhenMoreThanEnoughValues() throws Exception {
        final Double afterOneInput = movingAverage.calculateOneMinAvg(5.0d);
        final Double afterTwoInput = movingAverage.calculateOneMinAvg(2.3d);
        final Double afterThreeInput = movingAverage.calculateOneMinAvg(9.333d);

        final Double afterFourInput = movingAverage.calculateOneMinAvg(12.431d);
        final Double afterFiveInput = movingAverage.calculateOneMinAvg(19.7d);

        assertEquals(5.544333333333333d, afterThreeInput);
        assertEquals(8.021333333333333d, afterFourInput);
        assertEquals(13.821333333333333d, afterFiveInput);
    }
}