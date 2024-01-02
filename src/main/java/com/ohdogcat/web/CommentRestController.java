package com.ohdogcat.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohdogcat.dto.post.CommentRegisterDto;
import com.ohdogcat.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post/comment")
public class CommentRestController {
	
	private final CommentService commentService;
	
	@PostMapping
	public ResponseEntity<Integer> registerComment (@RequestBody CommentRegisterDto dto){
		int result = commentService.create(dto);
		
		return ResponseEntity.ok(result);
	}
	
}
