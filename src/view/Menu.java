package view;

import model.*;
import persistence.JsonHandler;
import persistence.LoggerUtil;
import service.ImbissService;

import java.io.IOException;
import java.util.*;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        try {
            var produkte = JsonHandler.ladeProdukte();
            var service = new ImbissService(produkte);

            while (true) {
                System.out.println("\n--- IMBISS-VERWALTUNG ---");
                System.out.println("1. Speisekarte anzeigen");
                System.out.println("2. Bestellung aufnehmen");
                System.out.println("3. Bestellungen anzeigen (aus Log-Datei)");
                System.out.println("4. Bestellstatus ändern");
                System.out.println("0. Beenden");
                System.out.print("> ");
                String eingabe = scanner.nextLine();

                switch (eingabe) {
                    case "1" -> service.zeigeSpeisekarte();
                    case "2" -> bestellungAufnehmen(service);
                    case "3" -> LoggerUtil.zeigeLog();
                    case "4" -> statusAendern(service);
                    case "0" -> {
                        System.out.println("Programm beendet.");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Ungültige Eingabe.");
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Produkte: " + e.getMessage());
        }
    }

    private void bestellungAufnehmen(ImbissService service) {
        System.out.print("Name des Kunden: ");
        String name = scanner.nextLine();
        System.out.print("Adresse: ");
        String adresse = scanner.nextLine();

        Kunde kunde = new Kunde(name, adresse);
        List<Integer> auswahl = new ArrayList<>();

        service.zeigeSpeisekarte();
        System.out.println("Produktnummern durch Komma getrennt (z.B. 1,3,5): ");
        String[] eingaben = scanner.nextLine().split(",");
        //split: Teilt den eingelesenen String an den Kommas auf

        try {
            for (String e : eingaben) {
                auswahl.add(Integer.parseInt(e.trim()) - 1);
                //Wandelt String in Integer um, entfernt Leerzeichen und Zahl wird -1 subtrahiert
                //aufgrund von Listen
            }
            service.bestellungAufnehmen(kunde, auswahl);

        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe.");
        }
    }

    private void statusAendern(ImbissService service) {
        try {
            System.out.print("Admin-Passwort: ");
            String eingabePasswort = scanner.nextLine();
            String echtesPasswort = JsonHandler.ladePasswort();

            if (!eingabePasswort.equals(echtesPasswort)) {
                System.out.println("Falsches Passwort. Zugriff verweigert.");
                return;
            }

            System.out.print("Bestellnummer: ");
            int nr = Integer.parseInt(scanner.nextLine());
            //Zeile aus Terminal wird in Integer versucht umzuwandeln
            //->NumberFormatException

            System.out.println("Neuer Status: (1) OFFEN, (2) IN_BEARBEITUNG, (3) ABGESCHLOSSEN");
            int s = Integer.parseInt(scanner.nextLine());
            Status neuerStatus = switch (s) {
                case 1 -> Status.OFFEN;
                case 2 -> Status.IN_BEARBEITUNG;
                case 3 -> Status.ABGESCHLOSSEN;
                default -> null;
            };


            //sucht aktuelle Bestellnr in allen Bestellungen
            for (Bestellung b : service.getBestellungen()) {

                if (b.getBestellNr() == nr && neuerStatus != null) {
                    b.setStatus(neuerStatus);
                    LoggerUtil.loggeBestellung(b);
                    System.out.println("Status erfolgreich geändert.");
                    return;
                }
            }

            System.out.println("Bestellung nicht gefunden oder ungültiger Status.(null)");

        } catch (IOException e) {
            System.out.println("Fehler beim Laden des Passworts: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe.");
        }

    }
}
