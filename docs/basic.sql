use mall;

-- 주문 테이블의 주문코드(code) unique 제약 설정
alter table tblOrder
modify column code varchar(50) unique;

alter table tblMaindisplay_category add isshow char(3);
alter table tblMaindisplay_category modify column isshow char(3) unique;
alter table tblOrder modify column password varbinary(128) null;

alter table tblGoods add viewdetail text null;
update tblGoods set viewdetail = '앞에 보여질 간단한 디테일 내용';

insert into tblGoods values(null,"테스트 상품 what",12300,"좀 긴 설명입니다. 잘 알아 듣겠죠?",'y','y',now(),null,null,null,null,null,null,11);

select count(*) from tblGoods where isdel is null;

-- Table 결과들
select * from tblGoods;
select * from tblGoodsImages;
select * from tblGoodsDetail;
select * from tblMember;
select * from tblBigCategory;
select * from tblSmallCategory;
select * from tblOrder;
select * from tblOrderGoods;
SELECT * FROM tblGoodsDetail;
select * from tblChangeApply;
select * from tblCustomerBasketCode;
select * from tblBasket;
select * from tblCustomerBasketCode;

delete from tblSmallCategory where bigcategory_no=6;

delete from tblBasket where no < 100;
delete from tblOrder where no <100;
delete from tblOrderGoods where order_no<30;


update tblBasket set basket_code= '2' where basket_code = 'u_78780';
select sum(regdate) from tblOrder;

select * from tblMaindisplay_category where isshow = 'y';
select * from tblMaindisplay;

insert into tblMaindisplay_category values(null,"SK mall's Choice");
insert into tblMaindisplay values(1,6);

-- 가장 높은 상품번호 가져오기
select ifnull(max(no),0) from tblGoods;

delete from tblMember where no = 4 and password =
      SHA2('1234', 512);
-- 주문 코드에 따른 주문 취소내역
select 
no,
(select code from tblOrder where no = c.order_no) as orderCode,
(select image from tblGoodsImages where goods_no=(select goods_no from tblGoodsDetail where no=c.goods_detail_no and ismain='y')) as thumbnail,
(select name from tblGoods where no = (select goods_no from tblGoodsDetail where no=c.goods_detail_no)) as goodsName,
(select option_name from tblGoodsDetail where no=c.goods_detail_no) as optionName,
(select option_name from tblGoodsDetail where no=c.change_goods_detail_no) as changeOptionName,
change_cnt as changeCnt,
status
from tblChangeApply c where order_no = (select no from tblOrder where code = '20190722-00005');

-- 주문코드에 따른 상품상세정보 리스트
select 
name as goodsName, 
option_name as optionName, 
cnt as orderCnt,
code as orderCode, 
status as orderStatus, 
sailing_price as sailingPrice,
invoice_code as invoiceCode 
from tblOrder o 
inner join tblOrderGoods og 
on o.no = og.order_no inner join tblGoodsDetail gd
on gd.no = og.goods_detail_no inner join tblGoods g
on g.no = gd.goods_no
where code = '20190722-00002';

-- 마지막 insert 된 pk 값
 select last_insert_id();

-- 주문번호 중에 현재날짜에서 가장 큰 주문번호
select max(no) from tblOrder o where DATE_FORMAT(o.regdate, '%Y-%m-%d')=DATE_FORMAT(curdate(), '%Y-%m-%d');

-- 주문코드 생성
 select concat(DATE_FORMAT(curdate(), '%Y%m%d'),'-',lpad((select ifnull(max(no),1) from tblOrder o where DATE_FORMAT(o.regdate, '%Y-%m-%d')=DATE_FORMAT(curdate(), '%Y-%m-%d')),5,'0'));

 select concat(curdate(),'-',lpad((select ifnull(max(no),0)+1 from tblOrder o ),5,'0'));
SELECT DATE_FORMAT("2016-04-08 11:12:14", '%Y-%m-%d');

-- 특정 상품의 가격
select seilling_price from tblGoods where no = (select goods_no from tblGoodsDetail where no = 1);

-- 회원 주문내역
select code,
	regdate,
   (select name from tblGoods where no = (select goods_no from tblGoodsDetail where no =  (select min(goods_detail_no) from tblOrderGoods where order_no=o.no))) as titleGoodsName,
   (select count(*) from tblOrderGoods where order_no = o.no) as orderGoodsCnt,
   (select sum(sailing_price) from tblOrderGoods where order_no=o.no) as total
from tblOrder o where member_no = 1;

-- 비회원 장바구니를 등록
insert into tblCustomerBasketCode(code,member_no) values('1',1);

-- 장바구니 코드에 따른 장바구니 리스트
select
b.no as no,
gd.no as goodsDetailNo,
(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail,
g.name as goodsName,
gd.option_name as optionName,
b.cnt as cnt,
g.seilling_price * b.cnt as price
from tblBasket b inner join tblGoodsDetail gd
                            on b.goods_detail_no = gd.no inner join tblGoods g
                                on gd.goods_no = g.no
                                where basket_code = '1';

-- 관리자 회원 조회
select 
			m.no, 
			cast(AES_DECRYPT(m.name, 'CAFE24') as char(100)) as name, 
			cast(AES_DECRYPT(m.address, 'CAFE24') as char(200)) as address,
			cast(AES_DECRYPT(m.birth_date, 'CAFE24') as char(100)) as birthDate,
			m.gender,
			m.id,
			cast(AES_DECRYPT(m.email, 'CAFE24') as char(100)) as email,
			cast(AES_DECRYPT(m.tel, 'CAFE24') as char(200)) as tel,
			m.regdate as regdate,
            max(o.regdate) as currentOrderDate
			from tblMember m inner join tblOrder o 
			on m.no = o.member_no
            where m.id like concat('%','','%')
		and o.regdate between '2019-07-01' and date_add('2019-07-26',interval 1 DAY) group by m.no;
		


-- 주문 리스트
select 
				code,
				o.regdate as regdate,
				m.id as memberId,
				 cast(AES_DECRYPT(m.name, 'CAFE24') as char(100)) as memberName,
			    cast(AES_DECRYPT(o.order_name, 'CAFE24') as char(100)) as orderName,
			    cast(AES_DECRYPT(o.order_tel, 'CAFE24') as char(100)) as orderTel,
				(select name from tblGoods where no = (select goods_no from tblGoodsDetail where no =  (select min(goods_detail_no) from tblOrderGoods where order_no=o.no))) as titleGoodsName,
				(select count(*) from tblOrderGoods where order_no = o.no) as orderGoodsCnt,
				(select sum(sailing_price) from tblOrderGoods where order_no=o.no) as total
						from tblOrder o inner join tblMember m 
							on o.member_no = m.no;
                            
-- 상품 2차 카테고리 1번
select 
			no,
			name,
			(select name from tblSmallCategory where no =  g.smallcategory_no) as smallcategoryName,
			(select name from tblBigCategory where no =(select bigcategory_no from tblSmallCategory where no =  g.smallcategory_no)) as bigcategoryName,
			seilling_price as seillingPrice,
			(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail
			from tblGoods g 
            where 
            display_status = 'y' and
            g.smallcategory_no = 1;
            
 
select 
			no,
			name,
			(select name from tblSmallCategory where no =  g.smallcategory_no) as smallcategoryName,
			(select name from tblBigCategory where no =(select bigcategory_no from tblSmallCategory where no =  g.smallcategory_no)) as bigcategoryName,
			seilling_price as seillingPrice,
			(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail
			from tblGoods g 
            where 
            display_status = 'y' 
			order by no
			limit 8 offset 0;

select * from oauth_client_details;       

select 
			no,
			name,
			concat(left(g.detail,5),'...') as detail,
			(select name from tblSmallCategory where no =  g.smallcategory_no) as smallcategoryName,
			(select name from tblBigCategory where no =(select bigcategory_no from tblSmallCategory where no =  g.smallcategory_no)) as bigcategoryName,
			seilling_price as seillingPrice,
			(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail
			from tblGoods g ;     
            
select * from tblGoodsImages;

update tblGoodsImages set image='main.jpg';


select 
			g.no as no,
			g.name as name,
			s.name as smallcategoryName,
			b.name as bigcategoryName,
			concat(left(g.detail,30),'...') as detail,
			g.seilling_price as seillingPrice,
			(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail
			from tblGoods g inner join tblSmallCategory s 
			on  s.no = g.smallcategory_no inner join tblBigCategory b
			on b.no = s.bigcategory_no
            where 
            g.display_status = 'y' and
            g.no in (select goods_no from tblMaindisplay where maindisplay_no = 1)
            order by g.no;
            
select goods_no from tblMaindisplay where maindisplay_no = 1;

select * from tblMaindisplay;

select 
			 s.name as smallcategoryName,
			 b.name as bigcategoryName 
            from tblSmallCategory s inner join tblBigCategory b
			on s.bigcategory_no = b.no where s.no=11;
           
select 
			g.isdel,
			no,
			name,
			seilling_price as seillingPrice,
			display_status as displayStatus,
			seilling_status as seillingStatus,
			regdate,
			(select image from tblGoodsImages where ismain='y' and goods_no=g.no) as thumbnail
			from tblGoods g 
            where g.isdel is null
			limit 10 offset 1;        
            
            
select 
			m.no, 
			cast(AES_DECRYPT(m.name, 'CAFE24') as char(100)) as name, 
			cast(AES_DECRYPT(m.address, 'CAFE24') as char(200)) as address,
			cast(AES_DECRYPT(m.birth_date, 'CAFE24') as char(100)) as birthDate,
			m.gender as gender,
			m.id as id,
			cast(AES_DECRYPT(m.email, 'CAFE24') as char(100)) as email,
			cast(AES_DECRYPT(m.tel, 'CAFE24') as char(200)) as tel,
			m.regdate,
			max(o.regdate) as currentOrderDate
			from tblMember m left outer join tblOrder o 
			on m.no = o.member_no
			where m.id != 'admin' and m.id like concat('%','','%') 
			limit 10 offset 1;


select 
			no,
			name,
			seilling_price as seillingPrice,
			concat(left(g.viewdetail,30),'...') as detail,
			(select name from tblSmallCategory where no =  g.smallcategory_no) as smallcategoryName,
			(select name from tblBigCategory where no =(select bigcategory_no from tblSmallCategory where no =  g.smallcategory_no)) as bigcategoryName,
			seilling_price as seillingPrice,
			(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail
			from tblGoods g 
            where 
            display_status = 'y' and isdel is null
            and no < 8
            order by no desc
			limit 0,8;
            
select count(*) from tblGoods where smallcategory_no = 1 and isdel is null;


select 
			g.no as no,
			g.name as name,
			concat(left(g.viewdetail,30),'...') as detail,
			s.name as smallcategoryName,
			b.name as bigcategoryName,
			g.seilling_price as seillingPrice,
			(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail
			from tblGoods g inner join tblSmallCategory s 
			on  s.no = g.smallcategory_no inner join tblBigCategory b
			on b.no = s.bigcategory_no
            where 
            g.display_status = 'y' and g.isdel is null
            order by no;
            
 
 
 select
				b.no as no,
				gd.no as goodsDetailNo,
				(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail,
				g.name as goodsName,
				gd.option_name as optionName,
				b.cnt as cnt,
				g.seilling_price * b.cnt as price
				from tblBasket b inner join tblGoodsDetail gd
				                            on b.goods_detail_no = gd.no inner join tblGoods g
				                                on gd.goods_no = g.no
                                where basket_code in (select code from tblCustomerBasketCode where member_no =2);
            
select
				b.no as no,
				gd.no as goodsDetailNo,
				(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail,
				g.name as goodsName,
				gd.option_name as optionName,
				sum(b.cnt) as cnt,
				g.seilling_price * sum(b.cnt) as price
				from tblBasket b inner join tblGoodsDetail gd
				                            on b.goods_detail_no = gd.no inner join tblGoods g
				                                on gd.goods_no = g.no
                                where basket_code in (select code from tblCustomerBasketCode where member_no =2) group by goodsDetailNo;
                                
select * from tblBasket;
delete from tblBasket where basket_code = 2 and goods_detail_no=35;
select * from tblGoodsDetail;

delete from tblBasket where basket_code = 2 and goods_detail_no=22;							
                            
select
				goods_no,cnt * seilling_price
				from tblBasket b inner join tblGoodsDetail gd
				                            on b.goods_detail_no = gd.no inner join tblGoods g
                                where basket_code in (select code from tblCustomerBasketCode where member_no =2) group by goods_no;
      
 -- 멤버번호 2 의 총 구매금액     
select sum(a.price) from
(select
				b.no as no,
				gd.no as goodsDetailNo,
				(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail,
				g.name as goodsName,
				gd.option_name as optionName,
				sum(b.cnt) as cnt,
				g.seilling_price * sum(b.cnt) as price
				from tblBasket b inner join tblGoodsDetail gd
				                            on b.goods_detail_no = gd.no inner join tblGoods g
				                                on gd.goods_no = g.no
                                where basket_code in (select code from tblCustomerBasketCode where member_no =2) group by goodsDetailNo) a;
 
 -- 장바구니 코드 의 총 구매금액
 select sum(a.price) from
(select
				b.no as no,
				gd.no as goodsDetailNo,
				(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail,
				g.name as goodsName,
				gd.option_name as optionName,
				sum(b.cnt) as cnt,
				g.seilling_price * sum(b.cnt) as price
				from tblBasket b inner join tblGoodsDetail gd
				                            on b.goods_detail_no = gd.no inner join tblGoods g
				                                on gd.goods_no = g.no
                                where basket_code = 'u_78780' group by goodsDetailNo) a;
 
 -- 상품 상세번호의 goodsName, optionName, thumbnail, price
 
 select 
 g.name as goodsName,
 option_name as optionName,
 seilling_price as price,
 (select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail
 from tblGoodsDetail gd inner join tblGoods g
			on gd.goods_no = g.no where gd.no = 1;
 
 
 select
      no, id, 
      cast(AES_DECRYPT(name, 'CAFE24') as char(200)) as name,
      gender,
      cast(AES_DECRYPT(address, 'CAFE24') as char(200)) as address,
      cast(AES_DECRYPT(birth_date, 'CAFE24') as char(100)) as birthDate,
      cast(AES_DECRYPT(email, 'CAFE24') as char(100)) as email,
      cast(AES_DECRYPT(tel, 'CAFE24') as char(50)) as tel,
      regdate,
      password
      from tblMember
      where id = 'skok1025' and password =
      SHA2('1234', 512);
 
 
 -- 멤버번호 2 의 주문내역
 select 
			name as goodsName, 
			option_name as optionName, 
			cnt as orderCnt,
			code as orderCode, 
			status as orderStatus, 
			sailing_price as sailingPrice,
			invoice_code as invoiceCode,
			(select image from tblGoodsImages where goods_no=g.no and ismain='y') as thumbnail 
		from tblOrder o 
		inner join tblOrderGoods og 
		on o.no = og.order_no inner join tblGoodsDetail gd
		on gd.no = og.goods_detail_no inner join tblGoods g
		on g.no = gd.goods_no
		where code in (select code tblOrder where o.member_no = 2);
 
 select * from tblOrder;
 
 select 
				code,
				o.regdate as regdate,
				m.id as memberId,
				 cast(AES_DECRYPT(m.name, 'CAFE24') as char(100)) as memberName,
			    cast(AES_DECRYPT(o.order_name, 'CAFE24') as char(100)) as orderName,
			    cast(AES_DECRYPT(o.order_tel, 'CAFE24') as char(100)) as orderTel,
				(select name from tblGoods where no = (select goods_no from tblGoodsDetail where no =  (select min(goods_detail_no) from tblOrderGoods where order_no=o.no))) as titleGoodsName,
				(select count(*) from tblOrderGoods where order_no = o.no) as orderGoodsCnt,
				(select sum(sailing_price) from tblOrderGoods where order_no=o.no) as total,
                (select image from tblGoodsImages where goods_no = (select goods_no from tblGoodsDetail where no =  (select min(goods_detail_no) from tblOrderGoods where order_no=o.no)) and ismain='y') as thumbnail
						from tblOrder o inner join tblMember m 
							on o.member_no = m.no;
