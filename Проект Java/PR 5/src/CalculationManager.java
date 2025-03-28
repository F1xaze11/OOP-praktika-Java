package com.primer.commandpattern;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private static CalculationManager instance;

    private List<CalculationData> items = new ArrayList<>();

    private CalculationManager() {
    }

    public static CalculationManager getInstance() {
        if (instance == null) {
            instance = new CalculationManager();
        }
        return instance;
    }

    public List<CalculationData> getItems() {
        return items;
    }

    public void addItem(CalculationData data) {
        items.add(data);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(items);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            items = (List<CalculationData>) ois.readObject();
        }
    }
}