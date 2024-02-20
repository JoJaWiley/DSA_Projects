package projects.lock;

public class Lock {
    private int x;
    private int y;
    private int z;

    private int numberOnTop;

    private boolean isOpen;
    String[] turns = {"", "", "", "", ""};

    public Lock(int x, int y, int z) {
        combinationValidation(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setCombination(int x, int y, int z) {
        combinationValidation(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void turnKnob(String direction, int n, int t) {
        if (!(direction.equals("clockwise") || direction.equals("c-clockwise")))
            throw new IllegalArgumentException("Direction is not clockwise or c-clockwise, direction: " + direction);

        if (n < 0 || n > 39)
            throw new IllegalArgumentException("n is not between 0 and 39 inclusive, n: " + n);

        if (t < 0 || t > 4)
            throw new IllegalArgumentException("t is less than zero or just too damn high, t: " + t);

        numberOnTop = n;
        turns[t] = direction + ", " + n;
    }

    private void combinationValidation(int x, int y, int z) {
        if ((x < 0) || (x > 39) || (y < 0) || (y > 39) || (z < 0) || (z > 39))
            throw new IllegalArgumentException("x, y, or z < 0 or > 39 -- x: " + x + ", y: " + y + ", z: " + z + ".");
    }

    public void close() {
        isOpen = false;
    }

    public void attemptOpen() {
        isOpen = turns[0].equals("clockwise, " + x) && turns[1].equals("clockwise, " + x)  && turns[2].equals("c-clockwise, " + y) && turns[3].equals("c-clockwise, " + y)
                && turns[4].equals("clockwise, " + z);;
    }

    public String getLockStatus() {
        if (isOpen) return "Open";

        else
            return "Shut";
    }

    public int getNumberOnTop() {
        return numberOnTop;
    }
}
