package com.primer.serialization;

import java.util.List;

public class TableResultDisplay extends TextResultDisplay {

    private static final String[] HEADERS = {
            "a", "b", "result", "number", "binary", "octal", "hex"
    };

    @Override
    public void display(List<CalculationData> dataList) {
        display(dataList, 12);
    }

    public void display(List<CalculationData> dataList, int columnWidth) {
        System.out.println("=== Табличне відображення результатів ===");

        printSeparatorLine(columnWidth, HEADERS.length);
        printHeaderRow(columnWidth);
        printSeparatorLine(columnWidth, HEADERS.length);

        for (CalculationData data : dataList) {
            // Формуємо значення полів у порядку, відповідному HEADERS
            String[] rowValues = {
                    String.format("%.2f", data.getA()),
                    String.format("%.2f", data.getB()),
                    String.format("%.2f", data.getResult()),
                    String.valueOf(data.getNumber()),
                    data.getBinaryRepresentation(),
                    data.getOctalRepresentation(),
                    data.getHexRepresentation()
            };
            printRow(rowValues, columnWidth);
        }

        printSeparatorLine(columnWidth, HEADERS.length);
        System.out.println("=== Кінець таблиці ===");
    }

    private void printSeparatorLine(int columnWidth, int columns) {
        for (int c = 0; c < columns; c++) {
            System.out.print("+");
            for (int i = 0; i < columnWidth; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    private void printHeaderRow(int columnWidth) {
        for (String header : HEADERS) {
            String cell = String.format(" %-"+(columnWidth-2)+"s", header);
            System.out.print("|" + cell);
        }
        System.out.println("|");
    }

    private void printRow(String[] values, int columnWidth) {
        for (String val : values) {
            if (val == null) val = "null";
            String cell = String.format(" %-"+(columnWidth-2)+"s", val);
            System.out.print("|" + cell);
        }
        System.out.println("|");
    }
}