package org.sopra.rogueguild.repository.model.World;

import java.util.HashMap;

public class City {
    private HashMap<Integer, String> ciudades = new HashMap<>();
    private String nombre;
    private int road;

    public City(HashMap<Integer, String> ciudades, String nombre, int road) {
        this.ciudades = ciudades;
        this.nombre = nombre;
        this.road = road;
    }

    public HashMap<Integer, String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(HashMap<Integer, String> ciudades) {
        this.ciudades = ciudades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRoad() {
        return road;
    }

    public void setRoad(int road) {
        this.road = road;
    }

}
