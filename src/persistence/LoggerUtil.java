package persistence;

import model.Bestellung;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {
    private static final String LOG_DATEI = "bestellungen.log";

    public static void loggeBestellung(Bestellung bestellung) {
        try {
            File logDatei = new File(LOG_DATEI);
            if (!logDatei.exists()) {
                logDatei.createNewFile();
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
                writer.write("Gesamt: " + bestellung.berechneGesamtpreis() + " EUR\n\n");
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben in die Logdatei: " + e.getMessage());
        }
    }
}
