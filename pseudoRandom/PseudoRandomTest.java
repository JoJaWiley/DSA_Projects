package projects.pseudoRandom;

public class PseudoRandomTest {
    public static void main(String[] args) {
        PseudoRandom pr = new PseudoRandom(1, 40, 3641, 729);

        pr.printNext();
        pr.printNext();
        pr.printNext();
        pr.printNext();

        PseudoRandom.experiments(1, 40, 3641, 729);

        PseudoRandom.experiments(1, 55, 7282, 9999);

        PseudoRandom.experiments(555, 99, 7878, 9350);

        PseudoRandom.experiments(555, 999, 78780, 935353);

        System.out.println("Gaussian from book:");
        pr.gaussianExperiments(0.5, 0.0001);
        System.out.println("---------------------------------------");
        System.out.println("Box Muller Gaussian:");
        pr.betterGaussianExperiments();

        System.out.println(pr.nextBetterGaussian());
        System.out.println(pr.nextBetterGaussian());


    }
}
