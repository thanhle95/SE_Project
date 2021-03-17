package edu.mum.mumsched.controller;

import edu.mum.mumsched.domain.Block;
import edu.mum.mumsched.domain.Entry;
import edu.mum.mumsched.service.BlockService;
import edu.mum.mumsched.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlockController {
    @Autowired
    BlockService blockService;

    @Autowired
    EntryService entryService;

    @RequestMapping(value = "/block", method = RequestMethod.GET)
    public String blockListForm(@ModelAttribute("newBlock") Block block, Model model) {
        List<Block> blockList = new ArrayList<>();
        blockList.addAll(blockService.getAllBlock());
        List<String> entryNameList = new ArrayList<>();
        for(Entry entry : entryService.getAllEntry()) {
            entryNameList.add(entry.getEntryName());
        }
        model.addAttribute("entryNameList",entryNameList);
        model.addAttribute("blockList",blockList);
        model.addAttribute("newBlock",block);
        return "admin/blockListForm";
    }

    @RequestMapping(value = "/block/add", method = RequestMethod.GET)
    public String blockAddForm(@ModelAttribute("newBlock") Block block, Model model) {
        List<Block> blockList = new ArrayList<>();
        blockList.addAll(blockService.getAllBlock());
        List<String> entryNameList = new ArrayList<>();
        for(Entry entry : entryService.getAllEntry()) {
            entryNameList.add(entry.getEntryName());
        }
        model.addAttribute("entryNameList",entryNameList);
        model.addAttribute("blockList",blockList);
        model.addAttribute("newBlock",block);
        return "admin/blockAddForm";
    }

    @RequestMapping(value = "/block/addseries", method = RequestMethod.GET)
    public String blockAddSeriesForm(@ModelAttribute("newBlock") Block block, Model model) {
        List<Block> blockList = new ArrayList<>();
        blockList.addAll(blockService.getAllBlock());
        List<String> entryNameList = new ArrayList<>();
        for(Entry entry : entryService.getAllEntry()) {
            entryNameList.add(entry.getEntryName());
        }
        model.addAttribute("entryNameList",entryNameList);
        model.addAttribute("blockList",blockList);
        model.addAttribute("newBlock",block);
        return "admin/blockAddSeriesForm";
    }

    @RequestMapping(value = {"/block/addnewblock"}, method = RequestMethod.POST)
    public String registerNewBlock(@ModelAttribute("newBlock") Block block, Model model) {
        Entry entry = entryService.getEntryByEntryName(block.getEntryName());
        entry.addBlock(block);
        blockService.save(block);
        return "redirect:/block";
    }

    @RequestMapping(value = {"/block/addnewblockseries"}, method = RequestMethod.POST)
    public String registerNewBlock(@RequestParam(name = "entry_name") String entry_name,
                                   @RequestParam(name = "block_start_month") String block_start_month,
                                   @RequestParam(name = "block_num") String block_num,
                                   @RequestParam(name = "block_start_date") String block_start_date) {
                                   //@ModelAttribute("newBlock") Block block, Model model) {
        Entry entry = entryService.getEntryByEntryName(entry_name);
        int block_number = Integer.valueOf(block_num);
        int block_year = entry.getStartDate().getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate block_date = LocalDate.parse("01"+ " " + block_start_month.substring(0,3)+ " " +String.valueOf(block_year),formatter);

        LocalDate start_date = LocalDate.parse(block_start_date);
        for(int i=0;i<block_number;i++){
            Block block = new Block();
            block.setEntryName(entry_name);
            block.setMPPNum(entry.getMPPNum());
            block.setFPPNum(entry.getFPPNum());
            block.setBlockName(block_date.getMonth().toString().substring(0,3) + "_" + String.valueOf(block_year));
            block.setStartDate(start_date);
            block.setEndDate(start_date.plusDays(28));
            start_date = start_date.plusMonths(1);
            entry.addBlock(block);
            block_date = block_date.plusMonths(1);

            blockService.save(block);
        }
        return "redirect:/block";
    }

    @RequestMapping(value = {"/block/edit/{id}"}, method = RequestMethod.GET)
    public String blockEditForm(@PathVariable("id") Long id, Model model) {
        Block block = blockService.getBlockByBlockID(id);

        List<String> entryNameList = new ArrayList<>();
        for(Entry entry : entryService.getAllEntry()) {
            entryNameList.add(entry.getEntryName());
        }
        model.addAttribute("editBlock",block);
        model.addAttribute("entryNameList",entryNameList);
        return "admin/blockUpdateForm";
    }

    @RequestMapping(value = {"/block/update/{id}"}, method = RequestMethod.POST)
    public String blockUpdateForm(@PathVariable("id") Long id, Block block, Model model) {
        block.setBlockId(id);

        Block oldBlock = blockService.getBlockByBlockID(id);
        // remove block from old entry if entry is changed
        if(!oldBlock.getEntryName().equals(block.getEntryName())) {
            Entry entry1 = entryService.getEntryByEntryName(oldBlock.getEntryName());
            entry1.removeBlock(oldBlock);

            Entry entry2 = entryService.getEntryByEntryName(block.getEntryName());
            entry2.addBlock(block);
            block.setEntry(entry2);
        }

        blockService.save(block);
        return "redirect:/block";
    }

    @RequestMapping(value = {"/block/delete/{id}"}, method = RequestMethod.GET)
    public String blockDeleteForm(@PathVariable("id") Long id, Model model) {
        Block block = blockService.getBlockByBlockID(id);
        Entry entry = entryService.getEntryByEntryName(block.getEntryName());
        entry.removeBlock(block);
        blockService.deleteBlockByBlockID(id);
        return "redirect:/block";
    }
}
