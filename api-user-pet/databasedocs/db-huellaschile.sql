drop table if exists PET;

drop table if exists USER;

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

alter table PET add constraint FK_REFERENCE_2 foreign key (ID_USER)
      references USER (ID_USER) on delete restrict on update restrict;
