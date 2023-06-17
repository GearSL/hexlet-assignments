package exercise.controllers;

import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;
import java.util.List;

import exercise.domain.User;
import exercise.domain.query.QUser;

import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .findList();
        String jsonUsers = DB.json().toJson(users);
        ctx.json(jsonUsers);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .where().id.eq(Long.parseLong(id))
                .findOne();
        String jsonUser = DB.json().toJson(user);
        ctx.json(jsonUser);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        EmailValidator emailValidatorInstance = EmailValidator.getInstance();
        User user = ctx.bodyValidator(User.class)
                .check(obj -> !obj.getFirstName().isEmpty(), "First Name mustn't be empty")
                .check(obj -> !obj.getLastName().isEmpty(), "Last Name mustn't be empty")
                .check(obj -> emailValidatorInstance.isValid(obj.getEmail()), "Email is not correct")
                .check(obj -> StringUtils.isNumeric(obj.getPassword()), "Password should be numeric")
                        .get();
        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.eq(Long.parseLong(id))
                .delete();
        // END
    };
}
