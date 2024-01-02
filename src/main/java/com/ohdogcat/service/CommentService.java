package com.ohdogcat.service;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.post.CommentRegisterDto;
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
	
	

}
