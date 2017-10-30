Lemon Cloud PlatForm
---------------------------------------

#### 项目简介
* Restful风格的JavaEE安全、稳定、快速的云平台
* 核心平台：
* (1) 基于OAuth2的认证授权平台 
* (2) 基于Jersey的webservice资源平台
---------------------------------------
#### 核心技术
* Spring Boot1.5.8+MyBatis3.2+Druid1.1.4+Apache Shiro 1.2
* SpringMVC+OAuth2+Jersey1.19.4+Shiro1.2
---------------------------------------
#### 项目管理与版本控制
* Maven3项目管理
* Git版本控制
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
<pre><code><profile>
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
	</profile></code></pre>