package com.ohdogcat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.post.CommentRegisterDto;
import com.ohdogcat.model.Comment;
import com.ohdogcat.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
	
	private final CommentDao commentDao;

	public int create(CommentRegisterDto dto) {
		log.debug("create(dto={})", dto);
		
		int result = commentDao.insert(dto.toEntity());
		log.debug("댓글 등록 결과 = {} ", result); // result가 1이면 성공
		
		return result;
	}
	
	// 댓글 수정
    public int update(CommentRegisterDto dto) {
        return commentDao.update(dto.toEntity());
    }

    // 댓글 삭제
    public int delete(Long commentId) {
        return commentDao.delete(commentId);
    }

    public List<Comment> getAllCommentsByPost(Long postId) {
        return commentDao.selectByPostId(postId);
    }
	
	

}
