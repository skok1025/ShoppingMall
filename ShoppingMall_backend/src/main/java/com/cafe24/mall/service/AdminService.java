package com.cafe24.mall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.repository.AdminDao;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
import com.cafe24.mall.vo.SmallCategoryVo;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	@Transactional
	public int addGoods(GoodsVo goodsvo) {
		
		List<GoodsImagesVo> imagesList = goodsvo.getGoodsImagesList();
		List<GoodsDetailVo> detailList = goodsvo.getGoodsDetailList();
		
		int result = adminDao.insertGoods(goodsvo);
		
		if(imagesList != null) {
			for(GoodsImagesVo imagesVo:imagesList) {
				imagesVo.setGoodsNo(goodsvo.getNo());
				result *= adminDao.insertImages(imagesVo);
			}
		}
		if(detailList != null) {
			for(GoodsDetailVo detailVo:detailList) {
				detailVo.setGoodsNo(goodsvo.getNo());
				result *= adminDao.insertGoodsDetail(detailVo);
			}
		}
		return result;
	}

	public int removeGoodsInfo(Long goodsNo) {
		return adminDao.updateGoodsIsDel(goodsNo);
	}

	public int modifyGoodsInfo(GoodsVo goodsvo) {
		List<GoodsImagesVo> imagesList = goodsvo.getGoodsImagesList();
		List<GoodsDetailVo> detailList = goodsvo.getGoodsDetailList(); 
		// 이미지는 기존의 것을 삭제하고 새로 db에 인서트
		// 상품상세정보는 추가항목만 기존 것은 삭제불가 
		
		int result = adminDao.updateGoods(goodsvo);
		
		if(imagesList != null) {
			adminDao.deleteImages(goodsvo.getNo());
			
			for(GoodsImagesVo imagesVo:imagesList) {
				result *= adminDao.insertImages(imagesVo);
			}
		}
		
		if(detailList != null) {
			for(GoodsDetailVo detailVo:detailList) {
				result *= adminDao.insertGoodsDetail(detailVo);
			}
		}
		
		
		return result;
	}

	public int addBigCatergory(BigCategoryVo vo) {
		return adminDao.insertBigCategory(vo);
	}

	public int modifyBigCatergory(BigCategoryVo vo) {
		return adminDao.updateBigCategory(vo);
	}

	@Transactional
	public int removeBigCatergory(BigCategoryVo vo) {
		
		adminDao.updateGoodsSmallCategoryNullByBigCategoryNo(vo.getNo());
		adminDao.deleteSmallCategoryByBigCategoryNo(vo.getNo());
		int result = adminDao.deleteBigCategory(vo);
		
		return result;
	}

	public int addSmallCatergory(SmallCategoryVo vo) {
		return adminDao.insertSmallCategory(vo);
	}

	public int modifySmallCatergory(SmallCategoryVo vo) {
		return adminDao.updateSmallCategory(vo);
	}

	public int removeSmallCatergory(SmallCategoryVo vo) {
		return adminDao.deleteSmallCategory(vo);
	}

	public List<BigCategoryVo> getCategoryList() {

		List<BigCategoryVo> list = adminDao.selectBigCategoryList();
		
		for(BigCategoryVo bVo:list) {
			List<SmallCategoryVo> slist = adminDao.selectSmallCategoryList(bVo.getNo());
			bVo.setSmallCategoryList(slist);
		}
		
		
		return list;
	}

	public GoodsVo getGoods(Long goodsno) {
		
		GoodsVo result =  adminDao.selectGoods(goodsno);
		
		List<GoodsImagesVo> imagesList = adminDao.selectImages(goodsno);
		List<GoodsDetailVo> detailList = adminDao.selectDetail(goodsno);
		
		result.setGoodsImagesList(imagesList);
		result.setGoodsDetailList(detailList);
		
		
		return result;
	}

	public List<GoodsVo> getGoodsList(Long pageNum) {
		Long startCol = pageNum*10-10;
		return adminDao.selectGoodsList(startCol);
	}

	public int removeMemberInfo(Long memberNo) {
		adminDao.deleteMemberOrderInfo(memberNo);
		adminDao.updateOrderMemberNull(memberNo);
		adminDao.updateCustomerBasketCodeMemberNull(memberNo);
		adminDao.updateMemberTermsMemberNull(memberNo);
		
		return adminDao.deleteMemberInfo(memberNo);
	}

	public List<MemberVo> getMemberList(String id, String orderdateStart, String orderdateEnd) {
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("id", id);
		map.put("orderdateStart", orderdateStart);
		map.put("orderdateEnd", orderdateEnd);
		
		return adminDao.selectMemberList(map);
	}
	
}
