package com.myproject.journalApp.controller;

import com.myproject.journalApp.entity.JournalEntry;
import com.myproject.journalApp.srevice.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController2 {

    @Autowired
    private JournalService journalService;

    @PostMapping("/create")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public List<?> getAll() {
        return journalService.getAll();

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<JournalEntry> journalEntry = journalService.findById(id);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateByID(@PathVariable String id, @RequestBody JournalEntry myEntry1) {
       JournalEntry journalEntry = journalService.findById(id).orElse(null);
       if (journalEntry != null) {
           journalEntry.setName(journalEntry.getName() != null && journalEntry.getName().equals("") ? myEntry1.getName() : journalEntry.getName());
           journalEntry.setName(journalEntry.getContent() != null && journalEntry.getContent().equals("") ? myEntry1.getContent() : journalEntry.getContent());
            journalService.updateEntry(id, myEntry1);
            return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            journalService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
