tomcat server의 context.xml을 다음과 같이 변경

<?xml version="1.0" encoding="UTF-8"?>
<!-- Licensed to the Apache Software Foundation (ASF) under one or more contributor 
	license agreements. See the NOTICE file distributed with this work for additional 
	information regarding copyright ownership. The ASF licenses this file to 
	You under the Apache License, Version 2.0 (the "License"); you may not use 
	this file except in compliance with the License. You may obtain a copy of 
	the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required 
	by applicable law or agreed to in writing, software distributed under the 
	License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS 
	OF ANY KIND, either express or implied. See the License for the specific 
	language governing permissions and limitations under the License. --><!-- The contents of this file will be loaded for each web application -->
<Context allowCasualMultipartParsing="true" path="/">

	<!-- Default set of monitored resources. If one of these changes, the -->
	<!-- web application will be reloaded. -->
	<WatchedResource>WEB-INF/web.xml</WatchedResource>
	<WatchedResource>WEB-INF/tomcat-web.xml</WatchedResource>
	<WatchedResource>${catalina.base}/conf/web.xml</WatchedResource>

	<!-- Uncomment this to disable session persistence across Tomcat restarts -->
	<!-- <Manager pathname="" /> -->
	<Resources cachingAllowed="true" cacheMaxSize="100000" />
</Context>
