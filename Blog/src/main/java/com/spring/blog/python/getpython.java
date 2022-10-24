package com.spring.blog.python;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class getpython {
@Test
        public static void main(String[] args) {
            // TODO Auto-generated method stub
    //Scanner sc = new Scanner(System.in);
    //String a =sc.next();
            Process proc;
            try {
                proc = Runtime.getRuntime().exec("python D:\\pythonProject\\爬虫协成3\\爬京东参数.py");// 执行py文件
                //用输入输出流来截取结果
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gb2312"));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
                proc.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
