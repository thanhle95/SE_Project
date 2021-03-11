package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Entry;

import java.util.List;

public interface EntryService {
    public void save(Entry entry);

    public Entry getEntryByEntryID(long entryID);

    public Entry getEntryByEntryName(String entryName);

    public List<Entry> getAllEntry();

    public void deleteEntryByEntryID(long entryID);
}
