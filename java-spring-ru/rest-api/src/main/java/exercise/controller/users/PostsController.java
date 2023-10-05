package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RequestMapping(path = "/api")
@RestController
class PostsController {

    private final List<Post> postsData = Data.getPosts();

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) {
        return postsData.stream()
                .filter(post -> post.getUserId() == id)
                .toList();
    }

    @PostMapping(path = "/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@PathVariable int id, @RequestBody Post post) {
        post.setUserId(id);
        postsData.add(post);
        return post;
    }
}
// END
