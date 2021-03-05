package edu.mum.mumsched.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    private Date startDate;
    private Date endDate;
    private int FPPNum;
    private int MPPNum;
    private String entryName;
    private long entryId;

    @JoinColumn(name="entryId",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Entry entry;

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private Set<Session> sessionList = new HashSet<>();

    public void addSession(Session session){
        sessionList.add(session);
        session.setBlock(this);
    }


    public long getBlockId() {
        return blockId;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryID) {
        this.entryId = entryID;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
}
