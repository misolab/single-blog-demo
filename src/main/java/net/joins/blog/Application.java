package net.joins.blog;

import net.joins.blog.batch.OldPostBatchConfig;
import net.joins.blog.domain.entity.Post;
import net.joins.blog.domain.repository.PostRepository;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ock
 */
@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    PostRepository repository;

    @Bean
    public CommandLineRunner insertDB() {
        LocalDateTime updated = LocalDateTime.now().minusSeconds(100);

        return args -> {
            List<Post> list = IntStream.range(1, 10)
                    .mapToObj(i -> Post.builder()
                            .link("post.html")
                            .title("제목 - " + i)
                            .writter("misolab")
                            .updated(Timestamp.valueOf(updated.plusSeconds(i * 10)))
                            .build())
                    .collect(Collectors.toList());
            repository.saveAll(list);
        };
    }

    @Autowired
    OldPostBatchConfig oldPostBatchConfig;

    @Autowired
    JobLauncher jobLauncher;

    private JobParameters currentTimeJobParameter() {
        Map<String, JobParameter> m = new HashMap<>();
        m.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters p = new JobParameters(m);
        return p;
    }

    @Scheduled(fixedDelay = 10000)
    void batchOldPost() {
        try {
            jobLauncher.run(oldPostBatchConfig.hiddenOldPostJob(), currentTimeJobParameter());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
