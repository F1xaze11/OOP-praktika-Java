package com.primer.workerthread;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ParallelAppTest {

    @Test
    public void testMinCommandRealResult() throws InterruptedException {
        // Очищаємо й заповнюємо CalculationManager
        CalculationManager manager = CalculationManager.getInstance();
        manager.clear();

        manager.addItem(new CalculationData(10.0, 1.0));
        manager.addItem(new CalculationData(5.0, 2.0));
        manager.addItem(new CalculationData(7.0, 3.0));

        // Створюємо чергу завдань і запускаємо воркер-потік
        BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
        WorkerThread worker = new WorkerThread(queue);
        worker.start();

        // Створюємо саме ТУ команду, яку хочемо перевірити
        MinCommand cmd = new MinCommand();

        // Кладемо команду в чергу
        queue.put(cmd);

        // Даємо час воркеру виконати команду
        Thread.sleep(300);

        // Тепер перевіряємо, чи знайдений мінімум дорівнює 5.0
        Assert.assertEquals(Double.valueOf(5.0), cmd.getFoundMin());

        // Коректно завершуємо потік
        worker.shutdown();
        worker.join();
    }
}