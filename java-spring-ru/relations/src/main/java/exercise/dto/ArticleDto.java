package exercise.dto;

import exercise.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Data
public class ArticleDto {
    private String name;
    private String body;
    private Category category;
}
// END
