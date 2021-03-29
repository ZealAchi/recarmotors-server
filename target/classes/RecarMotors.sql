-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.7.24 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para recarmotors
DROP DATABASE IF EXISTS `recarmotors`;
CREATE DATABASE IF NOT EXISTS `recarmotors` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `recarmotors`;

-- Volcando estructura para tabla recarmotors.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(28) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` varchar(1) DEFAULT '0' COMMENT '是否有效  1有效  0无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1376231351926657026 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Tabla de roles de fondo ';

-- Volcando datos para la tabla recarmotors.role: ~1 rows (aproximadamente)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `role_name`, `create_time`, `update_time`, `is_delete`) VALUES
	(1170646492210294786, 'admin', '2021-03-28 11:12:24', '2021-03-28 11:12:24', '0');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Volcando estructura para tabla recarmotors.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(28) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '登录名称',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `role_id` bigint(28) DEFAULT '3' COMMENT '角色ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` varchar(1) DEFAULT '1' COMMENT '是否删除  1有效  0无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1170640705480060932 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='运营后台用户表';

-- Volcando datos para la tabla recarmotors.user: ~1 rows (aproximadamente)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `user_name`, `pass_word`, `real_name`, `role_id`, `create_time`, `update_time`, `is_delete`) VALUES
	(1170640705480060931, 'admin', '23', '23', 1170646492210294786, '2021-03-28 12:24:28', '2021-03-28 12:38:46', '1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
