package net.joins.blog.batch;

import lombok.RequiredArgsConstructor;
import net.joins.blog.domain.entity.Post;
import net.joins.blog.domain.repository.PostRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class OldPostBatchConfig {

    private static final String OLD_POST_BATCH_JOB = "old_post_batch_job";
    private static final String OLD_POST_BATCH_STEP = "old_post_batch_step";

    final JobBuilderFactory jobBuilderFactory;
    final StepBuilderFactory stepBuilderFactory;
    final PostRepository postRepository;

    public Job hiddenOldPostJob() {
        return jobBuilderFactory.get(OLD_POST_BATCH_JOB)
                .start(hiddenOldPostStep())
                .build();
    }

    private Step hiddenOldPostStep() {
        return stepBuilderFactory.get(OLD_POST_BATCH_STEP)
                .<Post, Post>chunk(10)
                .reader(readOldPost())
                .processor(hiddenOlPost())
                .writer(saveOldPost())
                .build();
    }

    private ItemReader<Post> readOldPost() {
        Timestamp old = Timestamp.valueOf(LocalDateTime.now().minusSeconds(100));
        List<Post> list = postRepository.findByUpdatedBeforeAndSubtitleIsNull(old);
        return new ListItemReader<>(list);
    }

    private ItemProcessor<Post, Post> hiddenOlPost() {
        return post -> {
            post.setSubtitle("Old Post");
            return post;
        };
    }

    private ItemWriter<Post> saveOldPost() {
        return list -> postRepository.saveAll(list);
    }
}
