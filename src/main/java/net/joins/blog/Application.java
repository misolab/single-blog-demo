package net.joins.blog;

import net.joins.blog.domain.entity.Post;
import net.joins.blog.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ock
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    PostRepository repository;

    @Bean
    public CommandLineRunner insertDB() {
        return args -> {
            List<Post> list = IntStream.range(1, 10)
                    .mapToObj(i -> Post.builder()
                            .link("post.html")
                            .title("제목 - " + i)
                            .subtitle("subtitle - " + i)
                            .writter("misolab")
                            .build())
                    .collect(Collectors.toList());
            repository.saveAll(list);
        };
    }
}
