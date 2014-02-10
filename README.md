ServMono
========

ServMono 是一个基于HttpServer(sun)，运行在Android平台的HTTP WEB，包含了jboss项目中的RestEasy组件。
ServMono is a based on HttpServer (sun), HTTP WEB runs on the Android platform, contains the jboss RestEasy components in the project

========

ServMono 是一个Android Lib Project

启动服务：

		try {
			ResteasyDeployment deployment = new ResteasyDeployment();
			deployment.getActualResourceClasses().add(SimpleResource.class);
			
			deployment.getActualProviderClasses().add(StringTextStar.class);
			deployment.getActualProviderClasses().add(DefaultTextPlain.class);
			deployment.getActualProviderClasses().add(FileProvider.class);
			deployment.getActualProviderClasses().add(ByteArrayProvider.class);
			deployment.getActualProviderClasses().add(InputStreamProvider.class);
			
			HttpServerContainer.start(deployment);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

使用：

		@Path("/test")
		@Produces(MediaType.WILDCARD) 
		public interface SimpleInterface {

			@GET
			@Path("basic") 
			@Produces(MediaType.TEXT_PLAIN) 
			public String getBasic(); 
		}
		
		public class SimpleResource implements SimpleInterface{
			
			@Override
			public String getBasic() 
			{ 
				System.out.println("getBasic()"); 
				return "basic"; 
			} 
		}
	
访问服务：
	路径：GET /test/basic
	
	回应：“basic”
	
