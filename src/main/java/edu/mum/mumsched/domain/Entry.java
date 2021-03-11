package edu.mum.mumsched.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long entryId;

    @NotEmpty
    private String entryName;
    private int FPPNum;
    private int MPPNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entry")
    @OneToMany(mappedBy = "entry")
    private Set<Block> blockList = new HashSet<>();

    public void addBlock(Block block) {
        blockList.add(block);
        block.setEntry(this);
    }

    public void removeBlock(Block block) {
        blockList.remove(block);
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryID) {
        this.entryId = entryID;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(Set<Block> blockList) {
        this.blockList = blockList;
    }
}
