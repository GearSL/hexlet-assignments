package exercise.controller;
import exercise.dao.UserFilter;
import exercise.dao.UserSearchDao;
import exercise.model.User;
import exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;
    private final UserSearchDao userSearchDao;

    // BEGIN
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(@RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName) {
        UserFilter filter = new UserFilter();
        filter.setFirstName(firstName);
        filter.setLastName(lastName);
        return userSearchDao.filterByQueryDsl(filter);
    }
    // END
}

