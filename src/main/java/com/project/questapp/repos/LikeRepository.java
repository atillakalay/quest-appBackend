package com.project.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
