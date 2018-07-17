package controllers;

import db.DBHelper;
import models.Department;
import models.Department;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class DepartmentsController {

    public DepartmentsController() {
        this.setUpEndpoints();
    }

    public void setUpEndpoints() {

        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();


        //Index
        get("/departments", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/departments/index.vtl");

            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("departments", departments);

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);


        //Create
        get("/departments/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Department> departments = DBHelper.getAll(Department.class);

            model.put("departments", departments);
            model.put("template", "templates/departments/create.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);


        post("/departments", (req, res) -> {
            String title = req.queryParams("title");

            Department newDepartment = new Department(title);
            DBHelper.save(newDepartment);

            res.redirect("/departments");
            return null;
        }, velocityTemplateEngine);


        //Show
        get("/departments/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/departments/show.vtl");

            int departmentId = Integer.parseInt(req.params(":id"));
            Department department = DBHelper.find(departmentId, Department.class);

            model.put("department", department);
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);


        //Edit
        get("/departments/:id/edit", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Department> departments = DBHelper.getAll(Department.class);

            int departmentId = Integer.parseInt(req.params(":id"));
            Department department = DBHelper.find(departmentId, Department.class);

            model.put("department", department);
            model.put("departments", departments);
            model.put("template", "templates/departments/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, velocityTemplateEngine);


        post("/departments/:id", (req, res) -> {
            String title = (req.queryParams("title"));

            int departmentId = Integer.parseInt(req.params(":id"));
            Department department = DBHelper.find(departmentId, Department.class);
            department.setTitle(title);
            DBHelper.update(department);

            res.redirect("/departments");
            return null;
        }, velocityTemplateEngine);


        //Delete
        post("/departments/:id/delete", (req, res) -> {
            int departmentId = Integer.parseInt(req.params(":id"));
            Department department = DBHelper.find(departmentId, Department.class);
            DBHelper.delete(department);

            res.redirect("/departments");
            return null;
        }, velocityTemplateEngine);


    }
}
