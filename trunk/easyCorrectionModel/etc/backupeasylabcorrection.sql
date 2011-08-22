-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.41


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema easylabcorrection
--

CREATE DATABASE IF NOT EXISTS easylabcorrection;
USE easylabcorrection;

--
-- Definition of table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE `avaliacao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `submissao_id` int(10) unsigned NOT NULL,
  `nota_automatica` decimal(10,2) NOT NULL,
  `nota_correcao` decimal(10,2) NOT NULL,
  `resultado_execucao_testes` text,
  `penalidade` decimal(10,2) DEFAULT NULL,
  `data_avaliacao` datetime DEFAULT NULL,
  `corretor_id` int(10) unsigned DEFAULT NULL,
  `corrigido` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `submissao_id` (`submissao_id`),
  KEY `FK_avaliacao_Usuario` (`corretor_id`),
  CONSTRAINT `avaliacao_ibfk_1` FOREIGN KEY (`submissao_id`) REFERENCES `submissao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_avaliacao_Usuario` FOREIGN KEY (`corretor_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `avaliacao`
--

/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` (`id`,`submissao_id`,`nota_automatica`,`nota_correcao`,`resultado_execucao_testes`,`penalidade`,`data_avaliacao`,`corretor_id`,`corrigido`) VALUES 
 (105,318,'2.10','0.00','Assessment Report: \n\nTotal of Test Cases Executed = 21\nError Verdict(s) = 12\nSuccess Verdict(s) = 12\nSuccess percentage = 42 %\nAutomatic Execution Grade: 2.1\n\nConsole: \narrays first differed at element [0]; expected:<1> but was:<20>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testBubbleSortTC01(MainTest.java:82)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<0> but was:<20>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testBubbleSortTC02(MainTest.java:90)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<-10> but was:<5>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testBubbleSortTC03(MainTest.java:98)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<0> but was:<2>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testBubbleSortTC06(MainTest.java:122)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<1> but was:<20>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testSelectionSortTC01(MainTest.java:138)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<0> but was:<20>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testSelectionSortTC02(MainTest.java:146)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<-10> but was:<5>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testSelectionSortTC03(MainTest.java:154)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<0> but was:<2>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testSelectionSortTC06(MainTest.java:178)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<1> but was:<20>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testInsertionSortTC01(MainTest.java:194)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<0> but was:<20>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testInsertionSortTC02(MainTest.java:202)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<-10> but was:<5>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testInsertionSortTC03(MainTest.java:210)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\narrays first differed at element [0]; expected:<0> but was:<2>\r\n	at org.junit.Assert.internalArrayEquals(Assert.java:290)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:219)\r\n	at org.junit.Assert.assertArrayEquals(Assert.java:229)\r\n	at sorting.MainTest.testInsertionSortTC06(MainTest.java:234)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\r\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\r\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\r\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\r\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\r\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\r\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\r\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\r\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\r\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\r\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\r\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\r\n	at junit.textui.TestRunner.run(TestRunner.java:77)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.executeTests(TestExecution.java:93)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.TestExecution.runAutomaticTests(TestExecution.java:52)\r\n	at br.edu.ufcg.easyLabCorrection.managers.automatedCorrection.AutomatedEvaluationManager.runAutomaticTests(AutomatedEvaluationManager.java:26)\r\n	at br.edu.ufcg.easyLabCorrection.system.System.processSubmission(System.java:536)\r\n	at br.edu.ufcg.easyLabCorrection.system.Facade.processSubmission(Facade.java:694)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)\r\n	at java.lang.reflect.Method.invoke(Method.java:597)\r\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\r\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\r\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\r\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\r\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\r\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\r\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\r\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\r\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\r\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\r\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\r\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:127)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:298)\r\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:861)\r\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:579)\r\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1584)\r\n	at java.lang.Thread.run(Thread.java:619)\r\n\n','0.00','2011-08-17 00:00:00',NULL,0);
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;


--
-- Definition of table `chat`
--

DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roteiro_id` int(10) unsigned NOT NULL,
  `mensagem` text NOT NULL,
  `data_envio` datetime NOT NULL,
  `usuario_origem_id` int(10) unsigned NOT NULL,
  `usuario_destino_id` int(10) unsigned DEFAULT NULL,
  `equipe_destino_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `chat_FKIndex1` (`roteiro_id`),
  KEY `FK_chat_usuario_origem` (`usuario_origem_id`),
  KEY `FK_chat_usuario_destino` (`usuario_destino_id`),
  KEY `FK_chat_equipe` (`equipe_destino_id`),
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`roteiro_id`) REFERENCES `roteiro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_chat_equipe` FOREIGN KEY (`equipe_destino_id`) REFERENCES `equipe` (`id`),
  CONSTRAINT `FK_chat_usuario_destino` FOREIGN KEY (`usuario_destino_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_chat_usuario_origem` FOREIGN KEY (`usuario_origem_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `chat`
--

/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;


--
-- Definition of table `equipe`
--

DROP TABLE IF EXISTS `equipe`;
CREATE TABLE `equipe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `equipe`
--

/*!40000 ALTER TABLE `equipe` DISABLE KEYS */;
INSERT INTO `equipe` (`id`,`nome`) VALUES 
 (1,'Equipe 1'),
 (2,'Equipe 2'),
 (3,'Equipe 3'),
 (4,'Equipe 4'),
 (5,'Equipe 5'),
 (6,'Equipe 6'),
 (7,'Equipe 7'),
 (8,'Equipe 8'),
 (9,'Equipe 9'),
 (10,'Equipe 10'),
 (11,'Equipe 11'),
 (12,'Equipe 12'),
 (13,'Equipe 13'),
 (14,'Equipe 14'),
 (15,'Equipe 15'),
 (16,'Equipe 16'),
 (17,'Equipe 17'),
 (18,'Equipe 18'),
 (19,'Equipe 19'),
 (20,'Equipe 20'),
 (21,'Equipe 21'),
 (22,'Equipe 22'),
 (23,'Equipe 23'),
 (24,'Equipe 24'),
 (25,'Equipe 25'),
 (26,'Equipe 26'),
 (27,'Equipe 27'),
 (28,'Equipe 28'),
 (29,'Equipe 29'),
 (30,'Equipe 30'),
 (31,'Equipe 31'),
 (32,'Equipe 32'),
 (33,'Equipe 33'),
 (34,'Equipe 34'),
 (35,'Equipe 35'),
 (36,'Equipe 36'),
 (37,'Equipe 37'),
 (38,'Equipe 38'),
 (39,'Equipe 39'),
 (40,'Equipe 40'),
 (41,'Equipe 41'),
 (42,'Equipe 42'),
 (43,'Equipe 43'),
 (44,'Equipe 44'),
 (45,'Equipe 45'),
 (46,'Equipe 46'),
 (47,'Equipe 47'),
 (48,'Equipe 48'),
 (49,'Equipe 49'),
 (50,'Equipe 50'),
 (51,'Equipe 51'),
 (52,'Equipe 52'),
 (53,'Equipe 53'),
 (54,'Equipe 54'),
 (55,'Equipe 55'),
 (56,'Equipe 56'),
 (57,'Equipe 57'),
 (58,'Equipe 58'),
 (59,'Equipe 59'),
 (60,'Equipe 60');
/*!40000 ALTER TABLE `equipe` ENABLE KEYS */;


--
-- Definition of table `equipe_has_usuario_has_roteiro`
--

DROP TABLE IF EXISTS `equipe_has_usuario_has_roteiro`;
CREATE TABLE `equipe_has_usuario_has_roteiro` (
  `equipe_id` int(10) unsigned NOT NULL DEFAULT '0',
  `usuario_id` int(10) unsigned NOT NULL DEFAULT '0',
  `roteiro_id` int(10) unsigned NOT NULL DEFAULT '0',
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_equipe_usuario_roteiro` (`equipe_id`,`usuario_id`,`roteiro_id`),
  KEY `FK_equipe_has_usuario_has_roteiro_usuario` (`usuario_id`),
  KEY `FK_equipe_has_usuario_has_roteiro_roteiro` (`roteiro_id`),
  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_equipe` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`),
  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_roteiro` FOREIGN KEY (`roteiro_id`) REFERENCES `roteiro` (`id`),
  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=364 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `equipe_has_usuario_has_roteiro`
--

/*!40000 ALTER TABLE `equipe_has_usuario_has_roteiro` DISABLE KEYS */;
INSERT INTO `equipe_has_usuario_has_roteiro` (`equipe_id`,`usuario_id`,`roteiro_id`,`id`) VALUES 
 (1,229,18,297),
 (1,261,19,329),
 (1,278,20,344),
 (2,230,18,298),
 (2,262,19,330),
 (2,279,20,345),
 (3,231,18,299),
 (3,263,19,331),
 (3,280,20,346),
 (4,232,18,300),
 (4,264,19,332),
 (4,281,20,347),
 (5,233,18,301),
 (5,265,19,333),
 (5,282,20,348),
 (6,234,18,302),
 (6,266,19,334),
 (6,283,20,349),
 (7,235,18,303),
 (7,267,19,335),
 (7,284,20,350),
 (8,236,18,304),
 (8,268,19,336),
 (8,285,20,351),
 (9,237,18,305),
 (9,269,19,337),
 (9,286,20,352),
 (10,238,18,306),
 (10,270,19,338),
 (10,287,20,353),
 (11,239,18,307),
 (11,271,19,339),
 (11,288,20,354),
 (12,240,18,308),
 (12,272,19,340),
 (12,289,20,355),
 (13,241,18,309),
 (13,273,19,341),
 (13,290,20,356),
 (14,242,18,310),
 (14,274,19,342),
 (14,291,20,357),
 (15,243,18,311),
 (15,292,20,358),
 (16,244,18,312),
 (16,293,20,359),
 (17,245,18,313),
 (17,294,20,360),
 (18,246,18,314),
 (18,295,20,361),
 (19,247,18,315),
 (19,296,20,362),
 (20,248,18,316),
 (20,297,20,363),
 (21,249,18,317),
 (22,250,18,318),
 (23,251,18,319),
 (24,252,18,320),
 (25,253,18,321),
 (26,254,18,322),
 (27,255,18,323),
 (28,256,18,324),
 (29,257,18,325),
 (30,258,18,326),
 (31,259,18,327),
 (32,260,18,328),
 (33,298,18,343);
/*!40000 ALTER TABLE `equipe_has_usuario_has_roteiro` ENABLE KEYS */;


--
-- Definition of table `funcao`
--

DROP TABLE IF EXISTS `funcao`;
CREATE TABLE `funcao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(500) NOT NULL,
  `rotulo` varchar(500) NOT NULL,
  `menu_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_rotulo` (`rotulo`),
  KEY `FK_funcao_menu` (`menu_id`),
  KEY `Index_nome` (`nome`),
  CONSTRAINT `FK_funcao_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcao`
--

/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` (`id`,`nome`,`rotulo`,`menu_id`) VALUES 
 (1,'Registrations','registrations',1),
 (2,'Permissions','permissions',1),
 (4,'Assignment Registration','assignmentRegistration',4),
 (5,'Evaluator-Team Allocation','evaluatorAllocation',3),
 (7,'Grading Tables','gradingTables',3),
 (8,'Submissions','assignmentSubmission',4),
 (9,'Assignment Assessment','assignmentAssessment',3),
 (11,'Team Creation','createTem',4);
/*!40000 ALTER TABLE `funcao` ENABLE KEYS */;


--
-- Definition of table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo`
--

/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` (`id`,`nome`) VALUES 
 (3,'Assistant'),
 (2,'Instructor'),
 (4,'Student'),
 (1,'System Manager');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;


--
-- Definition of table `grupo_usuario`
--

DROP TABLE IF EXISTS `grupo_usuario`;
CREATE TABLE `grupo_usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_usuario` int(10) unsigned NOT NULL,
  `id_grupo` int(10) unsigned NOT NULL,
  `id_periodo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_grupo_usuario_grupo` (`id_grupo`),
  KEY `FK_grupo_usuario_usuario` (`id_usuario`),
  KEY `FK_grupo_usuario_periodo` (`id_periodo`),
  CONSTRAINT `FK_grupo_usuario_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FK_grupo_usuario_periodo` FOREIGN KEY (`id_periodo`) REFERENCES `periodo` (`id`),
  CONSTRAINT `FK_grupo_usuario_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo_usuario`
--

/*!40000 ALTER TABLE `grupo_usuario` DISABLE KEYS */;
INSERT INTO `grupo_usuario` (`id`,`id_usuario`,`id_grupo`,`id_periodo`) VALUES 
 (216,1,1,1),
 (217,3,1,1),
 (218,119,1,1),
 (219,3,1,5),
 (220,209,2,1),
 (231,220,2,6),
 (232,220,2,7),
 (233,221,2,8),
 (236,224,2,5),
 (237,225,2,3),
 (238,226,3,6),
 (239,227,3,6),
 (240,228,3,6),
 (241,229,4,6),
 (242,230,4,6),
 (243,231,4,6),
 (244,232,4,6),
 (245,233,4,6),
 (246,234,4,6),
 (247,235,4,6),
 (248,236,4,6),
 (249,237,4,6),
 (250,238,4,6),
 (251,239,4,6),
 (252,240,4,6),
 (253,241,4,6),
 (254,242,4,6),
 (255,243,4,6),
 (256,244,4,6),
 (257,245,4,6),
 (258,246,4,6),
 (259,247,4,6),
 (260,248,4,6),
 (261,249,4,6),
 (262,250,4,6),
 (263,251,4,6),
 (264,252,4,6),
 (265,253,4,6),
 (266,254,4,6),
 (267,255,4,6),
 (268,256,4,6),
 (269,257,4,6),
 (270,258,4,6),
 (271,259,4,6),
 (272,260,4,6),
 (273,261,4,7),
 (274,262,4,7),
 (275,263,4,7),
 (276,264,4,7),
 (277,265,4,7),
 (278,266,4,7),
 (279,267,4,7),
 (280,268,4,7),
 (281,269,4,7),
 (282,270,4,7),
 (283,271,4,7),
 (284,272,4,7),
 (285,273,4,7),
 (286,274,4,7),
 (287,275,1,1),
 (288,226,3,7),
 (289,226,3,8),
 (290,227,3,7),
 (291,227,3,8),
 (292,228,3,7),
 (293,228,3,8),
 (295,277,2,4),
 (296,278,4,8),
 (297,279,4,8),
 (298,280,4,8),
 (299,281,4,8),
 (300,282,4,8),
 (301,283,4,8),
 (302,284,4,8),
 (303,285,4,8),
 (304,286,4,8),
 (305,287,4,8),
 (306,288,4,8),
 (307,289,4,8),
 (308,290,4,8),
 (309,291,4,8),
 (310,292,4,8),
 (311,293,4,8),
 (312,294,4,8),
 (313,295,4,8),
 (314,296,4,8),
 (315,297,4,8),
 (316,298,4,6);
/*!40000 ALTER TABLE `grupo_usuario` ENABLE KEYS */;


--
-- Definition of table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(200) NOT NULL,
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_nome` (`nome`),
  KEY `Index_rotulo` (`titulo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`,`titulo`,`nome`) VALUES 
 (1,'access','Access Control'),
 (3,'assessment','Assessment'),
 (4,'assignment','Assignment');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
CREATE TABLE `periodo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `semestre` varchar(50) NOT NULL,
  `disciplina` varchar(50) NOT NULL,
  `turma` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `trio_unico` (`semestre`,`disciplina`,`turma`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `periodo`
--

/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
INSERT INTO `periodo` (`id`,`semestre`,`disciplina`,`turma`) VALUES 
 (6,'2011.2','Laboratório de Estrutura de Dados','Turma 1'),
 (7,'2011.2','Laboratório de Estrutura de Dados','Turma 2'),
 (8,'2011.2','Laboratório de Estrutura de Dados','Turma 3'),
 (3,'2011.2','Laboratório de Programação 2','Turma 1'),
 (4,'2011.2','Laboratório de Programação 2','Turma 2'),
 (5,'2011.2','Laboratório de Programação 2','Turma 3'),
 (1,'Permanente','Administradores','Admin');
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;


--
-- Definition of table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
CREATE TABLE `permissao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `grupo_id` int(10) unsigned NOT NULL,
  `funcao_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_permissao_grupo` (`grupo_id`),
  KEY `FK_permissao_funcao` (`funcao_id`),
  CONSTRAINT `FK_permissao_funcao` FOREIGN KEY (`funcao_id`) REFERENCES `funcao` (`id`),
  CONSTRAINT `FK_permissao_grupo` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permissao`
--

/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` (`id`,`grupo_id`,`funcao_id`) VALUES 
 (1,1,1),
 (2,1,2),
 (3,1,4),
 (4,1,5),
 (5,1,7),
 (6,1,8),
 (7,1,9),
 (9,1,11),
 (10,2,1),
 (11,2,4),
 (12,3,4),
 (13,3,9),
 (14,4,8),
 (15,4,11),
 (16,4,7),
 (17,2,5),
 (18,2,7),
 (19,2,9),
 (20,3,7);
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;


--
-- Definition of table `roteiro`
--

DROP TABLE IF EXISTS `roteiro`;
CREATE TABLE `roteiro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `periodo_id` int(10) unsigned NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` text,
  `data_liberacao` datetime DEFAULT NULL,
  `data_final_entrega` datetime DEFAULT NULL,
  `data_final_discussao` datetime DEFAULT NULL,
  `numero_maximo_envios` int(10) unsigned DEFAULT NULL,
  `penalidade_dias_atraso` decimal(10,2) DEFAULT NULL,
  `porcentagem_testes_automaticos` decimal(10,2) DEFAULT NULL,
  `tempo_limite_testes` int(10) unsigned DEFAULT NULL,
  `diretorio_interface` varchar(255) DEFAULT NULL,
  `diretorio_testes` varchar(255) DEFAULT NULL,
  `numero_maximo_participantes` int(10) unsigned DEFAULT NULL,
  `tipo_roteiro_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roteiro_FKIndex1` (`periodo_id`),
  KEY `FK_roteiro_tipo_roteiro` (`tipo_roteiro_id`),
  CONSTRAINT `FK_roteiro_tipo_roteiro` FOREIGN KEY (`tipo_roteiro_id`) REFERENCES `tipo_roteiro` (`id`),
  CONSTRAINT `roteiro_ibfk_1` FOREIGN KEY (`periodo_id`) REFERENCES `periodo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roteiro`
--

/*!40000 ALTER TABLE `roteiro` DISABLE KEYS */;
INSERT INTO `roteiro` (`id`,`periodo_id`,`nome`,`descricao`,`data_liberacao`,`data_final_entrega`,`data_final_discussao`,`numero_maximo_envios`,`penalidade_dias_atraso`,`porcentagem_testes_automaticos`,`tempo_limite_testes`,`diretorio_interface`,`diretorio_testes`,`numero_maximo_participantes`,`tipo_roteiro_id`) VALUES 
 (18,6,'Algoritmos de ordenação por comparação (parte I)','Roteiro sobre algoritmos de ordenação. Consulte o site da disciplina para baixar o arquivo PDF com a descrição completa do roteiro.','2011-08-16 07:00:00','2011-08-24 09:00:00','2011-09-06 23:00:00',5,'0.00','50.00',10000,'/periodo2011.2/environment/18/','/periodo2011.2/testes/18/',1,2),
 (19,7,'Algoritmos de ordenação por comparação (parte I)','Roteiro sobre algoritmos de ordenação. Consulte o site da disciplina para baixar o arquivo PDF com a descrição completa do roteiro.','2011-08-17 09:00:00','2011-08-17 11:00:00','2011-08-23 23:00:00',3,'0.00','40.00',10000,'/periodo2011.2/environment/19/','/periodo2011.2/testes/19/',1,2),
 (20,8,'Algoritmos de ordenação por comparação (parte I)','Roteiro sobre algoritmos de ordenação. Consulte o site da disciplina para baixar o arquivo PDF com a descrição completa do roteiro.','2011-08-17 09:00:00','2011-08-17 11:00:00','2011-08-23 23:00:00',3,'0.00','0.00',10000,'/periodo2011.2/environment/20/','/periodo2011.2/testes/20/',1,2);
/*!40000 ALTER TABLE `roteiro` ENABLE KEYS */;


--
-- Definition of table `submissao`
--

DROP TABLE IF EXISTS `submissao`;
CREATE TABLE `submissao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` text NOT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `data_submissao` datetime NOT NULL,
  `equipe_has_usuario_has_roteiro_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `submissao`
--

/*!40000 ALTER TABLE `submissao` DISABLE KEYS */;
INSERT INTO `submissao` (`id`,`url`,`estado`,`data_submissao`,`equipe_has_usuario_has_roteiro_id`) VALUES 
 (315,'/periodo2011.2/submissoes/roteiro_18/Equipe 33/',NULL,'2011-08-17 00:00:00',343),
 (316,'/periodo2011.2/submissoes/roteiro_18/Equipe 33/',NULL,'2011-08-17 00:00:00',343),
 (317,'/periodo2011.2/submissoes/roteiro_18/Equipe 33/',NULL,'2011-08-17 00:00:00',343),
 (318,'/periodo2011.2/submissoes/roteiro_18/Equipe 33/',NULL,'2011-08-17 00:00:00',343);
/*!40000 ALTER TABLE `submissao` ENABLE KEYS */;


--
-- Definition of table `tipo_roteiro`
--

DROP TABLE IF EXISTS `tipo_roteiro`;
CREATE TABLE `tipo_roteiro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(500) NOT NULL,
  `compilacao` tinyint(1) NOT NULL,
  `testes_automaticos` tinyint(1) NOT NULL,
  `comparacao_saida` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_roteiro`
--

/*!40000 ALTER TABLE `tipo_roteiro` DISABLE KEYS */;
INSERT INTO `tipo_roteiro` (`id`,`nome`,`compilacao`,`testes_automaticos`,`comparacao_saida`) VALUES 
 (2,'Compilation / Test Execution',1,1,0),
 (4,'Compilation',1,0,0),
 (5,'Compilation / Output Comparison',1,0,1);
/*!40000 ALTER TABLE `tipo_roteiro` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`login`,`nome`,`senha`,`email`) VALUES 
 (1,'demas','Demetrio Gomes','e10adc3949ba59abbe56e057f20f883e','demetr@gmail.com'),
 (3,'alysson','Alysson Filgueira Milanez','e10adc3949ba59abbe56e057f20f883e','alysson'),
 (119,'augusto','Augusto','e10adc3949ba59abbe56e057f20f883e','augustomacedo@gmail.com'),
 (208,'livia','Livia','e10adc3949ba59abbe56e057f20f883e','livia@dsc.ufcg.edu.br'),
 (209,'dem','Demetrio','4297f44b13955235245b2497399d7a93','demetrio@gmail.com'),
 (220,'adalberto','Adalberto Cajueiro de Farias','e10adc3949ba59abbe56e057f20f883e','adalberto.cajueiro@gmail.com'),
 (221,'wilkerson','Wilkerson de Lucena Andrade','99bea35c559032452d9fef1db1ccaa8f','wilkerson@computacao.ufcg.edu.br'),
 (224,'nazareno','Nazareno Andrade','bb9120fdf85da90975317b844253a550','nazareno@computacao.ufcg.edu.br'),
 (225,'liviasampaio','Lívia Maria Rodrigues Sampaio Campos','ad6549bbd10205576d6b6a311154a3f','sampaiolivia@gmail.com'),
 (226,'20911808','Matheus de Araújo Maciel','ddaf22a8e230a8ae3a5214398b8b8b03','teu.araujo@gmail.com'),
 (227,'21111893','Alexandre Bruno de Macedo Medeiros','ee3f9aff663316e7942034fe30763f7','sk.ufcg@gmail.com'),
 (228,'20911057','Diego Pedro Gonçalves da Silva','c5a71cc1f76739562fac168cd0ceeaf4','diegopedro@gmail.com'),
 (229,'21021041','ADILSON JUNIOR DIAS DE PONTES','ef58b9d25c72e9b6f2cfd254c9980c8f','adilson.pontes@ccc.ufcg.edu.br'),
 (230,'21021044','ANDRE YURI ALVES SOARES DE GOIS','e10adc3949ba59abbe56e057f20f883e','andrecg16@gmail.com'),
 (231,'21021494','DANILO DUARTE PIMENTEL','e10adc3949ba59abbe56e057f20f883e','danilo.pimentel@ccc.ufcg.edu.br'),
 (232,'21021060','DIEGO DE SOUSA CAVALCANTE','e10adc3949ba59abbe56e057f20f883e','diegosc.cavalcante@gmail.com'),
 (233,'21121066','ELIAS PAULINO MEDEIROS','e10adc3949ba59abbe56e057f20f883e','eliaspaulinom@gmail.com'),
 (234,'21021386','FELIPE DA SILVA TRAVASSOS','e10adc3949ba59abbe56e057f20f883e','felipe.travassos@ccc.ufcg.edu.br'),
 (235,'21121073','FELIPE EDUARDO FARIAS BELO','e10adc3949ba59abbe56e057f20f883e','malkavianbr@gmail.com'),
 (236,'21011070','FELIPE JOSE FERREIRA CAVALCANTI','e10adc3949ba59abbe56e057f20f883e','fjfcavalcanti@gmail.com'),
 (237,'21021068','FELIPE LINDEMBERG LIMA VILAR','e10adc3949ba59abbe56e057f20f883e','felipelindemberg.cc.ufcg@gmail.com'),
 (238,'21021388','FLAVIA DA SILVA GANGORRA','e97d6089b0f90d6478311c7f5c5794f9','flavia.gangorra@gmail.com'),
 (239,'21021072','GILLES MEDEIROS HENRIQUES','9de24943eb195bb0f3238d44d4d5734a','gilles.medeiros@gmail.com'),
 (240,'21021073','GUILHERME HENRIQUE PEIXOTO CORREA','e10adc3949ba59abbe56e057f20f883e','guilherme.correa@ccc.ufcg.edu.br'),
 (241,'21021075','HEMA VIDAL NEGREIROS BEZERRA','e10adc3949ba59abbe56e057f20f883e','hemavidal@gmail.com'),
 (242,'21021078','IGOR CANDEIA FRANCA DE MORAIS','37ba79e38bf2d7e1d8ba28af5abc56d0','igorcandeia@gmail.com'),
 (243,'21021080','ISAQUE SALES DOMINGOS FONTINELE','50b7cfd26fffabeefa367ef39f21b03b','isaquefontinele@gmail.com'),
 (244,'21021088','JEYMISSON EVELLY BARRETO E OLIVEIRA','a6699c60fc19f4ad5e543abab1d5ba3b','jeymissoncz@gmail.com'),
 (245,'21021526','JORDAO EZEQUIEL SERAFIM DE ARAUJO','e10adc3949ba59abbe56e057f20f883e','jordaotalk@gmail.com'),
 (246,'21021093','JULIO ANDHERSON DE OLIVEIRA SILVA','e10adc3949ba59abbe56e057f20f883e','julioandherson1@gmail.com'),
 (247,'21021094','KAIO CEZAR DA SILVA OLIVEIRA','e10adc3949ba59abbe56e057f20f883e','kaioo.ufcg@gmail.com'),
 (248,'21021095','KEVIN VIEIRA LUCENA VELOSO','e10adc3949ba59abbe56e057f20f883e','kevinveloso@gmail.com'),
 (249,'21021102','LUCAS ALBUQUERQUE DE ALMEIDA','e10adc3949ba59abbe56e057f20f883e','lucas.ufcg@gmail.com'),
 (250,'21021391','LUIS HENRIQUE DA SILVA SEGUNDO','e10adc3949ba59abbe56e057f20f883e','luis.henrique.segundo@gmail.com'),
 (251,'21011098','MARCELO ROLIM SOBREIRA','e10adc3949ba59abbe56e057f20f883e','marcelorolimsobreira@gmail.com'),
 (252,'21021105','MATEUS SILVA LUNA','94207f51382e4c36bcdb59a5e48acdea','mateuslunacc@gmail.com'),
 (253,'21021108','MONICA SANTANA CAVALCANTE','e10adc3949ba59abbe56e057f20f883e','monica.cavalcante@ccc.ufcg.edu.br'),
 (254,'21011107','PEDRO AUGUSTUS DINIZ FALCAO SILVA','e10adc3949ba59abbe56e057f20f883e','pedro.falcao.ufcg@gmail.com'),
 (255,'21021114','RAFAEL RIBEIRO FONTENELE','e10adc3949ba59abbe56e057f20f883e','rafaelrf500@gmail.com'),
 (256,'21021115','RAIFF ANDERSON SANTOS DA MATA','e10adc3949ba59abbe56e057f20f883e','raiff.mata@ccc.ufcg.edu.br'),
 (257,'21115292','RENNAN HENRIQUE MARQUES DE ALMEIDA','e10adc3949ba59abbe56e057f20f883e','rennanhm@gmail.com'),
 (258,'21021128','THIAGO ALMEIDA DE MEDEIROS','e10adc3949ba59abbe56e057f20f883e','thiago2010.2@gmail.com'),
 (259,'21011130','VICTOR HUGO ARCELINO DE BRITO','3f460599d45c534cc82d3e846f873c9','vhugo.britto@gmail.com'),
 (260,'21021129','WALTER ARRUDA ALVES','99b80f21046a2a881de2b07430cfdea','walter.arruda.alves@gmail.com'),
 (261,'21021047','ANTONIO MARCOS FLOR RODRIGUES','6724eada4787352de006a2dc5d7a95b1','tony.amfr@gmail.com'),
 (262,'21011061','DIEGO COELHO DOS SANTOS','e10adc3949ba59abbe56e057f20f883e','diegocoelho.13@gmail.com'),
 (263,'21121061','DIEGO DE LIMA PEREIRA','e10adc3949ba59abbe56e057f20f883e','diegolimapereira@gmail.com'),
 (264,'21011073','GUILHERME MONTEIRO GADELHA','e10adc3949ba59abbe56e057f20f883e','guilhermemgadelha@gmail.com'),
 (265,'21021079','IRVILE RODRIGUES LAVOR','e10adc3949ba59abbe56e057f20f883e','irvile.turin@gmail.com'),
 (266,'21021081','ISRAEL CAVALCANTE DE LUCENA','e10adc3949ba59abbe56e057f20f883e','israel.lucena@ccc.ufcg.edu.br'),
 (267,'21011853','JOEFFISON SILVERIO DE ANDRADE','e10adc3949ba59abbe56e057f20f883e','joeffisonsa@gmail.com'),
 (268,'21021091','JONHNANTHAN VICTOR PEREIRA OLIVEIRA','b7f30d747d4c6f9d2931375eff6cc7c4','jonhnanthan@gmail.com'),
 (269,'21021390','LEONARDO ALVES DOS SANTOS','e10adc3949ba59abbe56e057f20f883e','leonardoconstrutoresdapaz@gmail.com'),
 (270,'20921294','MICHELL JACKS DE OLIVEIRA NETTO','e10adc3949ba59abbe56e057f20f883e','michell_jacks@hotmail.com'),
 (271,'21021110','PABLO HERIVELTON RAMOS GOES','e10adc3949ba59abbe56e057f20f883e','pablo.goes@ccc.ufcg.edu.br'),
 (272,'21011106','PEDRO AUGUSTO ARAUJO RIBEIRO','e10adc3949ba59abbe56e057f20f883e','pedro.ribeiro@ccc.ufcg.edu.br'),
 (273,'21015173','THALYSSON DAVYD VIEIRA','3e57f7ee22713728335920e979ed0bde','thalyssondavyd@gmail.com'),
 (274,'21021397','WALLISON FERNANDO DA SILVA','e10adc3949ba59abbe56e057f20f883e','w.fernando.20@gmail.com'),
 (275,'admin','admin','abfd94b664e5d264b108586574c7869d','adminadmin@admin.co'),
 (277,'reinaldo','Reinaldo Gomes','e8c75d264bd5ed1fb4bd0fb855498f87','reinaldo@computacao.ufcg.edu.br'),
 (278,'21011047','ARMSTRONG MARDILSON DA SILVA GOES','4dca25873a85ca1ea8b70f8ef641686b','armstrong.goes@ccc.ufcg.edu.br'),
 (279,'21011052','BRUNNA DE SOUSA PEREIRA AMORIM','21c09be3c1d6b9a33282bd8758995224','brunna.amorim@ccc.ufcg.edu.br'),
 (280,'21121655','CAAINA JERONIMO DE SOUZA NASCIMENTO','e10adc3949ba59abbe56e057f20f883e','caainaje@gmail.com'),
 (281,'21011053','CAIO LIBANIO MELO JERONIMO','69209e8543c217a1a3a787e1acdf0bd8','caio.jeronimo@ccc.ufcg.edu.br'),
 (282,'21021052','CARLOS VICTOR DO NASCIMENTO MORAIS FILHO','e10adc3949ba59abbe56e057f20f883e','carlos.filho@ccc.ufcg.edu.br'),
 (283,'21011057','DANIELL WAGNER AZEVEDO DE LIMA','e10adc3949ba59abbe56e057f20f883e','daniell.lima@ccc.ufcg.edu.br'),
 (284,'21021061','DIEGO TAVARES PEREIRA','e10adc3949ba59abbe56e057f20f883e','diego.tavares.pereira@ccc.ufcg.edu.br'),
 (285,'21015167','DIMITRE ANDREW AIRES DE OLIVEIRA','e10adc3949ba59abbe56e057f20f883e','dimitre.oliveira@ccc.ufcg.edu.br'),
 (286,'21111073','EDUARDO ROVARIS DA SILVA','e10adc3949ba59abbe56e057f20f883e','eduardo.silva@ccc.ufcg.edu.br'),
 (287,'20921290','EUGENIO SAMER MARQUES MEIRA','e10adc3949ba59abbe56e057f20f883e','eugenio.meira@ccc.ufcg.edu.br'),
 (288,'21021066','FABIANO ANDRE LAUREANO','ae43eecfa75097af86cb8a5888402515','fabiano.laureano@ccc.ufcg.edu.br'),
 (289,'21011974','HUDSON DANIEL DE ALMEIDA SANTANA','e10adc3949ba59abbe56e057f20f883e','hudson.santana@ccc.ufcg.edu.br'),
 (290,'21011080','ISABELLE CARDOSO FERREIRA','83e51b645ba9442950e6f3e9a76a8e11','isabelle.ferreira@ccc.ufcg.edu.br'),
 (291,'21021083','ITALO TAVARES DE MOURA','e10adc3949ba59abbe56e057f20f883e','italo.moura@ccc.ufcg.edu.br'),
 (292,'21011087','JOEUMAR CRYSTHOFFERSON CORDEIRO DE SOUZA','40ef7ceeedad804773008463cb4ad4e7','joeumar.souza@ccc.ufcg.edu.br'),
 (293,'21011090','JOSE DIOGO SILVA FONSECA','e10adc3949ba59abbe56e057f20f883e','jdiogofonseca@gmail.com'),
 (294,'21021097','LAERTON MARQUES DE FIGUEIREDO','e10adc3949ba59abbe56e057f20f883e','laerton.figueiredo@ccc.ufcg.edu.br'),
 (295,'21011104','MAYRA KELLY DA SILVA CALIXTO','e10adc3949ba59abbe56e057f20f883e','mayra.calixto@ccc.ufcg.edu.br'),
 (296,'21011112','RAFAEL CAVALCANTE PAULINO','e10adc3949ba59abbe56e057f20f883e','rafaeleldandil@gmail.com'),
 (297,'21121124','TIAGO SILVEIRA OLIVEIRA','53d934c98128ae55b916fa273cd126a','tiago.oliveira@ccc.ufcg.edu.br'),
 (298,'teste','Usuario de testes para LEDA','e10adc3949ba59abbe56e057f20f883e','adalberto@computacao.ufcg.edu.br');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
