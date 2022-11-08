USE huellaschile;

drop table if exists CONSULTATION;

drop table if exists PET;

drop table if exists USER;

drop table if exists VET;

drop table if exists VETERINARY;

/*==============================================================*/
CREATE TABLE CONSULTATION (
    ID_CONSULTATION VARCHAR(50) NOT NULL,
    ID_USER VARCHAR(11) NOT NULL,
    NAME_PET VARCHAR(50) NOT NULL,
    NATIONAL_REGISTER VARCHAR(50) NOT NULL,
    STATUS VARCHAR(11) NOT NULL,
    PRICE INT NOT NULL,
    METHOD_PAYMENT VARCHAR(50) NOT NULL,
    PROCESS VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID_CONSULTATION)
);

/*==============================================================*/
CREATE TABLE PET (
    ID_USER VARCHAR(11) NOT NULL,
    NAME_PET VARCHAR(50) NOT NULL,
    RACE VARCHAR(50) NOT NULL,
    TREATMENT_NAME VARCHAR(50) NOT NULL,
    TREATMENT_ACTIVE BOOLEAN NOT NULL,
    PRIMARY KEY (ID_USER , NAME_PET)
);

/*==============================================================*/
CREATE TABLE USER (
    ID_USER VARCHAR(11) NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    ADDRESS VARCHAR(150) NOT NULL,
    CITY VARCHAR(50) NOT NULL,
    COUNTRY VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID_USER)
);

/*==============================================================*/
CREATE TABLE VET (
    NATIONAL_REGISTER VARCHAR(50) NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    CERTIFIED BOOLEAN NOT NULL,
    PROFESSIONAL_LICENSE_NUMBER VARCHAR(50) NOT NULL,
    PRIMARY KEY (NATIONAL_REGISTER)
);

/*==============================================================*/
CREATE TABLE VETERINARY (
    PROFESSIONAL_LICENSE_NUMBER VARCHAR(50) NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    PRIMARY KEY (PROFESSIONAL_LICENSE_NUMBER)
);

alter table CONSULTATION add constraint FK_REFERENCE_4 foreign key (ID_USER, NAME_PET)
      references PET (ID_USER, NAME_PET) on delete restrict on update restrict;

alter table CONSULTATION add constraint FK_REFERENCE_5 foreign key (NATIONAL_REGISTER)
      references VET (NATIONAL_REGISTER) on delete restrict on update restrict;

alter table PET add constraint FK_REFERENCE_2 foreign key (ID_USER)
      references USER (ID_USER) on delete restrict on update restrict;

alter table VET add constraint FK_REFERENCE_3 foreign key (PROFESSIONAL_LICENSE_NUMBER)
      references VETERINARY (PROFESSIONAL_LICENSE_NUMBER) on delete restrict on update restrict;
