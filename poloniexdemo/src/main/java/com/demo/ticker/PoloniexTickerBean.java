package com.demo.ticker;

public class PoloniexTickerBean {

    private int id;
    private double last;
    private double lowestAsk;
    private double highestBid;
    private double percentChange;
    private double baseVolume;
    private double quoteVolume;
    private int isFrozen;
    private double high24hr;
    private double low24hr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public double getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(double lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    public double getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(double baseVolume) {
        this.baseVolume = baseVolume;
    }

    public double getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(double quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public int getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(int isFrozen) {
        this.isFrozen = isFrozen;
    }

    public double getHigh24hr() {
        return high24hr;
    }

    public void setHigh24hr(double high24hr) {
        this.high24hr = high24hr;
    }

    public double getLow24hr() {
        return low24hr;
    }

    public void setLow24hr(double low24hr) {
        this.low24hr = low24hr;
    }

    @Override
    public String toString() {
        return "PoloniexTickerBean{" +
                "id=" + id +
                ", last=" + last +
                ", lowestAsk=" + lowestAsk +
                ", highestBid=" + highestBid +
                ", percentChange=" + percentChange +
                ", baseVolume=" + baseVolume +
                ", quoteVolume=" + quoteVolume +
                ", isFrozen=" + isFrozen +
                ", high24hr=" + high24hr +
                ", low24hr=" + low24hr +
                '}';
    }
}
