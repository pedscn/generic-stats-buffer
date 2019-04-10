import java.util.ArrayList;
import java.util.Collections;

class DoubleStatsBuffer extends StatsBuffer<Double> {

    DoubleStatsBuffer(ArrayList<Double> items, int n) {
        super(items, n);
    }

    @Override
    Double getMean() {
        return (getSum()/currentBuffer.size());
    }

    private boolean isOdd(int d) {
        return ((d & 1) != 0);
    }

    @Override
    Double getMedian() {
        ArrayList<Double> sortedBuffer = currentBuffer;
        Collections.sort(sortedBuffer);
        if (!isOdd(currentBuffer.size())) {
            return (sortedBuffer.get(currentBuffer.size()/2) + sortedBuffer.get((currentBuffer.size()/2) - 1))/2;
        } else {
            return sortedBuffer.get(currentBuffer.size() / 2);
        }
    }

    private double getVariance() {
        double currentSum = 0;
        double mean = getMean();
        for (double item : currentBuffer) {
            currentSum += (item-mean)*(item-mean);
        }
        return currentSum/(currentBuffer.size()-1);
    }

    @Override
    Double getStandDev() {
        return Math.sqrt(getVariance());
    }

    @Override
    Double getSum() {
        double currentSum = 0;
        for (Double item : currentBuffer) {
            currentSum += item;
        }
        return currentSum;
    }

    Double getMeanInRange(int a, int b) {
        double currentSum = 0;
        for (int i = a ; i < b ; i++) {
            currentSum += currentBuffer.get(i);
        }
        return currentSum/(b-a);
    }
}
