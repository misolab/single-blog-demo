package net.joins.blog.domain.repository;

import net.joins.blog.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUpdatedBeforeAndSubtitleIsNull(Timestamp timestamp);
}