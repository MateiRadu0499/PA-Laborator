package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        ArtistController artistController = new ArtistController();
        AlbumController albumController = new AlbumController();

        artistController.create("Kek","Romania");
        albumController.create("Yee",4,1920);

        artistController.findByName("Kek");
        albumController.findByArtist(4);

    }
}
