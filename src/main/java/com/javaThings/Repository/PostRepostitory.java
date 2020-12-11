package com.javaThings.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaThings.Bean.Post;

@Repository
public interface PostRepostitory extends JpaRepository<Post, Integer>{
	
}
