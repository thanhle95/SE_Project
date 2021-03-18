package edu.mum.mumsched.service;

import edu.mum.mumsched.common.ScheduleStatus;
import edu.mum.mumsched.domain.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    public void save(Schedule schedule);

    public Schedule getScheduleByScheduleId(long scheduleId);

    public List<Schedule> getSchedulesByStatus(ScheduleStatus status);

    public List<Schedule> getAllSchedule();

    public void GenerateSchedule();
}
