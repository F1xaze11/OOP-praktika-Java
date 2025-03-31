package com.primer.workerthread;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainParallelApp {

    private static BlockingQueue<Command> taskQueue = new LinkedBlockingQueue<>();
    private static WorkerThread worker;

    public static void main(String[] args) {
        CalculationManager manager = CalculationManager.getInstance();
        manager.addItem(new CalculationData(1.0, 2.0));
        manager.addItem(new CalculationData(3.0, 4.0));
        manager.addItem(new CalculationData(2.0, 5.0));

        worker = new WorkerThread(taskQueue);
        worker.start();

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1) min   (знайти мінімум a)");
            System.out.println("2) max   (знайти максимум a)");
            System.out.println("3) avg   (середнє значення a)");
            System.out.println("4) print (вивести колекцію)");
            System.out.println("5) add   (додати елемент)");
            System.out.println("6) exit  (вихід)");
            System.out.print("Вибір: ");
            String choice = sc.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1":
                case "min":
                    enqueueCommand(new MinCommand());
                    break;
                case "2":
                case "max":
                    enqueueCommand(new MaxCommand());
                    break;
                case "3":
                case "avg":
                    enqueueCommand(new AverageCommand());
                    break;
                case "4":
                case "print":
                    printCollection();
                    break;
                case "5":
                case "add":
                    addData(sc);
                    break;
                case "6":
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Невідома команда.");
            }
        }

        worker.shutdown();
        sc.close();
        System.out.println("Програма завершена.");
    }

    private static void enqueueCommand(Command cmd) {
        try {
            taskQueue.put(cmd);
            System.out.println("Команду додано до черги.");
        } catch (InterruptedException e) {
            System.out.println("Помилка enqueueCommand: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static void printCollection() {
        CalculationManager manager = CalculationManager.getInstance();
        System.out.println("Поточна колекція:");
        for (CalculationData d : manager.getItems()) {
            System.out.println(d);
        }
    }

    private static void addData(Scanner sc) {
        System.out.print("Введіть a: ");
        double a = safeParseDouble(sc.nextLine());
        System.out.print("Введіть b: ");
        double b = safeParseDouble(sc.nextLine());
        CalculationData data = new CalculationData(a, b);
        CalculationManager.getInstance().addItem(data);
        System.out.println("Додано елемент: " + data);
    }

    private static double safeParseDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}