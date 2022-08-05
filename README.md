# SpringCloudConfig
This repository has 2 maven projects for Spring Cloud Config:

<li><b>spring-config-server</b> - The Server Application. This will act as a central repository of all configurations for all applications in the ecosystem. 
<li><b>spring-config-client</b> - Sample Client Application

<b>Stack</b> - JDK 11, Spring Boot 2.7.2, Spring cloud 2021.0.3, MySQL

The server uses JDBC as backend. So Spring cloud expects you to have a table <b>Properties</b> in your DB schema. Currently this project is configured with MySQL database. So the table creation script is as below:
  
CREATE TABLE `PROPERTIES` (
  `id` int NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime DEFAULT NULL,
  `APPLICATION` varchar(255) DEFAULT NULL,
  `PROFILE` varchar(255) DEFAULT NULL,
  `LABEL` varchar(255) DEFAULT NULL,
  `PROP_KEY` varchar(255) DEFAULT NULL,
  `VALUE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  
This server is enabled with Basic Authentication and Encryption of values. To encrypt invoke /encrypt endpoint on the server URL. Decryption endpoint is enabled on the endpoint. You can disable /decrypt endpoint for production usage. If any value is appended with {cipher} then that automatically becomes candidate for decryption. Else the value will be returned as it is. If any value fails to get decrypted then the key will be prepended with <i>invaid.</i> text.

If you intend to encrypt any value the encryption key needs to be added in application.properties of the server or it can also be passed as VM argument. 
Usage: java -Dencrypt.key=<<your_own_key>> -jar spring-config-server-0.0.1-SNAPSHOT.war

  
The client application is equipped with Actuator if you want to refresh the properties at runtime. To do that you need to invoke API /actuator/refresh on client side. It will automatically refresh the modified properties. To refresh the required properties, the bean needs to be annotated with <i>@RefreshScope</i>. 
