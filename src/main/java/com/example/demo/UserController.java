package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
public class UserController {
    private List<User> users = new ArrayList<>();
    @PostMapping("user")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.accepted().build();
    }
    //curl http://localhost:8080/user -X POST -d "{/"name/" : /"Ivan/", /"age/": 69}"
    @DeleteMapping("user/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable("index") Integer index) {
        users.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    //curl http://localhost:8080/user/0 -X DELETE
    @GetMapping("user/{index}")
    public ResponseEntity<User> getUser(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(users.get(index));
    }
    //curl http://localhost:8080/user/0
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(users);
    }
    //curl http://localhost:8080/users
    @PostMapping("user/age/{index}")
    public ResponseEntity<Void> setAge(@PathVariable("index") Integer index, @RequestBody Integer age) {
        users.get(index).setAge(age);
        return ResponseEntity.accepted().build();
    }
    //curl http://localhost:8080/user/age/2 -X POST -d 67
}
