package projects.statistician;

public class StatisticianTest {
    public static void main(String[] args) {
        Statistician s1 = new Statistician();
        Statistician s2 = new Statistician();

        s1.nextNumber(1.1);
        s1.nextNumber(0.8);
        s1.nextNumber(-2.5);

        s1.prints();
        s2.prints();

        Statistician s3 = Statistician.add(s1, s2);
        Statistician s4 = Statistician.add(s2, s1);

        s3.prints();
        s4.prints();

        s2.nextNumber(12.2);
        s2.nextNumber(-0.5);
        s2.nextNumber(33.9);
        s2.nextNumber(-1);

        s3 = Statistician.add(s1, s2);
        s4 = Statistician.add(s2, s1);

        s3.prints();
        s4.prints();
    }
}
