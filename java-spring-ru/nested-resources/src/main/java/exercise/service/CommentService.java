package exercise.service;

import exercise.ResourceNotFoundException;
import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentService {
@Autowired
PostRepository postRepository;
@Autowired
CommentRepository commentRepository;

    public String createComment(CommentDto commentDto, long postId) {
        Comment comment = new Comment();

        if (postRepository.existsById(postId)) {
            comment.setPost(postRepository.findPostById(postId));
            comment.setContent(commentDto.content());
            commentRepository.save(comment);
            return "Comment was created";
        } else {
            return "Comment wasn't created";
        }
    }

    public String updateComment(CommentDto commentDto, long postId, long commentId) {
        if (commentRepository.existsById(commentId)) {
            Comment comment = commentRepository.findByIdAndPostId(commentId, postId);
            comment.setContent(commentDto.content());
            commentRepository.save(comment);
            return "Comment was updated";
        } else {
            throw new ResourceNotFoundException("Comment not found");
        }
    }

    public String deleteComment(CommentDto commentDto, long postId, long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId);
        if (commentRepository.existsById(commentId) && comment != null) {
            comment.setContent(commentDto.content());
            commentRepository.delete(comment);
            return "Comment was deleted";
        } else {
            throw new ResourceNotFoundException("Comment not found");
        }
    }
}
