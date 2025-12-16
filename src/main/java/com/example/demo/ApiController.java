package com.example.demo;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    private final List<User> users = new ArrayList<>();


    //curl http://localhost:8080/
    @PostMapping("registration/{username}/{age}")
    public ResponseEntity<Void> createUser(@PathVariable("username") String username, @PathVariable("age") Integer age, @RequestBody String password){
        for (User user: users) {
            if(user.getUsername().equals(username)){
                return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
            }
        }
        User user = new User(username, password, age);
        users.add(user);
        return ResponseEntity.ok().build();
    }
    @GetMapping("users")
    public ResponseEntity<List<User>> seekAll(){
        return ResponseEntity.ok(users);
    }
    @GetMapping("user/{id}")
    public ResponseEntity<User> seekUser(@PathVariable("id") Integer id){
        if(id < 1 || id > users.size()){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        return ResponseEntity.ok(users.get(id-1));
    }
    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
        if(id < 1 || id > users.size()){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        users.remove(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("rename/{username}/{id}")
    public ResponseEntity<Void> renameUser(@PathVariable("username") String username, @PathVariable("id") int id,  @RequestBody String repeatPassword){
        if(id < 1 || id > users.size()){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        if(!users.get(id).getPassword().equals(repeatPassword)){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
        users.get(id).setUsername(username);
        return ResponseEntity.ok().build();
    }






}
