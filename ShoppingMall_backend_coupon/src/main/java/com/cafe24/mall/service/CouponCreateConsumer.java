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
        
        couponService.couponAdd(vo);
    }
    
    private CouponVo getString2Couponvo(String rawMessage) {
    	String data = rawMessage.replace("CouponVo ", "").replace("[", "").replace("]", "");
    	String[] data2 = data.split(",");
    	String[] noData = data2[0].split("=");
    	String[] nameData = data2[1].split("=");
    	String[] saleTypeData = data2[2].split("=");
    	String[] saleValueData = data2[3].split("=");
    	String[] userIdData = data2[4].split("=");
    	String[] isUsedData = data2[5].split("=");
    	String[] infoNoData = data2[6].split("=");
    	
    	CouponVo vo = new CouponVo();
    	vo.setNo(noData[1]);
    	vo.setName(nameData[1]);
    	vo.setSale_type(saleTypeData[1]);
    	vo.setSale_value(saleValueData[1]);
    	vo.setUser_id(userIdData[1]);
    	vo.setIs_used(isUsedData[1]);
    	vo.setInfo_no(infoNoData[1]);
    	return vo;
    }

}