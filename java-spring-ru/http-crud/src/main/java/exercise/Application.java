package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping(path = "/posts")
    public List<Post> getPosts() {
        return this.posts;
    }

    @GetMapping(path = "/posts/{id}")
    public Post getPost(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.getSlug().equals(id))
                .findAny()
                .orElseThrow();
    }

    @PostMapping(path = "/posts")
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping(path = "/posts/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post post) {
        Post uptatedPost = posts.stream()
                .filter(filteredPost -> filteredPost.getSlug().equals(id))
                .findAny()
                .orElseThrow();
        uptatedPost.setBody(post.getBody());
        uptatedPost.setSlug(post.getSlug());
        uptatedPost.setTitle(post.getTitle());

        return post;
    }

    @DeleteMapping(path = "/posts/{id}")
    public void deletePost(@PathVariable String id) {
        posts.removeIf(post -> !post.getTitle().equals(id));
    }
    // END
}
