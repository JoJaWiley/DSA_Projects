package projects.statistician;

public class Statistician {
    private double length;
    private double sum;
    private double last;
    private double smallest;
    private double largest;

    public void nextNumber(double number) {
        length++;
        sum+= number;
        last = number;
        if (number < smallest) smallest = number;
        if (number > largest) largest = number;
    }

    public double getLength() {
        return length;
    }

    public double getSum() {
        return sum;
    }

    public double getLast() {
        if (length == 0) return Double.NaN;
        return last;
    }

    public double getSmallest() {
        if (length == 0) return Double.NaN;
        return smallest;
    }

    public double getLargest() {
        if (length == 0) return Double.NaN;
        return largest;
    }

}
