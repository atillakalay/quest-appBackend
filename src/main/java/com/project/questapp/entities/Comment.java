package com.project.questapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "comment")
@Data
public class Comment {
	@Id
	Long id;
	Long postId;
	Long userId;

	@Lob
	@Column(columnDefinition = "text")
	String text;

}
