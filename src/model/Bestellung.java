package model;

import java.time.LocalDateTime;
import java.util.List;

public class Bestellung {
    private static int counter = 1;
    private int bestellNr;
    private Kunde kunde;
    private List<Produkt> produkte;
    private LocalDateTime zeit;
    private Status status;

    public Bestellung(Kunde kunde, List<Produkt> produkte) {
        this.bestellNr = counter++;
        this.kunde = kunde;
        this.produkte = produkte;
        this.zeit = LocalDateTime.now();
        this.status = Status.OFFEN;
    }

    public int getBestellNr() { return bestellNr; }
    public Kunde getKunde() { return kunde; }
    public List<Produkt> getProdukte() { return produkte; }
    public LocalDateTime getZeit() { return zeit; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public double berechneGesamtpreis() {
        return produkte.stream().mapToDouble(Produkt::getPreis).sum();
    }

    @Override
    public String toString() {
        return "Bestellung #" + bestellNr + " von " + kunde +
                " am " + zeit + " Status: " + status +
                "\nProdukte: " + produkte +
                "\nGesamt: " + berechneGesamtpreis() + " EUR";
    }
}
