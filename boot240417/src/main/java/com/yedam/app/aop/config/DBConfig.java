package com.yedam.app.aop.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // 설정을 담당하는 bean.
@EnableTransactionManagement // 트랜잭션 매니저.(트랜잭션을 제어, DB에 대한 접근을 제어하는 것 아님)
public class DBConfig {

	//private final DataSource datasource; 이렇게 해도되고..
	
	@Bean
	public TransactionManager transactionManager(DataSource datasource) { // PlatformTransactionManager 해도됨.
		return new DataSourceTransactionManager(datasource);
	}
	
	// 2가지가 필요하다. 매퍼와 서비스.
	
}
