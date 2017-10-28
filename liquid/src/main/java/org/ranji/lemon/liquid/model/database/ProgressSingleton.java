package org.ranji.lemon.liquid.model.database;

import java.util.Hashtable;
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
 * 
 * @author fengjie
 * @date 2017-10-18
 * @since JDK1.7
 * @version 1.0
 */

public class ProgressSingleton {
	//为了防止多用户并发，使用线程安全的Hashtable  
    private static Hashtable<Object, Object> table = new Hashtable<>();  
      
    public static void put(Object key, Object value){  
        table.put(key, value);  
    }  
      
    public static Object get(Object key){  
        return table.get(key);  
    }  
      
    public static Object remove(Object key){  
        return table.remove(key);  
    }  
}
