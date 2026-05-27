package org.sopra.rogueguild.repository.model;

public class QuestStats extends Quest {


    public QuestStats(String name, String description, int goldReward) {
        super(name, description, goldReward);
        this.type = -1;
    }

    
}
