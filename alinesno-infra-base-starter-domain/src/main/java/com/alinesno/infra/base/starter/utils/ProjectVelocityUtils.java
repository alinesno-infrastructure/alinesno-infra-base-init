package com.alinesno.infra.base.starter.utils;

import com.alinesno.infra.base.starter.api.dto.ProjectInfoDto;
import com.alinesno.infra.base.starter.utils.base.BaseVelocityTemplate;
import com.alinesno.infra.common.core.constants.Constants;
import com.alinesno.infra.common.core.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.exception.RpcServiceRuntimeException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 种子项目模板生成
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class ProjectVelocityUtils extends BaseVelocityTemplate {

	private static final Logger log = LoggerFactory.getLogger(ProjectVelocityUtils.class);

	/**
	 * 设置模板变量信息
	 *
	 * @return 模板列表
	 */
	public static VelocityContext prepareSeedContext(ProjectInfoDto info) {

		VelocityContext velocityContext = new VelocityContext();

		velocityContext.put("artifactId", info.getArtifactId());
		velocityContext.put("groupId", info.getGroupId());

//		velocityContext.put("domain", info.getDomain());
//		velocityContext.put("checkEnv", info.getCheckEnv());
//		velocityContext.put("generatorDemo", info.getGeneratorDemo());
//		velocityContext.put("jdk", info.getJdk());
//		velocityContext.put("packageType", info.getPackageType());
//		velocityContext.put("alinesnoVersion", info.getAlinesnoVersion());
//		velocityContext.put("alinesnoUI", info.getAlinesnoUI());
//		velocityContext.put("dependency", info.getDependency());
//		velocityContext.put("author", info.getAuthor());
//		velocityContext.put("authorEmail", info.getAuthorEmail());

		velocityContext.put("deployType", info.getDeployType());

		// >>>>>>>>>>>>>>>>>>>>>> 添加模板依赖关系_start  >>>>>>>>>>>>>>>>>>>>
		
//		MavenTemplateRootBean tb = info.getTemplateBean() ;
//		if(tb != null) {
//			velocityContext.put("apiDependency", tb.getDependencies().get(DependencyEnums.API.label())) ;
//			velocityContext.put("starterDependency",  tb.getDependencies().get(DependencyEnums.STARTER.label())) ;
//			velocityContext.put("gatewayDependency",  tb.getDependencies().get(DependencyEnums.GATEWAY.label())) ;
//			velocityContext.put("webDependency",  tb.getDependencies().get(DependencyEnums.WEB.label())) ;
//			velocityContext.put("bootDependency",  tb.getDependencies().get(DependencyEnums.BOOT.label())) ;
//
//			velocityContext.put("dependencyProjectName", tb.getProject().getName()); // 项目名称
//			velocityContext.put("packageScan", tb.getProject().getPackageScan()); // 包扫描
//		}
		
		// >>>>>>>>>>>>>>>>>>>>>> 添加模板依赖关系_end  >>>>>>>>>>>>>>>>>>>>

		log.debug("info = {}", ToStringBuilder.reflectionToString(info));

		return velocityContext;
	}

	/**
	 * 获取UI项目文件工程模板
	 *
	 * @return 模板列表
	 */
	public static List<String> getUiProjectTemplatesList(ProjectInfoDto info) {

		List<String> templates = new ArrayList<String>();
		
		// docker
		templates.add("seed/ui/docker/build-docker.sh.vm");
		templates.add("seed/ui/docker/Dockerfile.vm");
		templates.add("seed/ui/docker/nginx.conf.vm");
		templates.add("seed/ui/docker/replace_api_url.sh.vm");
		
		// public
		templates.add("seed/ui/public/favicon.ico.vm");
		templates.add("seed/ui/public/index.html.vm");
		templates.add("seed/ui/public/config.js.vm");
		
		templates.add("seed/ui/src/asserts/gitkeep.vm");
		templates.add("seed/ui/src/components/gitkeep.vm");
		
		templates.add("seed/ui/src/main.js.vm");
		templates.add("seed/ui/src/permission.js.vm");
		templates.add("seed/ui/src/router/index.js.vm");

		// dashboard
		templates.add("seed/ui/src/views/dashboard/home.vue.vm");
		templates.add("seed/ui/src/views/template.vue.vm");
		templates.add("seed/ui/src/views/demo.vue.vm");
		
		// 状态
		templates.add("seed/ui/src/store/getters.js.vm");
		templates.add("seed/ui/src/store/index.js.vm");
		templates.add("seed/ui/src/store/modules/app.js.vm");
		templates.add("seed/ui/src/store/modules/permission.js.vm");
		templates.add("seed/ui/src/store/modules/settings.js.vm");
		templates.add("seed/ui/src/store/modules/tagsView.js.vm");
		templates.add("seed/ui/src/store/modules/user.js.vm");

		templates.add("seed/ui/babel.config.js.vm");
		
		templates.add("seed/gitignore.vm");
		
		templates.add("seed/ui/qiniu-upload.sh.vm");
		templates.add("seed/ui/k8s-dev-ingress.yaml.vm");
		templates.add("seed/ui/k8s-dev.yaml.vm");
		templates.add("seed/ui/package.json.vm");
		templates.add("seed/ui/pom.xml.vm");
		templates.add("seed/ui/vue.config.js.vm");

		// 隐藏文件
		templates.add("seed/ui/hidden/editorconfig.vm");
		templates.add("seed/ui/hidden/env.development.vm");
		templates.add("seed/ui/hidden/env.production.vm");
		templates.add("seed/ui/hidden/env.staging.vm");
		templates.add("seed/ui/hidden/eslintignore.vm");
		templates.add("seed/ui/hidden/eslintrc.js.vm");
		templates.add("seed/ui/hidden/gitkeep.vm");

//		log.debug("templates = {}", ToStringBuilder.reflectionToString(templates));

		return templates;
	}

	/**
	 * 获取Java项目文件工程模板
	 *
	 * @return 模板列表
	 */
	public static List<String> getJavaProjectTemplatesList(ProjectInfoDto info) {

		List<String> templates = new ArrayList<String>();

		// 配置pom.xml文件

		if("simple".equals(info.getProjectGenType())) {
			templates.add("seed/pom/pom.simple.boot.xml.vm");
			templates.add("seed/pom/pom.simple.parent.boot.xml.vm");
		}else {
			templates.add("seed/pom/pom.parent.xml.vm");
			templates.add("seed/pom/pom.api.xml.vm");
			templates.add("seed/pom/pom.api.xml.gitkeep.vm");
			templates.add("seed/pom/pom.api.xml.resource.gitkeep.vm");

			templates.add("seed/pom/pom.starter.xml.vm");
			templates.add("seed/pom/pom.starter.xml.gitkeep.vm");
			templates.add("seed/pom/pom.starter.xml.resource.gitkeep.vm");

			templates.add("seed/pom/pom.client.xml.vm");
			templates.add("seed/pom/pom.client.xml.gitkeep.vm");
			templates.add("seed/pom/pom.client.xml.resource.gitkeep.vm");

			templates.add("seed/pom/pom.adapter.xml.vm");
			templates.add("seed/pom/pom.adapter.xml.gitkeep.vm");
			templates.add("seed/pom/pom.adapter.xml.resource.gitkeep.vm");

			templates.add("seed/pom/pom.gateway.xml.vm");
			templates.add("seed/pom/pom.gateway.xml.gitkeep.vm");
			templates.add("seed/pom/pom.gateway.xml.resource.gitkeep.vm");

			templates.add("seed/pom/pom.boot.xml.vm");
			templates.add("seed/pom/pom.boot.xml.gitkeep.vm");
			templates.add("seed/pom/pom.boot.xml.resource.gitkeep.vm");
		}
		

		// Java工程
		templates.add("seed/Application.java.vm");
		templates.add("seed/CheckEnv.java.vm");
		templates.add("seed/spring/rpc-consumer.xml.vm");
		templates.add("seed/spring/rpc-provider.xml.vm");
		templates.add("seed/application-dev.yaml.vm");
		templates.add("seed/application-hub.yaml.vm");
		templates.add("seed/gitignore.service.vm");
		templates.add("seed/gitignore.vm");
		templates.add("seed/README.md.vm");

		// 示例工程
		templates.add("seed/demo/Service.java.vm");
		templates.add("seed/demo/Service.impl.java.vm");
		templates.add("seed/demo/Controller.java.vm");
		templates.add("seed/demo/Dto.java.vm");
		templates.add("seed/demo/Gateway.java.vm");

		// K8S文件
		templates.add("seed/k8s-dev-ingress.yaml.vm");
		templates.add("seed/k8s-dev.yaml.vm");
		
		if(!"simple".equals(info.getProjectGenType())) {
			templates.add("seed/helm/values.yaml.vm");
			templates.add("seed/helm/Chart.yaml.vm");
			templates.add("seed/helm/helmignore.vm");
			templates.add("seed/helm/README.md.vm");
			templates.add("seed/helm/templates/deployment.yaml.vm");
			templates.add("seed/helm/templates/service.yaml.vm");
			templates.add("seed/helm/templates/_helpers.tpl.vm");
			templates.add("seed/helm/templates/ingress.yaml.vm");
		}

//		log.debug("templates = {}", ToStringBuilder.reflectionToString(templates));
	
		// 前端工程文件
		templates.addAll(getUiProjectTemplatesList(info)) ;

		return templates;
	}
	
	/**
	 * 构建生成Java项目
	 * @return 
	 */
	public static Map<String , byte[]> genJavaProjectSeedByte(ProjectInfoDto info) {
		
		VelocityInitializer.initVelocity();
		VelocityContext context = prepareSeedContext(info) ;
		
		Map<String , byte[]> fileByteMap = new HashMap<String , byte[]>() ; 

		// 获取模板列表
		List<String> templates = ProjectVelocityUtils.getJavaProjectTemplatesList(info) ; 
		for (String template : templates) {
				
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
				
			String path = getGenPath(info, template);
			log.debug("template:{} , path:{}" , template , path) ; 
			
			String fname = getFileName(template, info);
			byte[] fileByte = sw.toString().getBytes() ; 
		
			fileByteMap.put(fname, fileByte) ; 
				
		}
		
		return fileByteMap ; 
	}

	/**
	 * 构建生成Java项目
	 * @return 
	 */
	public static byte[] genJavaProjectSeed(ProjectInfoDto info) {
		
		VelocityInitializer.initVelocity();
		VelocityContext context = prepareSeedContext(info) ;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		// 获取模板列表
		List<String> templates = ProjectVelocityUtils.getJavaProjectTemplatesList(info) ; 
		for (String template : templates) {
				
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
				
			try {
				String path = getGenPath(info, template);
				log.debug("template:{} , path:{}" , template , path) ; 
				
				ZipOutputStream zip = new ZipOutputStream(outputStream);
				String fname = getFileName(template, info);
				
				// 添加到zip
				zip.putNextEntry(new ZipEntry(fname));
				IOUtils.write(sw.toString(), zip, Constants.UTF8);
				IOUtils.closeQuietly(sw);
				
				zip.flush();
				zip.closeEntry();
				
			} catch (IOException e) {
				throw new RpcServiceRuntimeException("渲染模板失败，模板名：" + template);
			}
		}
		
		log.debug("outputStream.toByteArray().length = {}" , outputStream.toByteArray().length);
		
		return outputStream.toByteArray() ; 
	}

	public static String getGenPath(ProjectInfoDto info, String template) {
		String genPath = System.getProperty("user.dir") ; 
		return genPath + File.separator + getFileName(template, info);
	}
	
	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, ProjectInfoDto info) {
		
		// 文件名称
		String fileName = "";
		String artifactId = info.getArtifactId() ; 
		String packagesPath = info.getGroupId() ; 
	
		String packagesPathRev = packagesPath.replace("." , "/") ; 
		String apiProjectName = artifactId+API_SUFFIX ; 
		String starterProjectName = artifactId+STARTER_SUFFIX ; 
		
		String gatewayProjectName = artifactId+GATEWAY_SUFFIX ;
		String clientProjectName = artifactId+ CLIENT_SUFFIX;
		String adapterProjectName = artifactId+ ADAPTER_SUFFIX ;

		String bootProjectName = artifactId+ BOOT_SUFFIX ;
		String uiProjectName = artifactId+UI_SUFFIX ; 
		
		if("simple".equals(info.getProjectGenType())) {
			apiProjectName = artifactId+BOOT_SUFFIX ; 
			starterProjectName = artifactId+BOOT_SUFFIX ; 
		
			gatewayProjectName = artifactId+BOOT_SUFFIX ; 
			bootProjectName = artifactId+ BOOT_SUFFIX ; 
		}
		
		// pom 文件
		if (template.contains("pom.parent.xml.vm")) {
			fileName = StringUtils.format("pom.xml");
			
		} else if(template.contains("pom.simple.parent.boot.xml.vm")) {
			fileName = StringUtils.format("pom.xml");
		} else if(template.contains("pom.simple.boot.xml.vm")) {
			fileName = StringUtils.format("{}/pom.xml" , apiProjectName);
			
		} else if(template.contains("pom.api.xml.vm")) {
			fileName = StringUtils.format("{}/pom.xml" , apiProjectName);
		} else if(template.contains("pom.api.xml.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/.gitkeep" ,apiProjectName,  SOURCE_PATH ,  PROJECT_PATH ,packagesPathRev) ; 
		} else if(template.contains("pom.api.xml.resource.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/.gitkeep" ,apiProjectName,  SOURCE_PATH ,RESOUCES_PATH ) ; 
		} else if(template.contains("pom.starter.xml.vm")) {
			fileName = StringUtils.format("{}/pom.xml" , starterProjectName);
		} else if(template.contains("pom.starter.xml.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/.gitkeep" ,starterProjectName,  SOURCE_PATH ,  PROJECT_PATH ,packagesPathRev) ; 
		} else if(template.contains("pom.starter.xml.resource.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/.gitkeep" ,starterProjectName,  SOURCE_PATH ,RESOUCES_PATH ) ;

		} else if(template.contains("pom.gateway.xml.vm")) {
			fileName = StringUtils.format("{}/pom.xml" , gatewayProjectName);
		} else if(template.contains("pom.gateway.xml.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/.gitkeep" ,gatewayProjectName,  SOURCE_PATH ,  PROJECT_PATH ,packagesPathRev) ; 
		} else if(template.contains("pom.gateway.xml.resource.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/.gitkeep" ,gatewayProjectName,  SOURCE_PATH ,RESOUCES_PATH) ;

		} else if(template.contains("pom.client.xml.vm")) {
			fileName = StringUtils.format("{}/pom.xml" , clientProjectName);
		} else if(template.contains("pom.client.xml.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/.gitkeep" ,clientProjectName,  SOURCE_PATH ,  PROJECT_PATH ,packagesPathRev) ;
		} else if(template.contains("pom.client.xml.resource.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/.gitkeep" ,clientProjectName,  SOURCE_PATH ,RESOUCES_PATH) ;

		} else if(template.contains("pom.adapter.xml.vm")) {
			fileName = StringUtils.format("{}/pom.xml" , adapterProjectName);
		} else if(template.contains("pom.adapter.xml.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/.gitkeep" ,adapterProjectName,  SOURCE_PATH ,  PROJECT_PATH ,packagesPathRev) ;
		} else if(template.contains("pom.adapter.xml.resource.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/.gitkeep" ,adapterProjectName,  SOURCE_PATH ,RESOUCES_PATH) ;

		} else if(template.contains("pom.boot.xml.vm")) {
			fileName = StringUtils.format("{}/pom.xml" , bootProjectName);
		} else if(template.contains("pom.boot.xml.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/.gitkeep" ,bootProjectName,  SOURCE_PATH ,  PROJECT_PATH ,packagesPathRev) ; 
		} else if(template.contains("pom.boot.xml.resource.gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/.gitkeep" ,bootProjectName,  SOURCE_PATH ,RESOUCES_PATH ) ; 
		} 
		
		// java 文件
		if(template.contains("Application.java.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/Application.java" ,  bootProjectName ,  SOURCE_PATH ,  PROJECT_PATH ,  packagesPathRev);
		} else if(template.contains("CheckEnv.java.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/CheckEnv.java" ,  bootProjectName ,  SOURCE_PATH , PROJECT_PATH , packagesPathRev);
		} else if(template.contains("spring/rpc-consumer.xml.vm")) {
			fileName = StringUtils.format("{}/{}/{}/spring/rpc-consumer.xml" ,  bootProjectName ,  SOURCE_PATH , RESOUCES_PATH ); 
		} else if(template.contains("spring/rpc-provider.xml.vm")) {
			fileName = StringUtils.format("{}/{}/{}/spring/rpc-provider.xml" ,  bootProjectName ,  SOURCE_PATH , RESOUCES_PATH ); 
		} else if(template.contains("application-dev.yaml.vm")) {
			fileName = StringUtils.format("{}/{}/{}/application-dev.yaml" ,  bootProjectName ,  SOURCE_PATH , RESOUCES_PATH ); 
		} else if(template.contains("application-hub.yaml.vm")) {
			fileName = StringUtils.format("{}/{}/{}/application-hub.yaml" ,  bootProjectName ,  SOURCE_PATH , RESOUCES_PATH ); 
		} else if(template.contains("gitignore.service.vm")) {
			fileName = StringUtils.format(".gitignore") ;  
		} else if(template.contains("seed/README.md.vm")) {
			fileName = StringUtils.format("README.md") ;  
		}
	
		// 示例工程
		if(template.contains("Service.java.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/DemoService.java" ,  apiProjectName ,  SOURCE_PATH ,  PROJECT_PATH ,  packagesPathRev + "/service");
		} else if(template.contains("Service.impl.java.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/DemoServiceImpl.java" ,  starterProjectName ,  SOURCE_PATH ,  PROJECT_PATH ,  packagesPathRev + "/service/impl");
			
		} else if(template.contains("Dto.java.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/DemoDto.java" ,  apiProjectName ,  SOURCE_PATH ,  PROJECT_PATH ,  packagesPathRev + "/gateway/dto");
		} else if(template.contains("Gateway.java.vm")) {
			fileName = StringUtils.format("{}/{}/{}/{}/DemoGateway.java" ,  apiProjectName ,  SOURCE_PATH ,  PROJECT_PATH ,  packagesPathRev + "/gateway/rest");
		}

		// K8S文件
		String chart = "chart/" + artifactId ; 
		if(template.contains("helm/values.yaml.vm")) {
			fileName = StringUtils.format("{}/values.yaml" ,chart) ; 
		} else if(template.contains("helm/Chart.yaml.vm")) {
			fileName = StringUtils.format("{}/Chart.yaml" ,chart) ; 
		} else if(template.contains("helm/helmignore.vm")) {
			fileName = StringUtils.format("{}/.helmignore" ,chart) ; 
		} else if(template.contains("helm/README.md.vm")) {
			fileName = StringUtils.format("{}/README.md" ,chart) ; 
		} else if(template.contains("helm/templates/deployment.yaml.vm")) {
			fileName = StringUtils.format("{}/templates/deployment.yaml" ,chart) ; 
		} else if(template.contains("templates/service.yaml.vm")) {
			fileName = StringUtils.format("{}/templates/service.yaml" ,chart) ; 
		} else if(template.contains("templates/_helpers.tpl.vm")) {
			fileName = StringUtils.format("{}/templates/_helpers.tpl" ,chart) ; 
		} else if(template.contains("templates/ingress.yaml.vm")) {
			fileName = StringUtils.format("{}/templates/ingress.yaml" ,chart) ; 
			
		} else if(template.contains("seed/k8s-dev-ingress.yaml.vm")) {
			fileName = StringUtils.format("{}/k8s-dev-ingress.yaml" , "deploy") ; 
		} else if(template.contains("seed/k8s-dev.yaml.vm")) {
			fileName = StringUtils.format("{}/k8s-dev.yaml" , "deploy") ; 
		}
	
		// 获取前端文件名称
		fileName = getNpmFileName(template, uiProjectName , fileName) ; 
	
		return fileName;
	}
	
	private static String getNpmFileName(String template , String uiProjectName , String fileName) {
		
		if(StringUtils.isNull(uiProjectName) || StringUtils.isBlank(uiProjectName)) {
			uiProjectName = "" ; 
		}
		
		// 前端文件
		if(template.contains("seed/ui/public/favicon.ico.vm")) {
			fileName = StringUtils.format("{}/public/favicon.ico" ,  uiProjectName ,  "public") ; 
		} else if(template.contains("seed/ui/public/index.html.vm")) {
			fileName = StringUtils.format("{}/public/index.html" ,  uiProjectName ,  "public") ; 
		} else if(template.contains("seed/ui/public/config.js.vm")) {
			fileName = StringUtils.format("{}/public/config.js" ,  uiProjectName ,  "public") ; 
		
		// Docker配置
		} else if(template.contains("seed/ui/docker/build-docker.sh.vm")) {
			fileName = StringUtils.format("{}/docker/build-docker.sh" ,  uiProjectName ,  "docker") ; 
		} else if(template.contains("seed/ui/docker/Dockerfile.vm")) {
			fileName = StringUtils.format("{}/docker/Dockerfile" ,  uiProjectName ,  "docker") ; 
		} else if(template.contains("seed/ui/docker/replace_api_url.sh.vm")) {
			fileName = StringUtils.format("{}/docker/replace_api_url.sh" ,  uiProjectName ,  "docker") ; 
		} else if(template.contains("seed/ui/docker/nginx.conf.vm")) {
			fileName = StringUtils.format("{}/docker/nginx.conf" ,  uiProjectName ,  "docker") ; 
			
		} else if(template.contains("seed/ui/src/main.js.vm")) {
			fileName = StringUtils.format("{}/{}/main.js" ,  uiProjectName , SOURCE_PATH ) ; 
		} else if(template.contains("seed/ui/src/permission.js.vm")) {
			fileName = StringUtils.format("{}/{}/permission.js" ,  uiProjectName , SOURCE_PATH ) ; 
		} else if(template.contains("seed/ui/src/router/index.js.vm")) {
			fileName = StringUtils.format("{}/{}/{}/index.js" ,  uiProjectName , SOURCE_PATH , "router") ; 
		} else if(template.contains("seed/ui/src/views/dashboard/home.vue.vm")) {
			fileName = StringUtils.format("{}/{}/{}/dashboard/home.vue" ,  uiProjectName , SOURCE_PATH , "views") ; 
		} else if(template.contains("seed/ui/src/views/template.vue.vm")) {
			fileName = StringUtils.format("{}/{}/{}/template.vue" ,  uiProjectName , SOURCE_PATH , "views") ; 
		} else if(template.contains("seed/ui/src/views/demo.vue.vm")) {
			fileName = StringUtils.format("{}/{}/{}/demo.vue" ,  uiProjectName , SOURCE_PATH , "views") ; 
		} else if(template.contains("seed/ui/babel.config.js.vm")) {
			fileName = StringUtils.format("{}/babel.config.js" ,  uiProjectName) ; 
			
		} else if(template.contains("seed/ui/qiniu-upload.sh.vm")) {
			fileName = StringUtils.format("{}/qiniu-upload.sh" ,  uiProjectName) ; 
			
		} else if(template.contains("seed/ui/k8s-dev-ingress.yaml.vm")) {
			fileName = StringUtils.format("{}/{}/k8s-dev-ingress.yaml" ,  uiProjectName , "deploy") ; 
		} else if(template.contains("seed/ui/k8s-dev.yaml.vm")) {
			fileName = StringUtils.format("{}/{}/k8s-dev.yaml" ,  uiProjectName , "deploy") ; 
			
		} else if(template.contains("seed/ui/package.json.vm")) {
			fileName = StringUtils.format("{}/package.json" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/pom.xml.vm")) {
			fileName = StringUtils.format("{}/pom.xml" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/vue.config.js.vm")) {
			fileName = StringUtils.format("{}/vue.config.js" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/hidden/editorconfig.vm")) {
			fileName = StringUtils.format("{}/.editorconfig" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/hidden/env.development.vm")) {
			fileName = StringUtils.format("{}/.env.development" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/hidden/env.production.vm")) {
			fileName = StringUtils.format("{}/.env.production" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/hidden/eslintignore.vm")) {
			fileName = StringUtils.format("{}/.eslintignore" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/hidden/eslintrc.js.vm")) {
			fileName = StringUtils.format("{}/.eslintrc.js" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/hidden/gitkeep.vm")) {
			fileName = StringUtils.format("{}/.gitkeep" ,  uiProjectName) ; 
		} else if(template.contains("seed/ui/hidden/env.staging.vm")) {
			fileName = StringUtils.format("{}/.env.staging" ,  uiProjectName) ; 
		} else if(template.contains("gitignore.vm")) {
			fileName = StringUtils.format("{}/.gitignore" ,  uiProjectName) ; 
		}
		
		// 前端状态 
		if(template.contains("seed/ui/src/store/getters.js.vm")) {
			fileName = StringUtils.format("{}/{}/{}/getters.js" ,  uiProjectName , SOURCE_PATH ,  "store") ; 
		} else if(template.contains("seed/ui/src/store/index.js.vm")) {
			fileName = StringUtils.format("{}/{}/{}/index.js" ,  uiProjectName , SOURCE_PATH , "store") ; 
		} else if(template.contains("seed/ui/src/store/modules/app.js.vm")) {
			fileName = StringUtils.format("{}/{}/{}/modules/app.js" ,  uiProjectName , SOURCE_PATH , "store") ; 
		} else if(template.contains("seed/ui/src/store/modules/permission.js.vm")) {
			fileName = StringUtils.format("{}/{}/{}/modules/permission.js" ,  uiProjectName , SOURCE_PATH , "store") ; 
		} else if(template.contains("seed/ui/src/store/modules/settings.js.vm")) {
			fileName = StringUtils.format("{}/{}/{}/modules/settings.js" ,  uiProjectName , SOURCE_PATH , "store") ; 
		} else if(template.contains("seed/ui/src/store/modules/tagsView.js.vm")) {
			fileName = StringUtils.format("{}/{}/{}/modules/tagsView.js" ,  uiProjectName , SOURCE_PATH , "store") ; 
		} else if(template.contains("seed/ui/src/store/modules/user.js.vm")) {
			fileName = StringUtils.format("{}/{}/{}/modules/user.js" ,  uiProjectName , SOURCE_PATH , "store") ; 
		}
		
		if(template.contains("seed/ui/src/asserts/gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/.gitkeep" ,  uiProjectName , SOURCE_PATH ,  "asserts") ; 
		} else if(template.contains("seed/ui/src/components/gitkeep.vm")) {
			fileName = StringUtils.format("{}/{}/{}/.gitkeep" ,  uiProjectName , SOURCE_PATH ,  "components") ; 
		}
		
		return fileName ; 
		
	}

	/**
	 * 生成前端工程脚手架构
	 * @param info
	 * @return
	 */
	public static byte[] generatorNpmSeed(ProjectInfoDto info) {

		VelocityInitializer.initVelocity();
		VelocityContext context = prepareSeedContext(info) ;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		// 获取模板列表
		List<String> templates = ProjectVelocityUtils.getUiProjectTemplatesList(info) ;
		for (String template : templates) {
				
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
				
			try {
				String path = getGenPath(info, template);
				log.debug("template:{} , path:{}" , template , path) ; 
				
				ZipOutputStream zip = new ZipOutputStream(outputStream);
				String fname = getNpmFileName(template, info.getArtifactId()+UI_SUFFIX , "");
				
				// 添加到zip
				zip.putNextEntry(new ZipEntry(fname));
				IOUtils.write(sw.toString(), zip, Constants.UTF8);
				IOUtils.closeQuietly(sw);
				
				zip.flush();
				zip.closeEntry();
				
			} catch (IOException e) {
				throw new RpcServiceRuntimeException("渲染模板失败，模板名：" + template);
			}
		}
		
		log.debug("outputStream.toByteArray().length = {}" , outputStream.toByteArray().length);
		
		return outputStream.toByteArray() ; 
	}
	
	public static Map<String, byte[]> genNpmProjectSeedByte(ProjectInfoDto info , boolean hasPrefix) {
		VelocityInitializer.initVelocity();
		VelocityContext context = prepareSeedContext(info) ;
		
		Map<String , byte[]> fileByteMap = new HashMap<String , byte[]>() ; 

		// 获取模板列表
		List<String> templates = ProjectVelocityUtils.getUiProjectTemplatesList(info) ;
		for (String template : templates) {
				
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
				
			String path = getGenPath(info, template);
			log.debug("template:{} , path:{}" , template , path) ; 
			
			String fname = getNpmFileName(template, info.getArtifactId()+UI_SUFFIX , "");
		
			if(!hasPrefix) {
				fname = fname.substring((info.getArtifactId()+UI_SUFFIX).length() + 1 , fname.length()) ; 
			}
			
			byte[] fileByte = sw.toString().getBytes() ; 

			fileByteMap.put(fname, fileByte) ;
		}
		
		return fileByteMap ; 
	}

}





















