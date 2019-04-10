import java.util.ArrayList;
import java.util.List;

abstract class StatsBuffer<T> {
    ArrayList<T> currentBuffer = new ArrayList<>();
    private int bufferSize;
    StatsBuffer(List<T> items, int n) {
        if (n<items.size()) {
            currentBuffer.addAll(items.subList(items.size()-n, items.size()));
        } else {
            currentBuffer.addAll(items);
        }
        bufferSize = n;
    }
    abstract T getMean();
    abstract T getMedian();
    abstract T getStandDev();
    abstract T getSum();

    int getBufferSize() {
        return bufferSize;
    }

    ArrayList<T> getCurrentBuffer() {
        return currentBuffer;
    }

    void addToBuffer(T item) {
        if (currentBuffer.size()>=bufferSize) {
            currentBuffer.remove(0);
        }
        currentBuffer.add(item);
    }
}
