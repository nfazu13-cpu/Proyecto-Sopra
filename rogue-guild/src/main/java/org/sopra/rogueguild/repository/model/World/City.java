package org.sopra.rogueguild.repository.model.World;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String nombre;
    private List<City> ciudadesConectadas = new ArrayList<>();

    public City(String nombre) {
        this.nombre = nombre;
        this.ciudadesConectadas = new ArrayList<>();
    }

    public void añadirCamino(City otraCiudad) {
        if (!this.ciudadesConectadas.contains(otraCiudad)) {
            this.ciudadesConectadas.add(otraCiudad);
        }

        if (!otraCiudad.getCiudadesConectadas().contains(this)) {
            otraCiudad.getCiudadesConectadas().add(this);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<City> getCiudadesConectadas() {
        return ciudadesConectadas;
    }

    public void setCiudadesConectadas(List<City> ciudadesConectadas) {
        this.ciudadesConectadas = ciudadesConectadas;
    }
}