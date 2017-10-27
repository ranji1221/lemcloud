package org.ranji.lemon.core.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.ranji.lemon.core.system.PackagesSqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class MyBatisConfig {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private Environment env;
	
	@Bean
	public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException{
		SqlSessionFactoryBean sqlSessionFactoryBean = new PackagesSqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		/** 添加mapper 扫描路径 */        
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();  
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + env.getProperty("mybatis.mapperLocations");
		sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
		sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		
		return sqlSessionFactoryBean;
	}
}
