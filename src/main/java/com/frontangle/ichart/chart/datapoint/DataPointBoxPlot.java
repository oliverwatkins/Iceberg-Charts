package com.frontangle.ichart.chart.datapoint;

public class DataPointBoxPlot extends DataPoint {

    public double bottomWhisker;
    public double bottom;
    public double middle;
    public double top;
    public double topWhisker;

    public DataPointBoxPlot(double x, double y,
            double bottomWhisker,
            double bottom,
            double middle,
            double top,
            double topWhisker) {
        super(x, y);

        this.bottomWhisker = bottomWhisker;
        this.bottom = bottom;
        this.middle = middle;
        this.top = top;
        this.topWhisker = topWhisker;
    }

}
