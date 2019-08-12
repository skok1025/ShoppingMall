package com.cafe24.mall.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MaindisplayCategoryVo;

@Repository
public class GoodsDao {

	@Autowired
	private SqlSession sqlSession;

	public List<GoodsVo> selectGoodsList(String kw) {
		return sqlSession.selectList("goods.selectGoodsListByKw", kw);
	}

	public List<GoodsVo> selectGoodsList(Integer startNo) {
		return sqlSession.selectList("goods.selectGoodsList",startNo);
	}
	
	public GoodsVo selectGoods(Long goodsNo) {
		return sqlSession.selectOne("goods.selectGoods", goodsNo);
	}

	public List<GoodsImagesVo> selectSubImageList(Long goodsNo) {
		return sqlSession.selectList("goods.selectSubImageList", goodsNo);
	}

	public GoodsImagesVo selectMainImage(Long goodsNo) {
		return sqlSession.selectOne("goods.selectMainImage", goodsNo);
	}

	public List<GoodsDetailVo> selectGoodsDetailList(Long goodsNo) {
		return sqlSession.selectList("goods.selectGoodsDetailList", goodsNo);
	}

	public List<GoodsVo> selectGoodsList(Map<String,Object> map) {
		return sqlSession.selectList("goods.selectGoodsListByCategory", map);
	}

	public List<GoodsVo> selectMainDisplayList(Long maindisplayNo) {
		return sqlSession.selectList("goods.selectMainDisplayList",maindisplayNo);
	}

	public GoodsVo selectCategoryName(Long smallCategoryNo) {
		return sqlSession.selectOne("goods.selectCategoryName", smallCategoryNo);
	}

	public MaindisplayCategoryVo selectMainDisplayCategoryVo() {
		return sqlSession.selectOne("goods.selectMainDisplayCategoryVo");
	}

	public Integer selectTotalCount(Long smallcategoryNo) {
		return sqlSession.selectOne("goods.selectTotalCountCategoryGoods",smallcategoryNo);
	}

	
}
