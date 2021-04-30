package net.joins.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ock
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Autowired
//    PostRepository repository;
//
//    @Bean
//    public CommandLineRunner insertDB() {
//        return args -> {
//            List<Post> list = IntStream.range(1, 100)
//                    .mapToObj(i -> Post.builder()
//                            .link("post.html")
//                            .title("제목 - " + i)
//                            .subtitle("subtitle - " + i)
//                            .writter("misolab")
//                            .build())
//                    .collect(Collectors.toList());
//            repository.saveAll(list);
//        };
//    }
}
