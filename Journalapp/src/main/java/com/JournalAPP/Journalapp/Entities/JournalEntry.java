package com.JournalAPP.Journalapp.Entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "journal_entries")
@Data // generates getters and setters
@NoArgsConstructor
public class JournalEntry {
    @Id // id is unique
    private ObjectId id;
    @NonNull
    private String Title;
    private String Content;
    private LocalDateTime date;

}
