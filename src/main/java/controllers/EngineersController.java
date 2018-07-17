package controllers;

import db.DBHelper;
import models.Department;
import models.Engineer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class EngineersController {

    public EngineersController() {
        this.setUpEndpoints();
    }

    public void setUpEndpoints() {

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();


        //Index
        get("/engineers", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/engineers/index.vtl");

            List<Engineer> engineers = DBHelper.getAll(Engineer.class);
            model.put("engineers", engineers);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);


        //Create
        get("/engineers/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Department> departments = DBHelper.getAll(Department.class);

            model.put("departments", departments);
            model.put("template", "templates/engineers/create.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);


        post("/engineers", (req, res) -> {
            String firstName = req.queryParams("first-name");
            String lastName = req.queryParams("last-name");
            int salary = Integer.parseInt(req.queryParams("salary"));

            int departmentId = Integer.parseInt(req.queryParams("department"));
            Department department = DBHelper.find(departmentId, Department.class);

            Engineer newEngineer = new Engineer(firstName, lastName, salary, department);
            DBHelper.save(newEngineer);

            res.redirect("/engineers");
            return null;
        }, velocityTemplateEngine);
























    }
}
