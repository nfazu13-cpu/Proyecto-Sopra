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
    private int id;
    private City currentCity;
    static private int hitPoints;
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
            this.gold = 500;
        } else {
            this.gold = gold;
        }
    }

    public void setItemEquipped(HashMap<ItemCategory, Item[]> itemEquipped) {
        this.itemEquipped = new HashMap<>();
        this.itemEquipped.put(ItemCategory.ARMOR, maxItems(1));
        this.itemEquipped.put(ItemCategory.BOOTS, maxItems(1));
        this.itemEquipped.put(ItemCategory.HELMET, maxItems(1));
        this.itemEquipped.put(ItemCategory.WEAPON, maxItems(2));
    }

    public void equipItem(Item nuevoItem) {
        ItemCategory categoria = nuevoItem.getCategory();
        Item[] ranuras = this.itemEquipped.get(categoria);

        if (ranuras.length == 1) {
            ranuras[0] = nuevoItem;
            Item itemAnterior = ranuras[0];

            if (itemAnterior != null) {
                id = random.nextInt();
                this.inventory.put(id, itemAnterior);
                System.out.println(itemAnterior + " ha vuelto al inventario.");
            }
        } else {

            if (ranuras[0] == null) {

                ranuras[0] = nuevoItem;
                System.out.println("Arma equipada en ranura 1.");

            } else if (ranuras[1] == null) {

                ranuras[1] = nuevoItem;
                System.out.println("Arma equipada en ranura 2.");
            } else {

                int indiceReemplazo = 0;
                Weapon arma1 = (Weapon) ranuras[1];
                Weapon arma0 = (Weapon) ranuras[0];

                if (arma1.getDamage() < arma0.getDamage()) {
                    indiceReemplazo = 1;
                }

                Item armaAnterior = ranuras[indiceReemplazo];
                ranuras[indiceReemplazo] = nuevoItem; // Reemplazo directo
                System.out.println("Sustituida arma de menor daño: " + armaAnterior);

                this.inventory.put(id, armaAnterior);
                System.out.println(armaAnterior + " ha vuelto al inventario.");

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
            if (hitPoints < 20) {
                hitPoints += potion.getHealPoints();
            } else {
                System.out.println("No se puede tener mas de 20 de vida");
            }
        }
        this.gold -= item.getBasePrice();
    }

    public void addItem(int repositoryId, Item item) {
        inventory.put(repositoryId, item);
    }

    public void removeItem(int repositoryId) {
        if (inventory.remove(repositoryId) == null) {
            System.out.println("El objeto seleccionado no se encuentra en el inventario.");
        }
    }

    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Vacio");
        } else {
            boolean esPrimero = true;
            for (Item item : inventory.values()) {
                if (esPrimero) {

                    System.out.println(item);
                    esPrimero = false;
                } else {

                    System.out.println("       |                     " + item);
                }
            }
        }
    }

    public void printInventoryByCategory(ItemCategory category) {
        System.out.println("--- Objetos de la categoría: " + category + " ---");
        for (Integer id : inventory.keySet()) {
            Item item = inventory.get(id);

            if (item.getCategory() == category) {
                System.out.println(item);
            } else {
                System.out.println("No hay de esta categoria");
            }
        }
    }

}
