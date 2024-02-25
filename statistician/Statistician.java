package projects.statistician;

public class Statistician {
    private int length;
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

    public int getLength() {
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

    public double arithmeticMean() {
        return sum/length;
    }

    public static Statistician add(Statistician r, Statistician s) {
        Statistician thisPlusS = new Statistician();
        thisPlusS.length = r.length + s.length;
        thisPlusS.sum = r.sum + s.sum;

        if (r.length == 0 && s.length == 0) thisPlusS.last = Double.NaN;
        else if (s.length == 0) thisPlusS.last = r.last;
        else thisPlusS.last = s.last;

        if (r.length == 0 && s.length == 0) thisPlusS.smallest = Double.NaN;
        else if (r.length == 0) thisPlusS.smallest = s.smallest;
        else if (s.length == 0) thisPlusS.smallest = r.smallest;
        else if (s.smallest < r.smallest) thisPlusS.smallest = s.smallest;
        else thisPlusS.smallest = r.smallest;

        if (r.length == 0 && s.length == 0) thisPlusS.largest = Double.NaN;
        else if (r.length == 0) thisPlusS.largest = s.largest;
        else if (s.length == 0) thisPlusS.largest = r.largest;
        else if (s.largest > r.largest) thisPlusS.largest = s.largest;
        else thisPlusS.largest = r.largest;

        return thisPlusS;
    }

    public void prints() {
        System.out.println(length);
        System.out.println(sum);
        System.out.println(this.getLast());
        System.out.println(this.getSmallest());
        System.out.println(this.getLargest());
    }
}
