package com.cafe24.mall.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.vo.CouponVo;

@Service
public class CouponCreateConsumer {

	@Autowired
	private CouponService couponService;
	
    @RabbitListener(queues = "coupon-create")
    public void receiveMessage(final Message message) {
    	String rawMessage = new String(message.getBody());
    
    	CouponVo vo = getString2Couponvo(rawMessage);
    	
    	System.out.println("=============Consumer START===========");
    	System.out.println(vo);
        System.out.println("=============Consumer END===========");
        
        if (isHaveRequireField(vo)) {
        	couponService.couponAdd(vo);
		} else {
			// 발급 실패 로그처리
			System.out.println("발급 실패");
			System.out.println(vo);
		}
    }
    
    private CouponVo getString2Couponvo(String rawMessage) {
    	String data = rawMessage.replace("CouponVo ", "").replace("[", "").replace("]", "");
    	String[] data2 = data.split(",");
    	String[] noData = data2[0].split("=");
    	String[] infoNoData = data2[1].split("=");
    	String[] memberNoData = data2[2].split("=");
    	String[] nameData = data2[3].split("=");
    	String[] saleTypeData = data2[4].split("=");
    	String[] saleValueData = data2[5].split("=");
    	String[] isUsedData = data2[6].split("=");
    	
    	
    	CouponVo vo = new CouponVo();
    	//vo.setNo(Long.parseLong(noData[1]));
    	vo.setInfo_no(infoNoData[1]);
    	vo.setMember_no(memberNoData[1]);
    	vo.setName(nameData[1]);
    	vo.setSale_type(saleTypeData[1]);
    	vo.setSale_value(saleValueData[1]);
    	vo.setIs_used(isUsedData[1]);
    	
    	return vo;
    }
    
    private boolean isHaveRequireField(CouponVo vo) {
    	if (vo.getName() == null || vo.getName().equals("null")) {
			return false;
		}
    	return true;
    }

}