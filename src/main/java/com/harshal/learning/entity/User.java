package com.harshal.learning.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @Nonnull
    private String userName;
    private String email;
    private boolean sentimentAnalysis;
    @Nonnull
    private String password;
    @DBRef
    List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String> roles;
}
