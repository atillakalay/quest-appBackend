package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Like;
import com.project.questapp.repos.LikeRepository;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;

	public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
		if (userId.isPresent()) {
			return likeRepository.findByUserId(userId.get());
		}
		if (postId.isPresent()) {
			return likeRepository.findByPostId(postId.get());
		}
		return likeRepository.findAll();
	}

	public Like getOneLikeById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}

}
