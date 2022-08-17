package com.project.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
