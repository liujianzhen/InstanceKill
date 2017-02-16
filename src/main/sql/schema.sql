--数据库初始化脚本

--创建数据库
create database db_instancekill;
--使用数据库
use db_instanceKill;
--创建秒杀库存表
create table t_instanceKill(
'instanceKill_id' bigint not null auto_increment comment '商品库存ID',
'name' varchar(120) not null comment '商品名称',
'number' int not null comment '库存数量',
'start_time' timestamp not null comment '秒杀开启时间',
'end_time' timestamp not null comment '秒杀结束时间',
'create_time' timestamp not null default current_timestamp comment '创建时间',
primary key(instanceKill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine=InnoDB auto_increment=1000 default charset=utf8 comment '秒杀库存表';
--初始化数据
insert into
	t_instanceKill(name,number,start_time,end_time)
values
	('1000元秒杀iPhone6s',100,'2016-05-15 00:00:00','2016-05-20 00:00:00'),
	('500元秒杀iPad2',200,'2016-05-15 00:00:00','2016-05-20 00:00:00'),
	('300元秒杀小米4',300,'2016-05-15 00:00:00','2016-05-20 00:00:00'),
	('200元秒杀红米note',400,'2016-05-15 00:00:00','2016-05-20 00:00:00');
	
--秒杀成功明细表
--用户登录认证信息
create table t_successKilled(
instanceKill_id bigint not null comment '秒杀商品ID',

user_phone bigint not null comment '用户手机号',
state tinyint not null default -1 comment '状态标示：-1:无效  0:成功  1:已付款  2:已发货',
create_time timestamp not null default current_timestamp comment '创建时间',
primary key(instanceKill_id,user_phone),/*联合主键*/
key idx_create_time(create_time)
)engine=InnoDB default charset=utf8 comment '秒杀成功明细表';