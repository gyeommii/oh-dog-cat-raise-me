package com.ohdogcat.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohdogcat.dto.member.MemberSessionDto;
import com.ohdogcat.dto.post.CommentDto;
import com.ohdogcat.dto.post.CommentRegisterDto;
import com.ohdogcat.model.Comment;
import com.ohdogcat.service.CommentService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post/comment")
public class CommentRestController {
	
	private final CommentService commentService;
	
//	@PostMapping
//	public ResponseEntity<Integer> registerComment (@RequestBody CommentRegisterDto dto){
//		int result = commentService.create(dto);
//		
//		return ResponseEntity.ok(result);
//	}
	
	@PostMapping
    public ResponseEntity<Integer> registerComment(@RequestBody CommentRegisterDto dto, HttpSession session) {
        // 세션에서 로그인한 사용자의 정보 가져오기
        MemberSessionDto signedMember = (MemberSessionDto) session.getAttribute("signedMember");
        if (signedMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(-1);// TODO 수정: 로그인페이지로 리다이렉트? 
        }

        long memberFk = signedMember.getMember_pk();
        dto.setMember_fk(memberFk);

        int result = commentService.create(dto);
        return ResponseEntity.ok(result);
    }
	
	@GetMapping("/all/{postId}")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable Long postId) {
	    List<Comment> comments = commentService.getAllCommentsByPost(postId);
	    List<CommentDto> commentDtos = comments.stream()
	            .map(comment -> new CommentDto(comment.getComments_pk(),
	                                           comment.getPost_fk(),
	                                           comment.getMember_fk(),
	                                           comment.getCtext(),
	                                           comment.getFormattedCreatedTime(),
	                                           comment.getFormattedModifiedTime()))
	            .collect(Collectors.toList());
	    return ResponseEntity.ok(commentDtos);
	}
	
	// 댓글 수정
	@PutMapping("/{commentId}")
	public ResponseEntity<Integer> updateComment(@PathVariable("commentId") Long commentId,
	                                             @RequestBody CommentRegisterDto dto) {
	    dto.setComments_pk(commentId); // DTO에 comments_pk 설정
	    int result = commentService.update(dto);
	    return ResponseEntity.ok(result);
	}

    
    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Integer> deleteComment(@PathVariable("commentId") Long commentId) {
        int result = commentService.delete(commentId);
        return ResponseEntity.ok(result);
    }
	
	
}
