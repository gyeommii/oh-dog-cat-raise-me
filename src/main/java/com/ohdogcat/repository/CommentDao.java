package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.model.Comment;

public interface CommentDao {
	
	List<Comment> selectByPostFk(long post_fk);
	
	int insert(Comment comment);
	
	// 댓글 수정
    int update(Comment comment);
    
    // 댓글 삭제
    int delete(Long commentId);

	List<Comment> selectByPostId(Long postId);

	
	
}
