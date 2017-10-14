package ru.oleg.korytnikov;

public class Timer implements Runnable {

    private int count = 0;
    private boolean isFinished = false;

    public void run() {
        while (!isFinished){
            try {
                Thread.sleep(100);
                count ++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The recording was created. It took " + count/10 + " seconds.");
    }

    public void stop(){
        isFinished = true;
    }
}
