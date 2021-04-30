package net.joins.blog.domain.service;

import lombok.RequiredArgsConstructor;
import net.joins.blog.domain.entity.Post;
import net.joins.blog.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    final PostRepository repository;

    public List<Post> getPostList() {
        return repository.findAll();
    }

    public Post getPost(Long postId) {
        return repository.findById(postId).orElseThrow(NullPointerException::new);
    }

}