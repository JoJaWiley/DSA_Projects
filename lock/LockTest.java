package projects.lock;

public class LockTest {

    public static void main(String[] args) {
        Lock lock = new Lock(1, 20, 3);

        lock.close();
        lock.attemptOpen();
        System.out.println(lock.getLockStatus());

        lock.turnKnob("clockwise", 1, 0);
        lock.turnKnob("clockwise", 1, 1);
        lock.turnKnob("c-clockwise", 20, 2);
        lock.turnKnob("c-clockwise", 20, 3);
        lock.turnKnob("clockwise", 3, 4);
        for (int i = 0; i < 5; i++) System.out.println(lock.turns[i]);

        lock.attemptOpen();
        System.out.println(lock.getLockStatus());

        lock.close();
        lock.attemptOpen();
        System.out.println(lock.getLockStatus());

        lock.close();
        lock.turnKnob("clockwise", 4, 0);
        for (int i = 0; i < 5; i++) System.out.println(lock.turns[i]);

        lock.attemptOpen();
        System.out.println(lock.getLockStatus());

        lock.turnKnob("clockwise", 1, 0);
        lock.turnKnob("clockwise", 2, 1);
        lock.turnKnob("c-clockwise", 20, 2);
        lock.turnKnob("c-clockwise", 20, 3);
        lock.turnKnob("clockwise", 3, 4);
        for (int i = 0; i < 5; i++) System.out.println(lock.turns[i]);

        lock.attemptOpen();
        System.out.println(lock.getLockStatus());

        lock.turnKnob("clockwise", 1, 0);
        lock.turnKnob("clockwise", 1, 1);
        lock.turnKnob("c-clockwise", 19, 2);
        lock.turnKnob("c-clockwise", 20, 3);
        lock.turnKnob("clockwise", 3, 4);
        for (int i = 0; i < 5; i++) System.out.println(lock.turns[i]);

        lock.attemptOpen();
        System.out.println(lock.getLockStatus());

        lock.turnKnob("clockwise", 1, 0);
        lock.turnKnob("clockwise", 1, 1);
        lock.turnKnob("c-clockwise", 20, 2);
        lock.turnKnob("c-clockwise", 19, 3);
        lock.turnKnob("clockwise", 3, 4);
        for (int i = 0; i < 5; i++) System.out.println(lock.turns[i]);

        lock.attemptOpen();
        System.out.println(lock.getLockStatus());

        lock.turnKnob("clockwise", 1, 0);
        lock.turnKnob("clockwise", 1, 1);
        lock.turnKnob("c-clockwise", 20, 2);
        lock.turnKnob("c-clockwise", 20, 3);
        lock.turnKnob("c-clockwise", 3, 4);
        for (int i = 0; i < 5; i++) System.out.println(lock.turns[i]);

        lock.attemptOpen();
        System.out.println(lock.getLockStatus());
    }
}
