package com.lil.springintegration;

import com.lil.springintegration.service.CustomerAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootTest
class DashboardApplicationTests {

	AbstractApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml", DashboardApplicationTests.class);

	@Test
	void springIntegrationContextLoads() {
		try {
			context.getBean("testMessageFlowImports");
			System.out.println("Spring Integration message flows imported successfully.");
			assert(true);
		} catch(NoSuchBeanDefinitionException e) {
			System.out.println(e.toString());
			assert(false);
		} finally {
			context.close();
		}
	}

	@Test
	void customerAccountServiceCreditApplied() {
		DirectChannel apiInputChannel = (DirectChannel) context.getBean("apiInputChannel");
		String apiResponse = "{\"runningVersion\":\"CH.03_03\",\"updateRequired\":true,\"netWind\":36,\"netSolar\":11,\"snapTime\":\"Fri Oct 30 12:29:26 CDT 2020\"}";
		assert(!CustomerAccountService.isAccountCredit());
		apiInputChannel.send(MessageBuilder.withPayload(apiResponse).build());
		assert(CustomerAccountService.isAccountCredit());
	}

}
