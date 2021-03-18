package edu.mum.mumsched.service.imp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.mum.mumsched.domain.Block;
import edu.mum.mumsched.domain.Course;
import edu.mum.mumsched.dto.RabbitmqBlockDTO;
import edu.mum.mumsched.dto.RabbitmqCourseDTO;
import edu.mum.mumsched.dto.RabbitmqDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import edu.mum.mumsched.service.RabbitMQService;
import edu.mum.mumsched.dto.ScheduleBuilderDTO;
import org.springframework.beans.BeanUtils;



@Service
public class RabbitMQServiceImp implements RabbitMQService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQServiceImp.class);
    private final AmqpTemplate rabbitTemplate;
    private final String exchange;
    private final String routingkey;

    public RabbitMQServiceImp(@Autowired AmqpTemplate rabbitTemplate,
                                    @Value("${spring.rabbitmq.exchange}") String exchange,
                                    @Value("${spring.rabbitmq.routingkey}") String routingkey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingkey = routingkey;
    }


    public RabbitmqDTO convertToRabbitJson(ScheduleBuilderDTO scheduleRabbitMQJob){
        RabbitmqDTO rabbitmqDTO = new RabbitmqDTO();

        for(Block block: scheduleRabbitMQJob.getBlockList()){
            RabbitmqBlockDTO blockDTO = new RabbitmqBlockDTO();
            blockDTO.setBlockId(block.getBlockId());
            blockDTO.setBlockName(block.getBlockName());
            blockDTO.setEntryName(block.getEntryName());
            blockDTO.setFPPNum(block.getFPPNum());
            blockDTO.setMPPNum(block.getMPPNum());
            blockDTO.setStartDate(block.getStartDate().toString());
            blockDTO.setEndDate(block.getEndDate().toString());

            rabbitmqDTO.addBlock(blockDTO);
        }

        for(Course course: scheduleRabbitMQJob.getCourseList()){
            RabbitmqCourseDTO rabbitmqCourseDTO = new RabbitmqCourseDTO();
            BeanUtils.copyProperties(course, rabbitmqCourseDTO);

            rabbitmqDTO.addCourse(rabbitmqCourseDTO);
        }
        rabbitmqDTO.setEntryId(scheduleRabbitMQJob.getEntryId());

        return rabbitmqDTO;
    }

    public void send(ScheduleBuilderDTO scheduleRabbitMQJob){

        RabbitmqDTO rabbitmqDTO = convertToRabbitJson(scheduleRabbitMQJob);

        rabbitTemplate.convertAndSend(exchange, routingkey, rabbitmqDTO);

        LOGGER.info("Push unify job to queue");
    }

    public void received(){

    }
}
