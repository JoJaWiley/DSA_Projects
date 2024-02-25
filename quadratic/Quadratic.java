package projects.quadratic;

public class Quadratic {
    private double a;
    private double b;
    private double c;

    public Quadratic() {
        a = 0;
        b = 0;
        c = 0;
    }

    public void setCoefficients(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }

    public double evaluateAt(double x) {
        return a*x*x + b*x + c;
    }

    public static Quadratic sum(Quadratic q1, Quadratic q2) {
        Quadratic q3 = new Quadratic();
        q3.setCoefficients(q1.a + q2.a, q1.b + q2.b, q1.c + q2.c);
        return q3;
    }

    public static Quadratic scale(double r, Quadratic q) {
        Quadratic q3 = new Quadratic();
        q3.setCoefficients(r*q.a, r*q.b, r*q.c);
        return q3;
    }

    public int numberOfRealRoots() {
        if (a == 0 && b == 0 && c == 0) return 3;
        else if (a == 0 && b == 0) return 0;
        else if (a == 0) return 1;
        else if (b*b < 4*a*c) return 0;
        else if (b*b == 4*a*c) return 1;
        else return 2;
    }

    public double getRoot1() {
        if (numberOfRealRoots() == 0) return Double.NaN;
        else if (numberOfRealRoots() == 3) return 0;
        else if (a == 0) return -c/b;
        else if (b*b == 4*a*c) return -b/(2*a);
        else return (-b - Math.sqrt(b*b - 4*a*c))/2*a;
    }

    public double getRoot2() {
        if (numberOfRealRoots() == 0 || numberOfRealRoots() == 1 || numberOfRealRoots() == 3)
            return getRoot1();
        else return (-b + Math.sqrt(b*b - 4*a*c))/2*a;
    }

    public void print() {
        System.out.println("a: " + a + ", " + "b: " + b + ", " + "c: " + c + ", ");
    }

    public void printRootInfo() {
        System.out.println(numberOfRealRoots());
        System.out.println(getRoot1());
        System.out.println(getRoot2());
    }
}
