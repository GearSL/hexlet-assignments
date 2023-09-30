package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

import javax.swing.text.html.Option;

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
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        Optional<Post> foundedPost = posts.stream().filter(post -> post.getId().equals(id)).findAny();
        if (foundedPost.isPresent()) {
            return ResponseEntity.ok()
                    .body(foundedPost.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(201)
                .body(post);
    }

    @PutMapping(path = "/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post updatePost) {
        Optional<Post> foundedPost = posts.stream().filter(post -> post.getId().equals(id)).findAny();

        if(foundedPost.isPresent()) {
            Post post = foundedPost.orElseThrow();
            post.setTitle(updatePost.getTitle());
            post.setBody(updatePost.getBody());

            return ResponseEntity.ok()
                    .body(post);
        }

        return ResponseEntity.status(204)
                .body(updatePost);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
