package exercise.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import exercise.model.QUser;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.QPredicates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSearchDao {

    @Autowired
    private final EntityManager em;
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllBySimpleQuery(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User>  criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
        Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
        Predicate andPredicate = criteriaBuilder.and(firstNamePredicate, lastNamePredicate);
        criteriaQuery.where(andPredicate);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<User> findAllByCriteria(UserFilter userFilter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User>  criteriaQuery = criteriaBuilder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> root = criteriaQuery.from(User.class);
        if (userFilter.getFirstName() != null) {
            Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"),
                    "%" + userFilter.getFirstName() + "%");
            predicates.add(firstNamePredicate);
        }
        if (userFilter.getLastName() != null) {
            Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"),
                    "%" + userFilter.getLastName() + "%");
            predicates.add(lastNamePredicate);
        }
        if (predicates.size() > 0) {
            criteriaQuery.where(
                    criteriaBuilder.and(predicates.toArray(new Predicate[0]))
            );
        }
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<User> filterByQueryDsl(UserFilter filter) {
        com.querydsl.core.types.Predicate predicate = QPredicates.builder()
                .add(filter.getFirstName(), QUser.user.firstName::containsIgnoreCase)
                .add(filter.getLastName(), QUser.user.lastName::containsIgnoreCase)
                .buildAnd();
        List<User> users = new ArrayList<>();

        if (predicate != null) {
            userRepository.findAll(predicate).forEach(users::add);
        } else {
            return userRepository.findAll();
        }

        return users;
    }
}
