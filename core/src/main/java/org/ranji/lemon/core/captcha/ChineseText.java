package org.ranji.lemon.core.captcha;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.util.Configurable;
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the"License"); 
 * you may not use this file except in compliance with the License.  
 * You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
 * See the License for the specific language governing permissions and limitations under the License.
 * Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * 验证码生成中文的工具类
 * @author RanJi
 * @date 2017-10-13
 * @since JDK1.7
 * @version 1.0
 */
public class ChineseText extends Configurable implements TextProducer{

	@Override
	public String getText() {
		int length = getConfig().getTextProducerCharLength();  
        char[] chars = getConfig().getTextProducerCharString();  
        String finalWord = "", firstWord = "";  
        String[] array = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  
                "a", "b", "c", "d", "e", "f" };  
  
        Random rand = new Random();  
  
        for (int i = 0; i < length; i++) {  
            switch (rand.nextInt(3)) {  
            case 1:  
                firstWord = String.valueOf(chars[rand.nextInt(chars.length)]);  
                break;  
            case 2:  
                int r1,  
                r2,  
                r3,  
                r4;  
                String strH,  
                strL;// high&low  
                r1 = rand.nextInt(3) + 11; // 前闭后开[11,14)  
                if (r1 == 13) {  
                    r2 = rand.nextInt(7);  
                } else {  
                    r2 = rand.nextInt(16);  
                }  
  
                r3 = rand.nextInt(6) + 10;  
                if (r3 == 10) {  
                    r4 = rand.nextInt(15) + 1;  
                } else if (r3 == 15) {  
                    r4 = rand.nextInt(15);  
                } else {  
                    r4 = rand.nextInt(16);  
                }  
  
                strH = array[r1] + array[r2];  
                strL = array[r3] + array[r4];  
  
                byte[] bytes = new byte[2];  
                bytes[0] = (byte) (Integer.parseInt(strH, 16));  
                bytes[1] = (byte) (Integer.parseInt(strL, 16));  
  
                try {  
                    firstWord = new String(bytes, "GBK");  
                } catch (UnsupportedEncodingException e) {  
                    e.printStackTrace();  
                }  
                break;  
            default:  
                firstWord = String.valueOf(chars[rand.nextInt(chars.length)]);  
                break;  
            }  
            finalWord += firstWord;  
        }  
        return finalWord;  
	}

}
