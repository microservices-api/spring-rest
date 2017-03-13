package net.wasdev.swaggersample.spring.dba;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


//------------------------------------------------------------------------------------------------
// Definition of Data Source. 
//------------------------------------------------------------------------------------------------
@Configuration
public class DataSourceConfig {

	/* Data Source for DB2 */
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(com.ibm.db2.jcc.DB2Driver.class.getName());
		driverManagerDataSource.setUrl("jdbc:db2://localhost:50000/SAMPLE");
		driverManagerDataSource.setUsername("db2admin");
		driverManagerDataSource.setPassword("password");
		return driverManagerDataSource;
	}
}
