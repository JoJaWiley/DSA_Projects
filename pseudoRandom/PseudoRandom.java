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

            for (int j = 0; j < occurrences.length; j++)
                if (liesInUniformInterval(next, j)) occurrences[j]++;
        }

        printUniformHistogram(occurrences);
    }

    //divide [0, 1) into 10 subintervals. test if a double element lies in the nth subdivision.
    private static boolean liesInUniformInterval(double element, int n) {
        return (element >= n*0.1 && element < (n + 1)*0.1);
    }

    //print out the histogram
    private static void printUniformHistogram(int[] occurrences) {
        System.out.printf("%-22s%s\n", "Range", "Number of Occurrences");
        for (int i = 0; i < occurrences.length; i++) {
            System.out.printf("[%.1f..%.1f)%19d\n", i*0.1, (i + 1)*0.1, occurrences[i]);
        }
    }


    //--------------------textbook Gaussian methods

    //generate pseudorandom doubles in a crappy approximation to a Gaussian distribution
    public double nextGaussian(double median, double variance) {
        validateVariance(variance);

        double r1 = nextNormalizedRand();
        double r2 = nextNormalizedRand();
        double r3 = nextNormalizedRand();

        //this approximation is crap - see the test histogram!
        return median + (2*(r1 + r2 + r3) - 3)*variance;
    }

    public void gaussianExperiments(double median, double variance) {
        validateVariance(variance);

        //generates the test histogram
        int[] occurrences = new int[10];

        //standard deviation is square root of variance
        double deviation = Math.sqrt(variance);

        for (int i = 0; i < 999999; i++) {
            double next = nextGaussian(median, variance);

            //standard partitioning for Gaussian distribution out to 5 standard deviations
            for (int j = 0; j < occurrences.length; j++)
                if (liesInGaussianInterval(next, median, deviation, j)) occurrences[j]++;
        }

        printGaussianHistogram(occurrences, median, deviation);
    }



    private boolean liesInGaussianInterval(double element, double median, double deviation, int n) {
        return (element >= median + (n - 5)*deviation && element < median + (n - 4)*deviation);
    }

    private void printGaussianHistogram(int[] occurrences, double median, double deviation) {
        System.out.printf("%-22s%s\n", "Range", "Number of Occurrences");

        for (int i = 0; i < occurrences.length; i++) {
            double rightEndPoint = median + (i - 4)*deviation;
            if (rightEndPoint < 0)
                System.out.printf("[%.2f..%.2f)%14d\n", median + (i - 5)*deviation, median + (i - 4)*deviation, occurrences[i]);
            else if (rightEndPoint == 0)
                System.out.printf("[%.2f..%.2f)%15d\n", median + (i - 5)*deviation, median + (i - 4)*deviation, occurrences[i]);
            else
                System.out.printf("[%.2f..%.2f)%16d\n", median + (i - 5)*deviation, median + (i - 4)*deviation, occurrences[i]);
        }
    }


    private void validateVariance(double variance) {
        if (variance < 0)
            throw new IllegalArgumentException("Variance is negative! Variance: " + variance);
    }

    //---------------------my Gaussian methods

    //Generate a better sequence of pseudorandom doubles with a Gaussian distribution
    public double nextBetterGaussian(double median, double variance) {
        validateVariance(variance);
        double deviation = Math.sqrt(variance);

        //uniformly distributed random "seeds" to turn into gaussian distributed random outputs
        double r1 = nextNormalizedRand();
        double r2 = nextNormalizedRand();

        //this is the Box Muller transform. Gives a Gaussian with given median and variance.
        double sqrt = Math.sqrt(-2 * Math.log(r1));
        if (ticker == 0) return sqrt *Math.cos(2*3.14*r2)*deviation + median;
        if (ticker == 1) return sqrt *Math.sin(2*3.14*r2)*deviation + median;

        ticker = (ticker + 1) % 2;
        return 0.0;
    }

    //Get a bell shaped histogram FFS
    public void betterGaussianExperiments(double median, double variance) {
        validateVariance(variance);
        double deviation = Math.sqrt(variance);

        //record the occurrences of random variable in each Gaussian interval
        int[] occurrences = new int[10];

        for (int i = 0; i < 999999; i++) {
            double next = nextBetterGaussian(median, variance);

            //mean from box muller is 0, standard deviation is 1
            for (int j = 0; j < occurrences.length; j++)
                if (liesInGaussianInterval(next, median, deviation, j)) occurrences[j]++;
        }

        printGaussianHistogram(occurrences, median, deviation);

    }
}