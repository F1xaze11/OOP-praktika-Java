package com.primer.serialization;

import java.io.IOException;
import java.util.Scanner;

public class MainFactoryDemo {
    private static final String FILE_NAME = "collection.ser";
    private static ResultDisplayFactory factory = new TextResultDisplayFactory();
    // За замовчуванням - текстовий формат

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculationManager manager = new CalculationManager();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1) Додати обчислення");
            System.out.println("2) Показати всі обчислення (обраний формат)");
            System.out.println("3) Зберегти у файл");
            System.out.println("4) Завантажити з файлу");
            System.out.println("5) Змінити формат відображення (Text/Table)");
            System.out.println("6) Вихід");
            System.out.print("Ваш вибір: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addCalculation(scanner, manager);
                    break;
                case "2":
                    showAll(manager);
                    break;
                case "3":
                    save(manager);
                    break;
                case "4":
                    load(manager);
                    break;
                case "5":
                    switchDisplayFormat(scanner);
                    break;
                case "6":
                    exit = true;
                    break;
                default:
                    System.out.println("Невідома команда.");
            }
        }
        scanner.close();
        System.out.println("Програма завершена.");
    }

    private static void addCalculation(Scanner scanner, CalculationManager manager) {
        System.out.print("Введіть a: ");
        double a = parseDouble(scanner.nextLine());
        System.out.print("Введіть b: ");
        double b = parseDouble(scanner.nextLine());
        System.out.print("Введіть ціле number: ");
        int number = safeParseInt(scanner);
        System.out.print("Введіть нотатку: ");
        String note = scanner.nextLine();

        CalculationData data = new CalculationData(a, b);
        data.setNumber(number);
        data.setNote(note);

        Calculator calc = new Calculator(data);
        calc.computeSum();
        calc.computeRepresentations();

        manager.addItem(calc.getData());
        System.out.println("Додано нове обчислення: " + calc.getData());
    }

    private static void showAll(CalculationManager manager) {
        // Динамічне призначення: factory.createDisplay()
        // поверне або TextResultDisplay, або TableResultDisplay
        ResultDisplay display = factory.createDisplay();
        display.display(manager.getItems());
    }

    private static void save(CalculationManager manager) {
        try {
            manager.saveToFile(FILE_NAME);
            System.out.println("Колекцію збережено у " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
    }

    private static void load(CalculationManager manager) {
        try {
            manager.loadFromFile(FILE_NAME);
            System.out.println("Колекцію відновлено з " + FILE_NAME);
        } catch (Exception e) {
            System.out.println("Помилка відновлення: " + e.getMessage());
        }
    }

    private static void switchDisplayFormat(Scanner scanner) {
        System.out.print("Оберіть формат (t - text, b - table): ");
        String mode = scanner.nextLine().trim().toLowerCase();
        if ("t".equals(mode)) {
            factory = new TextResultDisplayFactory();
            System.out.println("Формат змінено на: Text");
        } else if ("b".equals(mode)) {
            factory = new TableResultDisplayFactory();
            System.out.println("Формат змінено на: Table");
        } else {
            System.out.println("Невідомий формат. Залишається попередній.");
        }
    }

    private static double parseDouble(String input) {
        return Double.parseDouble(input.trim().replace(',', '.'));
    }

    private static int safeParseInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Помилка: введене значення не є цілим числом. Спробуйте ще раз: ");
            }
        }
    }
}