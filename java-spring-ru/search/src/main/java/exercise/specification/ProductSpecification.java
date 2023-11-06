package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO paramsDTO) {
        return inCategory(paramsDTO.getCategoryId())
                .and(priceGt(paramsDTO.getPriceGt()))
                .and(priceLt(paramsDTO.getPriceLt()))
                .and(ratingGt(paramsDTO.getRatingGt()))
                .and(titleContains(paramsDTO.getTitleCont()));
    }

    private Specification<Product> inCategory(Long categoryId) {
        return (root, query, cb) ->
                categoryId == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> priceGt(Integer priceGt) {
        return (root, query, cb) ->
                priceGt == null ? cb.conjunction() : cb.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> priceLt(Integer priceLt) {
        return (root, query, cb) ->
                priceLt == null ? cb.conjunction() : cb.lessThan(root.get("price"), priceLt);
    }

    private Specification<Product> ratingGt(Double ratingGt) {
        return (root, query, cb) ->
                ratingGt == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), ratingGt);
    }

    private Specification<Product> titleContains(String titleCont) {
        return (root, query, cb) ->
                titleCont == null ? cb.conjunction() : cb.like(cb.lower(root.get("title")), '%'+titleCont.toLowerCase()+'%');
    }
}
// END
