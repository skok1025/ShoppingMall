DELIMITER $$
	-- 1차 카테고리 삭제 프로시저
    CREATE procedure proc_deleteBigCategory(in pno int unsigned,out presult integer)
		begin
			DECLARE EXIT HANDLER FOR SQLEXCEPTION
			BEGIN        
		set presult =0;
			rollback;
			END;

		  update tblGoods set smallcategory_no=null where smallcategory_no in (select no from tblSmallCategory where bigcategory_no=pno);  
		  delete from tblSmallCategory where bigcategory_no=pno;     
		  delete from tblBigCategory where no= pno;    
	  set presult = 1;  
		commit;    
	end $$
    
    -- 2차 카테고리 삭제 프로시저
    CREATE procedure proc_deleteSmallCategory(in pno int unsigned,out presult integer)
	begin
		DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN        
	set presult =0;
		rollback;
		END;

	  update tblGoods set smallcategory_no=null where smallcategory_no = pno;  
	  delete from tblSmallCategory where no=pno;     
  set presult = 1;  
	commit;    
	end $$

DELIMITER ;