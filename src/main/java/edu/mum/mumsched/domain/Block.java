package edu.mum.mumsched.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long blockId;

    @NotEmpty
    private String blockName;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private int FPPNum;
    private int MPPNum;
    private String entryName;
//    private long entryId;

    @JoinColumn(name="entry_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Entry entry;

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "block")
    private Set<Session> sessionList = new HashSet<>();
    public void addSession(Session session){
        sessionList.add(session);
        session.setBlock(this);
    }

//    @JoinColumn(name="schedule_id",nullable = true)
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Schedule schedule;

    public long getBlockId() {
        return blockId;
    }

    public void setBlockId(long blockID) {
        this.blockId = blockID;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getFPPNum() {
        return FPPNum;
    }

    public void setFPPNum(int FPPNum) {
        this.FPPNum = FPPNum;
    }

    public int getMPPNum() {
        return MPPNum;
    }

    public void setMPPNum(int MPPNum) {
        this.MPPNum = MPPNum;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    @Override
    public String toString() {
        return "{" +
                "blockId=" + blockId +
                ", blockName=" + blockName +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", FPPNum=" + FPPNum +
                ", MPPNum=" + MPPNum +
                ", entryName=" + entryName +
                ", entryId=" + entry.getEntryId() +
                ", sessionList=" + sessionList +
                '}';
    }

//    public Schedule getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(Schedule schedule) {
//        this.schedule = schedule;
//    }
}
