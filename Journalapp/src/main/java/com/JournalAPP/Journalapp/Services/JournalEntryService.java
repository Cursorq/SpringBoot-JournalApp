package com.JournalAPP.Journalapp.Services;

import com.JournalAPP.Journalapp.Entities.JournalEntry;
import com.JournalAPP.Journalapp.Entities.User;
import com.JournalAPP.Journalapp.repository.JournalentryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
@Autowired
    private JournalentryRepo journalentryRepo;// dependency injection
    @Autowired
    private UserService userService;
    //THIS DOES TWO THINGS:-
    // 1. SAVES THE JOURNAL ENTRY INTO JOURNAL ENTRIES DOCUMENT
    // 2. ADDS THE REFERENCE OF THE SAVED JOURNAL ENTRY INTO journalEntries FIELD IN User.
    @Transactional
    public void saveEntry(JournalEntry journalEntry ,String user){
        try {
            User user1=userService.findByUserName(user);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save=journalentryRepo.save(journalEntry);
            user1.getJournalEntries().add(save);
            userService.saveEntry(user1);
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("The following Exception has ocuured",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
      journalentryRepo.save(journalEntry);
    }
    public List<JournalEntry>showEntries(){return journalentryRepo.findAll();}
    public Optional<JournalEntry> showEntriesById(ObjectId id){ return journalentryRepo.findById(id);}
    public void deleteById(ObjectId id,String user){
        User user1=userService.findByUserName(user);
        user1.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user1);
        journalentryRepo.deleteById(id);
    }
}
//budkovgersh_db_user gIIdkEe2K465LSaN