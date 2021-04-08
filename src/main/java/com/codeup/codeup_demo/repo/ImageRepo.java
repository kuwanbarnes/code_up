package com.codeup.codeup_demo.repo;

import com.codeup.codeup_demo.models.Image;
import com.codeup.codeup_demo.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findByImagePath(String image);
    Image save(String img);

}
