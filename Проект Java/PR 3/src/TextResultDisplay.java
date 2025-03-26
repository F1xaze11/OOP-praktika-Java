package com.primer.serialization;

import java.util.List;

public class TextResultDisplay implements ResultDisplay {
    @Override
    public void display(List<CalculationData> dataList) {
        System.out.println("=== Текстове відображення результатів ===");
        for (CalculationData data : dataList) {
            System.out.println(data);
        }
        System.out.println("=== Кінець відображення ===");
    }
}