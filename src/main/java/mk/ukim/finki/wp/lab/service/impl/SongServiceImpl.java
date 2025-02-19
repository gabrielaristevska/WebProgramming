package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exception.AlbumIdNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository){
        this.songRepository=songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        songRepository.save(song);
        return artist;
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
    }

    @Override
    public Optional<Song> findByTitle(String title) {
        return Optional.of(songRepository.findByTitle(title));
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, Album album) {
        Song song=new Song(trackId,title,genre,releaseYear,album);
        return Optional.of(songRepository.save(song));
    }

    @Override
    public void delete(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> update(Long id,String trackId, String title, String genre, Integer releaseYear, Album album) {
        Song song=songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);
        return Optional.of(songRepository.save(song));
    }

    @Override
    public List<Song> findAllByAlbum_Id(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }
}
