package batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldJobProducer {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CreateCustomerTasklet createCustomerTasklet;

	@Bean(name = "helloWorldJob")
	public Job getHelloWorldJob() {
		return jobBuilderFactory.get("helloWorldJob")
				.start(getCreateCustomerStep())
				.next(getHelloStep())
				.next(getSpaceStep())
				.next(getWorldStep())
				.build();
	}

	@Bean
	public Step getCreateCustomerStep() {
		return stepBuilderFactory.get("createCustomer")
				.tasklet(createCustomerTasklet).build();
	}

	@Bean
	public Step getHelloStep() {
		return stepBuilderFactory.get("helloStep")
				.tasklet(new PrintTasklet("Hello")).build();
	}

	@Bean
	public Step getSpaceStep() {
		return stepBuilderFactory.get("spaceStep")
				.tasklet(new PrintTasklet(" ")).build();
	}

	@Bean
	public Step getWorldStep() {
		return stepBuilderFactory.get("worldStep")
				.tasklet(new PrintTasklet("world!")).build();
	}

}
