package test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Robo {
    private final String name;
    private final AtomicInteger position = new AtomicInteger();// лучше заменить обычным интом
    private final int myStart;
    private final int friendStart;
    private boolean startPoint;

    public Robo(String name, int myStart, int friendStart) {
        this.name = name;
        this.myStart = myStart;
        this.friendStart = friendStart;
        position.set(myStart);
    }

    public int getPosition() {
        return position.get();
    }

    public void moveRight(int otherRobotPosition) {
        position.incrementAndGet();
        if (getPosition() == otherRobotPosition) return;
        if (needToSpeedUp()) {
            position.incrementAndGet();
        }
    }

    public boolean needToSpeedUp() {
        if (!startPoint) {
            startPoint = IntStream.of(myStart, friendStart).anyMatch(x -> x == getPosition());
            if (startPoint) {
                System.out.println();
                System.out.println("Parachute is found");
                System.out.println("Robot " + getName() + " going to go faster ");
                System.out.println();
            }
        }
        return startPoint;
    }

    public String getName() {
        return name;
    }
}
