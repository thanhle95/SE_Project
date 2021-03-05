package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.BlockDao;
import edu.mum.mumsched.domain.Block;
import edu.mum.mumsched.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImp implements BlockService {

    @Autowired
    BlockDao blockDAO;

    public void save(Block block) {
        blockDAO.save(block);
        return;
    }

    @Override
    public Block getBlockByBlockName(String blockName){
        return blockDAO.findBlockByBlockName(blockName);
    }

    @Override
    public Block getBlockByBlockID(long blockID) {
        return blockDAO.findBlockByBlockID(blockID);
    }

    @Override
    public List<Block> getAllBlock() {
        return blockDAO.getAllBlock();
    }
}
