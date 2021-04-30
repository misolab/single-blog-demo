package net.joins.blog.domain.service;


import lombok.RequiredArgsConstructor;
import net.joins.blog.domain.entity.Post;
import net.joins.blog.domain.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    final PostRepository repository;

    public Page<Post> getPostList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Post getPost(Long postId) {
        return repository.findById(postId).orElseThrow(NullPointerException::new);
    }

}