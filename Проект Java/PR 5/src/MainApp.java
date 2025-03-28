package com.primer.commandpattern;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu();
        menu.addCommand("scale", new ScaleCommand(2.0));
        menu.addCommand("sort", new SortCommand());

        MacroCommand macro = new MacroCommand();
        macro.addCommand(new ScaleCommand(1.1));
        macro.addCommand(new SortCommand());
        menu.addCommand("macro", macro);

        menu.addCommand("undo", new UndoCommand());

        // Демо-дані
        CalculationManager manager = CalculationManager.getInstance();
        manager.addItem(new CalculationData(1.0, 3.0));
        manager.addItem(new CalculationData(2.0, 2.0));
        manager.addItem(new CalculationData(3.0, 1.0));

        boolean exit = false;
        while (!exit) {
            System.out.println("\nМеню команд:");
            System.out.println(" scale - масштабувати a та b x2");
            System.out.println(" sort  - відсортувати за a");
            System.out.println(" macro - макрокоманда (scale x1.1 + sort)");
            System.out.println(" undo  - скасувати останню команду");
            System.out.println(" print - вивести колекцію");
            System.out.println(" exit  - вихід");
            System.out.print("Введіть команду: ");

            String cmdKey = sc.nextLine().trim().toLowerCase();
            switch (cmdKey) {
                case "exit":
                    exit = true;
                    break;
                case "print":
                    printCollection();
                    break;
                default:
                    menu.executeCommand(cmdKey);
            }
        }

        sc.close();
        System.out.println("Програма завершена.");
    }

    private static void printCollection() {
        CalculationManager manager = CalculationManager.getInstance();
        System.out.println("Поточна колекція:");
        for (CalculationData data : manager.getItems()) {
            System.out.println(data);
        }
    }
}