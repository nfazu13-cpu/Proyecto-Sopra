package org.sopra.rogueguild.repository.model.quest;

import java.util.HashMap;

import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.ItemStatsType;
import org.sopra.rogueguild.repository.model.item.Shield;
import org.sopra.rogueguild.repository.model.item.Weapon;
import org.sopra.rogueguild.repository.model.player.Player;
import org.sopra.rogueguild.view.utils.Ansi;

public class QuestStats extends Quest {

    private HashMap<ItemStatsType, Integer> requirements = new HashMap<>(); 
    private HashMap<ItemStatsType, QuestStatsOperator> operators = new HashMap<>(); 

    public QuestStats(String name, String description, int goldReward) {
        super(name, description, goldReward);
    }


    public void addRequierement(ItemStatsType requirementCategory, int quantity, QuestStatsOperator operator) {
        if (validateStatsCategory(requirementCategory) && validateOperator(operator) && super.validateQuantity(quantity)) {
            this.requirements.put(requirementCategory, quantity);
            this.operators.put(requirementCategory, operator);
        }

    }


    public HashMap<ItemStatsType, Integer> checkRequierement(Player p) {

        int totalPlayerShield = 0;
        int totalPlayerDamage = 0;

        for (Item[] item : p.getItemEquipped().values()) {
            for (int i = 0; i < item.length; i++) {

                if (item[i] != null) {
                    if (item[i] instanceof Shield s) {
                        totalPlayerShield += s.getShield();
                    } else if (item[i] instanceof Weapon w) {
                        totalPlayerDamage += w.getDamage();
                    }
                }
            }
        }

        HashMap<ItemStatsType, Integer> missingRequirements = new HashMap<>();

        if (this.requirements.containsKey(ItemStatsType.SHIELD)) {
            int requiredShield = this.requirements.get(ItemStatsType.SHIELD);
            QuestStatsOperator operator = this.operators.get(ItemStatsType.SHIELD);

            if (operator == QuestStatsOperator.GREATER_THAN) {
                if (totalPlayerShield < requiredShield) {
                    missingRequirements.put(ItemStatsType.SHIELD, (requiredShield - totalPlayerShield));
                }
            }

            if (operator == QuestStatsOperator.LESS_THAN) {
                if (totalPlayerShield > requiredShield) {
                    missingRequirements.put(ItemStatsType.SHIELD, (totalPlayerShield - requiredShield));
                }
            }
        }
            
        if (this.requirements.containsKey(ItemStatsType.DAMAGE)) {
            int requiredDamage = this.requirements.get(ItemStatsType.DAMAGE);
            QuestStatsOperator operator = this.operators.get(ItemStatsType.DAMAGE);

            if (operator == QuestStatsOperator.GREATER_THAN) {
                if (totalPlayerDamage < requiredDamage) {
                    missingRequirements.put(ItemStatsType.DAMAGE, (requiredDamage - totalPlayerDamage));
                }
            }

            if (operator == QuestStatsOperator.LESS_THAN) {
                if (totalPlayerDamage > requiredDamage) {
                    missingRequirements.put(ItemStatsType.DAMAGE, (totalPlayerDamage - requiredDamage));
                } 
            }
            
        }
        
        return missingRequirements;

    }


    public boolean validateStatsCategory(ItemStatsType requirementCategory) {
        if (requirementCategory == null) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }

        return true;
    }

    public boolean validateOperator(QuestStatsOperator operator) {
        if (operator == null) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }

        return true;
    }

    @Override
    public void printRequirements() {
        for (HashMap.Entry<ItemStatsType, Integer> requirement : requirements.entrySet()) {

            ItemStatsType stat = requirement.getKey();
            int requiretValue = requirement.getValue();

            QuestStatsOperator operator = operators.get(stat);

            char operatorChar = ' ';

            if (operator == QuestStatsOperator.GREATER_THAN) {
                operatorChar = '>';
            } else if (operator == QuestStatsOperator.LESS_THAN) {
                operatorChar = '<';
            }

            if (stat == ItemStatsType.SHIELD) {
                System.out.println("│ Armadura: " + operatorChar + " " + requiretValue + "                               │");
            } else if (stat == ItemStatsType.DAMAGE) {
                System.out.println("│ Daño: " + operatorChar + " " + requiretValue + "                                   │");
            }
        }
    }
    
}
