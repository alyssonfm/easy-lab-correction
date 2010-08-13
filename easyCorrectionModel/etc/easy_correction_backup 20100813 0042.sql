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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcao`
--

/*!40000 ALTER TABLE `funcao` DISABLE KEYS */;
INSERT INTO `funcao` (`id`,`nome`,`rotulo`,`menu_id`) VALUES 
 (1,'Cadastros','acesso',1),
 (2,'Definir Permissão','defPerm',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo`
--

/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` (`id`,`nome`) VALUES 
 (1,'Administrador');
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
) ENGINE=InnoDB AUTO_INCREMENT=1373 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo_usuario`
--

/*!40000 ALTER TABLE `grupo_usuario` DISABLE KEYS */;
INSERT INTO `grupo_usuario` (`id`,`id_usuario`,`id_grupo`) VALUES 
 (1,1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`,`titulo`,`nome`) VALUES 
 (1,'acesso','Controle de Acesso');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permissao`
--

/*!40000 ALTER TABLE `permissao` DISABLE KEYS */;
INSERT INTO `permissao` (`id`,`grupo_id`,`funcao_id`) VALUES 
 (1,1,1),
 (2,1,2);
/*!40000 ALTER TABLE `permissao` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `login` varchar(100) NOT NULL,
  `nome` varchar(600) NOT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Index_login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=1211 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`login`,`nome`,`senha`) VALUES 
 (1,'demas','Demetrio Gomes Mestre','202cb962ac59075b964b07152d234b70');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
