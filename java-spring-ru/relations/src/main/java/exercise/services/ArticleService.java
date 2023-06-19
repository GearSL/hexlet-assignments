package exercise.services;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleDto getArticleById(long id) {
        Article article = articleRepository.findById(id);

        ArticleDto articleDto = new ArticleDto();
        if(article != null) {
            articleDto.setName(article.getName());
            articleDto.setBody(article.getBody());
            articleDto.setCategory(article.getCategory());
        }

        return articleDto;
    }

    public void createArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setName(articleDto.getName());
        article.setBody(articleDto.getBody());
        article.setCategory(articleDto.getCategory());

        articleRepository.save(article);
    }

    public void updateArticle(ArticleDto articleDto, long id) {
        Article article = new Article();
        article.setId(id);
        article.setName(articleDto.getName());
        article.setBody(articleDto.getBody());
        article.setCategory(articleDto.getCategory());

        articleRepository.save(article);
    }
}
