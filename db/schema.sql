/* Create DB if needed */
IF DB_ID('WIRMS') IS NULL
  CREATE DATABASE [WIRMS];
GO
USE [WIRMS];
GO

/* Drop in FK-safe order (subtables first), then recreate */
IF OBJECT_ID('dbo.MainTable', 'U')      IS NOT NULL DROP TABLE dbo.MainTable;
IF OBJECT_ID('dbo.NearMiss', 'U')       IS NOT NULL DROP TABLE dbo.NearMiss;
IF OBJECT_ID('dbo.PropertyDamage', 'U') IS NOT NULL DROP TABLE dbo.PropertyDamage;
IF OBJECT_ID('dbo.ProductDamage', 'U')  IS NOT NULL DROP TABLE dbo.ProductDamage;
IF OBJECT_ID('dbo.Injury', 'U')         IS NOT NULL DROP TABLE dbo.Injury;
IF OBJECT_ID('dbo.MainTable', 'U')      IS NOT NULL DROP TABLE dbo.MainTable;
IF OBJECT_ID('dbo.Employees', 'U')      IS NOT NULL DROP TABLE dbo.Employees;
IF OBJECT_ID('dbo.USERS', 'U')          IS NOT NULL DROP TABLE dbo.USERS;
GO

/* USERS */
CREATE TABLE dbo.USERS
(
    Username   VARCHAR(50) NOT NULL,
    [Password] VARCHAR(50) NOT NULL,
    CONSTRAINT UQ_USERS_Username UNIQUE (Username)
    );
GO

/* Employees */
CREATE TABLE dbo.Employees
(
    EmployeeID SMALLINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    FirstName  VARCHAR(25) NOT NULL,
    LastName   VARCHAR(25) NOT NULL,
    Department VARCHAR(50) NOT NULL
);
GO

/* Main incident table */
CREATE TABLE dbo.MainTable
(
    ReportID INT IDENTITY(100000,1) NOT NULL,
    Title VARCHAR(25) NOT NULL,
    EmployeeID SMALLINT NOT NULL,
    Date DATE NOT NULL,
    IncidentType VARCHAR(20) NOT NULL,
    [Description] VARCHAR(500) NOT NULL,
    ActionTaken VARCHAR (500),
    Status VARCHAR (20),
    CONSTRAINT PK_Main PRIMARY KEY (ReportID),
    CONSTRAINT FK_MainTable_Employee
        FOREIGN KEY (EmployeeID)
            REFERENCES dbo.Employees(EmployeeID)

    );
GO

/* Injury (1–1 with MainTable via ReportID) */
CREATE TABLE dbo.Injury
(
    InjuryID     INT NOT NULL,
    Hospitalized BIT NOT NULL,
    InjuryType   VARCHAR(100) NULL,
    CONSTRAINT PK_Injury PRIMARY KEY (InjuryID),
    CONSTRAINT FK_Injury_Main FOREIGN KEY (InjuryID)
        REFERENCES dbo.MainTable(ReportID)
);
GO

/* ProductDamage (1–1 with MainTable) */
CREATE TABLE dbo.ProductDamage
(
    ProductID      INT NOT NULL,
    ProductDamage  SMALLINT NOT NULL,
    CONSTRAINT PK_Product PRIMARY KEY (ProductID),
    CONSTRAINT FK_Product_Main FOREIGN KEY (ProductID)
        REFERENCES dbo.MainTable(ReportID)
);
GO

/* PropertyDamage (1–1 with MainTable) */
CREATE TABLE dbo.PropertyDamage
(
    PropertyID     INT NOT NULL,
    PropertyDamage INT NOT NULL,
    CONSTRAINT PK_Property PRIMARY KEY (PropertyID),
    CONSTRAINT FK_Property_Main FOREIGN KEY (PropertyID)
        REFERENCES dbo.MainTable(ReportID)
);
GO

/* NearMiss (1–1 with MainTable) */
CREATE TABLE dbo.NearMiss
(
    MissID INT NOT NULL,
    CONSTRAINT PK_Miss PRIMARY KEY (MissID),
    CONSTRAINT FK_Miss_Main FOREIGN KEY (MissID)
        REFERENCES dbo.MainTable(ReportID)
);
GO


CREATE TABLE dbo.Other
(
    OtherID INT NOT NULL,
    CONSTRAINT PK_Other PRIMARY KEY (OtherID),
    CONSTRAINT FK_Other_Main FOREIGN KEY (OtherID)
        REFERENCES dbo.MainTable(ReportID)
);
GO


