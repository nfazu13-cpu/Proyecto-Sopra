package org.sopra.rogueguild.repository.model.quest;

import java.util.ArrayList;
import java.util.HashMap;

public class Quest {

    private final String description;
    private String name;
    private int goldReward;
    private boolean isComplete;
    private static int maxID = 1;
    private final int id;
    private static ArrayList<String> questList = new ArrayList<>();

    public Quest (String name, String description, int goldReward) {
        setGoldReward(goldReward);
        setName(name);
        this.id = setAutoID();
        this.description = description;
        this.isComplete = false;
        questList.add(name);
    }


    public int getId() {
        return this.id;
    }

    public static int getMaxID() {
        return maxID;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public String getName() {
        return name;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setName(String name) {
        if (questList.contains(name)) {
            throw new IllegalArgumentException("No puede repetirse el nombre de una misión.");
        }

        this.name = name;
    }

    public int setAutoID() {
        return maxID++; 
    }

    protected void setGoldReward(int goldReward) {
        if (goldReward % 5 != 0) {
            throw new IllegalArgumentException("La recompensa debe ser múltiplo de 5.");
        }

        this.goldReward = goldReward;
    }

    public boolean validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad no puede ser cero o inferior.");
        }

        return true;
    }

    public String checkStatus() {
        if (isComplete) {
            return "¡Has completado esta misión!";
        }
        return "Misión no completada aún.";
    }


    public boolean completeMission() {
        if (this.isComplete) {
            return false;
        }

        this.isComplete = true;
        return true;
    }


    @Override
    public String toString() {
        return id + ". " + name + "\n'" + description + "'\n\nRecompensa: " + goldReward + " (oro)" + "\n\n" + checkStatus();
    }

    public void printRequirements() { }

}
