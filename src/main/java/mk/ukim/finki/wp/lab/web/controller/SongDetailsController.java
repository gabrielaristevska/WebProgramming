package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exception.AlbumIdNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.SongNotFoundException;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/details")
public class SongDetailsController {
    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getDetailsPage(Model model){
        Song s=songService.listSongs().stream().findFirst().orElse(null);
        model.addAttribute("song",s);
        return "songDetails";
    }

    @PostMapping
    public String addArtistToSong(@RequestParam Long songId, @RequestParam Long artistId,Model model){
        Song s=songService.findById(songId);
        Artist artist=artistService.findById(artistId);
        songService.addArtistToSong(artist,s);
        model.addAttribute("song",s);
        return "songDetails";
    }
}
