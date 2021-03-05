package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionDao extends CrudRepository<Session, Long> {
    @Query("select se from Session se where se.sessionId= :sessionId")
    public Session findSessionBySessionId(@Param("sessionId") long sessionId);

    @Query("select se from Session se where se.block.blockId= :blockId")
    public List<Session> findSessionsByBlockBlockId(@Param("blockId") long blockId);

    @Query("select se from Session se")
    public List<Session> getAllSessions();
}
