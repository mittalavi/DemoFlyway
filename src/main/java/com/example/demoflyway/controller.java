package com.example.demoflyway;

import com.example.demoflyway.model.fileName;
import com.example.demoflyway.sql.runfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {


    @Autowired
    private runfile rf;

    @PostMapping("/{did}")
    public void undo(@PathVariable("did") int did) throws Exception {

        rf.attem(did);
    }
    @GetMapping("/files")
    public void print() throws Exception {
        fileName.getFiles();
    }
}
