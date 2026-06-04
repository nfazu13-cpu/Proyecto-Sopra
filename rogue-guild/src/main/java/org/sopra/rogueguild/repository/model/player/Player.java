package org.sopra.rogueguild.repository.model.player;

import java.util.*;

import org.sopra.rogueguild.repository.model.World.City;
import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.ItemCategory;
import org.sopra.rogueguild.repository.model.item.ItemGenerator;
import org.sopra.rogueguild.repository.model.item.Potion;
import org.sopra.rogueguild.repository.model.item.Weapon;

public class Player {

    private String name;
    private int gold = 0;
    private Map<Integer, Item> inventory = new HashMap<>();
    Random random = new Random();
    private ArrayList<Item> Armor = new ArrayList<Item>();
    private HashMap<ItemCategory, Item[]> itemEquipped;
    private City currentCity;
    private int hitPoints;
    private ItemGenerator itemGenerator;

    public Player(String name, int gold) {
        setGold(gold);
        setItemEquipped(itemEquipped);
        this.name = name;
        this.hitPoints = 20;

    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public Map<Integer, Item> getInventory() {
        return inventory;
    }

    public HashMap<ItemCategory, Item[]> getItemEquipped() {
        return itemEquipped;
    }

    public void setGold(int gold) {
        if (gold < 0) {
            this.gold = 0;
        } else if (gold > 500) {
            System.out.println("No puedes tener mas de 500 monedas");
            this.gold = 500;
        } else {
            this.gold = gold;
        }
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setItemEquipped(HashMap<ItemCategory, Item[]> itemEquipped) {
        this.itemEquipped = new HashMap<>();
        this.itemEquipped.put(ItemCategory.ARMOR, maxItems(1));
        this.itemEquipped.put(ItemCategory.BOOTS, maxItems(1));
        this.itemEquipped.put(ItemCategory.HELMET, maxItems(1));
        this.itemEquipped.put(ItemCategory.WEAPON, maxItems(2));
    }

    public void equipItem(Item newItem) {
        ItemCategory category = newItem.getCategory();
        Item[] slots = this.itemEquipped.get(category);

        if (slots.length == 1) {

            Item previousItem = slots[0];

            slots[0] = newItem;

            if (previousItem != null) {
                this.inventory.put(previousItem.getId(), previousItem);
                System.out.println(previousItem + " ha vuelto al inventario.");
            }

        } else {

            if (slots[0] == null) {

                slots[0] = newItem;
                System.out.println("Arma equipada en ranura 1.");

            } else if (slots[1] == null) {

                slots[1] = newItem;
                System.out.println("Arma equipada en ranura 2.");

            } else {

                int replacementIndex = 0;
                Weapon weapon1 = (Weapon) slots[1];
                Weapon weapon0 = (Weapon) slots[0];

                if (weapon1.getDamage() < weapon0.getDamage()) {
                    replacementIndex = 1;
                }

                Item previousItem = slots[replacementIndex];

                slots[replacementIndex] = newItem;

                System.out.println("Sustituida arma de menor daño: " + previousItem);

                if (previousItem != null) {
                    this.inventory.put(previousItem.getId(), previousItem);
                    System.out.println(previousItem + " ha vuelto al inventario.");
                }
            }
        }
    }

    public void travelTo(City destino) {

        if (currentCity == null || destino == null) {
            System.out.println("Error: El origen o el destino no son válidos.");
            return;
        }

        if (currentCity.equals(destino)) {
            System.out.println("Ya te encuentras en la ciudad de " + destino.getNombre());
            return;
        }

        Queue<City> cola = new LinkedList<>();
        Set<City> visitados = new HashSet<>();
        Map<City, City> mapaPadres = new HashMap<>();

        cola.add(currentCity);
        visitados.add(currentCity);
        boolean caminoEncontrado = false;

        while (!cola.isEmpty()) {
            City actual = cola.poll();

            if (actual.equals(destino)) {
                caminoEncontrado = true;
                break;
            }

            for (City vecino : actual.getCiudadesConectadas()) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    mapaPadres.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        if (!caminoEncontrado) {
            System.out.println("El viaje no se realiza: No existe una ruta posible entre "
                    + currentCity.getNombre() + " y " + destino.getNombre() + ".");
            return;
        }

        List<City> rutaCompleta = new ArrayList<>();
        City paso = destino;
        while (paso != null) {
            rutaCompleta.add(0, paso);
            paso = mapaPadres.get(paso);
        }

        System.out.println("Iniciando viaje desde: " + currentCity.getNombre());

        for (int i = 1; i < rutaCompleta.size(); i++) {
            City siguientePaso = rutaCompleta.get(i);
            System.out.println("-> Avanzando paso a paso a: " + siguientePaso.getNombre());

            this.currentCity = siguientePaso;
        }

        System.out.println("¡Viaje finalizado con éxito! Ciudad actual: " + currentCity.getNombre());
    }

    private Item[] maxItems(int numMax) {
        Item[] maxItems = new Item[numMax];
        return maxItems;
    }

    public void buy(Item item) {

        if (item.getCategory() == ItemCategory.POTION) {
            Potion potion = (Potion) item;
            int oldHealth = hitPoints;

            hitPoints += potion.getHealPoints();

            System.out.println("La poción ha curado " + (hitPoints - oldHealth) + ".");

            if (hitPoints >= 20) {
                hitPoints = 20;
                System.out.println("¡Tu vida está al máximo!");
            }

            if (hitPoints <= 0) {
                hitPoints = 0;
                System.out.println("Todos te recordarán...");
            }
        }

        this.gold -= item.getBasePrice();
    }

    public void addItem(int itemId, Item item) {
        inventory.put(itemId, item);
    }

    public void removeItem(int repositoryId) {
        if (inventory.remove(repositoryId) == null) {
            System.out.println("El objeto seleccionado ya no se encuentra en el inventario.");
        }

    }

    public void printInventoryMenu() {
        if (inventory.isEmpty()) {
            System.out.println("Vacío");
        } else {
            boolean esPrimero = true;
            for (Item item : inventory.values()) {
                if (esPrimero) {

                    System.out.println(item);
                    esPrimero = false;
                } else {

                    System.out.println("       | ░                   " + item);
                }
            }
        }
    }

    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Vacío");
        } else {
            for (Item item : inventory.values()) {
                System.out.println(item);
            }
        }
    }

    public boolean printInventoryByCategory(ItemCategory category) {
        String transcriptCat = "";

        if (category.equals(ItemCategory.ARMOR)) {
            transcriptCat = "armadura";
        } else if (category.equals(ItemCategory.HELMET)) {
            transcriptCat = "casco";
        } else if (category.equals(ItemCategory.BOOTS)) {
            transcriptCat = "botas";
        } else if (category.equals(ItemCategory.WEAPON)) {
            transcriptCat = "armas";
        } else if (category.equals(ItemCategory.OTHERS)) {
            transcriptCat = "otros";
        } else {
            transcriptCat = "Desconocido";
        }
        

        System.out.println("- Objetos de la categoria " + transcriptCat);
        boolean encontrado = false;

        for (Integer id : inventory.keySet()) {
            Item item = inventory.get(id);
            if (item != null && item.getCategory() == category) {
                System.out.println(item);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay de esta categoria");
        }

        return encontrado;
    }

    public void printEquipmentMenu() {
        boolean first = true;
        for (Map.Entry<ItemCategory, Item[]> equipement : itemEquipped.entrySet()) {
            ItemCategory category = equipement.getKey();
            Item[] slots = equipement.getValue();

            String categoryStr = transcriptCat(category);

            if (first) {
                System.out.println(categoryStr);
                first = false;
            } else {
                System.out.println("       | ░                 " + categoryStr);
            }

            for (int i = 0; i < slots.length; i++) {
                Item item = slots[i];

                String slotPrefix = "";

                if (slots.length > 1) {
                    slotPrefix = "  Ranura " + (i + 1) + ":";
                } else {
                    slotPrefix = "  Ranura Única:";
                }

                if (item != null) {
                    System.out.println("       | ░                 " + slotPrefix + " " + item);
                } else {
                    System.out.println("       | ░                 " + slotPrefix + " " + "[vacía]");
                }
            }
        }
    }

    public String transcriptCat(ItemCategory ic) {
        if (ic == ItemCategory.ARMOR) {
            return "- PECHERA";
        } else if (ic == ItemCategory.HELMET) {
            return "- CASCO";
        } else if (ic == ItemCategory.BOOTS) {
            return "- BOTAS";
        } else if (ic == ItemCategory.WEAPON) {
            return "- ARMAS";
        } else if (ic == ItemCategory.POTION) {
            return "- POCIONES";
        } else
            return "- OTROS";
    }

}
