package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Block;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlockDao extends CrudRepository<Block, Long> {
    @Query("select s from Block s where s.blockId= :blockID")
    public Block findBlockByBlockID(@Param("blockID") long blockID);

    @Query("select s from Block s where s.blockName= :blockName")
    public Block findBlockByBlockName(@Param("blockName") String blockName);

    @Query("select s from Block s")
    public List<Block> getAllBlock();

    @Query("select s from Block s where s.entryName= :entryName")
    public List<Block> findBlockByEntryName(@Param("entryName") String entryName);

    @Query("select s from Block s where s.entry.entryId= :entryId")
    public List<Block> findBlockByEntryEntryId(@Param("entryId") long entryId);

    @Transactional
    @Modifying
    @Query("delete from Block s where s.blockId= :blockID")
    public void deleteBlockByBlockID(@Param("blockID") long blockID);
}
