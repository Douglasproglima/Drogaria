-- MySQL dump 10.13  Distrib 5.6.28, for Win64 (x86_64)
--
-- Host: localhost    Database: drogaria
-- ------------------------------------------------------
-- Server version	5.6.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `caixa`
--

DROP TABLE IF EXISTS `caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caixa` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataAbertura` date NOT NULL,
  `dataFechamento` date DEFAULT NULL,
  `valorAbertura` decimal(6,2) NOT NULL,
  `codFuncionario` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `dataAbertura_UNIQUE` (`dataAbertura`),
  KEY `FK_CaixaXFuncionario` (`codFuncionario`),
  CONSTRAINT `FK_CaixaXFuncionario` FOREIGN KEY (`codFuncionario`) REFERENCES `funcionario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caixa`
--

LOCK TABLES `caixa` WRITE;
/*!40000 ALTER TABLE `caixa` DISABLE KEYS */;
INSERT INTO `caixa` VALUES (1,'2016-02-11',NULL,100.50,1),(2,'2016-04-07',NULL,120.00,1);
/*!40000 ALTER TABLE `caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `codEstado` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_EstadoXCidade` (`codEstado`),
  CONSTRAINT `FK_EstadoXCidade` FOREIGN KEY (`codEstado`) REFERENCES `estado` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'Itaúna',1),(2,'Divinopolis',1),(3,'Mateus Leme',1),(4,'Ourinhos',2),(5,'Americana',2),(6,'Passa Tempo',2),(7,'Belo Horizonte',1),(10,'Vila Velha',4),(11,'Para de Minas',1),(12,'Bauru',2);
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `dataCadastro` date NOT NULL,
  `codPessoa` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ClienteXPessoa` (`codPessoa`),
  CONSTRAINT `FK_ClienteXPessoa` FOREIGN KEY (`codPessoa`) REFERENCES `pessoa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'','2015-01-06',1),(2,'','2015-01-07',2),(3,'\0','2015-01-04',3),(5,'','2015-01-03',4);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `sigla` varchar(2) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UK_rqbyd5fl82quobtqh1sggbqda` (`nome`),
  UNIQUE KEY `UK_4oeovp4svvpd1e4xg3eycsc48` (`sigla`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'Minas Gerais','MG'),(2,'São Paulo','SP'),(4,'ESPIRITO SANTO','ES'),(5,'RIO GRANDE DO SUL','RS'),(6,'RIO DE JANEIRO','RJ');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabricante`
--

DROP TABLE IF EXISTS `fabricante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabricante` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabricante`
--

LOCK TABLES `fabricante` WRITE;
/*!40000 ALTER TABLE `fabricante` DISABLE KEYS */;
INSERT INTO `fabricante` VALUES (1,'CASA PRONTA'),(2,'FARCOM'),(5,'FARMAITA'),(6,'FARMASOL'),(7,'FABRICANTE TESTE MERGE 002'),(8,'COOPERITA'),(14,'FabricanteTeste');
/*!40000 ALTER TABLE `fabricante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `carteiraTrabalho` varchar(15) NOT NULL,
  `dataAdmissao` date NOT NULL,
  `codPessoa` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_FuncionarioXPessoa` (`codPessoa`),
  CONSTRAINT `FK_FuncionarioXPessoa` FOREIGN KEY (`codPessoa`) REFERENCES `pessoa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'','123456789','2015-12-01',1),(2,'','65543221','2015-01-01',2),(3,'','654897147','1998-01-01',4),(4,'','MG326541','2016-01-27',6);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataHorario` datetime NOT NULL,
  `observacoes` varchar(1024) NOT NULL,
  `codProduto` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ProdutoXHistorico` (`codProduto`),
  CONSTRAINT `FK_ProdutoXHistorico` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
INSERT INTO `historico` VALUES (6,'2016-02-10 14:58:40','TESTE, TESTE, TESTE, TESTE, TESTE, TESTE, TESTE',1);
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemvenda`
--

DROP TABLE IF EXISTS `itemvenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemvenda` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `qtde` smallint(6) NOT NULL,
  `valorParcial` decimal(7,2) NOT NULL,
  `codProduto` bigint(20) NOT NULL,
  `codVenda` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ItemVendaXProduto` (`codProduto`),
  KEY `FK_ItemVendaXVenda` (`codVenda`),
  CONSTRAINT `FK_ItemVendaXProduto` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codigo`),
  CONSTRAINT `FK_ItemVendaXVenda` FOREIGN KEY (`codVenda`) REFERENCES `venda` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemvenda`
--

LOCK TABLES `itemvenda` WRITE;
/*!40000 ALTER TABLE `itemvenda` DISABLE KEYS */;
INSERT INTO `itemvenda` VALUES (14,2,7.10,1,8),(15,8,388.40,4,8),(16,3,15.00,5,8),(17,20,971.00,4,9),(18,15,75.00,5,9),(19,9,58.95,3,9),(20,6,17.40,7,9),(21,4,10.20,10,9),(22,3,27.00,6,9),(23,3,10.65,1,9),(24,1,3.55,1,10),(25,1,48.21,4,10),(26,2,7.10,1,11),(27,9,26.10,7,12),(28,2,10.00,5,13),(29,1,48.21,4,13),(30,1,3.55,1,13),(31,2,7.10,1,14),(32,1,5.00,5,14),(33,9,433.89,4,14),(34,4,26.20,3,14),(35,1,3.55,1,15),(36,1,48.21,4,15),(37,1,5.00,5,15),(38,1,2.90,7,15),(39,1,4.55,13,15),(40,1,2.55,10,15),(41,1,5.00,5,16),(42,1,48.21,4,16);
/*!40000 ALTER TABLE `itemvenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pessoa` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(50) NOT NULL,
  `celular` varchar(14) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `complemento` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `email` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `numero` smallint(6) NOT NULL,
  `rg` varchar(12) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `telefone` varchar(13) NOT NULL,
  `codCidade` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_PessoaXCidade` (`codCidade`),
  CONSTRAINT `FK_PessoaXCidade` FOREIGN KEY (`codCidade`) REFERENCES `cidade` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Santanense','','35680-440','Casa','076.392.316-80','douglas@hotmail.com','douglas.fernando',502,'','Governador Mgalhães Pinto','(37)9903-1879',1),(2,'Morada Nova','3779071879','35680-335','Casa','02639231620','rafaela@hotmail.com','rafaela.lima',114,'MG-17593416','Juvenal Contagem Vilaça','3779071879',1),(3,'Arrudas','','45680-545','Apartamento 441','016.392.322-20','devops@hotmail.com','DevOps',211,'11.111.212-1','Vila da Pedra','(13)7907-1879',5),(4,'Vila do Mar','1579071879','21680-455','Apartamento 32','00000000000','update@hotmail.com','Pessoa alterada',532,'ES-00000000','Vila dos outros','1579071879',2),(6,'São Gerado','(37)99653-2212','35680-440','Casa','075.654.987-96','augusto@hotmail.com','Augusto Da Silva',101,'32.145.445-4','Rua das Flores','(37)9865-3221',1),(7,'São Geraldo','37 9965 3221','35680000','Casa','08563932185','lucas@hotmail.com','Lucas Alves',124,'MG-655432154','Das Flores','37 3241 6554',1),(8,'Santanense','(37)99999-3221','356810000','Apartamento 102','076.392.316-80','cris@hotmail.com','cris',503,'11.111.111-1','Av. Governador','(37)3241-6554',1),(10,'Novo Bairro','(37)98656-5656','35680-000','','085.986.554-21','joao@hotmail.com','joao',65,'12.326.548-7','Flores','(37)9865-2132',7);
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(150) NOT NULL,
  `qtde` smallint(6) NOT NULL,
  `valorCompra` decimal(6,2) NOT NULL,
  `valorVenda` decimal(6,2) NOT NULL,
  `codFabricante` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ProdutoXFabricante` (`codFabricante`),
  CONSTRAINT `FK_ProdutoXFabricante` FOREIGN KEY (`codFabricante`) REFERENCES `fabricante` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'EPACOL',23,2.55,3.55,1),(3,'REVORIN',46,5.55,6.55,2),(4,'ETILNALENO',12,45.56,48.21,2),(5,'FALGIS',25,3.90,5.00,5),(6,'PENATOL',45,7.90,9.50,5),(7,'MINOXIDIL',20,1.90,2.90,1),(10,'FILTEX',22,2.55,2.55,8),(13,'IMAGE',11,2.55,4.55,1);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `codPessoa` bigint(20) NOT NULL,
  `tipo` char(1) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_UsuarioXPessoa` (`codPessoa`),
  CONSTRAINT `FK_UsuarioXPessoa` FOREIGN KEY (`codPessoa`) REFERENCES `pessoa` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'douglas.fernando','','3d9188577cc9bfe9291ac66b5cc872b7',1,'A'),(2,'rafaela.lima','','3d9188577cc9bfe9291ac66b5cc872b7',2,'A'),(4,'joao.lima','','3d9188577cc9bfe9291ac66b5cc872b7',10,'A');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `horario` datetime NOT NULL,
  `valorTotal` decimal(8,2) NOT NULL,
  `codCliente` bigint(20) DEFAULT NULL,
  `codFuncionario` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_VendaXCliente` (`codCliente`),
  KEY `FK_VendaXFuncionario` (`codFuncionario`),
  CONSTRAINT `FK_VendaXCliente` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codigo`),
  CONSTRAINT `FK_VendaXFuncionario` FOREIGN KEY (`codFuncionario`) REFERENCES `funcionario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (8,'2016-02-01 20:24:30',410.50,2,1),(9,'2016-02-01 20:36:18',1170.20,3,1),(10,'2016-02-11 18:34:58',51.76,3,4),(11,'2016-02-11 18:35:23',7.10,3,4),(12,'2016-02-11 19:04:10',26.10,1,2),(13,'2016-02-22 15:25:17',61.76,1,4),(14,'2016-04-09 18:54:51',472.19,3,4),(15,'2016-05-17 20:41:34',66.76,2,1),(16,'2016-06-25 12:06:56',53.21,1,4);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-30 20:09:40
