package com.ohdogcat.repository;

import java.util.List;

import com.ohdogcat.model.Post;

public interface PostDao {

	List<Post> selectAllOrderByPostPkDesc();

}
