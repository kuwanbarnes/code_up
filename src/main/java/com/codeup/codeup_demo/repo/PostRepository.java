package com.codeup.codeup_demo.repo;

import com.codeup.codeup_demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post>findByTitleEquals(String titleToSearchFor);
    List<Post>findById(int id);

    @Query("from Post post where post.body like %:term%")
    List<Post> searchByBodyLike(@Param("term") String term);

    @Query("from Post post where post.title like %:title%")
    List<Post>deleteByTitle(@Param("title") String title);

    @Query("from Post post where post.id in :ids ")
    List<Post>deleteById(@Param("postId") int ids);

    @Query("from Post post where post.id in :ids ")
    List<Post>(@Param("postId") int ids);
//
//    @Query("from Post post where post.title like %:title%")
//    List<Post>findByTitle(@Modifying(flushAutomatically = false));



}
