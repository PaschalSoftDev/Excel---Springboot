package com.amaka.springexcel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class ApplicationInitializer {
    @Autowired
    private BranchService branchService;

   public void init () throws IOException{
        System.out.println("INPUT FILE PATH ");
        Scanner input =new Scanner(System.in);
        String path = input.nextLine();
        branchService.saveBranch(path);
        System.out.println("<<<<<branch fully migrated>>>>>>>>");
    }
}
