package com.vcyber.myframe.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * description :
 * author : zjl
 * date : 3/20/23
 */
public class TestText {
    public static void main(String[] args) {
        try {
            testReadFile1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void testReadFile1() throws IOException {
        // 带缓冲的流读取，默认缓冲区8k
        String fileName = "/Users/zhangjialong/Downloads/idiom_warehouse.json";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}