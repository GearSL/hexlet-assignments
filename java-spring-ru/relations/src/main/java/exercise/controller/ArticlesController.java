package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.repository.ArticleRepository;

import exercise.services.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    public ArticleDto getArticleById(@PathVariable long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping(path = "")
    public void createArticle(@RequestBody @Valid ArticleDto articleDto) {
        articleService.createArticle(articleDto);
    }

    @PatchMapping(path = "/{id}")
    public void updateArticle(@RequestBody ArticleDto articleDto, @PathVariable long id) {
        articleService.updateArticle(articleDto, id);
    }
    // END
}
