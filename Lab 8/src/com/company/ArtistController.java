package com.company;

import java.sql.*;

public class ArtistController {
    private Connection connection = Database.getInstance().getConnection();

    public void create(String name, String country) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO artists COLUMNS(name,country) VALUES(?,?)");
            statement.setString(1, name);
            statement.setString(2, country);
            statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void findByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM artists WHERE name=(?)");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nameQ = rs.getString("name");
                String countryQ = rs.getString("country");
                System.out.println(nameQ+" "+countryQ);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
