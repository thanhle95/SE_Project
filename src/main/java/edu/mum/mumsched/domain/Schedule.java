package edu.mum.mumsched.domain;

import edu.mum.mumsched.common.ScheduleStatus;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long scheduleId;
    private ScheduleStatus status;

    @JoinColumn(name="entry_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Entry entry;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "schedules_blocks",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "block_id")
    )
    private Set<Block> blockList = new HashSet<>();

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Set<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(Set<Block> blockList) {
        this.blockList = blockList;
    }

    public void addBlock(Block block) {
        this.blockList.add(block);
        //block.setSchedule(this);
    }

    public void sortBlock(){
        this.blockList = this.blockList
                         .stream()
                         .sorted((b1,b2) -> (int)b2.getBlockId() - (int)b1.getBlockId())
                         .collect(Collectors.toSet());
    }
}
