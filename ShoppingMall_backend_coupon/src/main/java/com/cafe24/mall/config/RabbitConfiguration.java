package com.cafe24.mall.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cafe24.mall.lib.libCouponConst;

@Configuration
public class RabbitConfiguration {
	
	/**
	 * Producer 
	 * 1) 메세지를 생성하고 발송하는 주체
	 * 2) Queue 애 직접 접근하지 않고 항상 Exchange 를 통해 접근.
	 * 
	 * Consumer 
	 * 1) 메세지를 수신하는 주체
	 * 2) Queue 에 직접 접근하여 메세지를 가져옴.
	 * 
	 * Queue
	 * 1) Producer 들이 발송한 메세지들이 Consumer 가 소비하기 전까지 보관되는 장소
	 * 2) Queue 는 이름으로 구분되는데 같은 이름과 같은 설정으로 Queue 를 생성하면 에러없이 기존 Queue 에 연결됨
	 * 3) 같은 이름 다른 설정으로 Queue 를 생성하려고 시도하면 에러가 발생함.
	 * 
	 * Exchange 
	 * 1) Producer 들에게서 전달받은 메세지들을 어떤 Queue 들에게 발송할지를 결정하는 객체
	 * 2) Exchange 는 네가지 타입이 있으며 일종의 라우터 개념
	 *      => Exchange 네가지 타입
	 *          type1) Direct
	 *                 : Routing key 가 정확히 일치하는 Queue에 메세지를 전송 (Unicast)
	 *          type2) Topic 
	 *                 : Routing key 패턴이 일치하는 Queue 에 메세지 전송 (Multicast)
	 *          type3) Headers
	 *                 : [key:value] 로 이루어진 header 값을 기준으로 일치하는 Queue 에 메세지 전송 (Multicast)
	 *          type4) Fanout
	 *                 : 해당 Exchange 에 등록된 모든 Queue 에 메세지 전송 (Broadcast)
	 * 
	 * Binding
	 * 1) Exchange 에게 메세지를 라우팅할 규칙을 지정하는 행위
	 * 2) 특정 조건에 맞는 메세지를 특정 큐에 전송하도록 설정할 수 있음.
	 * 
	 */

    @Bean
    Queue queue() {
        return new Queue(libCouponConst.COUPON_CREATE_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(libCouponConst.TOPIC_EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(libCouponConst.CREATE_ROUTING_KEY);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                  MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}