
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
CREATE TABLE `avaliacao` (`id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `submissao_id` int(10) unsigned NOT NULL,  `nota_automatica` decimal(10,2) NOT NULL,  `nota_correcao` decimal(10,2) NOT NULL,  `resultado_execucao_testes` text,  `penalidade` decimal(10,2) DEFAULT NULL,  `data_avaliacao` datetime DEFAULT NULL,  `corretor_id` int(10) unsigned DEFAULT NULL,  `corrigido` tinyint(1) unsigned DEFAULT NULL,  PRIMARY KEY (`id`),  KEY `submissao_id` (`submissao_id`),  KEY `FK_avaliacao_Usuario` (`corretor_id`),  CONSTRAINT `avaliacao_ibfk_1` FOREIGN KEY (`submissao_id`) REFERENCES `submissao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,  CONSTRAINT `FK_avaliacao_Usuario` FOREIGN KEY (`corretor_id`) REFERENCES `usuario` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `avaliacao`
--

/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;


--
-- Definition of table `chat`
--

DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `roteiro_id` int(10) unsigned NOT NULL,  `mensagem` text NOT NULL,  `data_envio` datetime NOT NULL,  `usuario_origem_id` int(10) unsigned NOT NULL,  `usuario_destino_id` int(10) unsigned DEFAULT NULL,  `equipe_destino_id` int(10) unsigned DEFAULT NULL,  PRIMARY KEY (`id`) USING BTREE,  KEY `chat_FKIndex1` (`roteiro_id`),  KEY `FK_chat_usuario_origem` (`usuario_origem_id`),  KEY `FK_chat_usuario_destino` (`usuario_destino_id`),  KEY `FK_chat_equipe` (`equipe_destino_id`),  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`roteiro_id`) REFERENCES `roteiro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,  CONSTRAINT `FK_chat_equipe` FOREIGN KEY (`equipe_destino_id`) REFERENCES `equipe` (`id`),  CONSTRAINT `FK_chat_usuario_destino` FOREIGN KEY (`usuario_destino_id`) REFERENCES `usuario` (`id`),  CONSTRAINT `FK_chat_usuario_origem` FOREIGN KEY (`usuario_origem_id`) REFERENCES `usuario` (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `chat`
--

/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;


--
-- Definition of table `equipe`
--

DROP TABLE IF EXISTS `equipe`;
CREATE TABLE `equipe` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `nome` varchar(50) NOT NULL,  PRIMARY KEY (`id`) USING BTREE) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `equipe`
--

/*!40000 ALTER TABLE `equipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipe` ENABLE KEYS */;


--
-- Definition of table `equipe_has_usuario_has_roteiro`
--

DROP TABLE IF EXISTS `equipe_has_usuario_has_roteiro`;
CREATE TABLE `equipe_has_usuario_has_roteiro` (  `equipe_id` int(10) unsigned NOT NULL DEFAULT '0',  `usuario_id` int(10) unsigned NOT NULL DEFAULT '0',  `roteiro_id` int(10) unsigned NOT NULL DEFAULT '0',  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  PRIMARY KEY (`id`),  UNIQUE KEY `Index_equipe_usuario_roteiro` (`equipe_id`,`usuario_id`,`roteiro_id`),  KEY `FK_equipe_has_usuario_has_roteiro_usuario` (`usuario_id`),  KEY `FK_equipe_has_usuario_has_roteiro_roteiro` (`roteiro_id`),  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_equipe` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`),  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_roteiro` FOREIGN KEY (`roteiro_id`) REFERENCES `roteiro` (`id`),  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `equipe_has_usuario_has_roteiro`
--

/*!40000 ALTER TABLE `equipe_has_usuario_has_roteiro` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipe_has_usuario_has_roteiro` ENABLE KEYS */;


--
-- Definition of table `funcao`
--

DROP TABLE IF EXISTS `funcao`;
CREATE TABLE `funcao` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `nome` varchar(500) NOT NULL,  `rotulo` varchar(500) NOT NULL,  `menu_id` int(10) unsigned NOT NULL,  PRIMARY KEY (`id`),  UNIQUE KEY `Index_rotulo` (`rotulo`),  KEY `FK_funcao_menu` (`menu_id`),  KEY `Index_nome` (`nome`),  CONSTRAINT `FK_funcao_menu` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcao`
--

/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcao` ENABLE KEYS */;


--
-- Definition of table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `nome` varchar(200) NOT NULL,  PRIMARY KEY (`id`),  UNIQUE KEY `Index_nome` (`nome`)) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo`
--

/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;


--
-- Definition of table `grupo_usuario`
--

DROP TABLE IF EXISTS `grupo_usuario`;
CREATE TABLE `grupo_usuario` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `id_usuario` int(10) unsigned NOT NULL,  `id_grupo` int(10) unsigned NOT NULL,  PRIMARY KEY (`id`),  KEY `FK_grupo_usuario_grupo` (`id_grupo`),  KEY `FK_grupo_usuario_usuario` (`id_usuario`),  CONSTRAINT `FK_grupo_usuario_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`),  CONSTRAINT `FK_grupo_usuario_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo_usuario`
--

/*!40000 ALTER TABLE `grupo_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_usuario` ENABLE KEYS */;


--
-- Definition of table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `titulo` varchar(200) NOT NULL,  `nome` varchar(200) NOT NULL,  PRIMARY KEY (`id`), KEY `Index_nome` (`nome`),  KEY `Index_rotulo` (`titulo`)) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
CREATE TABLE `periodo` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `semestre` varchar(50) NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `periodo`
--

/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;


--
-- Definition of table `permissao`
--

DROP TABLE IF EXISTS `permissao`;
CREATE TABLE `permissao` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `grupo_id` int(10) unsigned NOT NULL,  `funcao_id` int(10) unsigned NOT NULL,  PRIMARY KEY (`id`),  KEY `FK_permissao_grupo` (`grupo_id`),  KEY `FK_permissao_funcao` (`funcao_id`),  CONSTRAINT `FK_permissao_funcao` FOREIGN KEY (`funcao_id`) REFERENCES `funcao` (`id`),  CONSTRAINT `FK_permissao_grupo` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permissao`
--

/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;


--
-- Definition of table `roteiro`
--

DROP TABLE IF EXISTS `roteiro`;
CREATE TABLE `roteiro` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `periodo_id` int(10) unsigned NOT NULL,  `nome` varchar(255) NOT NULL,  `descricao` text, `data_liberacao` datetime DEFAULT NULL,  `data_final_entrega` datetime DEFAULT NULL,  `data_final_discussao` datetime DEFAULT NULL,  `numero_maximo_envios` int(10) unsigned DEFAULT NULL,  `penalidade_dias_atraso` decimal(10,2) DEFAULT NULL,  `porcentagem_testes_automaticos` decimal(10,2) DEFAULT NULL,  `tempo_limite_testes` int(10) unsigned DEFAULT NULL,  `diretorio_interface` varchar(255) DEFAULT NULL,  `diretorio_testes` varchar(255) DEFAULT NULL,  `numero_maximo_participantes` int(10) unsigned DEFAULT NULL,  `tipo_roteiro_id` int(10) unsigned DEFAULT NULL,  PRIMARY KEY (`id`),  KEY `roteiro_FKIndex1` (`periodo_id`),  KEY `FK_roteiro_tipo_roteiro` (`tipo_roteiro_id`),  CONSTRAINT `FK_roteiro_tipo_roteiro` FOREIGN KEY (`tipo_roteiro_id`) REFERENCES `tipo_roteiro` (`id`),  CONSTRAINT `roteiro_ibfk_1` FOREIGN KEY (`periodo_id`) REFERENCES `periodo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roteiro`
--

/*!40000 ALTER TABLE `roteiro` DISABLE KEYS */;
/*!40000 ALTER TABLE `roteiro` ENABLE KEYS */;


--
-- Definition of table `submissao`
--

DROP TABLE IF EXISTS `submissao`;
CREATE TABLE `submissao` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `url` text NOT NULL,  `estado` varchar(50) DEFAULT NULL,  `data_submissao` datetime NOT NULL,  `equipe_has_usuario_has_roteiro_id` int(10) unsigned NOT NULL,  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `submissao`
--

/*!40000 ALTER TABLE `submissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `submissao` ENABLE KEYS */;


--
-- Definition of table `tipo_roteiro`
--

DROP TABLE IF EXISTS `tipo_roteiro`;
CREATE TABLE `tipo_roteiro` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `nome` varchar(500) NOT NULL,  PRIMARY KEY (`id`) USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_roteiro`
--

/*!40000 ALTER TABLE `tipo_roteiro` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_roteiro` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,  `login` varchar(255) NOT NULL,  `nome` varchar(255) NOT NULL,  `senha` varchar(50) NOT NULL,  `email` varchar(255) DEFAULT NULL, `periodo_id` int(10) unsigned DEFAULT NULL,  PRIMARY KEY (`id`),  UNIQUE KEY `Index_login` (`login`),  KEY `FK_usuario_periodo` (`periodo_id`),  CONSTRAINT `FK_usuario_periodo` FOREIGN KEY (`periodo_id`) REFERENCES `periodo` (`id`)) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
