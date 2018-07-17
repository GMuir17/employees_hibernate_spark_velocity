package controllers;

import db.Seeds;

public class MainController {

    public static void main(String[] args) {

        Seeds.seedData();

        EmployeesController employeesController = new EmployeesController();
        ManagersController managersController = new ManagersController();
        EngineersController engineersController = new EngineersController();
        DepartmentsController departmentsController = new DepartmentsController();
    }
}
