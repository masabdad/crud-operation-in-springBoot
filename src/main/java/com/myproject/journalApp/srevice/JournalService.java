package com.myproject.journalApp.srevice;

import com.myproject.journalApp.entity.JournalEntry;
import com.myproject.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(String id){
        return journalEntryRepository.findById(id);

    }

    public void deleteById(String id){
        journalEntryRepository.deleteById(id);
    }


    public JournalEntry updateEntry(String id, JournalEntry myEntry1) {
        boolean opt = journalEntryRepository.existsById(id);
        if (opt){
            return journalEntryRepository.save(myEntry1);
        } else {
         return new JournalEntry();
        }
    }


}
