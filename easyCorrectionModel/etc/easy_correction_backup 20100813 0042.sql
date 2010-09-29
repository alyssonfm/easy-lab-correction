-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51


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
  `id` int(10) unsigned NOT NULL auto_increment,
  `submissao_id` int(10) unsigned NOT NULL,
  `nota_automatica` decimal(2,1) NOT NULL,
  `nota_correcao` decimal(2,1) NOT NULL,
  `resultado_execucao_testes` text,
  `penalidade` decimal(2,1) default NULL,
  `data_avaliacao` datetime NOT NULL,
  `usuario_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `submissao_id` (`submissao_id`),
  KEY `FK_avaliacao_usuario` (`usuario_id`),
  CONSTRAINT `FK_avaliacao_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `avaliacao_ibfk_1` FOREIGN KEY (`submissao_id`) REFERENCES `submissao` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `id` int(10) unsigned NOT NULL auto_increment,
  `roteiro_id` int(10) unsigned NOT NULL,
  `mensagem` text NOT NULL,
  `data_envio` datetime NOT NULL,
  `usuario_origem_id` int(10) unsigned NOT NULL,
  `usuario_destino_id` int(10) unsigned default NULL,
  `equipe_destino_id` int(10) unsigned default NULL,
  PRIMARY KEY  USING BTREE (`id`),
  KEY `chat_FKIndex1` (`roteiro_id`),
  KEY `FK_chat_usuario_origem` (`usuario_origem_id`),
  KEY `FK_chat_usuario_destino` (`usuario_destino_id`),
  KEY `FK_chat_equipe` (`equipe_destino_id`),
  CONSTRAINT `FK_chat_equipe` FOREIGN KEY (`equipe_destino_id`) REFERENCES `equipe` (`id`),
  CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`roteiro_id`) REFERENCES `roteiro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
  `id` int(10) unsigned NOT NULL auto_increment,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY  USING BTREE (`id`)
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
  PRIMARY KEY  USING BTREE (`equipe_id`,`usuario_id`,`roteiro_id`),
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
  `id` int(10) unsigned NOT NULL auto_increment,
  `nome` varchar(500) NOT NULL,
  `rotulo` varchar(500) NOT NULL,
  `menu_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
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
 (2,'Definir Permissão','defPerm',1),
 (4,'Agendamento de Roteiros','agendaRoteiros',2),
 (5,'Penalidades','penalidades',2),
 (6,'Atribuição de Roteiros','atribuicaoDeRoteiros',2),
 (7,'Visualizar Notas','notas',2),
 (8,'Avaliação de Roteiros','avaliacaoDeRoteiros',3),
 (9,'Submissão De Roteiros','submissaoDeRoteiros',4),
 (10,'Chat','chat',5);
/*!40000 ALTER TABLE `funcao` ENABLE KEYS */;


--
-- Definition of table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
CREATE TABLE `grupo` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY  (`id`),
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
  `id` int(10) unsigned NOT NULL auto_increment,
  `id_usuario` int(10) unsigned NOT NULL,
  `id_grupo` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_grupo_usuario_grupo` (`id_grupo`),
  KEY `FK_grupo_usuario_usuario` (`id_usuario`),
  CONSTRAINT `FK_grupo_usuario_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`),
  CONSTRAINT `FK_grupo_usuario_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo_usuario`
--

/*!40000 ALTER TABLE `grupo_usuario` DISABLE KEYS */;
INSERT INTO `grupo_usuario` (`id`,`id_usuario`,`id_grupo`) VALUES 
 (1,1,1),
 (2,2,1),
 (3,3,1),
 (4,4,2),
 (5,5,3),
 (6,6,2),
 (17,17,4),
 (18,1,4);
/*!40000 ALTER TABLE `grupo_usuario` ENABLE KEYS */;


--
-- Definition of table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `titulo` varchar(200) NOT NULL,
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `Index_nome` (`nome`),
  KEY `Index_rotulo` (`titulo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`,`titulo`,`nome`) VALUES 
 (1,'acesso','Controle de Acesso'),
 (2,'config','Configurações'),
 (3,'avaliacao','Avaliação'),
 (4,'submissao','Submissão'),
 (5,'discussao','Discussão');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
CREATE TABLE `periodo` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `semestre` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
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
  `id` int(10) unsigned NOT NULL auto_increment,
  `grupo_id` int(10) unsigned NOT NULL,
  `funcao_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_permissao_grupo` (`grupo_id`),
  KEY `FK_permissao_funcao` (`funcao_id`),
  CONSTRAINT `FK_permissao_funcao` FOREIGN KEY (`funcao_id`) REFERENCES `funcao` (`id`),
  CONSTRAINT `FK_permissao_grupo` FOREIGN KEY (`grupo_id`) REFERENCES `grupo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permissao`
--

/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` (`id`,`grupo_id`,`funcao_id`) VALUES 
 (2,1,2),
 (3,1,1),
 (5,1,4),
 (6,1,5),
 (7,1,6),
 (8,1,7),
 (9,1,8),
 (10,1,9),
 (11,1,10),
 (12,2,9),
 (13,2,10),
 (14,3,4),
 (15,3,5),
 (16,3,6),
 (17,3,7),
 (18,3,8),
 (19,3,10),
 (20,3,1),
 (21,3,2);
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;


--
-- Definition of table `roteiro`
--

DROP TABLE IF EXISTS `roteiro`;
CREATE TABLE `roteiro` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `periodo_id` int(10) unsigned NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` text,
  `data_liberacao` date NOT NULL,
  `data_final_entrega` date NOT NULL,
  `data_final_discussao` date NOT NULL,
  `numero_maximo_envios` int(10) unsigned NOT NULL,
  `penalidade_dia_atraso` decimal(2,1) NOT NULL,
  `porcentagem_testes_automaticos` decimal(2,1) NOT NULL,
  `tempo_limite_testes` int(10) unsigned NOT NULL,
  `diretorio_interface` varchar(255) default NULL,
  `diretorio_testes` varchar(255) default NULL,
  `versao_interface` varchar(50) default NULL,
  `versao_testes` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  KEY `roteiro_FKIndex1` (`periodo_id`),
  CONSTRAINT `roteiro_ibfk_1` FOREIGN KEY (`periodo_id`) REFERENCES `periodo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `id` int(10) unsigned NOT NULL auto_increment,
  `url` text NOT NULL,
  `estado` varchar(50) default NULL,
  `equipe_id` int(10) unsigned NOT NULL,
  `roteiro_id` int(10) unsigned NOT NULL,
  `usuario_id` int(10) unsigned NOT NULL,
  `data_submissao` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_submissao_equipe_has_usuario_has_roteiro` (`equipe_id`,`usuario_id`,`roteiro_id`),
  CONSTRAINT `FK_submissao_equipe_has_usuario_has_roteiro` FOREIGN KEY (`equipe_id`, `usuario_id`, `roteiro_id`) REFERENCES `equipe_has_usuario_has_roteiro` (`equipe_id`, `usuario_id`, `roteiro_id`)
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
  `id` int(10) unsigned NOT NULL auto_increment,
  `login` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `email` varchar(255) default NULL,
  `periodo_id` int(10) unsigned default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_login` (`login`),
  KEY `FK_usuario_periodo` (`periodo_id`),
  CONSTRAINT `FK_usuario_periodo` FOREIGN KEY (`periodo_id`) REFERENCES `periodo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`login`,`nome`,`senha`,`email`,`periodo_id`) VALUES 
 (1,'demas','Demas','d8f08986e8072e78bf9295c294ef3bc2','demetriogm@gmail.com',1),
 (2,'augusto','Augusto Macedo','202cb962ac59075b964b07152d234b70','',1),
 (3,'alysson','Alysson Filgueira','202cb962ac59075b964b07152d234b70','',1),
 (4,'20811007','Augusto Macedo','e10adc3949ba59abbe56e057f20f883e','augustoqmacedo@gmail.com',1),
 (5,'livia','Livia','202cb962ac59075b964b07152d234b70','liviagm@gmail.com',1),
 (6,'alyssonfm','Alysson Filgueira Milanez Alse','e10adc3949ba59abbe56e057f20f883e','alyssonfm@yahoo.com.br',1),
 (17,'demetriogm','Demetrio Gomes Mestre','e10adc3949ba59abbe56e057f20f883e','demetriogm@gmail.com',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
