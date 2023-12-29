package com.ohdogcat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.post.PostListItemDto;
import com.ohdogcat.model.Post;
import com.ohdogcat.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
	
	private final PostDao postDao;
	
	public List<PostListItemDto> getPostsList() {
		
		List<Post> list = postDao.selectAllOrderByPostPkDesc();
		log.debug("포스트 목록 개수={}", list);
		
		return list.stream()
				.map(PostListItemDto::fromEntity)
				.toList();
	}
	


}
