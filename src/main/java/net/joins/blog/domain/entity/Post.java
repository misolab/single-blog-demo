package net.joins.blog.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_POST")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    Long id;

    @Column(name = "LINK")
    String link;

    @Column(name = "TITLE", nullable = false)
    String title;

    @Setter
    @Column(name = "SUBTITLE")
    String subtitle;

    @Column(name = "WRITTER", nullable = false)
    String writter;

    @Column(name = "UPDATED")
    Timestamp updated;

    @Column(name = "CONTENT")
    String content;

    @Column(name = "BG_IMAGE")
    String bgImage;
}