package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.domain.Block;
import edu.mum.mumsched.domain.Course;
import edu.mum.mumsched.dto.ScheduleBuilderDTO;
import edu.mum.mumsched.service.RabbitMQService;
import edu.mum.mumsched.service.ScheduleBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ScheduleBuilderServiceImp implements ScheduleBuilderService {

    @Autowired
    RabbitMQService RabbitMQService;

    public ScheduleBuilderServiceImp(RabbitMQService rabbitMQService) {
        RabbitMQService = rabbitMQService;
    }

    @Override
    public void runScheduleBuilder(long entryId, Set<Course> courseList, Set<Block> blockList){
        ScheduleBuilderDTO scheduleBuilderConfig = new ScheduleBuilderDTO(courseList, blockList, entryId);

        RabbitMQService.send(scheduleBuilderConfig);
    }

}
