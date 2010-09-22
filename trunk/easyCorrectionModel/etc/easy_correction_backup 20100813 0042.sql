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

CREATE TABLE periodo(
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  semestre VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE usuario (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  login VARCHAR(255) NOT NULL,
  nome VARCHAR(255) NOT NULL,
  senha VARCHAR(50) NOT NULL,
  email VARCHAR(255) NULL,
  PRIMARY KEY(id),
  UNIQUE KEY `Index_login` (`login`)
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`,`login`,`nome`,`senha`,`email`) VALUES 
 (1,'demas','Demetrio Gomes Mestre','202cb962ac59075b964b07152d234b70',''),
 (2,'augusto','Augusto Macedo','202cb962ac59075b964b07152d234b70',''),
 (3,'alysson','Alysson Filgueira','202cb962ac59075b964b07152d234b70',''),
 (4,'20811007','Augusto Macedo','e10adc3949ba59abbe56e057f20f883e','augustoqmacedo@gmail.com'),
 (5,'livia','Livia','202cb962ac59075b964b07152d234b70','liviagm@gmail.com'),
 (6,'alyssonfm','Alysson Filgueira Milanez Alse','e10adc3949ba59abbe56e057f20f883e','alyssonfm@yahoo.com.br');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grupo`
--

/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` (`id`,`nome`) VALUES 
 (1,'Administrador'),
 (2,'Professor'),
 (3,'Monitor'),
 (4,'Aluno');

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
 (6,6,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

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
-- Dumping data for table `usuario`
--                                               

CREATE TABLE avaliacao (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  submissao_id INTEGER UNSIGNED NOT NULL,
  nota_automatica DECIMAL(2,1) NOT NULL,
  nota_correcao DECIMAL(2,1) NOT NULL,
  resultado_execucao_testes TEXT NULL,
  penalidade DECIMAL(2,1) NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(submissao_id)
    REFERENCES submissao(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE chat (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  roteiro_id INTEGER UNSIGNED NOT NULL,
  mensagem TEXT NOT NULL,
  data_envio DATETIME NOT NULL,
  usuario_Origem INTEGER UNSIGNED NOT NULL,
  usuario_Destino INTEGER UNSIGNED NULL,
  equipe_Destino INTEGER UNSIGNED NULL,
  PRIMARY KEY(id, roteiro_id),
  INDEX chat_FKIndex1(roteiro_id),
  FOREIGN KEY(roteiro_id)
    REFERENCES roteiro(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE equipe (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  roteiro_id INTEGER UNSIGNED NOT NULL,
  nome VARCHAR(50) NOT NULL,
  PRIMARY KEY(id, roteiro_id),
  INDEX equipe_FKIndex1(roteiro_id),
  FOREIGN KEY(roteiro_id)
    REFERENCES roteiro(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE equipe_has_usuario (
  equipe_id INTEGER UNSIGNED NOT NULL,
  usuario_id INTEGER UNSIGNED NOT NULL,
  equipe_roteiro_id INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(equipe_id, usuario_id, equipe_roteiro_id),
  INDEX equipe_has_usuario_FKIndex1(equipe_id, equipe_roteiro_id),
  INDEX equipe_has_usuario_FKIndex2(usuario_id),
  FOREIGN KEY(equipe_id, equipe_roteiro_id)
    REFERENCES equipe(id, roteiro_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(usuario_id)
    REFERENCES usuario(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


CREATE TABLE roteiro (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  periodo_id INTEGER UNSIGNED NOT NULL,
  nome VARCHAR(255) NOT NULL,
  descricao TEXT NULL,
  data_liberacao DATE NOT NULL,
  data_final_entrega DATE NOT NULL,
  data_final_discussao DATE NOT NULL,
  numero_maximo_envios INTEGER UNSIGNED NOT NULL,
  penalidade_dia_atraso DECIMAL(2,1) NOT NULL,
  porcentagem_testes_automaticos DECIMAL(2,1) NOT NULL,
  tempo_limite_testes INTEGER UNSIGNED NOT NULL,
  diretorio_interface VARCHAR(255) NULL,
  diretorio_testes VARCHAR(255) NULL,
  versao_interface VARCHAR(50) NULL,
  versao_testes VARCHAR(50) NULL,
  PRIMARY KEY(id),
  INDEX roteiro_FKIndex1(periodo_id),
  FOREIGN KEY(periodo_id)
    REFERENCES periodo(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE submissao (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  equipe_id INTEGER UNSIGNED NOT NULL,
  equipe_roteiro_id INTEGER UNSIGNED NOT NULL,
  url TEXT NOT NULL,
  estado VARCHAR(50) NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(equipe_id, equipe_roteiro_id)
    REFERENCES equipe(id, roteiro_id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;