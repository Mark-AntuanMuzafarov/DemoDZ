package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    private final List<String> messages = new ArrayList<>();

    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    //curl http://localhost:8080/messages

    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    //curl http://localhost:8080/messages -X POST -d "String"

    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(messages.get(index));
    }
    //curl http://localhost:8080/messages/0

    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    //curl http://localhost:8080/messages/0 -X DELETE
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(@PathVariable("index") Integer i, @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
    //curl http://localhost:8080/messages/0 -X PUT -d "String"

    @GetMapping("/messages/search/{text}")
    public ResponseEntity<Integer> searchingtext(@PathVariable("text") String text){
        for (int i = 0; i < messages.size(); i++) {
            if(messages.get(i).contains(text)){
                return ResponseEntity.ok(i);
            }
        }
        return ResponseEntity.noContent().build();
    }

    //curl http://localhost:8080/messages/search/what
    @GetMapping("/messages/count")
    public ResponseEntity<Integer> Messagecount() {
        return ResponseEntity.ok(messages.size());
    }
    //curl http://localhost:8080/messages/count

    @PostMapping("/messages/{index}/create")
    public ResponseEntity<Void> CreateMessage(@PathVariable Integer index) {
        messages.add(index,"");
        return ResponseEntity.accepted().build();
    }
    //curl http://localhost:8080/messages/0/create -X POST

    @DeleteMapping("/messages/search/{text}")
    public ResponseEntity<Void> deletesearchingText(@PathVariable("text") String text) {
        for (int i = 0; i < messages.size(); i++) {
            if(messages.get(i).contains(text)){
                deleteText(i);
            }
        }
        return ResponseEntity.noContent().build();
    }
    //curl http://localhost:8080//messages/search/{text} -X DELETE

    @GetMapping("messages/{text}")
    public ResponseEntity<List<String>> getSearchingMessages(@PathVariable("text") String text) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            if(messages.get(i).contains(text)){
                result.add(messages.get(i));
            }
        }
        return ResponseEntity.ok(result);
    }
    //curl http://localhost:8080//messages/{text}



}
