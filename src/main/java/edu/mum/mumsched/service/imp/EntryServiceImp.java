package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.EntryDao;
import edu.mum.mumsched.domain.Entry;
import edu.mum.mumsched.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImp implements EntryService {
    @Autowired
    EntryDao entryDAO;

    public void save(Entry entry){
        entryDAO.save(entry);
    }

    @Override
    public Entry getEntryByEntryName(String entryName) {
        return entryDAO.findEntryByEntryName(entryName);
    }

    @Override
    public Entry getEntryByEntryID(long entryID){
        return entryDAO.findEntryByEntryID(entryID);
    }

    @Override
    public List<Entry> getAllEntry() {
        return entryDAO.getAllEntry();
    }
}
