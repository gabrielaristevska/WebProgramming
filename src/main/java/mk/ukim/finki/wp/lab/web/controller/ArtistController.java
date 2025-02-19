package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    @Autowired
    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping("/artists")
    public String listArtists(Model model) {
        List<Artist> artists = artistService.listArtists();
        model.addAttribute("artists", artists);
        return "listArtists";
    }

    @PostMapping("/artists")
    public String selectSong(@RequestParam(name = "songRadio", required = false, defaultValue = "-") String trackId,
                             Model model) {
        List<Artist> artists = artistService.listArtists();
        Long songId=this.songService.findByTrackId(trackId).getId();
        model.addAttribute("trackId", trackId);
        model.addAttribute("songId", songId);
        model.addAttribute("artists", artists);
        return "listArtists";
    }
}
