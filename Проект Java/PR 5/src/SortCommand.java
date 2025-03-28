package com.primer.commandpattern;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortCommand implements Command {
    private List<CalculationData> previousState = new ArrayList<>();

    @Override
    public void execute() {
        CalculationManager manager = CalculationManager.getInstance();

        // Зберігаємо копію списку (для undo)
        for (CalculationData d : manager.getItems()) {
            CalculationData copy = new CalculationData(d.getA(), d.getB());
            copy.setResult(d.getResult());
            copy.setNumber(d.getNumber());
            copy.setNote(d.getNote());
            previousState.add(copy);
        }

        // Сортуємо за a
        manager.getItems().sort(Comparator.comparingDouble(CalculationData::getA));
        System.out.println("Виконано сортування за a (зростання)");
    }

    @Override
    public void undo() {
        // Повертаємося до попереднього списку
        CalculationManager manager = CalculationManager.getInstance();
        manager.getItems().clear();
        manager.getItems().addAll(previousState);
        System.out.println("Скасовано сортування");
    }
}