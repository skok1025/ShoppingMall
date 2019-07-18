package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.repository.AdminDao;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.SmallCategoryVo;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public int addGoods(GoodsVo goodsvo) {
		
		//int result1 = insertGoods(goodsvo);
		//int result2 = insertImages(goodsvo.getGoodsImagesList())
		
		
		return 1;
	}

	public int removeGoodsInfo(Long goodsNo) {
		return 1;
	}

	public int modifyGoodsInfo(GoodsVo goodsvo) {
		return 1;
	}

	public int addBigCatergory(BigCategoryVo vo) {
		return adminDao.insertBigCategory(vo);
	}

	public int modifyBigCatergory(BigCategoryVo vo) {
		return adminDao.updateBigCategory(vo);
	}

	//@Transactional
	public int removeBigCatergory(BigCategoryVo vo) {
		
		//adminDao.updateGoodsSmallCategoryNullByBigCategoryNo(vo.getNo());
		//adminDao.deleteSmallCategoryByBigCategoryNo(vo.getNo());
		int result =  adminDao.deleteBigCategory(vo);
		return result;
		//return result1*result2*result3;
	}

	public int addSmallCatergory(SmallCategoryVo vo) {
		return adminDao.insertSmallCategory(vo);
	}

	public int modifySmallCatergory(SmallCategoryVo vo) {
		return adminDao.updateSmallCategory(vo);
	}
	
}
