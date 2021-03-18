package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SessionService {

    public void save(Session session);

    public Session getSessionBySessionId(long sessionId);

    public List<Session> getSessionsByBlockBlockId(long blockId);

    public List<Session> getAllSession();
}
