# JavaTransactionProjectsRepo
This repository contains sample projects for transactions in java solutions. 
I've created these project to apprehend a comprehensive study the transactions in the mixture flavor of frameworks out there.
All the projects are tested on;
-Wildfly 10
-MySQL

If you decide to download the source code and run, before doing so, please consult the "Setup" section.

## SpringTransactionProject
In this project you will find transactions in three aspects;
-Programmatic Transactions(run ProgrammaticTransactionMainApp.java),
-Declarative Transactions using xml(run DeclarativeTransactionXMLMainApp.java),
-Declarative Transactions using annotations(run DeclarativeTransactionAnnotationMainApp.java).

## HibernateTransactionProject
This is a standalone project that I've used JPA Implementation of Hibernate, entity manager is used to manage entities. 
In this project I've demonstrated the Programmatic Transactions only.

## HibernateSpringJTAApp
In this project I am demonstating how Hibernate's Declarative JTA transactions can be handled using spring framework.
This is a web application that I couldn't turn it into a standalone project which is very tricky as I saw on the internet.
It required lots of configuration for a demonstation purpose, I found it unnecessary and decided to turn this into a web app.
To be able to run this project, you'll need to make configurations on your Wildfly Server.


## Setup
### Lombok Plugin
In all of my projects, I've used lombok for boiler plate code. If you are going to run the project;
-In Intellij: https://projectlombok.org/setup/intellij
-In Eclipse and flavors: https://projectlombok.org/setup/eclipse

###  Database Setup
CREATE SCHEMA `transactionproject` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `transactionproject`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `department` VARCHAR(45) NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  PRIMARY KEY (`id`));

### Wildfly JTA Setup and Configuration
1. Add a management user in wildfly,
2. Download the mysql connector jar
3. In the modules folder of Wildfly home, create these folders sequence: com\mysql\main
4. Move the mysql jar here
5. In the same folder, create a file named “module.xml” and paste content(take the name of the jar file into consideration);

<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.0" name="com.mysql">
  <resources>
    <resource-root path="mysql-connector-java-5.1.39.jar"/>
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>

6. Open the “standalone.xml” file and in the <drivers> </drivers> section add the following above h2 entry;

 <driver name="mysql" module="com.mysql">
 	 <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
 </driver>

7. Go to the <datasources> line and add the below line(mind the connection details)
                <datasource jndi-name="java:/mysqlDataSource" pool-name="mysqlPool" enabled="true" jta="true" use-java-context="true" use-ccm="true" statistics-enabled="true">
                    <connection-url>jdbc:mysql://localhost:3306/transactionproject</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>root</password>
                    </security>
                </datasource>
                <xa-datasource jndi-name="java:/mysqlXADataSource" pool-name="mysqXAlPool" enabled="true" statistics-enabled="true">
                    <xa-datasource-property name="ServerName">
                        127.0.0.1
                    </xa-datasource-property>
                    <xa-datasource-property name="DatabaseName">
                       transactionproject
                    </xa-datasource-property>
                    <xa-datasource-property name="User">
                        root
                    </xa-datasource-property>
                    <xa-datasource-property name="Password">
                        root
                    </xa-datasource-property>
                    <driver>mysql</driver>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                    </validation>
                </xa-datasource>

8. Start the server and you will see jar loaded and the datasource is created

INFO  [org.jboss.as.connector.deployers.jdbc] (MSC service thread 1-8) WFLYJCA0018: Started Driver service with driver-name = mysql
INFO  [org.jboss.as.connector.subsystems.datasources] (MSC service thread 1-3) WFLYJCA0001: Bound data source [java:/mysqlDataSource]

9. Go to http://narayana.io/downloads/index.html download the NTA Binary the ear
10. Move the ear file JBOSSHOME standalone\deployments
11. open browser and navigate to http://localhost:8080/nta/
