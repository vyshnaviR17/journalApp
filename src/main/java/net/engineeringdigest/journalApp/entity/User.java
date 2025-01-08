package net.engineeringdigest.journalApp.entity;
//POJO Class

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
//@Data is Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @DBRef //parent-child relationship
    private List<JournalEntry> journalEntries = new ArrayList<>(); // This will store
    // only the ids(means reference) of the created journal

    private List<String> roles;

}
