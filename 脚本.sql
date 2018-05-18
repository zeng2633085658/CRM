use CRM;
--部门表
create table Dep(
  depid	int identity(1001,1) primary key,--部门编号
  depname	varchar(20) not null,--部门名称
  chairman	varchar(20), --部门负责人
  description	varchar(50) --部门描述
);
go
--岗位表
create table Degrees(
    degreeid	int identity(1001,1) primary key, --主键
    degreename	varchar(20) --岗位名称
);
go
--用户表
create table Users(
  userid	int identity(1001,1) primary key, --主键
  username	varchar(20) not null, --用户名称（唯一）
  password	varchar(32) not null, --密码
  depid	int references Dep(depid), --部门编号（关联部门表主键）
  degreeid	int  references  Degrees(degreeid) ,    --岗位编号（关联岗位表主键）
  jobname	varchar(20)  not null,--职务
  managerType	int not null ,--管理员类别（0系统管理员，1部门经理，2员工）
  mobile	varchar(40) not null, --联系电话(用于找回密码必填)
  email	varchar(50), --邮箱
  qqcode	varchar(50), --QQ
  weixin	varchar(50), --微信
  cardno	varchar(50) not null, --身份证号码
  bankName	varchar(50), --开户银行
  bankCardNo	varchar(50), --银行账户
  joinDate	datetime not null, --入职日期
  workDate	datetime , --试用转正日期
  levelDate	datetime, --离职日期
  baseSalary	decimal(18,2), --基本工资
  degreeSalary	decimal(18,2), --岗位工资
  addr	varchar(100), --联系地址
  status	int not null, --用户状态（1=启用，0=禁用）
  remark	varchar(100) --说明

);
go
--单位表
create table Unit(
  unitId	int identity(1001,1) primary key, --主键
  unitName	varchar(20) not null--单位名称
);
go
--供应商表
create table Supplier(
  supplierId	int identity(1001,1) primary key, --"主键编码
  supplierName	varchar(20) not null ,--名称
  bankAccount	varchar(50) ,--银行账号
  bankName	varchar(50),--开户银行
  contact	varchar(50) not null,--联系人
  phone	varchar(50) not null, --电话
  addr	varchar(200) ,--地址
  remark	varchar(500) --描述
);
go

--Product(商品表)
create table Product(
  prodid	varchar(50)	primary key,--	主键
  prodname	varchar(20)	not null,	--商品名称（唯一）
  prodModel	varchar(20)		not null,--商品型号
  prodUnit	int	references Unit(unitId),	--	商品单位(关联单位表主键)
  prodStyle	varchar(50)	 not null,--		×产品规格
  prodCount	int	not null,	--库存数量
  inPrice	decimal(18,2)	not null,		--	进货价格
  salePrice	decimal(18,2)	not null	,	--	销售价格
  lowSalePrice	decimal(18,2)	 not null,		--×最低价格
  prodSerial	varchar(50)		,	--	商品序列号
  cdKey	varchar(50)			,--	商品CDKEY
  saleStatus	varchar(10) not null,				--已售，未售
  supplierId	int		REFERENCES Supplier(supplierId)	,--	产品供应商(关联供应商表)
  remark	varchar(500)		--		商品描述

);
go
--客户资料()
create table CustomerInfo(
  custId	int	identity(1001,1) primary key,--			客户编号
  custname	varchar(30)	  not null, --	客户名称
  custtype	varchar	(50)  not null ,--			行业
  bankAccount	varchar(50)	 not null,--	银行账号
  bankName	varchar	(50)	not null,--		开户银行
  Contact	varchar(50) not null, --			联系人
  Phone	varchar(50) not null,	--	电话
  TicketName	varchar(30) ,--				开票名称
  TicketAddr	varchar(50)		,--		开票地址
  TicketTel	varchar(20),	--		开票电话
  TaxNo	varchar(20)	, --	纳税登记号
  custState	varchar	(50)	, --			客户状态（新客户，成交客户）
  userid	int references Users(userid), --	,			业务员（关联用户表）
  source	varchar(100),--		信息来源
);
go

--合同表()
create table Contract(
  contract_id	int	identity(1001,1) primary key, --		主键
  contract_no	varchar(30)  not null,-- 		合同编号
  custId	int	references  CustomerInfo(custId),	--		客户编号（关联客户表）
  contract_time	datetime	 not null,	--	合同签订时间
  due_time	datetime	not null,--	合同到期时间
  total_money	decimal(18,2) not null,	--	合同金额
  deposit	varchar(30)		,--		合同有效期（年）
  pay_type	varchar(50) not null	,--		支付类别（按季度支付、按月支付、按半年支付，按年支付）
  status	varchar(20) not null	,--		合同状态（已签订，已付款，已完成，服务中）
  empid	int	references Users(userid)	,--		业务员（关联用户表）
  operator	int	references Users(userid)	,--		操作员（关联用户表）
  oprtime	datetime	not null	,--	操作时间
  contract_name	varchar(30)  not null,-- 		合同名称
  remark	varchar(500)		--		商品描述
  
);
go

--合同扫描附件表()
create table  ContractAttach(
  contractAttach_id	int identity(1001,1)  primary key,--			主键，标识列，自动生成
  contract_id	int	 REFERENCES Contract(contract_id),--		合同编号（关联合同表主键）
  Seq	int not null	 ,	--	排序号
  AttachFile	varchar(50) not null,	--附件名称
  UploadTime	datetime	not null,--	上传时间
  user_name	int references Users(userid) ,	--	操作员编号
  attachURL	varchar(100) not null --		附件地址
);
go
--订单表()
create table Orders(
  orderId	bigint primary key,--		订单编号
  custid	int	 references CustomerInfo(custId),--		客户编号（关联客户资料表）
  userid	int	 references Users(userid),	--	业务员（关联用户表id）
  orderType	varchar	(20) not null,	--	订单类别（采购入库，销售出库）
  orderStatus	varchar(20) not null,	--	订单状态（已出货，已收款，实施中）
  process	varchar	(50) not null,			--	进度
  totalMoney	decimal(18,2) not null,	--			订单金额
  oprtime	datetime	not null,	--		开票时间
  operator	varchar(30) not null,--		开票人
  remark	varchar(100),	--	描述
);
go
--订单明细表()
create table OrderDetail(
  DetailId	int	identity(1001,1) primary key,--		订单明细编号
  orderId	bigint references Orders(orderId), --		订单编号
  prodid	varchar	(50) not null,--		商品编号
  status	varchar(20) not null,--		销售类别（销售，赠送，配套）
  saleMoney	decimal(18,2) not null ,--					销售金额
  UnitId	int	references Unit(unitId),				--	单位
  regPerson	varchar(50) ,	--				注册联系人
  regPassword	varchar(50) ,--					注册密码
  servicePeriod	varchar	(50) ,--					服务期限
  expireDate	varchar(50), --					服务到期日期
  prodCount	int		not null,--		--		销售数量
  totalMoney	decimal(18,2) not null ,--					总金额
  oprtime	datetime not null ,--		开票时间
  operator	varchar(30) not null,--		开票人
  remark	varchar(100) --		描述
);
go
--派工单()
create table JobRecord(
  jobId	int	identity (1001,1) primary key, --	√	√	×		派工编号
  orderId	bigint references Orders(orderId) , --	×	×	×		订单号
  jobDate	datetime	not null	,--		×		日期
  prodid	varchar(50)	references Product(prodid),			--×		商品信息（关联商品表）
  custid	int		references CustomerInfo(custId)	,--	×		客户名称（关联客户表）
  jobContent	varchar(1000) not null, --	×	×	×		派工内容
  callback	varchar(3000) not null,--	×	×	×		派工完成情况
  userid	varchar(30)	not null , --×	×	×		员工信息
  custEval	varchar(100) not null,	--				客户评价
  custSign	varchar(10) not null 	, --×	×	×		客户签名(已签，未签)
  startTime	datetime	not null,	 --×	×	×		开工时间
  endTime	datetime	 not null	, --			结束时间
  workDay	int		 not  null, --			工作天数
  busMoney	decimal(18,2),		--				交通费
  attachMoney	decimal(18,2),		--				补助费
);
go
--商务洽谈表()
create table Business(
  businessId	int	 identity(1001,1) primary key,	--		商业洽谈编号
  busDate	datetime	not null ,--		日期
  prodid	varchar(50)	 not null,--		商品信息
  chatContent	varchar(2000) not null,--				洽谈内容
  chatResult	varchar(2000) not null ,--			洽谈情况
  custid	int	references CustomerInfo(custId),--			客户名称（关联客户表主键）
  custContact	varchar(30) not null,--			客户联系人
  phone	varchar(20) not null,--		客户电话
  userid	int	references	Users(userid),--		员工信息(关联用户表主键)
  module	varchar(200) not null ,--			报价模块
  moduleState	varchar(200) not null,--					报价情况
  moduleMoney	decimal(18,2)	not null,	--			报价金额
  remark	varchar(500) --					备注
);
go
--客户联系表()
create table Contact(
  contactId	int identity(1001,1) primary key, --		√	√	×		客户联系编号
  talkDate	datetime	not null,--	×	×	×		日期
  custContact	varchar(50) not null ,--			×		客户联系人
  phone1	varchar(20) not null	,--				手机1
  phone2	varchar(20) not null ,--					手机2
  custid int references CustomerInfo(custId), --					×		客户名称（关联客户表主键）
  qqCode	varchar(20) not null,--		QQ
  email	varchar(30) ,--					邮箱
  weixin	varchar(20),-- 					微信
  userid	int	references Users(userid) ,--			×		员工信息(关联用户表主键)
  birthday	varchar(200) not null, --					生日
  hobbit	varchar(200), --					喜爱
  jobName	varchar(50) , --					岗位
  remark	varchar(500) --					备注
);
go
--收款方式()
create table PaidType(
  paidtypeid	int	 identity(1001,1) primary key,--√	√	×		编号
  paidtypename	varchar(30) not null--	×	×	×		名称（POS刷卡，微信，支付宝，现金，转账）
);
go
--收款管理()
create table Finance(
  financeId	int	identity(1001,1) primary key ,--	√	√	×		编号
  orderId	bigint	references Orders(orderId)	,	--×		订单号（关联订单表）
  prodid		varchar(50)	 	references Product(prodid),--		×		产品名称
  paidtypeid	int references PaidType(paidtypeid) ,--	×	×	×		收款方式类别(关联付款方式表）
  remainMoney	decimal(18,2) not null, --			×		应收金额
  paidMoney	decimal(18,3) not null , --	×	×	×		收款金额
  orderMoney	decimal(18,3) not null	,--		×		订单金额
  paidPerson	varchar(20) not null,--	×	×			交款人
  inbank	varchar	(50)  not null,--	×	×			入账银行
  bankAccount	varchar	(30) not null, --	×	×			入账账号
  outbank	varchar(50) not null, --	×	×			出账银行
  warrant	varchar(30),--	×	×			相关凭证号
  paidTime	datetime	not null ,--	×	×	×		交款时间
  paidinTime	datetime	not null ,--	×	×			到账日期
  invalid	varchar(10) not null, -- 	×	×	×	有效	是否有效（有效，作废）
  userid	int references Users(userid)	, --	操作人（session取值，登录账号）
  oprtime	datetime default getdate(), --	录入时间
  oprType	varchar	(10) not null			--		操作类别（收款，付款）
  );
go
--(开票信息表)
create table Ticket(
  id	int identity(1001,1) primary key ,--	√	√	×	主键
  ticketDate	datetime	not null ,--		×	开票日期
  orderid	varchar(30) references Orders(orderId), --		×	订单号
  custid	int	references CustomerInfo(custId) ,--		×	客户编号（关联客户表）
  ticketMoney	decimal(18,2) not null ,--		×	开票金额
  ticketComp	varchar(30) not null,	--	×	出票公司
  userid	int		references Users(userid)	, --×	用户名称，（session用户名）
  oprtime	datetime	default getdate(), --		×	操作时间
  remark	varchar(1000)		--		备注

);
go
--WeeklyReport(工作周报表)
create table  WeeklyReport(
  id	int	 identity(1001,1) primary key, --	主键
  weekDate	datetime not null ,--	日期
  workContent	varchar(1000) not null,	 --		×	周工作内容
  workReview	varchar(1000)	not null,	--		周工作总结
  question	varchar(1000)		,		--存在问题
  warning	varchar(1000)			,--	急需解决问题
  weekPlan	varchar(1000)		,--	下周计划
  userid	int	references	Users(userid) ,--	操作人
  oprtime	datetime	default getdate(),	--	×	操作时间
  remark	varchar(1000),		--		备注
);
go