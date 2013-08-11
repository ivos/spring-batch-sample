package batch.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.MapExecutionContextDao;
import org.springframework.batch.core.repository.dao.MapJobExecutionDao;
import org.springframework.batch.core.repository.dao.MapJobInstanceDao;
import org.springframework.batch.core.repository.dao.MapStepExecutionDao;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryRepositoryProducer {

	@Bean
	public JobRepository getJobRepository() {
		return new SimpleJobRepository(new MapJobInstanceDao(),
				new MapJobExecutionDao(), new MapStepExecutionDao(),
				new MapExecutionContextDao());
	}

	@Bean
	public JobBuilderFactory getJobBuilderFactory() {
		return new JobBuilderFactory(getJobRepository());
	}

	@Bean
	public StepBuilderFactory getStepBuilderFactory() {
		return new StepBuilderFactory(getJobRepository(),
				new ResourcelessTransactionManager());
	}

	@Bean
	public JobLauncher getJobLauncher() {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(getJobRepository());
		return launcher;
	}

}
