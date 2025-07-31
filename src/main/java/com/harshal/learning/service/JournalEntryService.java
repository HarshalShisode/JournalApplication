package com.harshal.learning.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.harshal.learning.entity.JournalEntry;
import com.harshal.learning.entity.User;
import com.harshal.learning.repository.JournalEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing journal entries.
 */
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String userName) {
        User user=userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved=journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUserEntry(user);
    }

    public List<JournalEntry> getAllEntries() {
        List<JournalEntry> entries = journalEntryRepository.findAll();
        return entries;
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed=false;
        try {
            User user=userService.findByUserName(userName);
            removed=user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed==true){
                userService.saveUserEntry(user);
                journalEntryRepository.deleteById(id); 
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An Error occured while deleting the entry ",e);
        }
        return removed;
    }

    public JournalEntry updateJournalEntryById(ObjectId id, JournalEntry journalEntry) {
        JournalEntry oldEntry=journalEntryRepository.findById(id).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(journalEntry.getTitle()!=null&&!journalEntry.getTitle().equals("")?journalEntry.getTitle():oldEntry.getTitle());
            oldEntry.setContent(journalEntry.getContent()!=null&&!journalEntry.getContent().equals("")?journalEntry.getContent():oldEntry.getContent());
        }
        return journalEntryRepository.save(oldEntry);
    }
}
