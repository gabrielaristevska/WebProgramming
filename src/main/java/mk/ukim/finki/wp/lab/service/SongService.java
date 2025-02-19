package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findById(Long id);
    Optional<Song> findByTitle(String title);
    Song findByTrackId(String trackId);
    Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, Album album);
    void delete(Long id);
    Optional<Song> update(Long id,String trackId, String title, String genre, Integer releaseYear, Album album);
    List<Song> findAllByAlbum_Id(Long albumId);
}
