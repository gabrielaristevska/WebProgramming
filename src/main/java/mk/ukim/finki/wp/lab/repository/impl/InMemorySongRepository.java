package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepository {

    public List<Song> findAll(){
        return DataHolder.songs;
    }

    public Song findById(Long id){
        return DataHolder.songs.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Song findByTitle(String title){
        return DataHolder.songs.stream()
                .filter(song -> song.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public Song findByTrackId(String trackId){
        return DataHolder.songs.stream()
                .filter(song -> song.getTrackId().equals(trackId))
                .findFirst()
                .orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song){
        for(Song s:DataHolder.songs){
            if(s.getTrackId().equals(song.getTrackId())){
                s.getPerformers().add(artist);
                return artist;
            }
        }
        return null;
    }

    public Optional<Song> save(String title,String trackId, String genre, Integer releaseYear, Album album){
        Song song = new Song(trackId,title, genre, releaseYear, album);
        DataHolder.songs.removeIf(s -> s.getTrackId().equals(trackId));
        DataHolder.songs.add(song);
        return Optional.of(song);
    }

    public void delete(Long id){
        DataHolder.songs.removeIf(song -> song.getId().equals(id));
    }

}
