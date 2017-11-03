Lemon Cloud PlatForm
---------------------------------------

#### 项目简介
* Restful风格的JavaEE安全、稳定、快速的云平台
---------------------------------------
#### 核心技术
* 前端技术：摒弃掉JQuery和Bootstrap，和大牛讨论后的结果
 > React (适合构建大应用，兼容web和原生app应用)<br>
 > VUE (适合构建小型应用，也可以看看，但不作为本项目的重点)<br>
 > AntD  (基于React的UI组件)
* 后台技术：
 >模板引擎：Thymeleaf,代替JSP技术
 >Restful框架：Spring MVC / Jersey<br>
 >微服务基础框架：Spring Boot1.5.8<br>
 >ORM映射框架：MyBatis3.2<br>
 >数据源框架：Druid1.1.5<br>
 >安全认证框架：Shiro 1.2<br>
 >缓存技术：Redis3<br>
 >消息中间件：ActiveMQ<br>
 >工作流引擎：Flowable<br>
 >PRC去中心化的微服务框架：Dubbox / Thrift<br>
 >分布式云平台框架：Spring Cloud
---------------------------------------
#### 项目管理与版本控制
* Maven3项目管理
* Git版本控制
---------------------------------------
#### 相关服务器
---------------------------------------
* Redis3： 缓存数据库
* MySQL5： 存储数据库
* Tomcat： WEB应用容器
 >Spring Boot框架默认内置改容器，后期可以更换为Jetty或者Undertow，但据说Undertow的性能和内存使用是最好的
* ZooKeeper：分布式的，开放源码的分布式应用程序协调服务
* ActiveMQ：消息中间件服务器
* Docker: 应用容器引擎
---------------------------------------
#### 如何利用lemcloud开发
* 安装JDK1.8-64bit
 	>方案：配置相关环境变量JAVA_HOME CLASSPATH  PATH等
* 安装Eclipse-JEE-neon3-64bit 
	>方案：切记为neon3-64bit版，其他有的版本并不集成git和maven插件
* 配置eclipse的默认WorkSpace编码默认为UTF-8
	>方案：Window/Prepferences/General/Workspace下，更改GBK为UTF-8编码
* 配置eclipse的默认.properties属性文件的编码为UTF-8 
	>方案：Window/Prepferences/General/Content Types/Text/Java Properties File/下，更新ISO-8859-1为UTF-8编码
* .m2中的settings.xml文件中要配置jdk的版本
	>方案：在settings.xml文件中的profiles配置项中增加如下的profile
```xml
    <profiles>
        .............其他配置
        <profile>
        	<id>jdk-1.8</id>
        	<activation>
        	<activeByDefault>true</activeByDefault>
        	<jdk>1.8</jdk>
        	</activation>
        	<properties>
        	    <maven.compiler.source>1.8</maven.compiler.source>
        	    <maven.compiler.target>1.8</maven.compiler.target>
        	    <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
        	</properties>
    	</profile>
        ..............其他配置
    </profiles>
```