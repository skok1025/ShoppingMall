package com.cafe24.mall.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mall.dto.OrderDTO;
import com.cafe24.mall.provider.AdminProvider;
import com.cafe24.mall.util.PagingFrontUtil;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
import com.cafe24.mall.vo.SmallCategoryVo;

@Service
public class AdminService {

	
	private static final String SAVE_PATH = "/mall-uploads";
	@Autowired
	private AdminProvider adminProvider;
	
	// 페이징 변수
	private int listSize = 5; // 페이징 리스트 수 
	private int pageSize = 10; // 한 페이지의 게시물 수 

	public List<MemberVo> getMemberList(String id, String orderDateStart,
			String orderDateEnd ,Integer currentpage ) {
		
		
		Integer totalcount = adminProvider.selectTotalMemberCount(); 
		Integer startCol = PagingFrontUtil.getStartRecordNum(currentpage, totalcount, pageSize);
		
		System.out.println("총 멤버 수: "+totalcount);
		
		return adminProvider.selectMemberList(id,orderDateStart,orderDateEnd,startCol);
	}
	
	public Map<String, Integer> getMemberPaging(Integer currentPage) {
		Integer totalcount = adminProvider.selectTotalMemberCount();
		Map<String, Integer> result = PagingFrontUtil.getPagingVariable(currentPage, totalcount, pageSize, listSize);
		
		return result;
	}


	public int removerMember(Long userNo) {
		return adminProvider.deleteMember(userNo);
	}

	public List<GoodsVo> getGoodsList(Integer currentpage) {
		
		Integer totalcount = adminProvider.selectTotalGoodsCount();
		Integer startCol = PagingFrontUtil.getStartRecordNum(currentpage, totalcount, pageSize); // 시작 인덱스
		
		return adminProvider.selectGoodsList(startCol);
	}

	public List<OrderDTO> getOrderList(String orderdateStart,String orderdateEnd,Integer currentPage) {
		
		Integer totalcount = adminProvider.selectTotalOrderCount();
		Integer startCol = PagingFrontUtil.getStartRecordNum(currentPage, totalcount, pageSize);
		
		return adminProvider.selectOrderGoodsList(orderdateStart,orderdateEnd,startCol);
	}
	
	public Map<String, Integer> getGoodsPaging(Integer currentPage) {
	
		Integer totalcount = adminProvider.selectTotalGoodsCount();
		Map<String, Integer> result = PagingFrontUtil.getPagingVariable(currentPage, totalcount, pageSize, listSize);	
		
		return result;
	}
	
	public int removerGoods(Long goodsNo) {
		return adminProvider.deleteGoods(goodsNo);
	}

	public List<BigCategoryVo> getBigCategoryList() {
		return adminProvider.selectBigCategoryList();
	}

	public List<SmallCategoryVo> getSmallCategoryList(Long bigCategoryNo) {
		return adminProvider.selectSmallCategoryList(bigCategoryNo);
	}

	public int addGoods(GoodsVo goodsVo,
			MultipartFile mainattach,
			MultipartFile subattach1,
			MultipartFile subattach2,
			MultipartFile subattach3
			) {
		List<GoodsDetailVo> goodsDetailList = new ArrayList<GoodsDetailVo>();
		List<GoodsImagesVo> goodsImagesList = new ArrayList<GoodsImagesVo>();
		
		System.out.println("in service 등록하고자 하는 상품의 옵션값들: "+goodsVo.getOptionListTxt());
		
		 goodsVo.setViewdetail(goodsVo.getViewdetail().replace("\r\n", "<br>")); 
		
//		if(!goodsVo.getOptionListTxt().equals("")) {
//			
			String[] options = goodsVo.getOptionListTxt().split(",");
			
			for(String optionInfo:options) {
				
				String[] optionInfos = optionInfo.split(";;");
				
				if(optionInfos.length>1) {
					String optionName = optionInfos[0];
					Integer optionCnt = Integer.parseInt(optionInfos[1]);
					
					goodsDetailList.add(new GoodsDetailVo(optionName, optionCnt, optionCnt));
				}
			}
			
			goodsVo.setGoodsDetailList(goodsDetailList);
			// 옵션값들 세팅 완료
//		}
		
		
		try {
			
			if(!mainattach.isEmpty()) { 
				String originalFileName = mainattach.getOriginalFilename();
				String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
				String saveFileName = generateSaveFileName(extName);
				
				byte[] fileData = mainattach.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
				os.write(fileData);
				os.close();
				
				goodsImagesList.add(new GoodsImagesVo(saveFileName, GoodsImagesVo.status.y));
			}
			if(!subattach1.isEmpty()) { 
				String originalFileName = subattach1.getOriginalFilename();
				String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
				String saveFileName = generateSaveFileName(extName);
				
				byte[] fileData = subattach1.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
				os.write(fileData);
				os.close();
				
				goodsImagesList.add(new GoodsImagesVo(saveFileName, GoodsImagesVo.status.n));
			}
			if(!subattach2.isEmpty()) { 
				String originalFileName = subattach2.getOriginalFilename();
				String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
				String saveFileName = generateSaveFileName(extName);
				
				byte[] fileData = subattach2.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
				os.write(fileData);
				os.close();
				
				goodsImagesList.add(new GoodsImagesVo(saveFileName, GoodsImagesVo.status.n));
			}
			if(!subattach3.isEmpty()) { 
				String originalFileName = subattach3.getOriginalFilename();
				String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
				String saveFileName = generateSaveFileName(extName);
				
				byte[] fileData = subattach3.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
				os.write(fileData);
				os.close();
				
				goodsImagesList.add(new GoodsImagesVo(saveFileName, GoodsImagesVo.status.n));
			}
			
			
			goodsVo.setGoodsImagesList(goodsImagesList);
			
			// 이미지 리스트 세팅 완료
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("in service 등록이 되기전 풀 세팅 상품 vo: "+goodsVo);
		return adminProvider.insertGoods(goodsVo);
	}
	
	private String generateSaveFileName(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

	public List<BigCategoryVo> getNowCategoryList() {
		
		return adminProvider.getNowCategoryList();
	}

	public Integer addCategory(String bigCategoryName, String[] smallCategoryNames) {
		BigCategoryVo bigcategoryVo = new BigCategoryVo();
		bigcategoryVo.setName(bigCategoryName);
		
		List<SmallCategoryVo> smallCategoryList = new ArrayList<SmallCategoryVo>();
		
		smallCategoryList.add(new SmallCategoryVo("(미분류)"));
		
		if (smallCategoryNames.length > 0) {
			
			for (String smallCategoryName : smallCategoryNames) {
				smallCategoryList.add(new SmallCategoryVo(smallCategoryName));
			}
		} 
		
		bigcategoryVo.setSmallCategoryList(smallCategoryList);
		
		
		
		return adminProvider.addCategory(bigcategoryVo);
	}

	public int removeBigCategory(Long bigCategoryNo) {
	
		return adminProvider.removeBigCategory(bigCategoryNo);
	}

	public int removeSmallCategory(Long smallCategoryNo) {
		
		return adminProvider.removeSmallCategory(smallCategoryNo);
	}

	public Integer smallCategoryAdd(Long no, String name) {
		SmallCategoryVo vo = new SmallCategoryVo();
		vo.setBigcategoryNo(no);
		vo.setName(name);
		
		return adminProvider.smallCategoryAdd(vo);
	}

	public Integer smallCategoryEdit(Long no, String name) {
		SmallCategoryVo vo = new SmallCategoryVo();
		vo.setNo(no);
		vo.setName(name);
		
		return adminProvider.smallCategoryEdit(vo);
	}


	

	

	
}
