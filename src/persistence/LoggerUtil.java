package persistence;

import model.Bestellung;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoggerUtil {
    private static final String LOG_DATEI = "src/bestellungen.log";

    public static void loggeBestellung(Bestellung bestellung) {
        try {
            File logDatei = new File(LOG_DATEI);
            if (!logDatei.exists()) {
                throw new FileNotFoundException("Die Datei '" + LOG_DATEI + "' wurde nicht gefunden.");
            }

            try (FileWriter writer = new FileWriter(logDatei, true)) {
                writer.write("[" + bestellung.getZeit().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "] ");
                writer.write("Bestellung #" + bestellung.getBestellNr() + ", Kunde: " + bestellung.getKunde() + "\n");
                bestellung.getProdukte().forEach(p -> {
                    try {
                        writer.write(" - " + p.toString() + "\n");
                    } catch (IOException e) {
                        // ignore
                    }
                });
                writer.write("Gesamt: " + bestellung.berechneGesamtpreis() + " EUR\n");
                writer.write("Zustand:" + bestellung.getStatus() + "\n\n");
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben in die Logdatei: " + e.getMessage());
        }
    }

    public static void zeigeLog() {
        File file = new File(LOG_DATEI);
        if (!file.exists()) {
            System.out.println("Keine Logdatei vorhanden.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim Lesen der Logdatei: " + e.getMessage());
        }
    }
}