package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Block;

import java.util.List;

public interface BlockService {
    public void save(Block block);

    public Block getBlockByBlockID(long blockID);

    public Block getBlockByBlockName(String blockName);

    public List<Block> getAllBlock();

    public List<Block> getBlockByEntryName(String entryName);

    public List<Block> getBlockByEntryEntryId(long entryId);

    public void deleteBlockByBlockID(long blockID);
}
