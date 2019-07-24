use mall;

-- Table 결과들
select * from tblGoods;
select * from tblMember;
select * from tblBigCategory;
select * from tblSmallCategory;
select * from tblOrder;
select * from tblOrderGoods;
SELECT * FROM tblGoodsDetail;
select * from tblChangeApply;

delete from tblOrder where no = 2;

-- 주문 테이블의 주문코드(code) unique 제약 설정
alter table tblOrder
modify column code varchar(50) unique;

-- 가장 높은 상품번호 가져오기
select ifnull(max(no),0) from tblGoods;


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


 select last_insert_id();
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
