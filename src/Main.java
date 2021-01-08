import model.Artist;
import model.DataSource;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if(!dataSource.open()){
            System.out.println("Can't connect.");
            return;
        }
        List<Artist> artists = dataSource.queryArtists();
        if (artists == null) {
            System.out.println("no artists");
        }
        for(Artist artist : artists)
            System.out.println(artist.getName());
        dataSource.close();
    }
}
