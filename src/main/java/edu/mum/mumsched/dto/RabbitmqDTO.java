package edu.mum.mumsched.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.mum.mumsched.domain.Course;

import java.util.HashSet;
import java.util.Set;

public class RabbitmqDTO {
    @JsonProperty("course_list")
    private Set<RabbitmqCourseDTO> courseList;
    @JsonProperty("block_list")
    private Set<RabbitmqBlockDTO> blockList;
    @JsonProperty("entry")
    private long entryId;

    public RabbitmqDTO() {
        this.courseList = new HashSet<>();
        this.blockList = new HashSet<>();
    }

    public void addCourse(RabbitmqCourseDTO courseDTO){
        courseList.add(courseDTO);
    }

    public void addBlock(RabbitmqBlockDTO blockDTO){
        blockList.add(blockDTO);
    }

    public Set<RabbitmqCourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(Set<RabbitmqCourseDTO> courseList) {
        this.courseList = courseList;
    }

    public Set<RabbitmqBlockDTO> getBlockList() {
        return blockList;
    }

    public void setBlockList(Set<RabbitmqBlockDTO> blockList) {
        this.blockList = blockList;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }
}
