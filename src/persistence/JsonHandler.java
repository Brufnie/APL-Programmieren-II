package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Produkt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonHandler {
    private static final String PRODUKT_DATEI = "produkte.json";
    private static final String PASSWORT_DATEI = "passwort.json";
    private static final Gson gson = new Gson();

    public static List<Produkt> ladeProdukte() throws IOException {
        File file = new File(PRODUKT_DATEI);
        if (!file.exists()) {
            throw new FileNotFoundException("Die Datei '" + PRODUKT_DATEI + "' wurde nicht gefunden. Bitte legen Sie sie im Projektverzeichnis ab.");
        }

        try (FileReader reader = new FileReader(file)) {
            Type typ = new TypeToken<List<Produkt>>() {}.getType();
            return gson.fromJson(reader, typ);
        }
    }

    public static String ladePasswort() throws IOException {
        File file = new File(PASSWORT_DATEI);
        if (!file.exists()) {
            throw new FileNotFoundException("Passwortdatei nicht gefunden.");
        }
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, String.class);
        }
    }

    public static void speichereProdukte(List<Produkt> produkte) throws IOException {
        try (java.io.FileWriter writer = new java.io.FileWriter(PRODUKT_DATEI)) {
            gson.toJson(produkte, writer);
        }
    }
}