package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import exercise.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;
    private final CommentService commentService;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getCommentsForPost(@PathVariable("postId") long postId) {
        return commentRepository.findByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getExactPostComment(@PathVariable("postId") long postId,
                                                 @PathVariable("commentId") long commentId) {
        Comment result = commentRepository.findByIdAndPostId(commentId, postId);
        if (result != null) {
            return result;
        } else {
            throw new ResourceNotFoundException("Comment bot found");
        }
    }

    @PostMapping(path = "/{postId}/comments")
    public String addComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto, postId);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public String updateComment(@PathVariable long postId,
                                @PathVariable long commentId,
                                @RequestBody CommentDto commentDto) {
        return commentService.updateComment(commentDto, postId, commentId);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable("postId") long postId,
                                @PathVariable("commentId") long commentId) {
        System.out.println("POST ID: " + postId);
        System.out.println("COMMENT ID: " + commentId);
        return commentService.deleteComment(postId, commentId);
    }
    // END
}
