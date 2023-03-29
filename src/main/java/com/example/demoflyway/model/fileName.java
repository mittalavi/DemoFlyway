package com.example.demoflyway.model;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class fileName {

    static public String[] getFiles(){
        String directoryPath = "/Users/avimittal/IdeaProjects/DemoFlyway/src/main/resources/db/migration";
        File directory = new File(directoryPath);
        String[] files = directory.list();
        String[] undo = Arrays.stream(files).filter(str->str.startsWith("U")).toArray(String[]::new);

        /*int n = (files.length-1)/2;
        int i=0;
        String[] undo = new String[n];
        for(String filename:files){
            if(filename.charAt(0)=='U'){
                undo[i++]=filename;
            }
        }*/
        Arrays.sort(undo);
        return undo;
    }

}
