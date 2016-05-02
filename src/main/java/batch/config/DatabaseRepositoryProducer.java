package batch.config;

import org.apache.commons.io.IOUtils;
import org.h2.Driver;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableBatchProcessing
public class DatabaseRepositoryProducer {

	private static final String URL = "jdbc:h2:~/data/project/spring-batch-sample/data/spring-batch-sample;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE";

	@Bean
	public DataSource dataSource() {
		final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(Driver.class);
		dataSource.setUrl(URL);
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@PostConstruct
	public void setup() throws IOException {
		execute("org/springframework/batch/core/schema-drop-h2.sql");
		execute("org/springframework/batch/core/schema-h2.sql");
		execute("business-table.ddl");
	}

	private void execute(String fileName) throws IOException {
		InputStream inputStream = new ClassPathResource(fileName).getInputStream();
		String sql = IOUtils.toString(inputStream);
		jdbcTemplate().execute(sql);
	}
}
