package com.ideas2it.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;  
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;  
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import com.ideas2it.employee.service.inter.TrainerService;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.inter.TraineeService;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TrainerNotFound;
import com.ideas2it.employee.exception.TraineeNotFound;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.models.Trainee;


public class EmployeeController extends HttpServlet {  
    private TrainerService trainerServiceImpl = new TrainerServiceImpl();
    private TraineeService traineeServiceImpl = new TraineeServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {   
	String operation = request.getParameter("action");
	switch (operation) {
	    case "addOrUpdateTrainer":
	 	addOrUpdateTrainer(request, response);
		break;

	    case "viewTrainer":
	 	viewTrainer(request, response);
		break;
	
	    case "updateTrainer":
	 	getTrainerById(request, response);
		break;

	    case "removeTrainer":
	 	removeTrainer(request, response);
		break;
		
	    case "addOrUpdateTrainee":
	 	addOrUpdateTrainee(request, response);
		break;

	    case "viewTrainee":
	 	viewTrainee(request, response);
		break;
	
	    case "updateTrainee":
	 	getTraineeById(request, response);
		break;

	    case "removeTrainee":
	 	removeTrainee(request, response);
		break;
	
	}	
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {   
	doPost(request, response);
    }

    public void addOrUpdateTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	HttpSession session = request.getSession();   
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String mobileNumber = request.getParameter("mobileNumber");
	String email = request.getParameter("email");
	String dateOfJoining = request.getParameter("dateOfJoining");
	String dateOfBirth = request.getParameter("dateOfBirth");
	String bloodGroup = request.getParameter("bloodGroup");
	String qualification = request.getParameter("qualification");
	String trainingExperience = request.getParameter("trainingExperience");
	String operation = request.getParameter("operation");
	Trainer trainer = (Trainer) session.getAttribute("trainer");
	try {
	    trainerServiceImpl.addOrModifyTrainer(trainer, name, address, mobileNumber, email, dateOfJoining, dateOfBirth,
					  qualification, bloodGroup, trainingExperience);
	    out.println("<h6>");
	    out.println(request.getParameter("name") + (trainer != null ? " Updated " : " Inserted ") + "Successfully"); 
	    out.println("</h6>");
	    RequestDispatcher rd=request.getRequestDispatcher("/employeeServlet?action=viewTrainer");  
            rd.include(request, response);  
	} catch (BadRequest e) {
	    out.println("<h6>");
	    out.println(e.getMessage());
	    out.println("</h6>");
	    request.setAttribute("trainer", trainer); 
            RequestDispatcher rd=request.getRequestDispatcher("/index.html"); 
            rd.include(request, response);             
	} finally {
	    out.close();
	}
    }

    public void viewTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	List<Trainer> trainers = trainerServiceImpl.getTrainers();
	request.setAttribute("trainers", trainers);
	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewTrainer.jsp");  
        requestDispatcher.forward(request, response); 
    }

    public void getTrainerById(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	int id = Integer.parseInt(request.getParameter("id"));
	Trainer trainer;
	try {
	    trainer = trainerServiceImpl.getTrainerById(id);
	    request.setAttribute("trainer", trainer);
	    RequestDispatcher rd=request.getRequestDispatcher("/addOrUpdateTrainer.jsp");  
            rd.forward(request, response);
	} catch (TrainerNotFound e) {
	    out.println(e.getMessage());
	    RequestDispatcher rd=request.getRequestDispatcher("/employeeServlet?action=viewTrainer");  
            rd.include(request, response);
	}
    }

    public void removeTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	int id = Integer.parseInt(request.getParameter("id"));
	try {
	    trainerServiceImpl.removeTrainerById(id);
	    out.println(id + "Deleted Successfully");
	    RequestDispatcher rd=request.getRequestDispatcher("/employeeServlet?action=viewTrainer");  
            rd.include(request, response);
	} catch (TrainerNotFound e) {
	    out.println(e.getMessage());
	    RequestDispatcher rd=request.getRequestDispatcher("/employeeServlet?action=viewTrainer");  
            rd.include(request, response);
	}
    }

    public void addOrUpdateTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	HttpSession session = request.getSession();   
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String mobileNumber = request.getParameter("mobileNumber");
	String email = request.getParameter("email");
	String dateOfJoining = request.getParameter("dateOfJoining");
	String dateOfBirth = request.getParameter("dateOfBirth");
	String bloodGroup = request.getParameter("bloodGroup");
	String qualification = request.getParameter("qualification");
	String trainingPeriod = request.getParameter("trainingPeriod");
	String course = request.getParameter("course");
	String batchNumber = request.getParameter("batchNumber");
	String values = request.getParameter("trainerIds");
	List<String> trainerIds = new ArrayList<>();
	for (String id : values.split(",")) {
	    trainerIds.add(id);
	}
	Trainee trainee = (Trainee) session.getAttribute("trainee");
	try {
	    traineeServiceImpl.addOrModifyTrainee(trainee, name, address, mobileNumber, email, dateOfJoining, dateOfBirth,
					  qualification, bloodGroup, trainingPeriod, course, batchNumber, trainerIds);
	    out.println("<h6>");
	    
	    out.println(request.getParameter("name") + (trainee != null ? " Updated " : " Inserted ")  + " Successfully"); 
	    out.println("</h6>");
	    RequestDispatcher rd=request.getRequestDispatcher("/employeeServlet?action=viewTrainee");  
            rd.include(request, response);  
	} catch (BadRequest e) {
	    out.println("<h6>");
	    out.println(e.getMessage());
	    out.println("</h6>");
            RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
            rd.include(request, response);             
	} finally {
	    out.close();
	}
    }

    public void viewTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	List<Trainee> trainees = traineeServiceImpl.getTrainees();
	request.setAttribute("trainees", trainees);
	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewTrainee.jsp");  
        requestDispatcher.forward(request, response);
    }

    public void getTraineeById(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	int id = Integer.parseInt(request.getParameter("id"));
	Trainee trainee;
	try {
	    trainee = traineeServiceImpl.getTraineeById(id);
	    request.setAttribute("trainee", trainee);
	    RequestDispatcher rd=request.getRequestDispatcher("/addOrUpdateTrainee.jsp");  
            rd.forward(request, response);
	} catch (TraineeNotFound e) {
	    out.println(e.getMessage());
	    RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
            rd.include(request, response);
	}
    }

    public void removeTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	int id = Integer.parseInt(request.getParameter("id"));
	try {
	    traineeServiceImpl.removeTraineeById(id);
	    out.println(id + " Deleted Successfully");
	    RequestDispatcher rd=request.getRequestDispatcher("/employeeServlet?action=viewTrainee");  
            rd.include(request, response);
	} catch (TraineeNotFound e) {
	    out.println(e.getMessage());
	    RequestDispatcher rd=request.getRequestDispatcher("/employeeServlet?action=viewTrainee");  
            rd.include(request, response);
	}
    }
  
}  
