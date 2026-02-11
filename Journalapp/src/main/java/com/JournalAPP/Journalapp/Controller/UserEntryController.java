package com.JournalAPP.Journalapp.Controller;
import com.JournalAPP.Journalapp.Entities.JournalEntry;
import com.JournalAPP.Journalapp.Entities.User;
import com.JournalAPP.Journalapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController  {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers(){
           return userService.showAllUsers();
    }
    @PostMapping
    void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
    @PutMapping("/{Username}")
    ResponseEntity<?>Updateuser( @RequestBody User user, @PathVariable String Username){
        User old= userService.findByUserName(Username);
        if(old!=null){
            old.setUserName(user.getUserName());
            old.setPassword(user.getPassword());
            userService.saveEntry(old);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }


        return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);


    }
    public void saveJournalInUser(JournalEntry journalEntry){ }

}
