package edu.mum.mumsched.dao;

import edu.mum.mumsched.common.ScheduleStatus;
import edu.mum.mumsched.domain.Schedule;
import edu.mum.mumsched.domain.Session;
import edu.mum.mumsched.service.ScheduleService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDao extends CrudRepository<Schedule, Long> {
    @Query("select sc from Schedule sc where sc.scheduleId= :ScheduleId")
    public Schedule findScheduleByScheduleId(@Param("ScheduleId") long ScheduleId);

    @Query("select sc from Schedule sc where sc.status= :status")
    public List<Schedule> findSchedulesByStatus(@Param("status") ScheduleStatus status);

    @Query("select sc from Schedule sc")
    public List<Schedule> getAllSchedules();

    @Query("delete from Schedule se where se.scheduleId= :id")
    public void removeById(@Param("id") Long scheduleId);
}
