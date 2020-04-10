package com.company;

import oracle.jdbc.proxy.annotation.Pre;

import javax.xml.transform.Result;
import java.sql.*;

public class AlbumController {
    private Connection connection = Database.getInstance().getConnection();

    public void create(String name, int artistId, int releaseYear) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO albums COLUMNS(name,artist_id,release_year) VALUES(?,?,?)");
            statement.setString(1, name);
            statement.setInt(2, artistId);
            statement.setInt(3, releaseYear);
            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void findByArtist(int artistId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM albums WHERE artist_id=(?)");
            statement.setInt(1, artistId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                int year = rs.getInt("release_year");
                System.out.println(name + " " + year);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
