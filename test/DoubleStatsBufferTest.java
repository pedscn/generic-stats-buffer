import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DoubleStatsBufferTest {
    private ArrayList<Double> list;
    private DoubleStatsBuffer stats;

    @Test
    public void getItemsTest() {
        list = new ArrayList<Double>(){{
            add(1.1);
            add(2.232);
        }};
        stats = new DoubleStatsBuffer(list, 2);
        assertEquals(stats.getCurrentBuffer(), list);
    }

    @Test
    public void getBufferSizeTest() {
        list = new ArrayList<Double>(){{
            add(1.2);
            add(2.54);
            add(3.0);
        }};

        stats = new DoubleStatsBuffer(list, 5);
        assertEquals(stats.getBufferSize(), 5);
    }

    @Test
    public void getSumTest() {
        list = new ArrayList<Double>(){{
            add(1.11);
            add(2.26);
            add(3.32);
            add(4.45);
            add(1.42);
        }};

        stats = new DoubleStatsBuffer(list, 5);
        assertEquals(stats.getSum(), Double.valueOf(1.11 + 2.26 + 3.32 + 4.45 + 1.42));
    }

    @Test
    public void getSumWhenBufferIsSmallerThanListTest() {
        list = new ArrayList<Double>(){{
            add(1.1);
            add(2.2);
            add(5.2341);
        }};

        stats = new DoubleStatsBuffer(list, 2);
        assertEquals(stats.getSum(), Double.valueOf(2.2+5.2341));
    }

    @Test
    public void getSumWhenBufferIsLargerThanListTest() {
        list = new ArrayList<Double>(){{
            add(1.1);
            add(2.2);
            add(5.2341);
        }};

        stats = new DoubleStatsBuffer(list, 10);
        assertEquals(stats.getSum(), Double.valueOf(1.1+2.2+5.2341));
    }

    @Test
    public void getMeanTest() {
        list = new ArrayList<Double>(){{
            add(1.1112);
            add(2.2312);
            add(5.2341);
        }};

        double mean = (1.1112 + 2.2312 + 5.2341)/3;

        stats = new DoubleStatsBuffer(list, 10);
        assertEquals(stats.getMean(), Double.valueOf(mean));
    }

    @Test
    public void getNegativeMeanWhenBufferIsSmallerThanListTest() {
        list = new ArrayList<Double>(){{
            add(1.1112);
            add(-2.2312);
            add(-5.2341);
            add(0d);
            add(8.2);
        }};

        double mean = (-2.2312 -5.2341 + 0 + 8.2)/4;

        stats = new DoubleStatsBuffer(list, 4);
        assertEquals(stats.getMean(), Double.valueOf(mean));
    }

    @Test
    public void addItemWhenBufferIsSmallerThanListTest() {
        list = new ArrayList<Double>(){{
            add(51.12);
            add(21.22312);
            add(-25.2341);
            add(81.2);
        }};

        stats = new DoubleStatsBuffer(list, 3);
        list.remove(0);
        stats.addToBuffer(1.15);

        list.add(1.15);
        list.remove(0);

        assertEquals(stats.getCurrentBuffer(), list);

    }

    @Test
    public void addItemWhenBufferIsLargerThanListTest() {
        list = new ArrayList<Double>(){{
            add(5.12);
            add(21.223);
            add(-1.2341);
            add(8.2);
        }};

        stats = new DoubleStatsBuffer(list, 10);
        stats.addToBuffer(1.15);
        list.add(1.15);

        assertEquals(stats.getCurrentBuffer(), list);

    }

    @Test
    public void getMedianTest() {
        list = new ArrayList<Double>(){{
            add(1.111);
            add(2.236);
            add(3.323);
            add(4.4532);
            add(1.422);
        }};

        stats = new DoubleStatsBuffer(list, 5);
        Collections.sort(list);
        assertEquals(stats.getMedian(), list.get(2));
    }

    @Test
    public void getMedianWhenListLargerThanBufferTest() {
        list = new ArrayList<Double>(){{
            add(1.11);
            add(2.1236);
            add(3.323);
            add(4.4532);
            add(1.422);
        }};

        stats = new DoubleStatsBuffer(list, 4);
        Collections.sort(list);
        assertEquals(stats.getMedian(), Double.valueOf((list.get(2)+list.get(3))/2));
    }

    @Test
    public void getStandDevTest() {
        list = new ArrayList<Double>(){{
            add(1.1);
            add(2.1);
            add(3.3);
            add(4.0);
            add(1.4);
        }};

        stats = new DoubleStatsBuffer(list, 5);
        assertEquals(stats.getStandDev(), 1.239758, 0.0001);
    }

    @Test
    public void getMeanInRangeTest() {
        list = new ArrayList<Double>(){{
            add(1.11);
            add(2.12);
            add(3.34);
            add(4.03);
            add(1.41);
        }};

        stats = new DoubleStatsBuffer(list, 5);
        assertEquals(stats.getMeanInRange(1,3), Double.valueOf((2.12+3.34)/2));
    }

    @Test
    public void getSumAfterAddTest() {
        list = new ArrayList<Double>(){{
            add(1.11);
            add(2.12);
        }};

        stats = new DoubleStatsBuffer(list, 5);
        stats.addToBuffer(3.1);
        assertEquals(stats.getSum(), Double.valueOf((2.12+1.11+3.1)));
    }
}