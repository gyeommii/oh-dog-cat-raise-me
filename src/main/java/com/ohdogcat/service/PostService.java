package com.ohdogcat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ohdogcat.dto.post.PostCreateDto;
import com.ohdogcat.dto.post.PostDto;
import com.ohdogcat.dto.post.PostListItemDto;
import com.ohdogcat.dto.post.PostSearchDto;
import com.ohdogcat.dto.post.PostUpdateDto;
import com.ohdogcat.model.Post;
import com.ohdogcat.repository.PostDao;
import com.ohdogcat.util.DateTimeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

	private final PostDao postDao;

	public List<PostListItemDto> getPostsList() {
		List<Post> list = postDao.selectAllOrderByPostPkDesc();
		return list.stream().map(post -> {
			PostListItemDto dto = PostListItemDto.fromEntity(post);
			dto.setFormattedModifiedTime(DateTimeUtil.formatLocalDateTime(post.getModified_time()));
			return dto;
		}).toList();
	}

	public List<PostListItemDto> getPostsByCategory(Long category) {
		List<Post> list = postDao.selectByCategory(category);
		return list.stream().map(PostListItemDto::fromEntity).toList();
	}

	// 특정 카테고리의 게시물 정렬
	public List<PostListItemDto> getPostsByCategoryAndSort(Long category, String sort) {
		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("category", category);
		mapInfo.put("sort", sort);

		List<Post> list = postDao.selectByCategoryAndSort(mapInfo);
		return list.stream().map(PostListItemDto::fromEntity).toList();
	}

	// 모든 카테고리의 게시물 정렬
	public List<PostListItemDto> getPostsSorted(String sort) {
		List<Post> list = postDao.selectAllSorted(sort);
		return list.stream().map(PostListItemDto::fromEntity).toList();
	}

	// 검색 결과를 정렬
	public List<PostListItemDto> searchWithSort(PostSearchDto dto, String sort) {
		List<Post> list = postDao.searchWithSort(dto, sort);
		return list.stream().map(PostListItemDto::fromEntity).toList();
	}

	public int createPost(PostCreateDto dto) {
		int result = postDao.insert(dto.toEntity());

		return result;
	}

	public PostDto read(long post_pk) {
		Post post = postDao.selectByPostPk(post_pk);
		return PostDto.fromEntity(post);
	}

	public int delete(Long post_pk) {
		log.debug("delete(id={})", post_pk);

		int result = postDao.delete(post_pk);
		log.debug("delete result = {}", result);

		return result;
	}

	public int update(PostUpdateDto dto) {
		log.debug("update(dto={})", dto);

		int result = postDao.update(dto.toEntity());
		log.debug("update result = {}", result);

		return result;
	}

	public List<PostListItemDto> search(PostSearchDto dto) {
		log.debug("search(dto={})", dto);

		List<Post> list = postDao.search(dto);

		return list.stream().map(PostListItemDto::fromEntity).toList();
	}

	public List<PostListItemDto> getPostsListSorted(String sort) {
		List<Post> list;
		if ("asc".equals(sort)) {
			list = postDao.selectAllOrderByPostPkAsc();
		} else {
			list = postDao.selectAllOrderByPostPkDesc();
		}
		log.debug("list={}", list);
		return list.stream().map(PostListItemDto::fromEntity).toList();
	}

}
