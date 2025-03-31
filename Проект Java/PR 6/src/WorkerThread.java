package com.primer.workerthread;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {
    private BlockingQueue<Command> taskQueue;
    private boolean running = true;

    public WorkerThread(BlockingQueue<Command> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Command cmd = taskQueue.take(); // take() блокує, поки немає елементів
                cmd.execute();
            } catch (InterruptedException e) {
                System.out.println("WorkerThread перервано");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void shutdown() {
        running = false;
        this.interrupt(); // перериваємо take()
    }
}