package org.sopra.rogueguild.repository.model;

public class Armor extends Item {

  private int shield;

  public Armor(String name, int price, int shield) {
    super(name, price, ItemCategory.ARMOR);
    this.shield = shield;
  }

  @Override
  public String toString() {
    return super.toString() + " Defensa: " + this.shield;
  }

}
