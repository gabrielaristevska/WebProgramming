package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exception.AlbumIdNotFoundException;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SongController {
    private final SongService songService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    public SongController(SongService songService, ArtistService artistService, AlbumService albumService) {
        this.songService = songService;
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest req){

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Song> songs = songService.listSongs();
        model.addAttribute("songs", songs);


        List<Song> songs1 = new ArrayList<>();
        String album = req.getParameter("albumId1");
        if (album != null) {
            if (!album.isEmpty()) {
                songs1 = songService.listSongs().stream()
                        .filter(s -> Long.toString(s.getAlbum().getId()).equals(req.getParameter("albumId1")))
                        .collect(Collectors.toList());
            }
        }

        List<Album> albums = albumService.findAll();
        model.addAttribute("songs1", songs1);
        model.addAttribute("albums", albums);


        return "listSongs";
    }

    @GetMapping("/songs/add")
    public String getAddSongPage(Model model){
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @PostMapping("/songs/add")
    public String saveSong(@RequestParam(required = false) Long id, String title,  String trackId,String genre, Integer releaseYear, Long albumId){
        Album album=albumService.findById(albumId).orElseThrow(() -> new AlbumIdNotFoundException(albumId));
        if(id==null){
            songService.save(trackId,title,genre,releaseYear,album);
        }
        else{
            songService.update(id,trackId,title,genre,releaseYear,album);
        }

        return "redirect:/songs";
    }

    @GetMapping("/songs/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model){
        Song song = this.songService.findById(id);
        List<Artist> artists = this.artistService.listArtists();
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("song", song);
        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        return "add-song";
    }

    /*@GetMapping("/songs/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(required = false) String trackId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId){

        songService.update(songId,trackId,title,genre,releaseYear,albumId);
        return "redirect:/songs";
    }*/

    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.delete(id);
        return "redirect:/songs";
    }
}
