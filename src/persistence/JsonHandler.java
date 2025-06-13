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
    private static final String PRODUKT_DATEI = "src/produkte.json";
    private static final String PASSWORT_DATEI = "src/passwort.json";
    private static final Gson gson = new Gson();

    public static List<Produkt> ladeProdukte() throws IOException {
        File file = new File(PRODUKT_DATEI);
        if (!file.exists()) {
            throw new FileNotFoundException("Die Datei '" + PRODUKT_DATEI + "' wurde nicht gefunden.");
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

}