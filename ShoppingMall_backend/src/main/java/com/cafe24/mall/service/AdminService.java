package com.cafe24.mall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.dto.OrderDTO;
import com.cafe24.mall.repository.AdminDao;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MaindisplayCategoryVo;
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
	

	public List<BigCategoryVo> getBigCategoryList() {
		List<BigCategoryVo> list = adminDao.selectBigCategoryList();
		
		return list;
	}
	
	public List<SmallCategoryVo> getSmallCategoryList(Long bigCategoryno) {
		List<SmallCategoryVo> list = adminDao.selectSmallCategoryList(bigCategoryno);
		
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

	public List<GoodsVo> getGoodsList(Long startCol) {
		return adminDao.selectGoodsList(startCol);
	}

	public int removeMemberInfo(Long memberNo) {
		adminDao.deleteMemberOrderInfo(memberNo);
		adminDao.updateOrderMemberNull(memberNo);
		adminDao.updateCustomerBasketCodeMemberNull(memberNo);
		adminDao.updateMemberTermsMemberNull(memberNo);
		
		return adminDao.deleteMemberInfo(memberNo);
	}

	public List<MemberVo> getMemberList(String id, String orderdateStart, String orderdateEnd, Integer startCol) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("id", id);
		map.put("orderdateStart", orderdateStart);
		map.put("orderdateEnd", orderdateEnd);
		map.put("startCol", startCol);
		
		
		return adminDao.selectMemberList(map);
	}

	/**
	 * 관리자가 회원들의 주문리스트를 얻어오논 메소드
	 * @param orderdateStart 시작주문날짜(검색필터)
	 * @param orderdateEnd 시작마지막날짜(검색필터)
	 * @return 회원들의 주문리스트
	 */
	public List<OrderDTO> getAdminOrderList(
			String orderdateStart, 
			String orderdateEnd) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderdateStart", orderdateStart);
		map.put("orderdateEnd",orderdateEnd);
		
		return adminDao.selectAdminOrderList(map);
	}

	/**
	 * 관리자가 옵션 상태를 disable 시키는 메소드
	 * @param goodsNo 상품번호
	 * @return 성공유무
	 */
	public int modifyOptionDisable(Long goodsNo) {
		return adminDao.updateOptionDisable(goodsNo);
	}

	public int addMaindisplayCategory(String mainDisplayName) {
		return adminDao.insertMainDisplayCategory(mainDisplayName);
	}

	public int modifyMaindisplayCategory(Long maindisplayNo, String mainDisplayName) {
		
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("mainDisplayNo", maindisplayNo);
		map.put("mainDisplayName", mainDisplayName);
		
		return adminDao.updateMainDisplayCateogry(map);
	}

	public List<MaindisplayCategoryVo> getMaindisplayCategoryList() {
		return adminDao.selectMaindisplayCategoryList();
	}

	@Transactional
	public int DeleteMaindisplayCategory(Long no) {
		adminDao.deleteMaindisplay(no);
		return adminDao.deleteMaindisplayCateogry(no);
	}

	public int addMaindisplay(Long goodsNo, Long maindisplayCategoryNo) {

		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("goodsNo", goodsNo);
		map.put("maindisplayCategoryNo", maindisplayCategoryNo);
		
		return adminDao.insertMaindisplay(map);
	}

	public int removeMaindisplay(Long goodsNo, Long maindisplayCategoryNo) {
		
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("goodsNo", goodsNo);
		map.put("maindisplayCategoryNo", maindisplayCategoryNo);
		
		return adminDao.deleteMaindisplay(map);
	}

	public Integer getGoodsTotalCount() {
		return adminDao.selectGoodsTotalCount();
	}

	public Integer getMemberTotalCount() {
		return adminDao.selectMemberTotalCount();
	}


	
}
