package model;

public class Speise extends Produkt {
    private boolean vegetarisch;
    
    public Speise(String name, double preis, boolean vegetarisch) {
        super(name, preis, "Speise");
        this.vegetarisch = vegetarisch;
    }
    
    public boolean isVegetarisch() { return vegetarisch; }
    public void setVegetarisch(boolean vegetarisch) { this.vegetarisch = vegetarisch; }
    
    @Override
    public String toString() {
        String vegetarischInfo = vegetarisch ? " (vegetarisch)" : "";
        return super.toString() + vegetarischInfo;
    }
}