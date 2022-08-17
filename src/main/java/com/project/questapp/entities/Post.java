package com.project.questapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {
	@Id
	Long id;
	Long userId;
	String title;
	@Lob
	@Column(columnDefinition = "text")
	String text;
}
