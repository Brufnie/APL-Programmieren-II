package main;

import model.Getraenk;
import model.Produkt;
import model.Speise;
import view.Menu;

public class Main {
    public static void main(String[] args) {

        Produkt G1 = new Getraenk("Bier", 3.14, 1.0, true);
        Produkt S1 = new Speise("Nudeln", 2.14, false);


        //Starten des Programms
        new Menu().start();
    }
}