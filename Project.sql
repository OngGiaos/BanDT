Create database [Assignment]
go
Use [Assignment]
go
--table [Phone]
Create  table [Phone](
	id int identity(1,1) primary key not null,
	creator nvarchar(50) not null,
	name nvarchar(50) not null,
	price nvarchar(100) not null,
	information nvarchar(max),
	img nvarchar(max) not null,
	sold int not null,
	quantity int not null,
	show bit not null
);
--insert phone here!

--insert phone here!
--table [User]
create table [User](
	name nvarchar(50) not null,
	email nvarchar(200) primary key not null,
	pass nvarchar(50) not null,
	[address] nvarchar(1000) not null,
	city nvarchar(50) not null,
	country nvarchar(50) not null,
	zip nvarchar(50) not null,
	phone nvarchar(50) not null
);

--table [Order] (Cart)
create table [Order](
	email nvarchar(200) foreign key references [User](email) not null,
	pid int foreign key references Phone(id) not null,
	quantity int not null
);
--table [Wishlist]
create table Wishlist(
	email nvarchar(200) foreign key references [User](email) not null,
	pid int foreign key references Phone(id) not null,
	quantity int not null
);

create table Sold(
	account nvarchar(200) not null,
	[date] date not null,
	pid int not null,
	pname nvarchar(max) not null,
	pprice nvarchar(max) not null,
	quantity int not null
);

create table History(
	name nvarchar(200) not null,
	email nvarchar(max) not null,
	[address] nvarchar(max) not null,
	city nvarchar(200) not null,
	country nvarchar(200) not null,
	zip nvarchar(200) not null,
	tel nvarchar(200) not null,
	note nvarchar(max) not null,
	[date] date not null,
	account nvarchar(200) foreign key references [User](email) not null
);