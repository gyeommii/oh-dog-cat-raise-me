package com.ohdogcat.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ohdogcat.dto.post.PostListItemDto;
import com.ohdogcat.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/community")
public class PostController {
	
	private final PostService postService;
	
	// 전체목록
	@GetMapping("/list")
	public void list(Model model) {
		log.debug("list()");
		
		List<PostListItemDto> list = postService.getPostsList();
		model.addAttribute("posts", list);
	}
	
	@GetMapping("/createPost")
	public void createPost() {
		log.debug("GET - createPost()");
	}
	
	
	
	
	
	
	
	

}
