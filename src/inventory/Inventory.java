package inventory;

import java.util.Arrays;

public abstract class Inventory {
    protected String[] items; // Gemeinsames Inventar-Array

    // Konstruktor: Initialisiert das Inventar
    public Inventory(String[] initialItems) {
        this.items = initialItems;
    }

    // Prüft, ob ein bestimmtes Item im Inventar vorhanden ist
    public boolean hasItem(String item) {
        for (String s : items) {
            if (s != null && s.equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }

    // Findet den Slot eines bestimmten Items im Inventar (oder -1, falls nicht vorhanden)
    public int findItemSlot(String item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].equalsIgnoreCase(item)) {
                return i;
            }
        }
        return -1; // Item wurde nicht gefunden
    }

    // Entfernt ein Item aus einem bestimmten Slot im Inventar
    public void removeItem(int slot) {
        if (slot >= 0 && slot < items.length) {
            System.out.println("Item '" + items[slot] + "' wurde aus dem Inventar entfernt.");
            items[slot] = null;
        } else {
            System.out.println("Ungültiger Inventar-Slot: " + slot);
        }
    }

    // Fügt ein neues Item ins Inventar hinzu, sofern ein freier Slot existiert
    public boolean addItem(String item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) { // Freier Slot gefunden
                items[i] = item;
                System.out.println("Item '" + item + "' wurde zum Inventar hinzugefügt.");
                return true; // Item erfolgreich hinzugefügt
            }
        }
        System.out.println("Kein freier Slot im Inventar verfügbar, Item '" + item + "' konnte nicht hinzugefügt werden.");
        return false; // Kein freier Slot verfügbar
    }

    public String[] getItems() {
        return items;
    }


    // Gibt das gesamte Inventar als String zurück
    @Override
    public String toString() {
        return "Inventar: " + Arrays.toString(items);
    }
}

