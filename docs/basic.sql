use mall;

-- Table 결과들
select * from tblGoods;
select * from tblMember;
select * from tblBigCategory;
select * from tblSmallCategory;
select * from tblOrder;
select * from tblOrderGoods;
SELECT * FROM tblGoodsDetail;

-- 주문 테이블의 주문코드(code) unique 제약 설정
alter table tblOrder
modify column code varchar(50) unique;

-- 가장 높은 상품번호 가져오기
select ifnull(max(no),0) from tblGoods;

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

