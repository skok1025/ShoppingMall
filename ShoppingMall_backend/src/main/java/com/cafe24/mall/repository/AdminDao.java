package com.cafe24.mall.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.dto.OrderDTO;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MaindisplayCategoryVo;
import com.cafe24.mall.vo.MemberVo;
import com.cafe24.mall.vo.SmallCategoryVo;

@Repository
public class AdminDao {

	@Autowired
	private SqlSession sqlsession;

	public int insertBigCategory(BigCategoryVo vo) {
		return sqlsession.insert("admin.insertBigCategory",vo);
	}

	public int updateBigCategory(BigCategoryVo vo) {
		return sqlsession.update("admin.updateBigCategory",vo);
	}

	public Long getCurrentInsertBigCategoryNo() {
		return sqlsession.selectOne("admin.getCurrentInsertBigCategoryNo");
	}

	public Long getCurrentInsertSmallCategoryNo() {
		return sqlsession.selectOne("admin.getCurrentInsertSmallCategoryNo");
	}
	public int deleteBigCategory(BigCategoryVo vo) {
		return sqlsession.delete("admin.deleteBigCategory",vo);
		//return vo.getPresult();
	}

	
	public int updateGoodsSmallCategoryNullByBigCategoryNo(Long no) {
		return sqlsession.update("admin.updateGoodsSmallCategoryNullByBigCategoryNo", no);
	}

	public int deleteSmallCategoryByBigCategoryNo(Long no) {
		return sqlsession.delete("admin.deleteSmallCategoryByBigCategoryNo",no);
	}

	public int insertSmallCategory(SmallCategoryVo vo) {
		return sqlsession.insert("admin.insertSmallCategory", vo);
	}

	public int updateSmallCategory(SmallCategoryVo vo) {
		return sqlsession.update("admin.updateSmallCategory", vo);
	}

	public int deleteSmallCategory(SmallCategoryVo vo) {
		sqlsession.delete("admin.deleteSmallCategory",vo);
		return vo.getPresult();
	}

	public List<BigCategoryVo> selectBigCategoryList() {
		return sqlsession.selectList("admin.selectBigCategoryList");
	}

	public List<SmallCategoryVo> selectSmallCategoryList(Long bigcategoryNo) {
		return sqlsession.selectList("admin.selectSmallCategoryList",bigcategoryNo);
	}

	public int insertGoods(GoodsVo goodsvo) {
		return sqlsession.insert("admin.insertGoods", goodsvo);
	}

	public int insertImages(GoodsImagesVo imagesVo) {
		return sqlsession.insert("admin.insertImages",imagesVo);
	}

	public int getCurrentInsertGoodsNo() {
		return sqlsession.selectOne("admin.getCurrentInsertGoodsNo");
	}

	public int insertGoodsDetail(GoodsDetailVo detailVo) {
		return sqlsession.insert("admin.insertGoodsDetail", detailVo);
	}

	public int updateGoods(GoodsVo goodsvo) {
		return sqlsession.update("admin.updateGoods",goodsvo);
	}

	public int deleteImages(Long goodsNo) {
		return sqlsession.delete("admin.deleteImages", goodsNo);
	}

	public int updateGoodsIsDel(Long goodsNo) {
		return sqlsession.update("admin.updateGoodsIsDel", goodsNo);
	}

	public GoodsVo selectGoods(Long goodsno) {
		return sqlsession.selectOne("admin.selectGoods",goodsno);
	}

	public List<GoodsImagesVo> selectImages(Long goodsno) {
		return sqlsession.selectList("admin.selectImages",goodsno);
	}

	public List<GoodsDetailVo> selectDetail(Long goodsno) {
		return sqlsession.selectList("admin.selectDetail",goodsno);
	}

	public List<GoodsVo> selectGoodsList(Long startCol) {
		return sqlsession.selectList("admin.selectGoodsList",startCol);
	}

	
	public void deleteMemberOrderInfo(Long memberNo) {
		sqlsession.delete("admin.deleteMemberOrderInfo", memberNo);
	}

	public void updateOrderMemberNull(Long memberNo) {
		sqlsession.update("admin.updateOrderMemberNull", memberNo);
	}

	public void updateCustomerBasketCodeMemberNull(Long memberNo) {
		sqlsession.update("admin.updateCustomerBasketCodeMemberNull", memberNo);
	}

	public void updateMemberTermsMemberNull(Long memberNo) {
		sqlsession.update("admin.updateMemberTermsMemberNull", memberNo);
	}

	public int deleteMemberInfo(Long memberNo) {
		return sqlsession.delete("admin.deleteMemberInfo", memberNo);
	}

	public List<MemberVo> selectMemberList(Map<String, String> map) {
		return sqlsession.selectList("admin.selectMemberList", map);
	}

	public List<OrderDTO> selectAdminOrderList(Map<String, String> map) {
		return sqlsession.selectList("admin.selectAdminOrderList",map);
	}

	public int updateOptionDisable(Long goodsNo) {
		return sqlsession.update("admin.updateOptionDisable", goodsNo);
	}

	public int insertMainDisplayCategory(String mainDisplayName) {
		return sqlsession.insert("admin.insertMainDisplayCategory", mainDisplayName);
	}

	public int updateMainDisplayCateogry(Map<String, Object> map) {
		return sqlsession.update("admin.updateMainDisplayCateogry", map);
	}

	public List<MaindisplayCategoryVo> selectMaindisplayCategoryList() {
		return sqlsession.selectList("admin.selectMaindisplayCategoryList");
	}

	public int deleteMaindisplayCateogry(Long no) {
		return sqlsession.delete("admin.deleteMaindisplayCateogry", no);
	}

	public int insertMaindisplay(Map<String, Object> map) {
		return sqlsession.insert("admin.insertMaindisplay", map);
	}

	public int deleteMaindisplay(Map<String, Object> map) {
		return sqlsession.delete("admin.deleteMaindisplay", map);
	}

	public void deleteMaindisplay(Long no) {
		sqlsession.delete("admin.deleteMaindisplayByMainCategoryNo", no);	
	}
	

	
}
