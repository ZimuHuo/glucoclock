package com.glucoclock.views.util;

import com.vaadin.flow.server.VaadinSession;

import java.util.Random;

public class verificationCode {
    static public String getRandomNum(){
        Random random = new Random();
        String num = random.nextInt(99999999)+""; //nice trick for conversion in case you are wondering :D
        StringBuffer buffer = new StringBuffer();
        for (int i =0; i<7-num.length();i++){
            buffer.append(random.nextInt(9)); //took me a while to figure out this :D forced consistency
        }
        num = buffer.toString()+num;
        return num;
    }
}
