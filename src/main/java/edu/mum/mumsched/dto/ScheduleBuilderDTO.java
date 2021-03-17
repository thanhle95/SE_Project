package edu.mum.mumsched.dto;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.mum.mumsched.domain.Block;
import edu.mum.mumsched.domain.Course;

public class ScheduleBuilderDTO {
    @JsonProperty("course_list")
    private Set<Course> courseList;
    @JsonProperty("block_list")
    private Set<Block> blockList;
    @JsonProperty("entry")
    private long entryId;

    public ScheduleBuilderDTO(Set<Course> courseList, Set<Block> blockList, long entry_schedule) {
        this.courseList = courseList;
        this.blockList = blockList;
        this.entryId = entry_schedule;
    }

    public Set<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(Set<Course> courseList) {
        this.courseList = courseList;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public Set<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(Set<Block> blockList) {
        this.blockList = blockList;
    }

    @Override
    public String toString() {
        return "{" +
                "courseList:" + courseList +
                ", blockList:" + blockList +
                ", entryId:" + entryId +
                '}';
    }
}
