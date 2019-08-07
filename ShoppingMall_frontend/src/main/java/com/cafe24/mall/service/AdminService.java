package com.cafe24.mall.service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mall.provider.AdminProvider;
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

	public List<MemberVo> getMemberList(String id, String orderDateStart, String orderDateEnd) {
		return adminProvider.selectMemberList(id,orderDateStart,orderDateEnd);
	}

	public int removerMember(Long userNo) {
		return adminProvider.deleteMember(userNo);
	}

	public List<GoodsVo> getGoodsList(Long pageNum) {
		return adminProvider.selectGoodsList(pageNum);
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
		
		if(!goodsVo.getOptionListTxt().equals("")) {
			
			String[] options = goodsVo.getOptionListTxt().split(",");
			
			int i = 0;
			
			for(String optionInfo:options) {
				if(i==0) continue;
				
				String[] optionInfos = optionInfo.split("++");
				
				String optionName = optionInfos[0];
				Integer optionCnt = Integer.parseInt(optionInfos[1]);
				
				goodsDetailList.add(new GoodsDetailVo(optionName, optionCnt, optionCnt));
				
				i++;
			}
			
			goodsVo.setGoodsDetailList(goodsDetailList);
			// 옵션값들 세팅 완료
		}
		
		
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

	
}
