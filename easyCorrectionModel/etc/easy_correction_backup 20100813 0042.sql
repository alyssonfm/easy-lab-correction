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
-- Create schema easy_correction
--

CREATE DATABASE IF NOT EXISTS easy_correction;
USE easy_correction;

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
  `data_avaliacao` datetime NOT NULL,
  `usuario_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `submissao_id` (`submissao_id`),
  KEY `FK_avaliacao_usuario` (`usuario_id`),
  CONSTRAINT `avaliacao_ibfk_1` FOREIGN KEY (`submissao_id`) REFERENCES `submissao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_avaliacao_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `avaliacao`
--

/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `equipe`
--

/*!40000 ALTER TABLE `equipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipe` ENABLE KEYS */;


--
-- Definition of table `equipe_has_usuario_has_roteiro`
--

DROP TABLE IF EXISTS `equipe_has_usuario_has_roteiro`;
CREATE TABLE `equipe_has_usuario_has_roteiro` (
  `equipe_id` int(10) unsigned NOT NULL,
  `usuario_id` int(10) unsigned NOT NULL,
  `roteiro_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`equipe_id`,`usuario_id`,`roteiro_id`) USING BTREE,
  KEY `FK_equipe_has_usuario_has_roteiro_usuario` (`usuario_id`),
  KEY `FK_equipe_has_usuario_has_roteiro_roteiro` (`roteiro_id`),
  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_equipe` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`),
  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_roteiro` FOREIGN KEY (`roteiro_id`) REFERENCES `roteiro` (`id`),
  CONSTRAINT `FK_equipe_has_usuario_has_roteiro_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `equipe_has_usuario_has_roteiro`
--

/*!40000 ALTER TABLE `equipe_has_usuario_has_roteiro` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcao`
--

/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` (`id`,`nome`,`rotulo`,`menu_id`) VALUES 
 (1,'Cadastros','acesso',1),
 (2,'Permiss�es','defPerm',1),
 (4,'Cria��o','agendaRoteiros',4),
 (5,'Atribuia��o de Atividades','atribuicaoDeRoteiros',3),
 (6,'Penalidades','penalidades',3),
 (7,'Visualizar Notas','notas',3),
 (8,'Submiss�o','submissaoDeRoteiros',4),
 (9,'Avalia��o de Roteiros','avaliacaoDeRoteiros',3),
 (10,'Discuss�o','chat',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo_usuario`
--

/*!40000 ALTER TABLE `grupo_usuario` DISABLE KEYS */;
INSERT INTO `grupo_usuario` (`id`,`id_usuario`,`id_grupo`) VALUES 
 (1,1,1),
 (39,33,1),
 (42,36,4),
 (43,37,1);
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
 (1,'2010.2');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permissao`
--

/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` (`id`,`grupo_id`,`funcao_id`) VALUES 
 (2,1,1),
 (3,1,2),
 (23,1,4),
 (24,1,5),
 (25,1,6),
 (26,1,7),
 (27,1,8),
 (28,1,9),
 (29,1,10);
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
  `data_liberacao` date NOT NULL,
  `data_final_entrega` date NOT NULL,
  `data_final_discussao` date NOT NULL,
  `numero_maximo_envios` int(10) unsigned NOT NULL,
  `penalidade_dias_atraso` decimal(10,2) NOT NULL,
  `porcentagem_testes_automaticos` decimal(10,2) NOT NULL,
  `tempo_limite_testes` int(10) unsigned NOT NULL,
  `diretorio_interface` varchar(255) DEFAULT NULL,
  `diretorio_testes` varchar(255) DEFAULT NULL,
  `numero_maximo_participantes` int(10) unsigned DEFAULT NULL,
  `bloqueado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roteiro_FKIndex1` (`periodo_id`),
  CONSTRAINT `roteiro_ibfk_1` FOREIGN KEY (`periodo_id`) REFERENCES `periodo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roteiro`
--

/*!40000 ALTER TABLE `roteiro` DISABLE KEYS */;
/*!40000 ALTER TABLE `roteiro` ENABLE KEYS */;


--
-- Definition of table `submissao`
--

DROP TABLE IF EXISTS `submissao`;
CREATE TABLE `submissao` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` text NOT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `equipe_id` int(10) unsigned NOT NULL,
  `roteiro_id` int(10) unsigned NOT NULL,
  `usuario_id` int(10) unsigned NOT NULL,
  `data_submissao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_submissao_equipe_has_usuario_has_roteiro` (`equipe_id`,`usuario_id`,`roteiro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Dumping data for table `submissao`
--

/*!40000 ALTER TABLE `submissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `submissao` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`login`,`nome`,`senha`,`email`,`periodo_id`) VALUES 
 (1,'demas','Augusto','202cb962ac59075b964b07152d234b70','demetriogm@gmail.com',NULL),
 (33,'augusto','Augusto','d9b1d7db4cd6e70935368a1efb10e377','demetriogm@gmail.com',NULL),
 (36,'demetriogm','Demetrio Gomes','5a656a920322c1aaf33410dc28967ae','demetriogm@gmail.com',NULL),
 (37,'alysson','alysson','d9b1d7db4cd6e70935368a1efb10e377','demetriogm@gmail.com',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
