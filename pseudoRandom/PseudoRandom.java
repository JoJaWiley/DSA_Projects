package projects.pseudoRandom;

public class PseudoRandom {
    private int seed;
    private final int multiplier;
    private final int increment;
    private final int modulus;
    private int ticker;

    public PseudoRandom(int seed, int multiplier, int increment, int modulus) {
        this.seed = seed;
        this.multiplier = multiplier;
        this.increment = increment;
        this.modulus = modulus;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int nextRand() {
        int next = (multiplier*seed + increment)%modulus;
        seed = next;
        return next;
    }

    public void printNext() {
        System.out.println(nextRand());
    }

    public double nextNormalizedRand() {
        int next = nextRand();
        return next/((double) modulus);
    }

    public void printNormalizedNext() {
        System.out.println(nextNormalizedRand());
    }

    public static void experiments(int seed, int multiplier, int increment, int modulus) {
        PseudoRandom pr = new PseudoRandom(seed, multiplier, increment, modulus);

        int[] occurrences = new int[10];

        for (int i = 0; i < 999999; i++) {
            double next = pr.nextNormalizedRand();

            if (next >= 0 && next < 0.1) occurrences[0]++;
            else if (next >= 0.1 && next < 0.2) occurrences[1]++;
            else if (next >= 0.2 && next < 0.3) occurrences[2]++;
            else if (next >= 0.3 && next < 0.4) occurrences[3]++;
            else if (next >= 0.4 && next < 0.5) occurrences[4]++;
            else if (next >= 0.5 && next < 0.6) occurrences[5]++;
            else if (next >= 0.6 && next < 0.7) occurrences[6]++;
            else if (next >= 0.7 && next < 0.8) occurrences[7]++;
            else if (next >= 0.8 && next < 0.9) occurrences[8]++;
            else if (next >= 0.9 && next < 1.0) occurrences[9]++;
        }

        System.out.println("Range          Number of Occurrences");
        System.out.println("[0.0..0.1)          " + occurrences[0]);
        System.out.println("[0.1..0.2)          " + occurrences[1]);
        System.out.println("[0.2..0.3)          " + occurrences[2]);
        System.out.println("[0.3..0.4)          " + occurrences[3]);
        System.out.println("[0.4..0.5)          " + occurrences[4]);
        System.out.println("[0.5..0.6)          " + occurrences[5]);
        System.out.println("[0.6..0.7)          " + occurrences[6]);
        System.out.println("[0.7..0.8)          " + occurrences[7]);
        System.out.println("[0.8..0.9)          " + occurrences[8]);
        System.out.println("[0.9..1.0)          " + occurrences[9]);
    }

    public double nextGaussian(double median, double variance) {
        double r1 = nextNormalizedRand();
        double r2 = nextNormalizedRand();
        double r3 = nextNormalizedRand();

        return median + (2*(r1 + r2 + r3) - 3)*variance;
    }

    public void gaussianExperiments(double median, double variance) {
        int[] occurrences = new int[10];

        for (int i = 0; i < 999999; i++) {
            double next = nextGaussian(median, variance);

            //standard deviation
            double deviation = Math.sqrt(variance);

            if (next >= median - 5*deviation && next < median - 4*deviation) occurrences[0]++;
            else if (next >= median - 4*deviation && next < median - 3*deviation) occurrences[1]++;
            else if (next >= median - 3*deviation && next < median - 2*deviation) occurrences[2]++;
            else if (next >= median - 2*deviation && next < median - deviation) occurrences[3]++;
            else if (next >= median - deviation && next < median) occurrences[4]++;
            else if (next >= median && next < median + deviation) occurrences[5]++;
            else if (next >= median + deviation && next < median + 2*deviation) occurrences[6]++;
            else if (next >= median + 2*deviation && next < median + 3*deviation) occurrences[7]++;
            else if (next >= median + 3*deviation && next < median + 4*deviation) occurrences[8]++;
            else if (next >= median + 4*deviation && next < next + 5*deviation) occurrences[9]++;
        }

        System.out.println("Range                                     Number of Occurrences");
        System.out.println("[median - 5sigma..median - 4sigma)               " + occurrences[0]);
        System.out.println("[median - 4sigma..median - 3sigma)               " + occurrences[1]);
        System.out.println("[median - 3sigma..median - 2sigma)               " + occurrences[2]);
        System.out.println("[median - 2sigma..median - sigma)                " + occurrences[3]);
        System.out.println("[median - sigma..median)                         " + occurrences[4]);
        System.out.println("[median..median + sigma)                         " + occurrences[5]);
        System.out.println("[median + sigma..median + 2sigma)                " + occurrences[6]);
        System.out.println("[median + 2sigma..median + 3sigma)               " + occurrences[7]);
        System.out.println("[median + 3sigma..median + 4sigma)               " + occurrences[8]);
        System.out.println("[[median + 4sigma..median + 5sigma)              " + occurrences[9]);
    }

    public double nextBetterGaussian() {
        double r1 = nextNormalizedRand();
        double r2 = nextNormalizedRand();

        double sqrt = Math.sqrt(-2 * Math.log(r1));
        if (ticker == 0) return sqrt *Math.cos(2*3.14*r2);
        if (ticker == 1) return sqrt *Math.sin(2*3.14*r2);

        ticker = (ticker + 1) % 2;
        return 0.0;
    }

    public void betterGaussianExperiments() {
        int[] occurrences = new int[10];

        for (int i = 0; i < 999999; i++) {
            double next = nextBetterGaussian();

            //mean from box muller is 0, standard deviation is 1

            if (next >= -5 && next < -4) occurrences[0]++;
            else if (next >= -4 && next < -3) occurrences[1]++;
            else if (next >= -3 && next < -2) occurrences[2]++;
            else if (next >= -2 && next < -1) occurrences[3]++;
            else if (next >= -1 && next < 0) occurrences[4]++;
            else if (next >= 0 && next < 1) occurrences[5]++;
            else if (next >= 1 && next < 2) occurrences[6]++;
            else if (next >= 2 && next < 3) occurrences[7]++;
            else if (next >= 3 && next < 4) occurrences[8]++;
            else if (next >= 4 && next < 5) occurrences[9]++;
        }

        System.out.println("Range                 Number of Occurrences");
        System.out.println("[-5..-4)              " + occurrences[0]);
        System.out.println("[-4..-3)              " + occurrences[1]);
        System.out.println("[-3..-2)              " + occurrences[2]);
        System.out.println("[-2..-1)              " + occurrences[3]);
        System.out.println("[-1..0)               " + occurrences[4]);
        System.out.println("[0..1)                " + occurrences[5]);
        System.out.println("[1..2)                " + occurrences[6]);
        System.out.println("[2..3)                " + occurrences[7]);
        System.out.println("[3..4)                " + occurrences[8]);
        System.out.println("[4..5)                " + occurrences[9]);
    }
}
