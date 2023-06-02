package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = Data.getCompanies();
        List<String> filteredCompanies = new ArrayList<>();
        String searchParameter = request.getParameter("search");
        PrintWriter out = response.getWriter();

        if (searchParameter == null || searchParameter.equals("")) {
            for (String company : companies) {
                out.println(company);
            }
        } else {
            filteredCompanies = companies.stream()
                    .filter(company -> company.contains(searchParameter))
                    .toList();
            if (filteredCompanies.size() == 0) {
                out.println("Companies not found");
            } else {
                for (String company : filteredCompanies) {
                    out.println(company);
                }
            }
        }
        // END
    }
}
