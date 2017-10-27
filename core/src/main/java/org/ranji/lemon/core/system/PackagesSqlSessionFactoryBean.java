package org.ranji.lemon.core.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

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
 * 自动做mybatis的别名处理
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */

public class PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean {    
    
    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";    
    
    private static Logger logger = Logger.getLogger(PackagesSqlSessionFactoryBean.class);    
    
    @Override    
    public void setTypeAliasesPackage(String typeAliasesPackage) {    
        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();    
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);    
        typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +    
                ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;    
    
        //将加载多个绝对匹配的所有Resource    
        //将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分    
        //然后进行遍历模式匹配    
        try {    
            List<String> result = new ArrayList<String>();    
            Resource[] resources =  resolver.getResources(typeAliasesPackage);    
            if(resources != null && resources.length > 0){    
                MetadataReader metadataReader = null;    
                for(Resource resource : resources){    
                    if(resource.isReadable()){    
                       metadataReader =  metadataReaderFactory.getMetadataReader(resource);    
                        try {    
                            result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                        } catch (ClassNotFoundException e) {    
                            e.printStackTrace();    
                        }    
                    }    
                }    
            }    
            if(result.size() > 0) {    
                super.setTypeAliasesPackage(StringUtils.join(result.toArray(), ","));    
            }else{    
                logger.warn("参数typeAliasesPackage:"+typeAliasesPackage+"，未找到任何包");    
            }    
            //logger.info("d");    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
    }    
    
}    
