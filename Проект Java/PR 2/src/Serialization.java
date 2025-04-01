package com.example.serialization;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Scanner;
public class SerializationDemo {
    private static final String FILE_NAME = "calculation.ser";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть значення a (дійсне число): ");
        double a = parseDouble(scanner.nextLine());
        System.out.print("Введіть значення b (дійсне число): ");
        double b = parseDouble(scanner.nextLine());
        System.out.print("Введіть ціле число: ");
        int number = parseInt(scanner.nextLine());
        CalculationData data = new CalculationData(a, b);
        data.setNumber(number);
        System.out.print("Введіть додаткову нотатку: ");
        String note = scanner.nextLine();
        data.setNote(note);
        Calculator calculator = new Calculator(data);
        calculator.computeSum();
        calculator.computeRepresentations();
        System.out.println("Сума a + b: " + calculator.getData().getResult());
        System.out.println("Двійкове представлення: " + calculator.getData().getBinaryRepresentation());
        System.out.println("Вісімкове представлення: " + calculator.getData().getOctalRepresentation());
        System.out.println("Шістнадцяткове представлення: " + calculator.getData().getHexRepresentation());
        System.out.print("Зберегти результати у файл? (y/n): ");
        String choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            serializeData(calculator.getData());
            System.out.println("Об’єкт серіалізовано у " + FILE_NAME);
        }
        System.out.print("Відновити дані з файлу? (y/n): ");
        choice = scanner.nextLine();
        if ("y".equalsIgnoreCase(choice)) {
            CalculationData loadedData = deserializeData();
            if (loadedData != null) {
                System.out.println("Відновлено об’єкт: " + loadedData);
            }
        }
        scanner.close();
    }
    private static void serializeData(CalculationData data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Помилка серіалізації: " + e.getMessage());
        }
    }
    private static CalculationData deserializeData() {
        CalculationData data = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            data = (CalculationData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Помилка десеріалізації: " + e.getMessage());
        }
        return data;
    }
    private static double parseDouble(String input) {
        return Double.parseDouble(input.trim().replace(',', '.'));
    }
    private static int parseInt(String input) {
        return Integer.parseInt(input.trim());
    }
}
