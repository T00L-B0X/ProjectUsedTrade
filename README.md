tomcat server의 context.xml을 다음과 같이 변경

	<?xml version="1.0" encoding="UTF-8"?>
	<Context allowCasualMultipartParsing="true" path="/">
		<WatchedResource>WEB-INF/web.xml</WatchedResource>
		<WatchedResource>WEB-INF/tomcat-web.xml</WatchedResource>
		<WatchedResource>${catalina.base}/conf/web.xml</WatchedResource>
		<Resources cachingAllowed="true" cacheMaxSize="100000" />
	</Context>
