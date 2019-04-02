
create database software_simple_class;

/*用户表*/
CREATE TABLE user (
	id NVARCHAR(200) NOT NULL,				/*id*/
	userid NVARCHAR(200) NOT NULL,			/*人员代码*/
	PASSWORD NVARCHAR(200) NOT NULL,		/*密码*/
	USERNAME NVARCHAR(200) NOT NULL,		/*用户名*/
	age INT NOT NULL,						/*年龄*/
	userGender INT DEFAULT 1,				/*性别 男1 女0*/
	birthday DATETIME,						/*生日*/
	registerDay DATETIME,					/*注册日期*/
	identityNum NVARCHAR(200),				/*身份证*/
	birthPlace NVARCHAR(200),				/*籍贯*/
	address NVARCHAR(200),					/*地址*/
	phone NVARCHAR(200),					/*电话*/
	permission INT DEFAULT 0,  				/*权限 管理员1，普通员工0*/
	PRIMARY KEY(id)
);

/*物料表*/
CREATE TABLE materials (
	id NVARCHAR(200) NOT NULL,				/*物料ID*/
	materialsId NVARCHAR(200) NOT NULL,		/*物料代码*/
	materialsName NVARCHAR(200) NOT NULL,	/*物料名称*/
	model NVARCHAR(200) NOT NULL,			/*型号规格*/
	unit NVARCHAR(200) NOT NULL,			/*计量单位*/
	stockQuantity INT NOT NULL DEFAULT 0,	/*库存数量*/
	remarks NVARCHAR(5000),					/*物料备注*/
	createDate DATETIME,					/*档案创建日期*/
	isDelete INT NOT NULL DEFAULT 0,		/*是否删除 1删除 0未删除*/
	PRIMARY KEY(id)
);

/*进出仓主表*/
CREATE TABLE materialsSell (
	id NVARCHAR(200) NOT NULL,				/*物料ID*/
	date DATETIME,							/*创建日期*/
	userId NVARCHAR(200) NOT NULL,			/*操作人员代码*/
	remarks NVARCHAR(5000),					/*物料备注*/
	type INT NOT NULL DEFAULT 1,			/*进出仓类别 进:1,出:0*/
	PRIMARY KEY(id)
);

/*进出仓详情表*/
CREATE TABLE materialsSellDetail (
	id INT AUTO_INCREMENT,					/*ID*/
	materialsSellId NVARCHAR(200) NOT NULL,	/*进出仓单号*/
	materialsId NVARCHAR(200) NOT NULL,		/*物料id*/
	total INT NOT NULL,						/*进出仓数量*/
	PRIMARY KEY(id)
);

SELECT * FROM materialsSellDetail
SELECT * FROM materialsSell
