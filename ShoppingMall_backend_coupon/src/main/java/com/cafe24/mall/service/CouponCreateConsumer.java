package com.cafe24.mall.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.cafe24.mall.vo.CouponVo;

@Service
public class CouponCreateConsumer {

    @RabbitListener(queues = "coupon-create")
    public void receiveMessage(final Message message) {
    	String rawMessage = new String(message.getBody());
    
    	CouponVo vo = getString2Couponvo(rawMessage);
    	
    	System.out.println("=============Consumer START===========");
    	System.out.println(vo);
        System.out.println("=============Consumer END===========");
    }
    
    private CouponVo getString2Couponvo(String rawMessage) {
    	String data = rawMessage.replace("CouponVo ", "").replace("[", "").replace("]", "");
    	String[] data2 = data.split(",");
    	String[] noData = data2[0].split("=");
    	String[] nameData = data2[1].split("=");
    	String[] typeData = data2[2].split("=");
    	String[] salerateData = data2[3].split("=");
    	
    	CouponVo vo = new CouponVo();
    	vo.setNo(noData[1]);
    	vo.setName(nameData[1]);
    	vo.setType(typeData[1]);
    	vo.setSalerate(salerateData[1]);
    	return vo;
    }

}