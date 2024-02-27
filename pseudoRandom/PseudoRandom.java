package projects.pseudoRandom;

public class PseudoRandom {
    //starting value
    private int seed;
    //linear multiplier
    private final int multiplier;
    //linear increment
    private final int increment;
    //fold the linear transformation over the modulus
    private final int modulus;
    //for the Box Muller transform, which alternates between cos and sin return values
    private int ticker;

    //this object can generate pseudoRandom integers uniformly distributed in the interval [0, modulus) - between 0 and the modulus - for good choice of
    //field values
    public PseudoRandom(int seed, int multiplier, int increment, int modulus) {
        this.seed = seed;
        this.multiplier = multiplier;
        this.increment = increment;
        this.modulus = modulus;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    //generate the next value
    public int nextRand() {
        int next = (multiplier*seed + increment)%modulus;
        seed = next;
        return next;
    }

    public void printNext() {
        System.out.println(nextRand());
    }

    //generates uniformly distributed pseudorandom numbers in [0, 1) for good values of fields
    public double nextNormalizedRand() {
        int next = nextRand();
        return next/((double) modulus);
    }

    public void printNormalizedNext() {
        System.out.println(nextNormalizedRand());
    }

    //for 1 million experiments, print the occurrences in a 10x partitioned unit interval [0, 1) - ie, generate & print a histogram
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

    //generate pseudorandom doubles in a crappy approximation to a Gaussian distribution
    public double nextGaussian(double median, double variance) {
        double r1 = nextNormalizedRand();
        double r2 = nextNormalizedRand();
        double r3 = nextNormalizedRand();

        //this approximation is crap - see the test histogram!
        return median + (2*(r1 + r2 + r3) - 3)*variance;
    }

    public void gaussianExperiments(double median, double variance) {
        //generates the test histogram
        int[] occurrences = new int[10];

        for (int i = 0; i < 999999; i++) {
            double next = nextGaussian(median, variance);

            //standard deviation is square root of variance
            double deviation = Math.sqrt(variance);

            //standard partitioning for Gaussian distribution out to 5 standard deviations
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

    //Generate a better sequence of pseudorandom doubles with a Gaussian distribution
    public double nextBetterGaussian() {
        double r1 = nextNormalizedRand();
        double r2 = nextNormalizedRand();

        //this is the Box Muller transform. Gives a Gaussian with mean 0, variance 1.
        //ToDO: extend to Gaussian with any given mean & variance using Z*deviation + mean
        double sqrt = Math.sqrt(-2 * Math.log(r1));
        if (ticker == 0) return sqrt *Math.cos(2*3.14*r2);
        if (ticker == 1) return sqrt *Math.sin(2*3.14*r2);

        ticker = (ticker + 1) % 2;
        return 0.0;
    }

    //Get a bell shaped histogram FFS
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

        //Todo: clean this^ shit up.
    }
}
