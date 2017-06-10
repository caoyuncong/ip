DROP DATABASE IF EXISTS db_ip;
CREATE DATABASE db_ip;

DROP TABLE IF EXISTS db_ip.ip;
CREATE TABLE db_ip.ip (
  id  INT AUTO_INCREMENT PRIMARY KEY
  COMMENT 'ID PK',
  min VARCHAR(255) COMMENT '开始IP',
  max VARCHAR(255) COMMENT '终止IP',
  geo VARCHAR(255) COMMENT '地理位置'
)
  COMMENT 'ip 表';

SELECT *
FROM db_ip.ip;

TRUNCATE TABLE db_ip.ip;