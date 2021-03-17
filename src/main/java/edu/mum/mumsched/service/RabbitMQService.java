package edu.mum.mumsched.service;

import edu.mum.mumsched.dto.ScheduleBuilderDTO;

public interface RabbitMQService {
  void send(ScheduleBuilderDTO scheduleRabbitMQJob);
}
