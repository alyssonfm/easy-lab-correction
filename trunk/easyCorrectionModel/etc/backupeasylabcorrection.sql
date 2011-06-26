
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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `avaliacao`
--

/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` (`id`,`submissao_id`,`nota_automatica`,`nota_correcao`,`resultado_execucao_testes`,`penalidade`,`data_avaliacao`,`corretor_id`,`corrigido`) VALUES 
 (4,81,'4.00','3.60','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (5,80,'4.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (6,82,'4.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (7,83,'4.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (8,150,'4.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (9,91,'4.00','3.60','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (10,86,'4.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (11,87,'4.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (12,88,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (13,89,'4.00','5.10','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (14,90,'4.00','4.80','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (15,92,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',135,1),
 (16,93,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (17,94,'4.00','5.52','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (18,95,'4.00','5.70','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (19,96,'4.00','5.70','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (20,97,'4.00','5.70','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (21,100,'4.00','5.46','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (22,101,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (23,103,'4.00','5.70','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (24,104,'4.00','5.88','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (25,107,'4.00','5.70','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (26,108,'4.00','5.70','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (27,111,'4.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',136,1),
 (28,112,'4.00','5.99','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (29,113,'4.00','5.99','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (30,117,'4.00','5.99','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (31,118,'4.00','5.99','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (32,119,'4.00','5.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (33,120,'4.00','5.99','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (34,121,'4.00','5.50','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (35,122,'4.00','5.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (36,125,'4.00','5.99','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (37,126,'4.00','5.99','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (38,127,'4.00','5.50','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (39,129,'4.00','5.99','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',138,1),
 (40,130,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,1),
 (41,131,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,1),
 (42,132,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,1),
 (43,133,'4.00','4.80','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,1),
 (44,134,'4.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,1),
 (45,135,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,0),
 (46,136,'4.00','4.80','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,1),
 (47,137,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,1),
 (48,142,'0.00','5.40','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 1\nPorcentagem de Acertos: 0 %\nNota dos Testes AutomÃ¡ticos: 0.0\n\nConsole:\njunit.framework.AssertionFailedError: null\n	at junit.framework.Assert.fail(Assert.java:47)\n	at junit.framework.Assert.assertTrue(Assert.java:20)\n	at junit.framework.Assert.assertTrue(Assert.java:27)\n	at TestQueue.testClass(TestQueue.java:48)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\n	at junit.textui.TestRunner.run(TestRunner.java:77)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorTestes.executarTestes(GerenciadorTestes.java:83)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorSubmissoes.rodarTestesAutomaticos(GerenciadorSubmissoes.java:276)\n	at br.edu.les.easyCorrection.sistema.Sistema.rodarTestesAutomaticos(Sistema.java:302)\n	at br.edu.les.easyCorrection.sistema.Facade.rodarTestesAutomaticos(Facade.java:603)\n	at sun.reflect.GeneratedMethodAccessor239.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:859)\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:574)\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1527)\n	at java.lang.Thread.run(Thread.java:636)\n\n','0.00','2011-03-30 00:00:00',134,1),
 (49,144,'4.00','6.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,1),
 (50,145,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,0),
 (51,146,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-03-30 00:00:00',134,0),
 (52,151,'0.00','3.60','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 1\nPorcentagem de Acertos: 0 %\nNota dos Testes AutomÃ¡ticos: 0.0\n\nConsole:\njunit.framework.AssertionFailedError: null\n	at junit.framework.Assert.fail(Assert.java:47)\n	at junit.framework.Assert.assertTrue(Assert.java:20)\n	at junit.framework.Assert.assertFalse(Assert.java:34)\n	at junit.framework.Assert.assertFalse(Assert.java:41)\n	at TestQueue.testClass(TestQueue.java:126)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\n	at junit.textui.TestRunner.run(TestRunner.java:77)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorTestes.executarTestes(GerenciadorTestes.java:83)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorSubmissoes.rodarTestesAutomaticos(GerenciadorSubmissoes.java:276)\n	at br.edu.les.easyCorrection.sistema.Sistema.rodarTestesAutomaticos(Sistema.java:302)\n	at br.edu.les.easyCorrection.sistema.Facade.rodarTestesAutomaticos(Facade.java:603)\n	at sun.reflect.GeneratedMethodAccessor239.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:859)\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:574)\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1527)\n	at java.lang.Thread.run(Thread.java:636)\n\n','0.00','2011-03-30 00:00:00',134,1),
 (53,160,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (54,162,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (55,165,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (56,166,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (57,168,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (58,169,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (59,170,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (60,214,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (61,172,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (62,212,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (63,200,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (64,179,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',135,0),
 (65,180,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (66,182,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (67,183,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (68,184,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (69,185,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (70,186,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (71,187,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (72,196,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (73,207,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (74,195,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (75,197,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (76,198,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',136,0),
 (77,208,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (78,216,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (79,217,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (80,219,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (81,220,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (82,223,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (83,225,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (84,226,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (85,232,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (86,234,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (87,235,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (88,237,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',138,0),
 (89,243,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',134,0),
 (90,244,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',134,0),
 (91,245,'0.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 1\nPorcentagem de Acertos: 0 %\nNota dos Testes AutomÃ¡ticos: 0.0\n\nConsole:\njunit.framework.AssertionFailedError: expected:<99> but was:<100>\n	at junit.framework.Assert.fail(Assert.java:47)\n	at junit.framework.Assert.failNotEquals(Assert.java:277)\n	at junit.framework.Assert.assertEquals(Assert.java:64)\n	at junit.framework.Assert.assertEquals(Assert.java:195)\n	at junit.framework.Assert.assertEquals(Assert.java:201)\n	at TestListaInteiros.testClass(TestListaInteiros.java:38)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\n	at junit.textui.TestRunner.run(TestRunner.java:77)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorTestes.executarTestes(GerenciadorTestes.java:83)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorSubmissoes.rodarTestesAutomaticos(GerenciadorSubmissoes.java:276)\n	at br.edu.les.easyCorrection.sistema.Sistema.rodarTestesAutomaticos(Sistema.java:302)\n	at br.edu.les.easyCorrection.sistema.Facade.rodarTestesAutomaticos(Facade.java:603)\n	at sun.reflect.GeneratedMethodAccessor239.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:859)\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:574)\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1527)\n	at java.lang.Thread.run(Thread.java:636)\n\n','0.00','2011-04-01 00:00:00',134,0),
 (92,252,'0.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 1\nPorcentagem de Acertos: 0 %\nNota dos Testes AutomÃ¡ticos: 0.0\n\nConsole:\njunit.framework.AssertionFailedError: expected:<100> but was:<2>\n	at junit.framework.Assert.fail(Assert.java:47)\n	at junit.framework.Assert.failNotEquals(Assert.java:277)\n	at junit.framework.Assert.assertEquals(Assert.java:64)\n	at junit.framework.Assert.assertEquals(Assert.java:195)\n	at junit.framework.Assert.assertEquals(Assert.java:201)\n	at TestListaInteiros.testClass(TestListaInteiros.java:31)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\n	at junit.textui.TestRunner.run(TestRunner.java:77)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorTestes.executarTestes(GerenciadorTestes.java:83)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorSubmissoes.rodarTestesAutomaticos(GerenciadorSubmissoes.java:276)\n	at br.edu.les.easyCorrection.sistema.Sistema.rodarTestesAutomaticos(Sistema.java:302)\n	at br.edu.les.easyCorrection.sistema.Facade.rodarTestesAutomaticos(Facade.java:603)\n	at sun.reflect.GeneratedMethodAccessor239.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:859)\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:574)\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1527)\n	at java.lang.Thread.run(Thread.java:636)\n\n','0.00','2011-04-01 00:00:00',134,0),
 (93,250,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',134,0),
 (94,253,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',134,0),
 (95,255,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-01 00:00:00',134,0),
 (96,264,'0.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 1\nPorcentagem de Acertos: 0 %\nNota dos Testes AutomÃ¡ticos: 0.0\n\nConsole:\njunit.framework.AssertionFailedError: expected:<100> but was:<0>\n	at junit.framework.Assert.fail(Assert.java:47)\n	at junit.framework.Assert.failNotEquals(Assert.java:277)\n	at junit.framework.Assert.assertEquals(Assert.java:64)\n	at junit.framework.Assert.assertEquals(Assert.java:195)\n	at junit.framework.Assert.assertEquals(Assert.java:201)\n	at TestListaInteiros.testClass(TestListaInteiros.java:31)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\n	at junit.textui.TestRunner.run(TestRunner.java:77)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorTestes.executarTestes(GerenciadorTestes.java:83)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorSubmissoes.rodarTestesAutomaticos(GerenciadorSubmissoes.java:276)\n	at br.edu.les.easyCorrection.sistema.Sistema.rodarTestesAutomaticos(Sistema.java:302)\n	at br.edu.les.easyCorrection.sistema.Facade.rodarTestesAutomaticos(Facade.java:603)\n	at sun.reflect.GeneratedMethodAccessor239.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:859)\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:574)\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1527)\n	at java.lang.Thread.run(Thread.java:636)\n\n','0.00','2011-04-02 00:00:00',134,0),
 (97,265,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-02 00:00:00',134,0),
 (98,266,'0.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 1\nPorcentagem de Acertos: 0 %\nNota dos Testes AutomÃ¡ticos: 0.0\n\nConsole:\njunit.framework.AssertionFailedError: expected:<99> but was:<199>\n	at junit.framework.Assert.fail(Assert.java:47)\n	at junit.framework.Assert.failNotEquals(Assert.java:277)\n	at junit.framework.Assert.assertEquals(Assert.java:64)\n	at junit.framework.Assert.assertEquals(Assert.java:195)\n	at junit.framework.Assert.assertEquals(Assert.java:201)\n	at TestListaInteiros.testClass(TestListaInteiros.java:38)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\n	at junit.textui.TestRunner.run(TestRunner.java:77)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorTestes.executarTestes(GerenciadorTestes.java:83)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorSubmissoes.rodarTestesAutomaticos(GerenciadorSubmissoes.java:276)\n	at br.edu.les.easyCorrection.sistema.Sistema.rodarTestesAutomaticos(Sistema.java:302)\n	at br.edu.les.easyCorrection.sistema.Facade.rodarTestesAutomaticos(Facade.java:603)\n	at sun.reflect.GeneratedMethodAccessor239.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:859)\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:574)\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1527)\n	at java.lang.Thread.run(Thread.java:636)\n\n','0.00','2011-04-02 00:00:00',134,0),
 (99,268,'0.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 1\nTotal de Erros: 1\nPorcentagem de Acertos: 0 %\nNota dos Testes AutomÃ¡ticos: 0.0\n\nConsole:\njunit.framework.AssertionFailedError: expected:<100> but was:<99>\n	at junit.framework.Assert.fail(Assert.java:47)\n	at junit.framework.Assert.failNotEquals(Assert.java:277)\n	at junit.framework.Assert.assertEquals(Assert.java:64)\n	at junit.framework.Assert.assertEquals(Assert.java:195)\n	at junit.framework.Assert.assertEquals(Assert.java:201)\n	at TestListaInteiros.testClass(TestListaInteiros.java:31)\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at org.junit.internal.runners.TestMethod.invoke(TestMethod.java:59)\n	at org.junit.internal.runners.MethodRoadie.runTestMethod(MethodRoadie.java:98)\n	at org.junit.internal.runners.MethodRoadie$2.run(MethodRoadie.java:79)\n	at org.junit.internal.runners.MethodRoadie.runBeforesThenTestThenAfters(MethodRoadie.java:87)\n	at org.junit.internal.runners.MethodRoadie.runTest(MethodRoadie.java:77)\n	at org.junit.internal.runners.MethodRoadie.run(MethodRoadie.java:42)\n	at org.junit.internal.runners.JUnit4ClassRunner.invokeTestMethod(JUnit4ClassRunner.java:88)\n	at org.junit.internal.runners.JUnit4ClassRunner.runMethods(JUnit4ClassRunner.java:51)\n	at org.junit.internal.runners.JUnit4ClassRunner$1.run(JUnit4ClassRunner.java:44)\n	at org.junit.internal.runners.ClassRoadie.runUnprotected(ClassRoadie.java:27)\n	at org.junit.internal.runners.ClassRoadie.runProtected(ClassRoadie.java:37)\n	at org.junit.internal.runners.JUnit4ClassRunner.run(JUnit4ClassRunner.java:42)\n	at junit.framework.JUnit4TestAdapter.run(JUnit4TestAdapter.java:36)\n	at junit.textui.TestRunner.doRun(TestRunner.java:116)\n	at junit.textui.TestRunner.doRun(TestRunner.java:109)\n	at junit.textui.TestRunner.run(TestRunner.java:77)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorTestes.executarTestes(GerenciadorTestes.java:83)\n	at br.edu.les.easyCorrection.gerenciadores.GerenciadorSubmissoes.rodarTestesAutomaticos(GerenciadorSubmissoes.java:276)\n	at br.edu.les.easyCorrection.sistema.Sistema.rodarTestesAutomaticos(Sistema.java:302)\n	at br.edu.les.easyCorrection.sistema.Facade.rodarTestesAutomaticos(Facade.java:603)\n	at sun.reflect.GeneratedMethodAccessor239.invoke(Unknown Source)\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n	at java.lang.reflect.Method.invoke(Method.java:616)\n	at flex.messaging.services.remoting.adapters.JavaAdapter.invoke(JavaAdapter.java:406)\n	at flex.messaging.services.RemotingService.serviceMessage(RemotingService.java:183)\n	at flex.messaging.MessageBroker.routeMessageToService(MessageBroker.java:1417)\n	at flex.messaging.endpoints.AbstractEndpoint.serviceMessage(AbstractEndpoint.java:878)\n	at flex.messaging.endpoints.amf.MessageBrokerFilter.invoke(MessageBrokerFilter.java:121)\n	at flex.messaging.endpoints.amf.LegacyFilter.invoke(LegacyFilter.java:158)\n	at flex.messaging.endpoints.amf.SessionFilter.invoke(SessionFilter.java:49)\n	at flex.messaging.endpoints.amf.BatchProcessFilter.invoke(BatchProcessFilter.java:67)\n	at flex.messaging.endpoints.amf.SerializationFilter.invoke(SerializationFilter.java:146)\n	at flex.messaging.endpoints.BaseHTTPEndpoint.service(BaseHTTPEndpoint.java:274)\n	at flex.messaging.MessageBrokerServlet.service(MessageBrokerServlet.java:377)\n	at javax.servlet.http.HttpServlet.service(HttpServlet.java:717)\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:290)\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)\n	at org.apache.coyote.http11.Http11AprProcessor.process(Http11AprProcessor.java:859)\n	at org.apache.coyote.http11.Http11AprProtocol$Http11ConnectionHandler.process(Http11AprProtocol.java:574)\n	at org.apache.tomcat.util.net.AprEndpoint$Worker.run(AprEndpoint.java:1527)\n	at java.lang.Thread.run(Thread.java:636)\n\n','0.00','2011-04-02 00:00:00',134,1),
 (100,270,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 12\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-07 00:00:00',NULL,0),
 (101,271,'4.00','0.00','Relatorio de Avaliacao: \n\nTotal de Testes: 12\nTotal de Erros: 0\nPorcentagem de Acertos: 100 %\nNota dos Testes AutomÃ¡ticos: 4.0\n\nConsole:\nSUCESSO!','0.00','2011-04-07 00:00:00',NULL,0),
 (102,275,'0.00','0.00','Roteiro sem testes automaticos.','0.00','2011-04-12 00:00:00',NULL,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `equipe_has_usuario_has_roteiro`
--

/*!40000 ALTER TABLE `equipe_has_usuario_has_roteiro` DISABLE KEYS */;
INSERT INTO `equipe_has_usuario_has_roteiro` (`equipe_id`,`usuario_id`,`roteiro_id`,`id`) VALUES 
 (1,139,6,58),
 (1,139,7,115),
 (1,139,8,174),
 (1,139,9,231),
 (2,140,6,59),
 (2,140,7,116),
 (2,140,8,175),
 (2,140,9,232),
 (3,141,6,60),
 (3,141,7,117),
 (3,141,8,176),
 (3,141,9,233),
 (4,142,6,61),
 (4,142,7,118),
 (4,142,8,177),
 (4,142,9,234),
 (5,143,6,62),
 (5,143,7,119),
 (5,143,8,178),
 (5,143,9,235),
 (6,144,6,63),
 (6,144,7,120),
 (6,144,8,179),
 (6,144,9,236),
 (7,145,6,64),
 (7,145,7,121),
 (7,145,8,180),
 (7,145,9,237),
 (8,146,6,65),
 (8,146,7,122),
 (8,146,8,181),
 (8,146,9,238),
 (9,147,6,66),
 (9,147,7,123),
 (9,147,8,182),
 (9,147,9,239),
 (10,148,6,67),
 (10,148,7,124),
 (10,148,8,183),
 (10,148,9,240),
 (11,149,6,68),
 (11,149,7,125),
 (11,149,8,184),
 (11,149,9,241),
 (12,150,6,69),
 (12,150,7,126),
 (12,150,8,185),
 (12,150,9,242),
 (13,151,6,70),
 (13,151,7,127),
 (13,151,8,186),
 (13,151,9,243),
 (14,152,6,71),
 (14,152,7,128),
 (14,152,8,187),
 (14,152,9,244),
 (15,153,6,72),
 (15,153,7,129),
 (15,153,8,188),
 (15,153,9,245),
 (16,154,6,73),
 (16,154,7,130),
 (16,154,8,189),
 (16,154,9,246),
 (17,155,6,74),
 (17,155,7,131),
 (17,155,8,190),
 (17,155,9,247),
 (18,156,6,75),
 (18,156,7,132),
 (18,156,8,191),
 (18,156,9,248),
 (19,157,6,76),
 (19,157,7,133),
 (19,157,8,192),
 (19,157,9,249),
 (20,158,6,77),
 (20,158,7,134),
 (20,158,8,193),
 (20,158,9,250),
 (21,159,6,78),
 (21,159,7,135),
 (21,159,8,194),
 (21,159,9,251),
 (22,160,6,79),
 (22,160,7,136),
 (22,160,8,195),
 (22,160,9,252),
 (23,161,6,80),
 (23,161,7,137),
 (23,161,8,196),
 (23,161,9,253),
 (24,162,6,81),
 (24,162,7,138),
 (24,162,8,197),
 (24,162,9,254),
 (25,163,6,82),
 (25,163,7,139),
 (25,163,8,198),
 (25,163,9,255),
 (26,164,6,83),
 (26,164,7,140),
 (26,164,8,199),
 (26,164,9,256),
 (27,165,6,84),
 (27,165,7,141),
 (27,165,8,200),
 (27,165,9,257),
 (28,166,6,85),
 (28,166,7,142),
 (28,166,8,201),
 (28,166,9,258),
 (29,167,6,86),
 (29,167,7,143),
 (29,167,8,202),
 (29,167,9,259),
 (30,168,6,87),
 (30,168,7,144),
 (30,168,8,203),
 (30,168,9,260),
 (31,169,6,88),
 (31,169,7,145),
 (31,169,8,204),
 (31,169,9,261),
 (32,170,6,89),
 (32,170,7,146),
 (32,170,8,205),
 (32,170,9,262),
 (33,171,6,90),
 (33,171,7,147),
 (33,171,8,206),
 (33,171,9,263),
 (34,172,6,91),
 (34,172,7,148),
 (34,172,8,207),
 (34,172,9,264),
 (35,173,6,92),
 (35,173,7,149),
 (35,173,8,208),
 (35,173,9,265),
 (36,174,6,93),
 (36,174,7,150),
 (36,174,8,209),
 (36,174,9,266),
 (37,175,6,94),
 (37,175,7,151),
 (37,175,8,210),
 (37,175,9,267),
 (38,176,6,95),
 (38,176,7,152),
 (38,176,8,211),
 (38,176,9,268),
 (39,177,6,96),
 (39,177,7,153),
 (39,177,8,212),
 (39,177,9,269),
 (40,178,6,97),
 (40,178,7,154),
 (40,178,8,213),
 (40,178,9,270),
 (41,179,6,98),
 (41,179,7,155),
 (41,179,8,214),
 (41,179,9,271),
 (42,180,6,99),
 (42,180,7,156),
 (42,180,8,215),
 (42,180,9,272),
 (43,181,6,100),
 (43,181,7,157),
 (43,181,8,216),
 (43,181,9,273),
 (44,182,6,101),
 (44,182,7,158),
 (44,182,8,217),
 (44,182,9,274),
 (45,183,6,102),
 (45,183,7,159),
 (45,183,8,218),
 (45,183,9,275),
 (46,184,6,103),
 (46,184,7,160),
 (46,184,8,219),
 (46,184,9,276),
 (47,185,6,104),
 (47,185,7,161),
 (47,185,8,220),
 (47,185,9,277),
 (48,186,6,105),
 (48,186,7,162),
 (48,186,8,221),
 (48,186,9,278),
 (49,187,6,106),
 (49,187,7,163),
 (49,187,8,222),
 (49,187,9,279),
 (50,188,6,107),
 (50,188,7,164),
 (50,188,8,223),
 (50,188,9,280),
 (51,189,6,108),
 (51,189,7,165),
 (51,189,8,224),
 (51,189,9,281),
 (52,190,6,109),
 (52,190,7,166),
 (52,190,8,225),
 (52,190,9,282),
 (53,191,6,110),
 (53,191,7,167),
 (53,191,8,226),
 (53,191,9,283),
 (54,192,6,111),
 (54,192,7,168),
 (54,192,8,227),
 (54,192,9,284),
 (55,193,6,112),
 (55,193,7,169),
 (55,193,8,228),
 (55,193,9,285),
 (56,194,6,113),
 (56,194,7,170),
 (56,194,8,229),
 (56,194,9,286),
 (57,195,6,114),
 (57,195,7,171),
 (57,195,8,230),
 (57,195,9,287);
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
 (1,'Cadastros','acesso',1),
 (2,'Permiss�es','defPerm',1),
 (4,'Cria��o de Roteiros','agendaRoteiros',4),
 (5,'Atribui��o de Corretores','atribuicaoDeRoteiros',3),
 (6,'Penalidades','penalidades',3),
 (7,'Visualizar Notas','notas',3),
 (8,'Submiss�es','submissaoDeRoteiros',4),
 (9,'Corre��o de Roteiros','avaliacaoDeRoteiros',3),
 (10,'Discuss�o','chat',3),
 (11,'Cria��o de Equipe','criaEquipe',4);
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
 (1,'Administrador'),
 (4,'Aluno'),
 (3,'Monitor'),
 (2,'Professor');
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;


--
-- Definition of table `grupo_usuario`
--

DROP TABLE IF EXISTS `grupo_usuario`;
CREATE TABLE `grupo_usuario` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_usuario` int(10) unsigned NOT NULL,
  `id_grupo` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_grupo_usuario_grupo` (`id_grupo`),
  KEY `FK_grupo_usuario_usuario` (`id_usuario`),
  CONSTRAINT `FK_grupo_usuario_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FK_grupo_usuario_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo_usuario`
--

/*!40000 ALTER TABLE `grupo_usuario` DISABLE KEYS */;
INSERT INTO `grupo_usuario` (`id`,`id_usuario`,`id_grupo`) VALUES 
 (1,1,1),
 (3,3,1),
 (125,119,1),
 (132,126,2),
 (139,132,3),
 (140,133,1),
 (141,134,3),
 (142,135,3),
 (143,136,3),
 (145,138,3),
 (146,139,4),
 (147,140,4),
 (148,141,4),
 (149,142,4),
 (150,143,4),
 (151,144,4),
 (152,145,4),
 (153,146,4),
 (154,147,4),
 (155,148,4),
 (156,149,4),
 (157,150,4),
 (158,151,4),
 (159,152,4),
 (160,153,4),
 (161,154,4),
 (162,155,4),
 (163,156,4),
 (164,157,4),
 (165,158,4),
 (166,159,4),
 (167,160,4),
 (168,161,4),
 (169,162,4),
 (170,163,4),
 (171,164,4),
 (172,165,4),
 (173,166,4),
 (174,167,4),
 (175,168,4),
 (176,169,4),
 (177,170,4),
 (178,171,4),
 (179,172,4),
 (180,173,4),
 (181,174,4),
 (182,175,4),
 (183,176,4),
 (184,177,4),
 (185,178,4),
 (186,179,4),
 (187,180,4),
 (188,181,4),
 (189,182,4),
 (190,183,4),
 (191,184,4),
 (192,185,4),
 (193,186,4),
 (194,187,4),
 (195,188,4),
 (196,189,4),
 (197,190,4),
 (198,191,4),
 (199,192,4),
 (200,193,4),
 (201,194,4),
 (202,195,4);
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
 (1,'acesso','Controle de Acesso'),
 (3,'avaliacao','Avalia��o'),
 (4,'submissao','Roteiros');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
CREATE TABLE `periodo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `semestre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `periodo`
--

/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
INSERT INTO `periodo` (`id`,`semestre`) VALUES 
 (1,'2011.1');
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permissao`
--

/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` (`id`,`grupo_id`,`funcao_id`) VALUES 
 (1,1,1),
 (2,1,2),
 (3,1,4),
 (4,1,5),
 (5,1,6),
 (6,1,7),
 (7,1,8),
 (8,1,9),
 (9,1,10),
 (10,1,11),
 (11,2,1),
 (12,2,4),
 (19,3,4),
 (22,3,9),
 (25,4,8),
 (26,4,11),
 (27,4,7),
 (28,2,5),
 (29,2,7),
 (30,2,9),
 (31,3,7),
 (33,3,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roteiro`
--

/*!40000 ALTER TABLE `roteiro` DISABLE KEYS */;
INSERT INTO `roteiro` (`id`,`periodo_id`,`nome`,`descricao`,`data_liberacao`,`data_final_entrega`,`data_final_discussao`,`numero_maximo_envios`,`penalidade_dias_atraso`,`porcentagem_testes_automaticos`,`tempo_limite_testes`,`diretorio_interface`,`diretorio_testes`,`numero_maximo_participantes`,`tipo_roteiro_id`) VALUES 
 (6,1,'Roteiro 2','Roteiro sobre algoritmos de ordenacao','2011-03-18 00:00:00','2011-03-24 23:00:00','2011-04-08 00:00:00',3,'5.00','40.00',10,'/periodo2011.1/interface/6/','/periodo2011.1/testes/6/',1,1),
 (7,1,'Roteiro sobre Fila','Roteiro sobre implementacao de estruturas de dados elementares. ','2011-03-29 23:00:00','2011-03-30 22:59:00','2011-04-03 23:00:00',3,'5.00','40.00',10,'/periodo2011.1/interface/7/','/periodo2011.1/testes/7/',1,1),
 (8,1,'Roteiro sobre Lista Encadeada','Roteiro sobre listas simplesmente encadeada. AS implementacoes devem ser recursivas.','2011-03-31 23:00:00','2011-04-02 11:00:00','2011-04-03 23:00:00',6,'1.00','40.00',90,'/periodo2011.1/interface/8/','/periodo2011.1/testes/8/',1,1),
 (9,1,'Roteiro sobre arvore binaria (08/04/2011)','','2011-04-12 00:00:00','2011-04-19 23:00:00','2011-05-03 23:00:00',5,'5.00','0.00',0,'/periodo2011.1/interface/9/','/periodo2011.1/testes/9/',1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `submissao`
--

/*!40000 ALTER TABLE `submissao` DISABLE KEYS */;
INSERT INTO `submissao` (`id`,`url`,`estado`,`data_submissao`,`equipe_has_usuario_has_roteiro_id`) VALUES 
 (79,'/periodo2011.1/submissoes/roteiro_7/Equipe 24/',NULL,'2011-03-30 00:00:00',138),
 (80,'/periodo2011.1/submissoes/roteiro_7/Equipe 36/',NULL,'2011-03-30 00:00:00',150),
 (81,'/periodo2011.1/submissoes/roteiro_7/Equipe 24/',NULL,'2011-03-30 00:00:00',138),
 (82,'/periodo2011.1/submissoes/roteiro_7/Equipe 47/',NULL,'2011-03-30 00:00:00',161),
 (83,'/periodo2011.1/submissoes/roteiro_7/Equipe 30/',NULL,'2011-03-30 00:00:00',144),
 (84,'/periodo2011.1/submissoes/roteiro_7/Equipe 31/',NULL,'2011-03-30 00:00:00',145),
 (85,'/periodo2011.1/submissoes/roteiro_7/Equipe 28/',NULL,'2011-03-30 00:00:00',142),
 (86,'/periodo2011.1/submissoes/roteiro_7/Equipe 45/',NULL,'2011-03-30 00:00:00',159),
 (87,'/periodo2011.1/submissoes/roteiro_7/Equipe 27/',NULL,'2011-03-30 00:00:00',141),
 (88,'/periodo2011.1/submissoes/roteiro_7/Equipe 41/',NULL,'2011-03-30 00:00:00',155),
 (89,'/periodo2011.1/submissoes/roteiro_7/Equipe 55/',NULL,'2011-03-30 00:00:00',169),
 (90,'/periodo2011.1/submissoes/roteiro_7/Equipe 22/',NULL,'2011-03-30 00:00:00',136),
 (91,'/periodo2011.1/submissoes/roteiro_7/Equipe 28/',NULL,'2011-03-30 00:00:00',142),
 (92,'/periodo2011.1/submissoes/roteiro_7/Equipe 33/',NULL,'2011-03-30 00:00:00',147),
 (93,'/periodo2011.1/submissoes/roteiro_7/Equipe 44/',NULL,'2011-03-30 00:00:00',158),
 (94,'/periodo2011.1/submissoes/roteiro_7/Equipe 37/',NULL,'2011-03-30 00:00:00',151),
 (95,'/periodo2011.1/submissoes/roteiro_7/Equipe 32/',NULL,'2011-03-30 00:00:00',146),
 (96,'/periodo2011.1/submissoes/roteiro_7/Equipe 50/',NULL,'2011-03-30 00:00:00',164),
 (97,'/periodo2011.1/submissoes/roteiro_7/Equipe 49/',NULL,'2011-03-30 00:00:00',163),
 (100,'/periodo2011.1/submissoes/roteiro_7/Equipe 34/',NULL,'2011-03-30 00:00:00',148),
 (101,'/periodo2011.1/submissoes/roteiro_7/Equipe 20/',NULL,'2011-03-30 00:00:00',134),
 (102,'/periodo2011.1/submissoes/roteiro_7/Equipe 6/',NULL,'2011-03-30 00:00:00',120),
 (103,'/periodo2011.1/submissoes/roteiro_7/Equipe 6/',NULL,'2011-03-30 00:00:00',120),
 (104,'/periodo2011.1/submissoes/roteiro_7/Equipe 17/',NULL,'2011-03-30 00:00:00',131),
 (107,'/periodo2011.1/submissoes/roteiro_7/Equipe 18/',NULL,'2011-03-30 00:00:00',132),
 (108,'/periodo2011.1/submissoes/roteiro_7/Equipe 1/',NULL,'2011-03-30 00:00:00',115),
 (111,'/periodo2011.1/submissoes/roteiro_7/Equipe 38/',NULL,'2011-03-30 00:00:00',152),
 (112,'/periodo2011.1/submissoes/roteiro_7/Equipe 8/',NULL,'2011-03-30 00:00:00',122),
 (113,'/periodo2011.1/submissoes/roteiro_7/Equipe 2/',NULL,'2011-03-30 00:00:00',116),
 (117,'/periodo2011.1/submissoes/roteiro_7/Equipe 3/',NULL,'2011-03-30 00:00:00',117),
 (118,'/periodo2011.1/submissoes/roteiro_7/Equipe 14/',NULL,'2011-03-30 00:00:00',128),
 (119,'/periodo2011.1/submissoes/roteiro_7/Equipe 12/',NULL,'2011-03-30 00:00:00',126),
 (120,'/periodo2011.1/submissoes/roteiro_7/Equipe 43/',NULL,'2011-03-30 00:00:00',157),
 (121,'/periodo2011.1/submissoes/roteiro_7/Equipe 51/',NULL,'2011-03-30 00:00:00',165),
 (122,'/periodo2011.1/submissoes/roteiro_7/Equipe 7/',NULL,'2011-03-30 00:00:00',121),
 (125,'/periodo2011.1/submissoes/roteiro_7/Equipe 10/',NULL,'2011-03-30 00:00:00',124),
 (126,'/periodo2011.1/submissoes/roteiro_7/Equipe 9/',NULL,'2011-03-30 00:00:00',123),
 (127,'/periodo2011.1/submissoes/roteiro_7/Equipe 21/',NULL,'2011-03-30 00:00:00',135),
 (129,'/periodo2011.1/submissoes/roteiro_7/Equipe 54/',NULL,'2011-03-30 00:00:00',168),
 (130,'/periodo2011.1/submissoes/roteiro_7/Equipe 39/',NULL,'2011-03-30 00:00:00',153),
 (131,'/periodo2011.1/submissoes/roteiro_7/Equipe 29/',NULL,'2011-03-30 00:00:00',143),
 (132,'/periodo2011.1/submissoes/roteiro_7/Equipe 25/',NULL,'2011-03-30 00:00:00',139),
 (133,'/periodo2011.1/submissoes/roteiro_7/Equipe 11/',NULL,'2011-03-30 00:00:00',125),
 (134,'/periodo2011.1/submissoes/roteiro_7/Equipe 23/',NULL,'2011-03-30 00:00:00',137),
 (135,'/periodo2011.1/submissoes/roteiro_7/Equipe 5/',NULL,'2011-03-30 00:00:00',119),
 (136,'/periodo2011.1/submissoes/roteiro_7/Equipe 19/',NULL,'2011-03-30 00:00:00',133),
 (137,'/periodo2011.1/submissoes/roteiro_7/Equipe 46/',NULL,'2011-03-30 00:00:00',160),
 (142,'/periodo2011.1/submissoes/roteiro_7/Equipe 48/',NULL,'2011-03-30 00:00:00',162),
 (143,'/periodo2011.1/submissoes/roteiro_7/Equipe 15/',NULL,'2011-03-30 00:00:00',129),
 (144,'/periodo2011.1/submissoes/roteiro_7/Equipe 15/',NULL,'2011-03-30 00:00:00',129),
 (145,'/periodo2011.1/submissoes/roteiro_7/Equipe 16/',NULL,'2011-03-30 00:00:00',130),
 (146,'/periodo2011.1/submissoes/roteiro_7/Equipe 13/',NULL,'2011-03-30 00:00:00',127),
 (148,'/periodo2011.1/submissoes/roteiro_7/Equipe 31/',NULL,'2011-03-30 00:00:00',145),
 (150,'/periodo2011.1/submissoes/roteiro_7/Equipe 31/',NULL,'2011-03-30 00:00:00',145),
 (151,'/periodo2011.1/submissoes/roteiro_7/Equipe 26/',NULL,'2011-03-30 00:00:00',140),
 (152,'/periodo2011.1/submissoes/roteiro_8/Equipe 41/',NULL,'2011-03-31 00:00:00',214),
 (160,'/periodo2011.1/submissoes/roteiro_8/Equipe 27/',NULL,'2011-04-01 00:00:00',200),
 (162,'/periodo2011.1/submissoes/roteiro_8/Equipe 44/',NULL,'2011-04-01 00:00:00',217),
 (165,'/periodo2011.1/submissoes/roteiro_8/Equipe 28/',NULL,'2011-04-01 00:00:00',201),
 (166,'/periodo2011.1/submissoes/roteiro_8/Equipe 31/',NULL,'2011-04-01 00:00:00',204),
 (167,'/periodo2011.1/submissoes/roteiro_8/Equipe 54/',NULL,'2011-04-01 00:00:00',227),
 (168,'/periodo2011.1/submissoes/roteiro_8/Equipe 54/',NULL,'2011-04-01 00:00:00',227),
 (169,'/periodo2011.1/submissoes/roteiro_8/Equipe 23/',NULL,'2011-04-01 00:00:00',196),
 (170,'/periodo2011.1/submissoes/roteiro_8/Equipe 36/',NULL,'2011-04-01 00:00:00',209),
 (171,'/periodo2011.1/submissoes/roteiro_8/Equipe 22/',NULL,'2011-04-01 00:00:00',195),
 (172,'/periodo2011.1/submissoes/roteiro_8/Equipe 47/',NULL,'2011-04-01 00:00:00',220),
 (177,'/periodo2011.1/submissoes/roteiro_8/Equipe 41/',NULL,'2011-04-01 00:00:00',214),
 (178,'/periodo2011.1/submissoes/roteiro_8/Equipe 17/',NULL,'2011-04-01 00:00:00',190),
 (179,'/periodo2011.1/submissoes/roteiro_8/Equipe 11/',NULL,'2011-04-01 00:00:00',184),
 (180,'/periodo2011.1/submissoes/roteiro_8/Equipe 12/',NULL,'2011-04-01 00:00:00',185),
 (181,'/periodo2011.1/submissoes/roteiro_8/Equipe 41/',NULL,'2011-04-01 00:00:00',214),
 (182,'/periodo2011.1/submissoes/roteiro_8/Equipe 14/',NULL,'2011-04-01 00:00:00',187),
 (183,'/periodo2011.1/submissoes/roteiro_8/Equipe 29/',NULL,'2011-04-01 00:00:00',202),
 (184,'/periodo2011.1/submissoes/roteiro_8/Equipe 18/',NULL,'2011-04-01 00:00:00',191),
 (185,'/periodo2011.1/submissoes/roteiro_8/Equipe 46/',NULL,'2011-04-01 00:00:00',219),
 (186,'/periodo2011.1/submissoes/roteiro_8/Equipe 34/',NULL,'2011-04-01 00:00:00',207),
 (187,'/periodo2011.1/submissoes/roteiro_8/Equipe 49/',NULL,'2011-04-01 00:00:00',222),
 (192,'/periodo2011.1/submissoes/roteiro_8/Equipe 50/',NULL,'2011-04-01 00:00:00',223),
 (194,'/periodo2011.1/submissoes/roteiro_8/Equipe 39/',NULL,'2011-04-01 00:00:00',212),
 (195,'/periodo2011.1/submissoes/roteiro_8/Equipe 45/',NULL,'2011-04-01 00:00:00',218),
 (196,'/periodo2011.1/submissoes/roteiro_8/Equipe 50/',NULL,'2011-04-01 00:00:00',223),
 (197,'/periodo2011.1/submissoes/roteiro_8/Equipe 37/',NULL,'2011-04-01 00:00:00',210),
 (198,'/periodo2011.1/submissoes/roteiro_8/Equipe 32/',NULL,'2011-04-01 00:00:00',205),
 (200,'/periodo2011.1/submissoes/roteiro_8/Equipe 17/',NULL,'2011-04-01 00:00:00',190),
 (207,'/periodo2011.1/submissoes/roteiro_8/Equipe 39/',NULL,'2011-04-01 00:00:00',212),
 (208,'/periodo2011.1/submissoes/roteiro_8/Equipe 51/',NULL,'2011-04-01 00:00:00',224),
 (212,'/periodo2011.1/submissoes/roteiro_8/Equipe 41/',NULL,'2011-04-01 00:00:00',214),
 (214,'/periodo2011.1/submissoes/roteiro_8/Equipe 22/',NULL,'2011-04-01 00:00:00',195),
 (216,'/periodo2011.1/submissoes/roteiro_8/Equipe 56/',NULL,'2011-04-01 00:00:00',229),
 (217,'/periodo2011.1/submissoes/roteiro_8/Equipe 26/',NULL,'2011-04-01 00:00:00',199),
 (219,'/periodo2011.1/submissoes/roteiro_8/Equipe 3/',NULL,'2011-04-01 00:00:00',176),
 (220,'/periodo2011.1/submissoes/roteiro_8/Equipe 38/',NULL,'2011-04-01 00:00:00',211),
 (223,'/periodo2011.1/submissoes/roteiro_8/Equipe 2/',NULL,'2011-04-01 00:00:00',175),
 (225,'/periodo2011.1/submissoes/roteiro_8/Equipe 21/',NULL,'2011-04-01 00:00:00',194),
 (226,'/periodo2011.1/submissoes/roteiro_8/Equipe 25/',NULL,'2011-04-01 00:00:00',198),
 (227,'/periodo2011.1/submissoes/roteiro_8/Equipe 35/',NULL,'2011-04-01 00:00:00',208),
 (232,'/periodo2011.1/submissoes/roteiro_8/Equipe 35/',NULL,'2011-04-01 00:00:00',208),
 (234,'/periodo2011.1/submissoes/roteiro_8/Equipe 8/',NULL,'2011-04-01 00:00:00',181),
 (235,'/periodo2011.1/submissoes/roteiro_8/Equipe 7/',NULL,'2011-04-01 00:00:00',180),
 (236,'/periodo2011.1/submissoes/roteiro_8/Equipe 9/',NULL,'2011-04-01 00:00:00',182),
 (237,'/periodo2011.1/submissoes/roteiro_8/Equipe 9/',NULL,'2011-04-01 00:00:00',182),
 (243,'/periodo2011.1/submissoes/roteiro_8/Equipe 19/',NULL,'2011-04-01 00:00:00',192),
 (244,'/periodo2011.1/submissoes/roteiro_8/Equipe 6/',NULL,'2011-04-01 00:00:00',179),
 (245,'/periodo2011.1/submissoes/roteiro_8/Equipe 48/',NULL,'2011-04-01 00:00:00',221),
 (249,'/periodo2011.1/submissoes/roteiro_8/Equipe 24/',NULL,'2011-04-01 00:00:00',197),
 (250,'/periodo2011.1/submissoes/roteiro_8/Equipe 4/',NULL,'2011-04-01 00:00:00',177),
 (251,'/periodo2011.1/submissoes/roteiro_8/Equipe 24/',NULL,'2011-04-01 00:00:00',197),
 (252,'/periodo2011.1/submissoes/roteiro_8/Equipe 24/',NULL,'2011-04-01 00:00:00',197),
 (253,'/periodo2011.1/submissoes/roteiro_8/Equipe 30/',NULL,'2011-04-01 00:00:00',203),
 (254,'/periodo2011.1/submissoes/roteiro_8/Equipe 1/',NULL,'2011-04-01 00:00:00',174),
 (255,'/periodo2011.1/submissoes/roteiro_8/Equipe 1/',NULL,'2011-04-01 00:00:00',174),
 (257,'/periodo2011.1/submissoes/roteiro_8/Equipe 13/',NULL,'2011-04-02 00:00:00',186),
 (264,'/periodo2011.1/submissoes/roteiro_8/Equipe 13/',NULL,'2011-04-02 00:00:00',186),
 (265,'/periodo2011.1/submissoes/roteiro_8/Equipe 16/',NULL,'2011-04-02 00:00:00',189),
 (266,'/periodo2011.1/submissoes/roteiro_8/Equipe 33/',NULL,'2011-04-02 00:00:00',206),
 (267,'/periodo2011.1/submissoes/roteiro_8/Equipe 5/',NULL,'2011-04-02 00:00:00',178),
 (268,'/periodo2011.1/submissoes/roteiro_8/Equipe 5/',NULL,'2011-04-02 00:00:00',178),
 (269,'/periodo2011.1/submissoes/roteiro_9/Equipe 27/',NULL,'2011-04-07 00:00:00',257),
 (270,'/periodo2011.1/submissoes/roteiro_9/Equipe 27/',NULL,'2011-04-07 00:00:00',257),
 (271,'/periodo2011.1/submissoes/roteiro_9/Equipe 41/',NULL,'2011-04-07 00:00:00',271),
 (273,'/periodo2011.1/submissoes/roteiro_9/Equipe 1/',NULL,'2011-04-12 00:00:00',231),
 (274,'/periodo2011.1/submissoes/roteiro_9/Equipe 1/',NULL,'2011-04-12 00:00:00',231),
 (275,'/periodo2011.1/submissoes/roteiro_9/Equipe 1/',NULL,'2011-04-12 00:00:00',231);
/*!40000 ALTER TABLE `submissao` ENABLE KEYS */;


--
-- Definition of table `tipo_roteiro`
--

DROP TABLE IF EXISTS `tipo_roteiro`;
CREATE TABLE `tipo_roteiro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(500) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_roteiro`
--

/*!40000 ALTER TABLE `tipo_roteiro` DISABLE KEYS */;
INSERT INTO `submissao` (`id`,`nome`) VALUES 
  (1, 'Roteiro Completo');
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
  `periodo_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_login` (`login`),
  KEY `FK_usuario_periodo` (`periodo_id`),
  CONSTRAINT `FK_usuario_periodo` FOREIGN KEY (`periodo_id`) REFERENCES `periodo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`login`,`nome`,`senha`,`email`,`periodo_id`) VALUES 
 (1,'demas','Demetrio Gomes','4297f44b13955235245b2497399d7a93','demetr@gmail.com',NULL),
 (3,'alysson','Alysson Filgueira Milanez','4b395a361e5233bafb1efa024cef5037','alyssonfilgueira@gmail.com',NULL),
 (119,'augusto','Augusto','4297f44b13955235245b2497399d7a93','augustomacedo@gmail.com',NULL),
 (126,'livia','Livia Campos','3fb4b2291f7bf8c4835b8a11f1cf199f','sampaiolivia@gmail.com',NULL),
 (132,'liviam','Livia M.','3fb4b2291f7bf8c4835b8a11f1cf199f','livia@dsc.ufcg.edu.br',NULL),
 (133,'adalberto','Adalberto Cajueiro','e53958bedc53ec0f9e2dd4a44c30bbe6','adalberto.cajueiro@gmail.com',NULL),
 (134,'matheus_maciel','Matheus de AraÃºjo Maciel','405aff1160f4edc26afff41684272a47','teu.araujo@gmail.com',NULL),
 (135,'alexandre_medeiros','Alexandre Bruno de Macedo Medeiros','20af13d296aad1ac8cb2623abea334c2','sk.ufcg@gmail.com',NULL),
 (136,'diego_silva','Diego Pedro GonÃ§alves da Silva','b511bb57ec8b8f3de7ac299cdf767503','diegopedro@gmail.com',NULL),
 (138,'henrique_truta','Italo Henrique Costa Truta','72ac71d960d0b8ed80341a83f07f0dca','henriquecostatruta@gmail.com',NULL),
 (139,'20821348','ANTONIO RICARDO MARQUES JUNIOR','4297f44b13955235245b2497399d7a93','antonioricardojr@gmail.com',NULL),
 (140,'21011049','ARTHUR SENA LINS CALDAS','66b189264155ea15db7b6d2155833b8a','skyline199269@gmail.com',NULL),
 (141,'20921009','BRUNO MIRANDA BRANDAO','a96f083b3e6d5e165ad96a2f9ae149d4','brunomb.ufcg@gmail.com',NULL),
 (142,'20911801','DIRCEU DE MEDEIROS TEIXEIRA','4297f44b13955235245b2497399d7a93','dirceudn@gmail.com',NULL),
 (143,'20911056','ELIAS PAULINO MEDEIROS','2bfada733f75cb441787dd5a0eec43b9','eliaspaulinom@gmail.com',NULL),
 (144,'21011074','GUILHERME SANTOS GALVAO BAPTISTA','5a19af55092c2121cfb519b88b5a33ea','guisgb13@gmail.com',NULL),
 (145,'21011078','IGOR GOMES DE MENESES CRUZ','1363c852fd0f17881cbe7b293077ded4','igorcruz20@gmail.com',NULL),
 (146,'20921422','JEFFERSON TRIGUEIRO DE OLIVEIRA','b8e407e4cece8b2af50d1860865cc8dd','jeffersontrigueiro@gmail.com',NULL),
 (147,'20921032','JOSE ARTHUR GADELHA ALEXANDRE','554e64e9763cbb770fd3bbf3b9959e94','arthurgad.alexandre@gmail.com',NULL),
 (148,'21011088','JOSE ARTHUR MORAIS VERAS E SILVA','1535900a8464e293213ae1bd03942557','arthur.tabira@gmail.com',NULL),
 (149,'21115289','LAYSE SOBREIRA BENTO','89bf6ba31525e3a2ddc2024ac10e0571','layse.sobreira@gmail.com',NULL),
 (150,'20921293','MATHEUS BATISTA SILVA','8197ca7bf01dbe4d7d6282693a49993a','matheus.ufcg@gmail.com',NULL),
 (151,'20921295','NATANAEL COSTA PEREIRA','63106ae86c04ea8ba049439048e28b16','natancc20092@gmail.com',NULL),
 (152,'20921049','PAULO ANDRE BRAGA SOUTO','5e347fd87ddeb97617a732e4c160e438','pauloabsouto@gmail.com',NULL),
 (153,'21011108','PEDRO RAWAN MEIRELES LIMEIRA','ebfb650d148a65e4bf26c8af17781705','prmlimeira@gmail.com',NULL),
 (154,'20921053','RAPHAEL SOARES DE CARVALHO','37e29032bfff38b2c1928b04684fea11','ccorzus@gmail.com',NULL),
 (155,'21011117','RODOLFO DE LIMA VIANA','52a6bab296c5709ce47b13108fdfa1b7','rodolfodelimaviana@gmail.com',NULL),
 (156,'20921057','RODOLFO MORAES MARTINS','42a557445c233c4bb1498d0bc3ed2cba','rodolfomoraes1@gmail.com',NULL),
 (157,'20921059','TALITA LOBO DE MENEZES','9b070b88ae61664647df97c772479d5','talitabac@gmail.com',NULL),
 (158,'21011125','TARCISO BRAZ DE OLIVEIRA FILHO','1c55cd64e6b747929763046da4bcbcf1','tarcisocomp@gmail.com',NULL),
 (159,'20921063','WERTON VINICIUS GUIMARAES GOMES','9b66f7d1f0b86646545cc9bbfca2876d','werton007@gmail.com',NULL),
 (160,'21011043','ABIMAEL DE SOUSA MONTEIRO','722e73cbb54fc4e331b14e304d0ad92b','abimaeldesousamonteiro@gmail.com',NULL),
 (161,'21011848','ALBERTO FAGNER FERREIRA DE BARROS','682b9807f1331df60edd097234974b85','albertofagner.cav@gmail.com',NULL),
 (162,'21011044','ANDERSON THELLES CLAUDINO DA SILVA','b4bb6f9335dafe126cf66000f91c4399','aandersonthelles@gmail.com',NULL),
 (163,'21011050','BENONI MARINHO DA SILVA','d18c76f3379534417380d9faecaf6049','benonisilva@gmail.com',NULL),
 (164,'21011973','DANILO GOMES DE LIMA LACERDA','9d25f5715cfcb58e5f14fcb2a07988ad','dnlgomes93@gmail.com',NULL),
 (165,'21011058','DANILO PIMENTEIRA MELO NASCIMENTO','be7a370976fb12344b244598329d9850','pimenteiramelo@gmail.com',NULL),
 (166,'21011065','EMANUEL CARLOS ALBUQUERQUE ALVES','80c1f45f28bc4e0a898c2608371d00c6','emanuelorigamista@gmail.com',NULL),
 (167,'21011072','FILIPE DE ALENCAR RAMOS','114b7cbdce99c114532fea446b9363ae','filipe.69@gmail.com',NULL),
 (168,'21011076','GUTHYERRZ MACIEL DA SILVA','4c8c831e8bac5082ce2964d37ba4dfa3','guthyerrz.ufcg@gmail.comÂ ',NULL),
 (169,'21011081','ISABELLY LOUREDO ROCHA','8fd8cf32f3f0bbb06c26fa1dfc92f9c5','isabellylr@gmail.com',NULL),
 (170,'21011085','JEREMIAS DINAMERICO SERAFIM DE ARAUJO','b037a3a796131c41af99799e3111b5e6','jeremias.serafim.araujo@gmail.com',NULL),
 (171,'21011091','JOSE NATHANIEL LACERDA DE ABRANTE','199045fa6caec98f69da3f159c026727','nathaniel.una@gmail.com',NULL),
 (172,'20921493','KAIO CESAR BEZERRA RANGEL','a9fcac4933063d64430498aeb6c5e950','kaio.ufcg@gmail.com',NULL),
 (173,'21111099','LAERCIO ALMEIDA VITORINO MARTINS','a701df1f8770e86ee21f895d756c29a7','laerciovitorino@gmail.com',NULL),
 (174,'21011094','LILIA RODRIGUES SAMPAIO','d8678ab633b977acd4c3c5d59243c4f7','liliarsampaio@gmail.com',NULL),
 (175,'21011095','LUIZ HENRIQUE DE ANDRADE','564ebca13ea0086bb117de212a01525b','luizha.cc@gmail.com',NULL),
 (176,'21011096','MANOEL FRANCISCO MARTINIANO NETO','723f3266560f981da41e2979f7a6a69c','manoelnpastor@gmail.com',NULL),
 (177,'21011857','PEDRO ADELINO DE MELLO NETO','d619521306e3c8d7f146d19432d77c6f','pedroadmn@gmail.com',NULL),
 (178,'21011109','PEDRO VITOR VILAR PITZER CLEIS','274ff13b5350266ecbc28e9182667a4f','pedrokleiz@gmail.com',NULL),
 (179,'21011110','PHYLLIPE CESAR RAMOS DE ALMEIDA MEDEIROS','dd68558b0895296954de2559b0834cda','phyllipecesar@gmail.com',NULL),
 (180,'21011114','RAFAEL NATA CIPRIANO','add6e04aecbd70f33f3c49009f042b8d','rafa.cipri@gmail.com',NULL),
 (181,'20921055','RENAN PINTO DA SILVA','31d36a6e79896a318df79e6bb8a73c6d','renanpintocc20092@gmail.com',NULL),
 (182,'21011116','RIGEL BEZERRA DE MELO','946ee852062cb01fc3aefd32cf42b3a6','rigelbmelo@gmail.com',NULL),
 (183,'21011121','RODRIGO LARANJEIRA DE BRITO','61f61d1150e61812ac988cc667799f38','rodrigo.debritto@gmail.com',NULL),
 (184,'20921299','SAMIR TRAJANO FEITOSA','fb879a911481ceef9a148f700372f77c','samircc20092@gmail.com',NULL),
 (185,'21011127','THIAGO GONCALVES MONTEIRO VITURINO','b0944966532fa1fe7de60dd02b906854','thiagogvm@gmail.com',NULL),
 (186,'20721037','TIAGO SILVEIRA OLIVEIRA','53d934c98128ae55b916fa273cd126a','tiago3112@gmail.com',NULL),
 (187,'21011129','VICTOR BATISTA CAMILO','a87b15835758238e7ec23101d7a2e9a9','vbatistacamilo@gmail.com',NULL),
 (188,'21011132','YURI SIQUEIRA NASCIMENTO MELO','fb7f27e8fe409205a01cd67124271c8f','yurisnm@gmail.com',NULL),
 (189,'20911806','JOSE ULISSES DE BRITO LIRA FILHO','c80123dfdca6b683212a6ea5dbd1a7ca','ulisses.sigma@gmail.com',NULL),
 (190,'20921039','LUCAS SANTOS SAMPAIO','ccd68c11e3b8268bac9023694813da2a','g0921039@lcc.ufcg.edu.br',NULL),
 (191,'20911110','PABLO ANDERSON DE LUNA LIMA','3706ce53ed411aaf6fa6aa0ad4ba5b7c','g0911110@lcc.ufcg.edu.br',NULL),
 (192,'21011851','EMILIO DE FARIAS NETO','780df0e2989af8c4c155424a463750e5','g1011851@lcc.ufcg.edu.br',NULL),
 (193,'21011067','ERICO RAMALHO DE FREITAS','9e15b7892f358674a3a68e93c67d5ab','g1011067@lcc.ufcg.edu.br',NULL),
 (194,'21011974','HUDSON DANIEL DE ALMEIDA SANTANA','8b59528c5bbf7073061271c79ba0e1e0','hudsondas@gmail.com',NULL),
 (195,'jose_ulisses','JOSE ULISSES DE BRITO LIRA FILHO','67155ef16671dd7a83bce9d86cebad68','ulisses.sigma@gmal.com',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
