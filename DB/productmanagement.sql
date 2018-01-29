create table product(
    Product_Code varchar(10) not null,
    Product_Name nvarchar2(100) not null,
    Product_Desc nvarchar2(500),
    Delete_Flg int,
    Create_Date date,
    Update_Date date,
    Creater varchar2(10),
    Updater varchar2(10),
    constraint product_pk primary key (Product_Code)
);

create table category(
    category_code varchar(10) not null,
    category_name nvarchar2(100) not null,
    category_desc nvarchar2(500),
    Delete_Flg int,
    Create_Date date,
    Update_Date date,
    Creater varchar2(10),
    Updater varchar2(10),
    constraint category_pk primary key (category_code)
);

create table attribute(
    attr_code varchar(10) not null,
    attr_name nvarchar2(100) not null,
    attr_desc nvarchar2(500),
    delete_flg int,
    craete_date date,
    update_date date,
    creater varchar(10),
    updater varchar(10),
    constraint attribute_pk primary key (attr_code)
);


create table product_category(
    seq NUMBER(10) NOT NULL,
    product_code varchar(10) NOT NULL,
    category_code varchar(10) NOT NULL ,
    delete_flg int, 
    start_date date,
    due_date date,
    creater varchar(10),
    updater varchar(10),
    create_date date,
    update_date date,
    CONSTRAINT FK_pc FOREIGN KEY (product_code)
    REFERENCES Product(product_code)
);
ALTER TABLE product_category ADD (
  CONSTRAINT prd_pk PRIMARY KEY (seq));


CREATE SEQUENCE product_category_seq START WITH 1;


