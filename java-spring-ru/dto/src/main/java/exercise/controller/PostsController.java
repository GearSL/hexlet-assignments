package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping(path = "/posts")
class PostsController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping(path = "/{id}")
    public PostDTO getPost(@PathVariable long id) {
        var post =  postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return toPostDTO(post);
    }

    @GetMapping
    public List<PostDTO> getPosts() {
        var posts = postRepository.findAll();
        return posts.stream()
                .map(this::toPostDTO)
                .toList();
    }

    private PostDTO toPostDTO(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        var comments = commentRepository.findByPostId(post.getId());
        var commentsDto = comments.stream()
                .map((comment) -> {
                    var commentDto = new CommentDTO();
                    commentDto.setBody(comment.getBody());
                    commentDto.setId(comment.getId());
                    return commentDto;
                })
                .toList();
        dto.setComments(commentsDto);
        return dto;
    }
}
// END
