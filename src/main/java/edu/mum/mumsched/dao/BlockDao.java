package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Block;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockDao extends CrudRepository<Block, Long> {
    @Query("select s from Block s where s.blockId= :blockID")
    public Block findBlockByBlockID(@Param("blockID") long blockID);

    @Query("select s from Block s where s.blockName= :blockName")
    public Block findBlockByBlockName(@Param("blockName") String blockName);

    @Query("select s from Block s")
    public List<Block> getAllBlock();
}
