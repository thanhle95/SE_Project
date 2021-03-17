package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.common.ScheduleStatus;
import edu.mum.mumsched.dao.ScheduleDao;
import edu.mum.mumsched.domain.Schedule;
import edu.mum.mumsched.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ScheduleServiceImp implements ScheduleService {

    @Autowired
    ScheduleDao ScheduleDAO;

    public void save(Schedule Schedule) {
        ScheduleDAO.save(Schedule);
        return;
    }

    @Override
    public Schedule getScheduleByScheduleId(long scheduleID) {
        return ScheduleDAO.findScheduleByScheduleId(scheduleID);
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return ScheduleDAO.getAllSchedules();
    }

    @Override
    public List<Schedule> getSchedulesByStatus(ScheduleStatus status) {
        return ScheduleDAO.findSchedulesByStatus(status);
    }

    @Override
    public void GenerateSchedule() {

    }
}
