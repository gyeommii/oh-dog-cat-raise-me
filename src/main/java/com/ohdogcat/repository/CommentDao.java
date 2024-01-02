package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.model.Comment;

public interface CommentDao {
	
	List<Comment> selectByPostFk(long post_fk);
	int insert(Comment comment);

}
