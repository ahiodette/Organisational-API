import spark.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);


        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/depts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "dep-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/departments", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "departments.hbs");
        }, new HandlebarsTemplateEngine());

        post("/departments", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String depname = request.queryParams("depname");
            String depdescr = request.queryParams("depdescr");
            int depsize = Integer.parseInt(request.queryParams("depsize"));

            Department depts = new Department(depname, depdescr, depsize);

            model.put("depname", depname);
            model.put("depdescr", depdescr);
            model.put("depsize", depsize);

            depts.save(depts);
            List<Department>departments=Department.getAll();
            model.put("departments",departments);
            return new ModelAndView(model, "departments.hbs");
        }, new HandlebarsTemplateEngine());

        get("user/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "user-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "usersByDepts.hbs");
        }, new HandlebarsTemplateEngine());

        post("/users", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String username = request.queryParams("username");
            String role = request.queryParams("role");
            String post = request.queryParams("post");
            String departid = request.queryParams("departid");

            User getUsers = new User(username, role, post, departid);
            model.put("username", username);
            model.put("role", role);
            model.put("departid", departid);

            getUsers.add(getUsers);
            List<User>users=User.getAll();
            model.put("users",users);

            return new ModelAndView(model, "usersByDepts.hbs");
        }, new HandlebarsTemplateEngine());


        get("news/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "news-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "news.hbs");
        }, new HandlebarsTemplateEngine());

        post("/news", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            int departid = Integer.parseInt(request.queryParams("departid"));
            String content = request.queryParams("content");
            String header = request.queryParams("header");
            System.out.println(departid);
            System.out.println(content);
            System.out.println(header);

            News getNews = new News(departid, content, header);

            model.put("departid", departid);
            model.put("content", content);
            model.put("header", header);

            getNews.add(getNews);
            List<News>news=News.getAll();
            model.put("news",news);
            return new ModelAndView(model, "news.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
