package com.alinesno.infra.base.starter.utils.base;

/**
 * 基础生成模板,参考规范：https://portal.infra.linesno.com/architecture/03_%E6%9C%8D%E5%8A%A1%E8%A7%84%E5%88%92/#%E6%9C%8D%E5%8A%A1%E8%A7%84%E5%88%92-2
 * @author luoxiaodong
 * @version 1.0.0
 */
public class BaseVelocityTemplate {

	/**
	 * 1. `facade`：表示门面（Facade），用于提供对外的接口和服务。它作为模块对外的统一入口，隐藏了内部复杂的实现细节，提供简单易用的接口供其他模块调用。
	 * 2. `domain`：表示领域（Domain），用于定义模块的核心领域模型和业务逻辑。在该模块中，通常包含了实体类、值对象、领域服务等与领域相关的代码。
	 * 3. `api`：表示接口（API），用于定义模块对外暴露的接口。在该模块中，通常包含了接口类、DTO（数据传输对象）等与外部交互的代码。
	 * 4. `adapter`：表示适配器（Adapter），用于与外部系统进行交互和适配。在该模块中，通常包含了与外部系统的接口实现、数据转换、适配器等代码。
	 * 5. `client`：表示客户端（Client），用于与模块进行交互的客户端代码。在该模块中，通常包含了调用模块提供的接口、处理响应结果等与客户端交互相关的代码。
	 * 6. `boot`：表示启动（Boot），用于处理模块的启动逻辑。在该模块中，通常包含了初始化、配置加载、依赖注入等启动过程的代码。
	 */
	public static final String BOOT_SUFFIX = "-boot" ; 
	public static final String API_SUFFIX = "-facade" ;
	public static final String CLIENT_SUFFIX = "-client" ;
	public static final String ADAPTER_SUFFIX = "-adapter" ;
	public static final String STARTER_SUFFIX = "-domain" ;
	public static final String WEB_SUFFIX = "-web" ; 
	public static final String UI_SUFFIX = "-ui" ; 
	public static final String UNI_SUFFIX = "-uni" ; 
	public static final String GATEWAY_SUFFIX = "-gateway" ; 

	/**
	 * 项目空间路径
	 */
	public static final String PARENT_POM = "pom.xml";  // 父类路径
	public static final String SOURCE_PATH = "src";  // 源码路径
	public static final String PROJECT_PATH = "main/java";  // 项目路径
	public static final String TEST_PROJECT_PATH = "test/java";  // 测试路径
	
	/**
	 * 资源文件，空间路径
	 */
	public static final String RESOUCES_PATH = "main/resources";
	public static final String TEST_RESOUCES_PATH = "test/resources";

	/**
	 * mybatis空间路径
	 */
	public static final String MYBATIS_PATH = "main/resources/mapper";

	/**
	 * html空间路径
	 */
	public static final String TEMPLATES_PATH = "main/resources/templates";

}
