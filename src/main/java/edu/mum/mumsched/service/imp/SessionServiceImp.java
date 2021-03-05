package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.SessionDao;
import edu.mum.mumsched.domain.Session;
import edu.mum.mumsched.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SessionServiceImp implements SessionService {
    @Autowired
    SessionDao sessionDao;

    public void save(Session session){
        sessionDao.save(session);
    }

    @Override
    public Session getSessionBySessionId(long sessionId) {
        return sessionDao.findSessionBySessionId(sessionId);
    }

    @Override
    public List<Session> getSessionsByBlockBlockId(long blockId){
        return sessionDao.findSessionsByBlockBlockId(blockId);
    }

    @Override
    public List<Session> getAllSession() {
        return sessionDao.getAllSessions();
    }
}

