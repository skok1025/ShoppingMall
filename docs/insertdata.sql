


insert into 
			tblMember (no,name,address,birth_date,gender,id,password,email,tel,regdate) 
			values(null,
			AES_ENCRYPT('admin','CAFE24'),
			AES_ENCRYPT('서울 동작구 보라매로5길','CAFE24'),
			AES_ENCRYPT(now(),'CAFE24'),
			'm',
			'admin',
			SHA2('1234',512),
			AES_ENCRYPT('admin@admin.com','CAFE24'),
			AES_ENCRYPT('01068669202','CAFE24'),
			now());
            
select * from tblMember;

insert into tblBigCategory(no,name) values(null,'상의');
insert into tblBigCategory(no,name) values(null,'하의');
insert into tblBigCategory(no,name) values(null,'셔츠');
insert into tblBigCategory(no,name) values(null,'가방');
insert into tblBigCategory(no,name) values(null,'악세서리');

select * from tblBigCategory;

insert into tblSmallCategory(no,name,bigcategory_no) values(null,'반팔티',1);
insert into tblSmallCategory(no,name,bigcategory_no) values(null,'긴팔티',1);
insert into tblSmallCategory(no,name,bigcategory_no) values(null,'나시',1);

insert into tblSmallCategory(no,name,bigcategory_no) values(null,'반바지',2);
insert into tblSmallCategory(no,name,bigcategory_no) values(null,'면바지',2);
insert into tblSmallCategory(no,name,bigcategory_no) values(null,'치마',2);

insert into tblSmallCategory(no,name,bigcategory_no) values(null,'베이직',3);
insert into tblSmallCategory(no,name,bigcategory_no) values(null,'청남방',3);
insert into tblSmallCategory(no,name,bigcategory_no) values(null,'체크',3);

insert into tblSmallCategory(no,name,bigcategory_no) values(null,'백팩',4);
insert into tblSmallCategory(no,name,bigcategory_no) values(null,'크로스백',4);

insert into tblSmallCategory(no,name,bigcategory_no) values(null,'모자',5);
insert into tblSmallCategory(no,name,bigcategory_no) values(null,'시계',5);

select * from tblSmallCategory;

insert into tblMaindisplay_category values(null,"SK mall's Choice");

