package projects.quadratic;

public class QuadraticTest {
    public static void main(String[] args) {
        Quadratic q1 = new Quadratic();
        Quadratic q2 = new Quadratic();

        q1.setCoefficients(3.4, 5, 6);
        q2.setCoefficients(-3.4, -5, -6);

        Quadratic q3 = Quadratic.sum(q1, q2);
        q3.print();

        Quadratic q4 = Quadratic.scale(2, q1);
        q4.print();

        Quadratic q5 = Quadratic.sum(q4, q2);
        q5.print();

        q5.printRootInfo();

        Quadratic q6 = new Quadratic();
        Quadratic q7 = new Quadratic();
        Quadratic q8 = new Quadratic();
        Quadratic q9 = new Quadratic();
        Quadratic q10 = new Quadratic();

        q6.setCoefficients(0, 0, 0);
        q7.setCoefficients(0, 0, 100);
        q8.setCoefficients(0, 2, 3);
        q9.setCoefficients(1, 2, 1);
        q10.setCoefficients(1, 5, 1);

        q6.printRootInfo();

        q7.printRootInfo();

        q8.printRootInfo();

        q9.printRootInfo();

        q10.printRootInfo();

        double x = 0;

        //q8
        x = -1.5;
        System.out.println(2*x + 3);

        //q9
        x = -1.0;
        System.out.println(x*x + 2*x + 1);

        //q10
        x = -4.7912878474779195;
        System.out.println(x*x + 5*x + 1);

        x = -0.20871215252208009;
        System.out.println(x*x + 5*x + 1);
    }
}
