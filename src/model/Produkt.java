package model;

public class Produkt {
    private String name;
    private double preis;
    private String produktart;

    public Produkt(String name, double preis, String produktart) {
        this.name = name;
        this.preis = preis;
        this.produktart = produktart;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPreis() { return preis; }
    public void setPreis(double preis) { this.preis = preis; }

    @Override
    public String toString() {
        return name + " - " + preis + " EUR";
    }
}
