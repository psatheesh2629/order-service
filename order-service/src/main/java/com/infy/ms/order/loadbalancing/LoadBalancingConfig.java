package com.infy.ms.order.loadbalancing;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancingConfig {

	@Bean
	public ServiceInstanceListSupplier discoverServicesInstanceListSupplier(ConfigurableApplicationContext context) {
		return ServiceInstanceListSupplier.builder().withBlockingDiscoveryClient().withSameInstancePreference()
				.build(context);
	}
}
