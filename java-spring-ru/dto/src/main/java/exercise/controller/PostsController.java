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
import java.util.stream.Collectors;

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
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post with id " + id + " not found")
        );
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        List<CommentDTO> commentDTOS = new ArrayList<>();

        if(!comments.isEmpty()) {
            for (Comment comment : comments) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                commentDTO.setBody(comment.getBody());
                commentDTOS.add(commentDTO);
            }
        }

        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setComments(commentDTOS);

        return postDTO;
    }

    @GetMapping
    public List<PostDTO> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();

        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setBody(post.getBody());

            List<Comment> comments = commentRepository.findByPostId(post.getId());
            List<CommentDTO> commentDTOS = new ArrayList<>();

            if (!comments.isEmpty()) {
                for (Comment comment : comments) {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setId(comment.getId());
                    commentDTO.setBody(comment.getBody());
                    commentDTOS.add(commentDTO);
                }
            }

            postDTO.setComments(commentDTOS);
            postDTOList.add(postDTO);
        }

        return postDTOList;
    }
}
// END
