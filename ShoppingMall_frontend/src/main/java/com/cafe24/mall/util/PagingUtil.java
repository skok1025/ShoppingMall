package com.cafe24.mall.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 페이징을 위한 유틸
 * @author 김석현
 *
 */
public class PagingUtil {

	/**
	 * 페이징 변수를 가진 맵을 반환하는 메소드
	 * @param currentPage 현재페이지
	 * @param totalcount 전체 게시물 수 
	 * @param pageSize 한 페이지의 게시물 수 
	 * @param listSize 페이징 리스트 수   ex)  < 1 2 3 4 5 >  이라면 listSize = 5
	 * @return 페이징 변수를 담은 Map
	 *         currentPage = 현재 페이지
	 *         endPage = 마지막 페이지
	 *         prevPage = 현재 페이지 리스트의 이전 페이지 (이전 페이지 리스트의 마지막 페이지)
	 *         nextPage = 현재 페이지 리스트의 다음 페이지 (다음 페이지 리스트의 첫번쨰 페이지)
	 *         nowStart = 현재 페이지 리스트의 시작 페이지
	 *         nowEnd = 현재 페이지 리스트의 마지막 페이지 
	 * @author 김석현
	 */
	public static Map<String, Integer> 
	getPagingVariable
	(Integer currentPage,Integer totalcount,Integer pageSize,Integer listSize){
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		Integer pageCount = (int) Math.ceil((double)totalcount/pageSize);
		
		if(currentPage > pageCount) {
			currentPage = pageCount;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		Integer listNum = (int)Math.ceil((double)currentPage/ listSize); // 페이지 리스트의 인덱스
		Integer nextPage = 1 + listSize * listNum; // 현재 페이지 리스트의 다음 페이지 (다음 페이지 리스트의 첫번쨰 페이지)
		Integer prevPage = listNum== 1 ? 1 : nextPage - listSize - 1; // 현재 페이지 리스트의 이전 페이지 (이전 페이지 리스트의 마지막 페이지)
		Integer endPage = (int)Math.ceil((double)totalcount / pageSize); // 마지막 페이지
		
		// 현재 페이지의 리스트 시작과 끝
		Integer nowStart = listNum*listSize-(listSize-1);
		Integer nowEnd = listNum*listSize;
		
		result.put("currentPage", currentPage);
		result.put("endPage", endPage);
		result.put("prevPage", prevPage);
		result.put("nextPage", nextPage);
		
		result.put("nowStart", nowStart);
		result.put("nowEnd", nowEnd);
		
		
		
		return result;
		
	}
	
	
	/**
	 * 가지고 올 게시물 시작 인덱스를 반환하는 메소드
	 * @param currentPage 현재 페이지
	 * @param totalCount 전체 게시물 수 
	 * @param pageSize 한 페이지의 게시물 수 
	 * @return 가지고 올 게시물 시작 인덱스
	 */
	public static Integer getStartRecordNum
	(Integer currentPage,
	Integer totalCount,		
	Integer pageSize
	) {
		Integer pageCount = (int) Math.ceil((double)totalCount/pageSize);
		
		if(currentPage > pageCount) {
			currentPage = pageCount;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		
		return (currentPage- 1) * pageSize;
	}
	
}
