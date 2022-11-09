USE huellaschile;

drop table if exists CONSULTATION;

drop table if exists PET;

drop table if exists USER;

drop table if exists VET;

drop table if exists VETERINARY;

/*==============================================================*/
/* Table: CONSULTATION                                          */
/*==============================================================*/
create table CONSULTATION
(
   ID_CONSULTATION      varchar(50) not null,
   ID_USER              varchar(11) not null,
   NAME_PET             varchar(50) not null,
   NATIONAL_REGISTER    varchar(50) not null,
   STATUS               varchar(11) not null,
   PRICE                int not null,
   METHOD_PAYMENT       varchar(50) not null,
   PAID_OUT             boolean not null,
   PROCESS              varchar(50) not null,
   primary key (ID_CONSULTATION)
);

/*==============================================================*/
/* Table: PET                                                   */
/*==============================================================*/
create table PET
(
   ID_USER              varchar(11) not null,
   NAME_PET             varchar(50) not null,
   RACE                 varchar(50) not null,
   TREATMENT_NAME       varchar(50) not null,
   TREATMENT_ACTIVE     boolean not null,
   primary key (ID_USER, NAME_PET)
);

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   ID_USER              varchar(11) not null,
   NAME                 varchar(50) not null,
   EMAIL                varchar(100) not null,
   ADDRESS              varchar(150) not null,
   CITY                 varchar(50) not null,
   COUNTRY              varchar(50) not null,
   primary key (ID_USER)
);

/*==============================================================*/
/* Table: VET                                                   */
/*==============================================================*/
create table VET
(
   NATIONAL_REGISTER    varchar(50) not null,
   NAME                 varchar(50) not null,
   CERTIFIED            boolean not null,
   PROFESSIONAL_LICENSE_NUMBER varchar(50) not null,
   primary key (NATIONAL_REGISTER)
);

/*==============================================================*/
/* Table: VETERINARY                                            */
/*==============================================================*/
create table VETERINARY
(
   PROFESSIONAL_LICENSE_NUMBER varchar(50) not null,
   NAME                 varchar(50) not null,
   primary key (PROFESSIONAL_LICENSE_NUMBER)
);

alter table CONSULTATION add constraint FK_REFERENCE_4 foreign key (ID_USER, NAME_PET)
      references PET (ID_USER, NAME_PET) on delete restrict on update restrict;

alter table CONSULTATION add constraint FK_REFERENCE_5 foreign key (NATIONAL_REGISTER)
      references VET (NATIONAL_REGISTER) on delete restrict on update restrict;

alter table PET add constraint FK_REFERENCE_2 foreign key (ID_USER)
      references USER (ID_USER) on delete restrict on update restrict;

alter table VET add constraint FK_REFERENCE_3 foreign key (PROFESSIONAL_LICENSE_NUMBER)
      references VETERINARY (PROFESSIONAL_LICENSE_NUMBER) on delete restrict on update restrict;
