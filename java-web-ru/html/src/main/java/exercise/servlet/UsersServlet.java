package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path fullPath = Paths.get("src/main/resources/users.json").toAbsolutePath().normalize();
        String content = "";
        try {
            content = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println("Read ERROR: " + e.getMessage());
        }

        ObjectMapper mapper = new ObjectMapper();
        List data = new ArrayList();
        try {
            data = mapper.readValue(content, List.class);
        } catch (Exception e) {
            System.out.println("Mapping ERROR: " + e.getMessage());
        }

        return data;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List users = getUsers();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuilder htmlPage = new StringBuilder();
        htmlPage.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                <body>
                <div class=\"container\">
                        <a href=\"/users\">Пользователи</a>
                </div>
                <table class="table">
                """);

        for (Object user : users) {
            Map<String, String> userDetails = (Map<String, String>)user;
            String fullNameLink = "<a href=\"/users/" + userDetails.get("id") + "\">"
                    + userDetails.get("firstName") + " "
                    + userDetails.get("lastName") + "</a>";
            htmlPage.append("<tr>")
                    .append("<td>")
                    .append(userDetails.get("id"))
                    .append("</td>")
                    .append("<td>")
                    .append(fullNameLink)
                    .append("</td>")
                    .append("</tr>");
        }
        htmlPage.append("""
                </table>
                </body>
                </html>
                """);
        out.println(htmlPage);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        PrintWriter out = response.getWriter();
        List users = getUsers();
        boolean isFound = false;
        for (Object user : users) {
            Map<String, String> userDetails = (Map<String, String>) user;
            if (userDetails.get("id").equals(id)) {
                isFound = true;
                out.println(userDetails.get("id"));
                out.println(userDetails.get("firstName"));
                out.println(userDetails.get("lastName"));
                out.println(userDetails.get("email"));
            }
        }
        if (!isFound) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        // END
    }
}
