package model;

public class Getraenk extends Produkt {
    private double volumen;  // in ml
    private boolean alkoholisch;
    
    public Getraenk(String name, double preis, double volumen, boolean alkoholisch) {
        super(name, preis, "Getränk");
        this.volumen = volumen;
        this.alkoholisch = alkoholisch;
    }
    
    public double getVolumen() { return volumen; }
    public void setVolumen(double volumen) { this.volumen = volumen; }
    
    public boolean isAlkoholisch() { return alkoholisch; }
    public void setAlkoholisch(boolean alkoholisch) { this.alkoholisch = alkoholisch; }
    
    @Override
    public String toString() {
        String volumenInfo = volumen + " ml)";
        String alkoholInfo = alkoholisch ? " [alkoholisch]" : "";
        return super.toString() + volumenInfo + alkoholInfo;
    }
}