package com.harshal.learning.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshal.learning.entity.JournalEntry;
import com.harshal.learning.entity.User;
import com.harshal.learning.service.JournalEntryService;
import com.harshal.learning.service.UserService;
/**
 * Controller class for managing journal entries.
 */
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

	@Autowired
	private JournalEntryService journalEntryService;

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> getAllEntries(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		User user=userService.findByUserName(userName);
		List<JournalEntry> entries=user.getJournalEntries();
		if(entries!=null&&!entries.isEmpty()){
			return new ResponseEntity(entries,HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<JournalEntry> addJournalEntry(@RequestBody JournalEntry journalEntry) {
		try{
			Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
			String userName=authentication.getName();
			journalEntryService.saveJournalEntry(journalEntry,userName);
			return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("id/{id}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		User user=userService.findByUserName(userName);
		List<JournalEntry> entries=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
		if(!entries.isEmpty()){
			Optional<JournalEntry> journalEntry=journalEntryService.findById(id);
			if(journalEntry.isPresent()){
				return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("id/{id}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		User user=userService.findByUserName(userName);
		List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
		if(!collect.isEmpty()){
			JournalEntry entry=journalEntryService.updateJournalEntryById(id, journalEntry);
			if(entry!=null){
				return new ResponseEntity<>(entry,HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}	

	@DeleteMapping("id/{id}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		boolean removed=journalEntryService.deleteById(id,userName);
		if(removed){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
