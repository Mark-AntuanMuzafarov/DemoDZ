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
    private final List<User> usersnopassword = new ArrayList<>();
    @PostMapping("registration/{username}/{age}")
    public ResponseEntity<Void> createUser(@PathVariable("username") String username, @PathVariable("age") Integer age, @RequestBody Password password){
        for (User user: users) {
            if(user.getUsername().equals(username)){
                return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
            }
        }
        if(!password.isRight()){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
        User user = new User(username, password.getPassword(), age);
        User user2 = new User(username, null, age);
        usersnopassword.add(user2);
        users.add(user);
        return ResponseEntity.ok().build();
    }
    @GetMapping("users")
    public ResponseEntity<List<User>> seekAll(){
        return ResponseEntity.ok(usersnopassword);
    }
    @GetMapping("user/{id}")
    public ResponseEntity<User> seekUser(@PathVariable("id") Integer id){
        if(id < 1 || id > users.size()){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        return ResponseEntity.ok(usersnopassword.get(id-1));
    }
    @GetMapping("users")
    public ResponseEntity<List<User>> seekUserByAge(@RequestBody int age){
        List<User> input = new ArrayList<>();
        for(User user: usersnopassword){
            if(user.getAge() >= age - 5 && user.getAge() <= age + 5){
                input.add(user);
            }
        }
        return ResponseEntity.ok(input);
    }
    @GetMapping("users/sortBy/{parameter}")
    public ResponseEntity<List<User>> seekUserBy(@PathVariable("parameter") String parameter){
        List<User> input = new ArrayList<>();
        if(parameter.equals("username")){
            return ResponseEntity.ok().build();
            //я не понял, что я должен здесь сделать
        }
        return ResponseEntity.ok(input);
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
