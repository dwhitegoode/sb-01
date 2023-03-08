package com.tannhauser.david.photos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotoController {

    private Map<String, Photo> db = new HashMap<>(){
        {
        put("1", new Photo("1","hello.jpg"));
        }
    };


    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/photos")
    public Collection<Photo> get(){

        return  db.values();
    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id){

        Photo photo = db.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id){
        Photo photo = db.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
    /*
    * (async function deletePhoto(id){
    await fetch('http://localhost:8080/photos/' + id, {
        method: "DELETE"
    })
})(1);
    * */

    @PostMapping("/photos/")
    public Photo create(@RequestBody Photo photo){
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(), photo);
        return photo;

    }
}
