package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.CommentRepository;
import com.project.questapp.repos.PostRepository;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;

	}

	public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
		if (userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
		}
		if (userId.isPresent()) {
			return commentRepository.findByUserId(userId.get());
		}
		if (postId.isPresent()) {
			return commentRepository.findByPostId(postId.get());
		}
		return commentRepository.findAll();
	}

	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest newCommentCreateRequest) {
		User user = userService.getOneUserById(newCommentCreateRequest.getUserId());
		Post post = postService.getOnePostById(newCommentCreateRequest.getPostId());
		if (user == null && post == null)
			return null;
		Comment toSave = new Comment();
		toSave.setId(newCommentCreateRequest.getId());
		toSave.setText(newCommentCreateRequest.getText());
		toSave.setUser(user);
		return commentRepository.save(toSave);
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isPresent()) {
			Comment toUpdate = comment.get();
			toUpdate.setText(commentUpdateRequest.getText());
			commentRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
	}

}
