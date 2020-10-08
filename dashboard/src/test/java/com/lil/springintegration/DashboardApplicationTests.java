package com.lil.springintegration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class DashboardApplicationTests {

	@Test
	void contextLoads() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/application.xml", DashboardApplicationTests.class);
		try {
			context.getBean("testMessageFlowImports");
			System.out.println("Spring Integration message flows imported successfully!");
			assert(true);
		} catch(NoSuchBeanDefinitionException e) {
			System.out.println(e.toString());
			assert(false);
		} finally {
			context.close();
		}
	}

}
