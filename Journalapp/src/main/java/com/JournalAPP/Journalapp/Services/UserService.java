package com.JournalAPP.Journalapp.Services;
import com.JournalAPP.Journalapp.Entities.User;
import com.JournalAPP.Journalapp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserService {
    @Autowired
    private UserRepo UserRepo;// dependency injection
    public void saveEntry(User User){UserRepo.save(User);}
    public List<User> showAllUsers(){return UserRepo.findAll();}
    public Optional<User> showEntriesById(ObjectId id){ return UserRepo.findById(id);}
    public void deleteById(ObjectId id){UserRepo.deleteById(id);}
    public User findByUserName(String userName){ return UserRepo.findByUserName(userName);}


}
