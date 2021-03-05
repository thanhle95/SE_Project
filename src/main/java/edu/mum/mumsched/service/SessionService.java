package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Session;

import java.util.List;

public interface SessionService {

    public void save(Session session);

    public Session getSessionBySessionId(long sessionId);

    public List<Session> getSessionsByBlockBlockId(long blockId);

    public List<Session> getAllSession();
}
