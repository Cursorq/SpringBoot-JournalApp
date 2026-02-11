package com.JournalAPP.Journalapp.repository;

import com.JournalAPP.Journalapp.Entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);

}

