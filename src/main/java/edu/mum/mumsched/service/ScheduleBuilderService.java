package edu.mum.mumsched.service;


import edu.mum.mumsched.domain.Block;
import edu.mum.mumsched.domain.Course;

import java.util.Set;

public interface ScheduleBuilderService {
   public void runScheduleBuilder(long entryId, Set<Course> courseList, Set<Block> blockList);
}
