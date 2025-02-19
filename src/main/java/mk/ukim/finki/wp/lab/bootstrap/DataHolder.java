package mk.ukim.finki.wp.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists=new ArrayList<>();
    public static List<Song> songs=new ArrayList<>();
    public static List<Album> albums=new ArrayList<>();

    @PostConstruct
    public void init(){
        artists.add(new Artist(1L, "Taylor","Swift","The music industry"));
        artists.add(new Artist(2L, "Gracie","Abrams","A great musician"));
        artists.add(new Artist(3L, "Maisie","Peters","The good witch"));
        artists.add(new Artist(4L, "Conan","Gray","In a lot of pain"));
        artists.add(new Artist(5L, "Noah","Kahan","Immune to love"));
        Album album=new Album("The good witch", "punk","2023");
        Album album2=new Album("Lover", "pop","2019");
        Album album3=new Album("Here forever", "indie","2023");
        Album album4=new Album("The secret of us", "pop","2024");
        Album album5=new Album("Superache", "pop","2022");
        albums.add(album);
        albums.add(album2);
        albums.add(album3);
        albums.add(album4);
        albums.add(album5);

        songs.add(new Song("song1","The archer","pop",2019,album2));
        songs.add(new Song("song2","us","pop",2024,album4));
        songs.add(new Song("song3","Everywhere, everything","indie",2022,album3));
        songs.add(new Song("song4","Coming of age","punk",2023,album));
        songs.add(new Song("song5","People watching","pop",2022,album5));
    }
}
