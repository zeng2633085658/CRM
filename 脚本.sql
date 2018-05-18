use CRM;
--���ű�
create table Dep(
  depid	int identity(1001,1) primary key,--���ű��
  depname	varchar(20) not null,--��������
  chairman	varchar(20), --���Ÿ�����
  description	varchar(50) --��������
);
go
--��λ��
create table Degrees(
    degreeid	int identity(1001,1) primary key, --����
    degreename	varchar(20) --��λ����
);
go
--�û���
create table Users(
  userid	int identity(1001,1) primary key, --����
  username	varchar(20) not null, --�û����ƣ�Ψһ��
  password	varchar(32) not null, --����
  depid	int references Dep(depid), --���ű�ţ��������ű�������
  degreeid	int  references  Degrees(degreeid) ,    --��λ��ţ�������λ��������
  jobname	varchar(20)  not null,--ְ��
  managerType	int not null ,--����Ա���0ϵͳ����Ա��1���ž���2Ա����
  mobile	varchar(40) not null, --��ϵ�绰(�����һ��������)
  email	varchar(50), --����
  qqcode	varchar(50), --QQ
  weixin	varchar(50), --΢��
  cardno	varchar(50) not null, --���֤����
  bankName	varchar(50), --��������
  bankCardNo	varchar(50), --�����˻�
  joinDate	datetime not null, --��ְ����
  workDate	datetime , --����ת������
  levelDate	datetime, --��ְ����
  baseSalary	decimal(18,2), --��������
  degreeSalary	decimal(18,2), --��λ����
  addr	varchar(100), --��ϵ��ַ
  status	int not null, --�û�״̬��1=���ã�0=���ã�
  remark	varchar(100) --˵��

);
go
--��λ��
create table Unit(
  unitId	int identity(1001,1) primary key, --����
  unitName	varchar(20) not null--��λ����
);
go
--��Ӧ�̱�
create table Supplier(
  supplierId	int identity(1001,1) primary key, --"��������
  supplierName	varchar(20) not null ,--����
  bankAccount	varchar(50) ,--�����˺�
  bankName	varchar(50),--��������
  contact	varchar(50) not null,--��ϵ��
  phone	varchar(50) not null, --�绰
  addr	varchar(200) ,--��ַ
  remark	varchar(500) --����
);
go

--Product(��Ʒ��)
create table Product(
  prodid	varchar(50)	primary key,--	����
  prodname	varchar(20)	not null,	--��Ʒ���ƣ�Ψһ��
  prodModel	varchar(20)		not null,--��Ʒ�ͺ�
  prodUnit	int	references Unit(unitId),	--	��Ʒ��λ(������λ������)
  prodStyle	varchar(50)	 not null,--		����Ʒ���
  prodCount	int	not null,	--�������
  inPrice	decimal(18,2)	not null,		--	�����۸�
  salePrice	decimal(18,2)	not null	,	--	���ۼ۸�
  lowSalePrice	decimal(18,2)	 not null,		--����ͼ۸�
  prodSerial	varchar(50)		,	--	��Ʒ���к�
  cdKey	varchar(50)			,--	��ƷCDKEY
  saleStatus	varchar(10) not null,				--���ۣ�δ��
  supplierId	int		REFERENCES Supplier(supplierId)	,--	��Ʒ��Ӧ��(������Ӧ�̱�)
  remark	varchar(500)		--		��Ʒ����

);
go
--�ͻ�����()
create table CustomerInfo(
  custId	int	identity(1001,1) primary key,--			�ͻ����
  custname	varchar(30)	  not null, --	�ͻ�����
  custtype	varchar	(50)  not null ,--			��ҵ
  bankAccount	varchar(50)	 not null,--	�����˺�
  bankName	varchar	(50)	not null,--		��������
  Contact	varchar(50) not null, --			��ϵ��
  Phone	varchar(50) not null,	--	�绰
  TicketName	varchar(30) ,--				��Ʊ����
  TicketAddr	varchar(50)		,--		��Ʊ��ַ
  TicketTel	varchar(20),	--		��Ʊ�绰
  TaxNo	varchar(20)	, --	��˰�ǼǺ�
  custState	varchar	(50)	, --			�ͻ�״̬���¿ͻ����ɽ��ͻ���
  userid	int references Users(userid), --	,			ҵ��Ա�������û���
  source	varchar(100),--		��Ϣ��Դ
);
go

--��ͬ��()
create table Contract(
  contract_id	int	identity(1001,1) primary key, --		����
  contract_no	varchar(30)  not null,-- 		��ͬ���
  custId	int	references  CustomerInfo(custId),	--		�ͻ���ţ������ͻ���
  contract_time	datetime	 not null,	--	��ͬǩ��ʱ��
  due_time	datetime	not null,--	��ͬ����ʱ��
  total_money	decimal(18,2) not null,	--	��ͬ���
  deposit	varchar(30)		,--		��ͬ��Ч�ڣ��꣩
  pay_type	varchar(50) not null	,--		֧����𣨰�����֧��������֧����������֧��������֧����
  status	varchar(20) not null	,--		��ͬ״̬����ǩ�����Ѹ������ɣ������У�
  empid	int	references Users(userid)	,--		ҵ��Ա�������û���
  operator	int	references Users(userid)	,--		����Ա�������û���
  oprtime	datetime	not null	,--	����ʱ��
  contract_name	varchar(30)  not null,-- 		��ͬ����
  remark	varchar(500)		--		��Ʒ����
  
);
go

--��ͬɨ�踽����()
create table  ContractAttach(
  contractAttach_id	int identity(1001,1)  primary key,--			��������ʶ�У��Զ�����
  contract_id	int	 REFERENCES Contract(contract_id),--		��ͬ��ţ�������ͬ��������
  Seq	int not null	 ,	--	�����
  AttachFile	varchar(50) not null,	--��������
  UploadTime	datetime	not null,--	�ϴ�ʱ��
  user_name	int references Users(userid) ,	--	����Ա���
  attachURL	varchar(100) not null --		������ַ
);
go
--������()
create table Orders(
  orderId	bigint primary key,--		�������
  custid	int	 references CustomerInfo(custId),--		�ͻ���ţ������ͻ����ϱ�
  userid	int	 references Users(userid),	--	ҵ��Ա�������û���id��
  orderType	varchar	(20) not null,	--	������𣨲ɹ���⣬���۳��⣩
  orderStatus	varchar(20) not null,	--	����״̬���ѳ��������տʵʩ�У�
  process	varchar	(50) not null,			--	����
  totalMoney	decimal(18,2) not null,	--			�������
  oprtime	datetime	not null,	--		��Ʊʱ��
  operator	varchar(30) not null,--		��Ʊ��
  remark	varchar(100),	--	����
);
go
--������ϸ��()
create table OrderDetail(
  DetailId	int	identity(1001,1) primary key,--		������ϸ���
  orderId	bigint references Orders(orderId), --		�������
  prodid	varchar	(50) not null,--		��Ʒ���
  status	varchar(20) not null,--		����������ۣ����ͣ����ף�
  saleMoney	decimal(18,2) not null ,--					���۽��
  UnitId	int	references Unit(unitId),				--	��λ
  regPerson	varchar(50) ,	--				ע����ϵ��
  regPassword	varchar(50) ,--					ע������
  servicePeriod	varchar	(50) ,--					��������
  expireDate	varchar(50), --					����������
  prodCount	int		not null,--		--		��������
  totalMoney	decimal(18,2) not null ,--					�ܽ��
  oprtime	datetime not null ,--		��Ʊʱ��
  operator	varchar(30) not null,--		��Ʊ��
  remark	varchar(100) --		����
);
go
--�ɹ���()
create table JobRecord(
  jobId	int	identity (1001,1) primary key, --	��	��	��		�ɹ����
  orderId	bigint references Orders(orderId) , --	��	��	��		������
  jobDate	datetime	not null	,--		��		����
  prodid	varchar(50)	references Product(prodid),			--��		��Ʒ��Ϣ��������Ʒ��
  custid	int		references CustomerInfo(custId)	,--	��		�ͻ����ƣ������ͻ���
  jobContent	varchar(1000) not null, --	��	��	��		�ɹ�����
  callback	varchar(3000) not null,--	��	��	��		�ɹ�������
  userid	varchar(30)	not null , --��	��	��		Ա����Ϣ
  custEval	varchar(100) not null,	--				�ͻ�����
  custSign	varchar(10) not null 	, --��	��	��		�ͻ�ǩ��(��ǩ��δǩ)
  startTime	datetime	not null,	 --��	��	��		����ʱ��
  endTime	datetime	 not null	, --			����ʱ��
  workDay	int		 not  null, --			��������
  busMoney	decimal(18,2),		--				��ͨ��
  attachMoney	decimal(18,2),		--				������
);
go
--����Ǣ̸��()
create table Business(
  businessId	int	 identity(1001,1) primary key,	--		��ҵǢ̸���
  busDate	datetime	not null ,--		����
  prodid	varchar(50)	 not null,--		��Ʒ��Ϣ
  chatContent	varchar(2000) not null,--				Ǣ̸����
  chatResult	varchar(2000) not null ,--			Ǣ̸���
  custid	int	references CustomerInfo(custId),--			�ͻ����ƣ������ͻ���������
  custContact	varchar(30) not null,--			�ͻ���ϵ��
  phone	varchar(20) not null,--		�ͻ��绰
  userid	int	references	Users(userid),--		Ա����Ϣ(�����û�������)
  module	varchar(200) not null ,--			����ģ��
  moduleState	varchar(200) not null,--					�������
  moduleMoney	decimal(18,2)	not null,	--			���۽��
  remark	varchar(500) --					��ע
);
go
--�ͻ���ϵ��()
create table Contact(
  contactId	int identity(1001,1) primary key, --		��	��	��		�ͻ���ϵ���
  talkDate	datetime	not null,--	��	��	��		����
  custContact	varchar(50) not null ,--			��		�ͻ���ϵ��
  phone1	varchar(20) not null	,--				�ֻ�1
  phone2	varchar(20) not null ,--					�ֻ�2
  custid int references CustomerInfo(custId), --					��		�ͻ����ƣ������ͻ���������
  qqCode	varchar(20) not null,--		QQ
  email	varchar(30) ,--					����
  weixin	varchar(20),-- 					΢��
  userid	int	references Users(userid) ,--			��		Ա����Ϣ(�����û�������)
  birthday	varchar(200) not null, --					����
  hobbit	varchar(200), --					ϲ��
  jobName	varchar(50) , --					��λ
  remark	varchar(500) --					��ע
);
go
--�տʽ()
create table PaidType(
  paidtypeid	int	 identity(1001,1) primary key,--��	��	��		���
  paidtypename	varchar(30) not null--	��	��	��		���ƣ�POSˢ����΢�ţ�֧�������ֽ�ת�ˣ�
);
go
--�տ����()
create table Finance(
  financeId	int	identity(1001,1) primary key ,--	��	��	��		���
  orderId	bigint	references Orders(orderId)	,	--��		�����ţ�����������
  prodid		varchar(50)	 	references Product(prodid),--		��		��Ʒ����
  paidtypeid	int references PaidType(paidtypeid) ,--	��	��	��		�տʽ���(�������ʽ��
  remainMoney	decimal(18,2) not null, --			��		Ӧ�ս��
  paidMoney	decimal(18,3) not null , --	��	��	��		�տ���
  orderMoney	decimal(18,3) not null	,--		��		�������
  paidPerson	varchar(20) not null,--	��	��			������
  inbank	varchar	(50)  not null,--	��	��			��������
  bankAccount	varchar	(30) not null, --	��	��			�����˺�
  outbank	varchar(50) not null, --	��	��			��������
  warrant	varchar(30),--	��	��			���ƾ֤��
  paidTime	datetime	not null ,--	��	��	��		����ʱ��
  paidinTime	datetime	not null ,--	��	��			��������
  invalid	varchar(10) not null, -- 	��	��	��	��Ч	�Ƿ���Ч����Ч�����ϣ�
  userid	int references Users(userid)	, --	�����ˣ�sessionȡֵ����¼�˺ţ�
  oprtime	datetime default getdate(), --	¼��ʱ��
  oprType	varchar	(10) not null			--		��������տ���
  );
go
--(��Ʊ��Ϣ��)
create table Ticket(
  id	int identity(1001,1) primary key ,--	��	��	��	����
  ticketDate	datetime	not null ,--		��	��Ʊ����
  orderid	varchar(30) references Orders(orderId), --		��	������
  custid	int	references CustomerInfo(custId) ,--		��	�ͻ���ţ������ͻ���
  ticketMoney	decimal(18,2) not null ,--		��	��Ʊ���
  ticketComp	varchar(30) not null,	--	��	��Ʊ��˾
  userid	int		references Users(userid)	, --��	�û����ƣ���session�û�����
  oprtime	datetime	default getdate(), --		��	����ʱ��
  remark	varchar(1000)		--		��ע

);
go
--WeeklyReport(�����ܱ���)
create table  WeeklyReport(
  id	int	 identity(1001,1) primary key, --	����
  weekDate	datetime not null ,--	����
  workContent	varchar(1000) not null,	 --		��	�ܹ�������
  workReview	varchar(1000)	not null,	--		�ܹ����ܽ�
  question	varchar(1000)		,		--��������
  warning	varchar(1000)			,--	����������
  weekPlan	varchar(1000)		,--	���ܼƻ�
  userid	int	references	Users(userid) ,--	������
  oprtime	datetime	default getdate(),	--	��	����ʱ��
  remark	varchar(1000),		--		��ע
);
go