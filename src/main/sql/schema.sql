--���ݿ��ʼ���ű�

--�������ݿ�
create database db_instancekill;
--ʹ�����ݿ�
use db_instanceKill;
--������ɱ����
create table t_instanceKill(
'instanceKill_id' bigint not null auto_increment comment '��Ʒ���ID',
'name' varchar(120) not null comment '��Ʒ����',
'number' int not null comment '�������',
'start_time' timestamp not null comment '��ɱ����ʱ��',
'end_time' timestamp not null comment '��ɱ����ʱ��',
'create_time' timestamp not null default current_timestamp comment '����ʱ��',
primary key(instanceKill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine=InnoDB auto_increment=1000 default charset=utf8 comment '��ɱ����';
--��ʼ������
insert into
	t_instanceKill(name,number,start_time,end_time)
values
	('1000Ԫ��ɱiPhone6s',100,'2016-05-15 00:00:00','2016-05-20 00:00:00'),
	('500Ԫ��ɱiPad2',200,'2016-05-15 00:00:00','2016-05-20 00:00:00'),
	('300Ԫ��ɱС��4',300,'2016-05-15 00:00:00','2016-05-20 00:00:00'),
	('200Ԫ��ɱ����note',400,'2016-05-15 00:00:00','2016-05-20 00:00:00');
	
--��ɱ�ɹ���ϸ��
--�û���¼��֤��Ϣ
create table t_successKilled(
instanceKill_id bigint not null comment '��ɱ��ƷID',

user_phone bigint not null comment '�û��ֻ���',
state tinyint not null default -1 comment '״̬��ʾ��-1:��Ч  0:�ɹ�  1:�Ѹ���  2:�ѷ���',
create_time timestamp not null default current_timestamp comment '����ʱ��',
primary key(instanceKill_id,user_phone),/*��������*/
key idx_create_time(create_time)
)engine=InnoDB default charset=utf8 comment '��ɱ�ɹ���ϸ��';