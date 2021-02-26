package test;

import java.util.concurrent.BlockingQueue;

public class RunRobots extends Thread{
    private final Robo robo1;
    private final Robo robo2;
    private final BlockingQueue<Robo> queue;

    public RunRobots(Robo robo1, Robo robo2, BlockingQueue<Robo> queue) {
        this.robo1 = robo1;
        this.robo2 = robo2;
        this.queue = queue;
    }

    public boolean stopMove() {
        return robo1.getPosition() == robo2.getPosition();
    }

    @Override
    public void run() {
        while (!stopMove()) {

            try {
                Robo r = queue.take();
                if (r == robo1) {
                    r.moveRight(robo2.getPosition());
                    queue.put(robo2);
                } else {
                    r.moveRight(robo1.getPosition());
                    queue.put(robo1);
                }
                System.out.println("I am " + r.getName() + ", and I am in position " + r.getPosition());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
