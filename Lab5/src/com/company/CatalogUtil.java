package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {
    public static void save(Catalog catalog)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path)
            throws InvalidCatalogException, IOException, ClassNotFoundException {
        Object object;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            object = ois.readObject();
        }
        if (!(object instanceof Catalog)) {
            throw new InvalidCatalogException();
        }
        return (Catalog) object;
    }

    public static void view(Document doc)
            throws IOException {
        Desktop desktop = Desktop.getDesktop();
        String location = doc.getLocation();
        if (location.startsWith("http://") || location.startsWith("https://")) {
            desktop.browse(URI.create(location));
        }
    }
}
