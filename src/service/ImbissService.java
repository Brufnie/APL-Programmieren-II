package service;

import model.*;
import persistence.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class ImbissService {
    private List<Produkt> speisekarte;
    private List<Bestellung> bestellungen;

    public ImbissService(List<Produkt> speisekarte) {
        this.speisekarte = speisekarte;
        this.bestellungen = new ArrayList<>();
    }

    public void zeigeSpeisekarte() {
        for (int i = 0; i < speisekarte.size(); i++) {
            System.out.println((i + 1) + ". " + speisekarte.get(i));
        }
    }

    public void bestellungAufnehmen(Kunde kunde, List<Integer> produktIndizes) {
        List<Produkt> produkte = new ArrayList<>();
        for (int index : produktIndizes) {
            if (index >= 0 && index < speisekarte.size()) {
                produkte.add(speisekarte.get(index));
            }
        }
        Bestellung bestellung = new Bestellung(kunde, produkte);
        bestellungen.add(bestellung);
        LoggerUtil.loggeBestellung(bestellung);
        System.out.println("\nBestellung aufgenommen: \n" + bestellung);
    }

    public void zeigeBestellungen() {
        for (Bestellung b : bestellungen) {
            System.out.println(b);
            System.out.println("---------------------");
        }
    }

    public List<Bestellung> getBestellungen() {
        return bestellungen;
    }
}