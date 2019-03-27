
create database software_simple_class;

/*用户表*/
CREATE TABLE USER (
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

