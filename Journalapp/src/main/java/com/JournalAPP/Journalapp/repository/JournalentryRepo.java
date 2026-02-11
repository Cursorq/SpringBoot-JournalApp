package com.JournalAPP.Journalapp.repository;

import com.JournalAPP.Journalapp.Entities.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface JournalentryRepo extends MongoRepository<JournalEntry, ObjectId> {


}
