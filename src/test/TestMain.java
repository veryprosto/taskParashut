package test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestMain {
    public static void main(String[] args) {
        int start1 = new Random().nextInt(500) - 250;
        int start2 = new Random().nextInt(500) - 250;

        System.out.println("robot1 start point - " + start1 + "robot2 start point - " + start2);

        Robo robo1 = new Robo("robo1", start1, start2);
        Robo robo2 = new Robo("robo2", start2, start1);

        //Вариант 1=======================================
        /*while (robo1.getPosition() != robo2.getPosition()) {
            robo1.moveRight(robo2.getPosition());
            System.out.println("I am " + robo1.getName() + ", and I am in position " + robo1.getPosition());
            if (robo1.getPosition() == robo2.getPosition()) break;

            robo2.moveRight(robo1.getPosition());
            System.out.println("I am " + robo2.getName() + ", and I am in position " + robo2.getPosition());
        }*/
        //конец варианта 1===========================

        //Вариант 2=======================================
        try {
            final BlockingQueue<Robo> queue = new ArrayBlockingQueue<>(1);
            queue.put(robo1);

            RunRobots r1 = new RunRobots(robo1, robo2, queue);
            RunRobots r2 = new RunRobots(robo2, robo1, queue);

            r1.start();
            r2.start();
            r1.join();
            r2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //конец варианта 2=============================

        System.out.println("роботы встретились, на позициях " + robo1.getPosition() + " " + robo2.getPosition());
    }
}
