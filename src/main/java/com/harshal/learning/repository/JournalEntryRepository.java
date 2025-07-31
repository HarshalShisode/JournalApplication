package com.harshal.learning.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.harshal.learning.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry,ObjectId> {
    // Custom query methods can be defined here if needed
    // For example, findByTitle(String title) or findByDate(Date date)
    // These methods will be automatically implemented by Spring Data MongoDB

}
