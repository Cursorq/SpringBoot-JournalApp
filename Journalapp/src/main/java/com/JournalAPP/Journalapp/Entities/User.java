package com.JournalAPP.Journalapp.Entities;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection ="users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    // contains reference of Journal Entry and stores all the journal entries of the user
    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();
}
