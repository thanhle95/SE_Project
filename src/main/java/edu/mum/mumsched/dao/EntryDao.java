package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Entry;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EntryDao extends CrudRepository<Entry, Long> {
    @Query("select s from Entry s where s.entryId= :entryID")
    public Entry findEntryByEntryID(@Param("entryID") long entryID);

    @Query("select s from Entry s where s.entryName= :entryName")
    public Entry findEntryByEntryName(@Param("entryName") String entryName);

    @Query("select s from Entry s")
    public List<Entry> getAllEntry();

    @Transactional
    @Modifying
    @Query("delete from Entry s where s.entryId= :entryID")
    public void deleteEntryByEntryID(@Param("entryID") long entryID);
}
