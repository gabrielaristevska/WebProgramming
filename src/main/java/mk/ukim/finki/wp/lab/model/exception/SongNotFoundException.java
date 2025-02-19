package mk.ukim.finki.wp.lab.model.exception;

import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SongNotFoundException extends RuntimeException{
    public SongNotFoundException(Long trackId){
        super(String.format("Song with id %d not found!",trackId));
    }
}
