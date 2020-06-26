
IF OBJECT_ID ('dbo.comm_task') IS NOT NULL
	DROP TABLE dbo.comm_task
GO

CREATE TABLE dbo.comm_task
	(
	id               INT IDENTITY NOT NULL,
	task_no          VARCHAR (50) NOT NULL,
	task_type        VARCHAR (50) NULL,
	task_subject     VARCHAR (200) NULL,
	task_content     TEXT NULL,
	system_name      VARCHAR (100) NULL,
	estimated_effort DECIMAL (12, 2) NULL,
	actual_effort    DECIMAL (12, 2) NULL,
	created_by       VARCHAR (150) NULL,
	assignee         VARCHAR (150) NULL,
	severity         VARCHAR (20) NULL,
	status           VARCHAR (20) NULL,
	attached_id      VARCHAR (20) NULL,
	remarks          VARCHAR (500) NULL,
	submission_date  DATE DEFAULT (getdate()) NOT NULL,
	timestamp        DATETIME DEFAULT (getdate()) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT task_no UNIQUE (task_no)
	)
GO

IF OBJECT_ID ('dbo.comm_task_his') IS NOT NULL
	DROP TABLE dbo.comm_task_his
GO

CREATE TABLE dbo.comm_task_his
	(
	id               INT IDENTITY NOT NULL,
	task_no          VARCHAR (50) NOT NULL,
	task_type        VARCHAR (50) NULL,
	task_subject     VARCHAR (200) NULL,
	task_content     TEXT NULL,
	system_name      VARCHAR (100) NULL,
	estimated_effort DECIMAL (12, 2) NULL,
	actual_effort    DECIMAL (12, 2) NULL,
	created_by       VARCHAR (150) NULL,
	assignee         VARCHAR (150) NULL,
	severity         VARCHAR (20) NULL,
	status           VARCHAR (20) NULL,
	attached_id      VARCHAR (20) NULL,
	remarks          VARCHAR (500) NULL,
	submission_date  DATE DEFAULT (getdate()) NOT NULL,
	timestamp        DATETIME DEFAULT (getdate()) NOT NULL,
	PRIMARY KEY (id)
	)
GO

