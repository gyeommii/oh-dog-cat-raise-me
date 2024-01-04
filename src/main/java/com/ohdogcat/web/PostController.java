package com.ohdogcat.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.post.PostCreateDto;
import com.ohdogcat.dto.post.PostDto;
import com.ohdogcat.dto.post.PostListItemDto;
import com.ohdogcat.dto.post.PostSearchDto;
import com.ohdogcat.dto.post.PostUpdateDto;
import com.ohdogcat.model.Post;
import com.ohdogcat.service.PostService;

import jakarta.servlet.http.HttpSession;
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
	    public void list(@RequestParam(required = false) Long category,
	                       @RequestParam(required = false, defaultValue = "desc") String sort,
	                       Model model) {
	        List<PostListItemDto> list;

	        if (category != null && category > 0) {
	            // 특정 카테고리의 게시물을 정렬 순서에 따라 가져옵니다.
	            list = postService.getPostsByCategoryAndSort(category, sort);
	        } else {
	            // 모든 카테고리의 게시물을 정렬 순서에 따라 가져옵니다.
	            list = postService.getPostsSorted(sort);
	        }

	        model.addAttribute("posts", list);
	    }

	@GetMapping("/createPost")
	public void createPost() {
		log.debug("GET - createPost()");
	}
	
	@PostMapping("/createPost")
	public String createPost(PostCreateDto dto, HttpSession session) {
	    log.debug("POST - createPost(dto={})", dto);

	    MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
	    if (signedMember == null) {
	        return "redirect:/user/signin";
	    }
	    dto.setMember_fk(signedMember.getMember_pk());

	    postService.createPost(dto);

	    return "redirect:/community/list"; 
	}

	
	@GetMapping({"/details", "/modify"})
    public void details(@RequestParam(name = "post_pk") long post_pk, Model model) {
        PostDto postDto = postService.read(post_pk);
        model.addAttribute("post", postDto);
    }
	
	@GetMapping("/delete")
	public String delete(Long post_pk, HttpSession session) {
	    MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
	    if (signedMember == null) {
	    	return "redirect:/user/signin";
	    }
	    postService.delete(post_pk);
	    return "redirect:/community/list";
	}


	@PostMapping("/update")
	public String update(PostUpdateDto dto, HttpSession session) {
	    MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
	    if (signedMember == null) {
	        return "redirect:/user/signin";
	    }
	    postService.update(dto);
	    return "redirect:/community/list";
	}


	@GetMapping("/search")
	public String search(PostSearchDto dto, Model model) {
		log.debug("search(dto={})", dto);

		List<PostListItemDto> list = postService.search(dto);

		model.addAttribute("posts", list);

		return "community/list"; 
		
	}
	
//	@GetMapping("/search")
//	public void search(@RequestParam(required = false) String category, 
//	                     @RequestParam(required = false) String searchCategory,
//	                     @RequestParam(required = false) String keyword,
//	                     @RequestParam(required = false, defaultValue = "desc") String sort, 
//	                     Model model) {
//
//	    Long categoryId = null;
//	    if (category != null && !category.equals("all")) {
//	        try {
//	            categoryId = Long.parseLong(category);
//	        } catch (NumberFormatException e) {
//	            log.error("Invalid category format: {}", category, e);
//	        }
//	    }
//
//	    PostSearchDto searchDto = new PostSearchDto();
//	    searchDto.setPostCategory(categoryId);
//	    searchDto.setSearchCategory(searchCategory);
//	    searchDto.setKeyword(keyword);
//
//	    List<PostListItemDto> list = postService.searchWithSort(searchDto, sort);
//	    model.addAttribute("posts", list);
//	}


	@GetMapping("/listOrdered")
	public String list(@RequestParam(required = false, defaultValue = "desc") String sort, Model model) {
	    List<PostListItemDto> list = postService.getPostsListSorted(sort);
	    model.addAttribute("posts", list);
	    return "community/list";
	}

	
	
	
	
	

}
