package com.JournalAPP.Journalapp.Controller;
import com.JournalAPP.Journalapp.Entities.JournalEntry;
import com.JournalAPP.Journalapp.Entities.User;
import com.JournalAPP.Journalapp.Services.JournalEntryService;
import com.JournalAPP.Journalapp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
@RestController
@RequestMapping("/journal")
public class JournalEntryv2 {
    @Autowired
    private JournalEntryService journalEntryService; // injecting service (calling service)
    @Autowired
    private UserService userService;
    // IT GETS ALL JOURNAL ENTRIES OF A SPECIFIC USER
    @GetMapping("/{user}")
    private ResponseEntity<?> allJournalEntriesOfUser(@PathVariable String user){
        User user1=userService.findByUserName(user);
        List<JournalEntry> all=user1.getJournalEntries();
        if(all!=null&&!all.isEmpty()){ return new ResponseEntity<>(all,HttpStatus.OK);}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //SAVES THE ENTRY INTO DB OF A SPECIFIC USER
    @PostMapping("/{user}")
    public ResponseEntity<JournalEntry>createEntry(@RequestBody JournalEntry myEntry,@PathVariable String user){
        try {
            journalEntryService.saveEntry(myEntry,user);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(myEntry,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> byId= journalEntryService.showEntriesById(myId);
        if(byId.isPresent()){ return new ResponseEntity<>(byId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(byId.get(), HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{myId}/{user}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId,@PathVariable String user){
        journalEntryService.deleteById(myId,user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{myId}/{user}")
    public  ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId myId,@RequestBody JournalEntry updEntry,@PathVariable String user){
        JournalEntry old=journalEntryService.showEntriesById(myId).orElse(null);
        if(old!=null){
            old.setTitle(updEntry.getId()!=null&&!updEntry.getTitle().equals(" ")?updEntry.getTitle():old.getTitle());
            old.setContent(updEntry.getContent()!=null&&!updEntry.equals("")?updEntry.getContent(): old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }


        return new ResponseEntity<>(updEntry,HttpStatus.NOT_FOUND);

    }

}
