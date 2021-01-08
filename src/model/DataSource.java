package model;

import java.sql.*;
import java.util.ArrayList;

public class DataSource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\midas\\Desktop\\java programs\\MusicDB\\" + DB_NAME;

    public static final String TABLE_SONGS = "songs";
    public static final String SONGS_ID = "_id";
    public static final String SONGS_TRACK = "track";
    public static final String SONGS_TITLE = "title";
    public static final String SONGS_ALBUM = "album";

    public static final String TABLE_ARTISTS = "artists";
    public static final String ARTISTS_ID = "_id";
    public static final String ARTISTS_NAME = "name";

    public static final String TABLE_ALBUMS = "albums";
    public static final String ALBUMS_ID = "_id";
    public static final String ALBUMS_NAME = "name";
    public static final String ALBUMS_ARTIST = "artist";

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Artist> queryArtists() {
        ArrayList<Artist> artists = new ArrayList<>();

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from " + TABLE_ARTISTS)) {

            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(ARTISTS_ID));
                artist.setName(resultSet.getString(ARTISTS_NAME));
                artists.add(artist);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return artists;
    }

}
