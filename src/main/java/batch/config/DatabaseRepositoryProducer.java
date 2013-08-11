package batch.config;

import javax.sql.DataSource;

import org.h2.Driver;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@EnableBatchProcessing
public class DatabaseRepositoryProducer {

	private static final String URL = "jdbc:h2:~/data/project/spring-batch-sample/data/spring-batch-sample"
			+ ";AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE";

	@Bean
	public DataSource getDataSource() {
		final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(Driver.class);
		dataSource.setUrl(URL);
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}
}
