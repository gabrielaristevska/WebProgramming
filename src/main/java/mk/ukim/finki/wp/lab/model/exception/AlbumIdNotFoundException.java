package mk.ukim.finki.wp.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlbumIdNotFoundException extends RuntimeException{
    public AlbumIdNotFoundException(Long id){
        super(String.format("Album with id %d not found!", id));
    }
}
