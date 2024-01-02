package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.dto.post.PostSearchDto;
import com.ohdogcat.model.Post;

public interface PostDao {

	List<Post> selectAllOrderByPostPkDesc();
	
	List<Post> selectAllOrderByPostPkAsc();
	
	int insert(Post post);

	Post selectByPostPk(long post_pk);
	
	int update(Post post);

	int delete(long post_pk);

	List<Post> search(PostSearchDto dto);

	List<Post> selectByCategory(Long category);
	
	// 특정 카테고리의 게시물을 정렬
    List<Post> selectByCategoryAndSort(Long category, String sort);

    // 모든 카테고리의 게시물을 정렬
    List<Post> selectAllSorted(String sort);

	List<Post> searchWithSort(PostSearchDto dto, String sort);

	

	
}
