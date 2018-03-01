package java8.domain;

/**
 * Created by zhanghaojie on 2018/3/1.
 *
 * 范围类
 *
 */
public class Range {
    private final double from;
    private final double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }


    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range range = (Range) o;

        if (Double.compare(range.from, from) != 0) return false;
        return Double.compare(range.to, to) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(from);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(to);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
