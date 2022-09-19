package com.ideas2it.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;  
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;  
import java.util.List;
import com.ideas2it.employee.service.inter.TrainerService;
import com.ideas2it.employee.service.impl.TrainerServiceImpl;
import com.ideas2it.employee.service.inter.TraineeService;
import com.ideas2it.employee.service.impl.TraineeServiceImpl;
import com.ideas2it.employee.exception.BadRequest;
import com.ideas2it.employee.exception.TrainerNotFound;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.models.Trainee;


public class EmployeeController extends HttpServlet {  
    private TrainerService trainerServiceImpl = new TrainerServiceImpl();
    private TraineeService traineeServiceImpl = new TraineeServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {   
	String operation = request.getParameter("action");
	switch (operation) {
	    case "addTrainer":
	 	addTrainer(request, response);
		break;
	
	    case "updateTrainer":
	 	updateTrainer(request, response);
		break;

	    case "removeTrainer":
	 	removeTrainer(request, response);
		break;
		
	    case "addTrainee":
	 	addTrainee(request, response);
		break;
	
	    case "updateTrainee":
	 	updateTrainee(request, response);
		break;

	    case "removeTrainee":
	 	removeTrainee(request, response);
		break;
	
	}	
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {   
	String operation = request.getParameter("action");
	switch (operation) {
	
	    case "viewTrainer":
	 	viewTrainer(request, response);
		break;
	
	    case "viewTrainee":
	 	viewTrainee(request, response);
		break;
	}	
    }

    public void addTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String mobileNumber = request.getParameter("mobileNumber");
	String email = request.getParameter("email");
	String dateOfJoining = request.getParameter("dateOfJoining");
	String dateOfBirth = request.getParameter("dateOfBirth");
	String bloodGroup = request.getParameter("bloodGroup");
	String qualification = request.getParameter("qualification");
	String trainingExperience = request.getParameter("trainingExperience");
	try {
	    trainerServiceImpl.addTrainer(name, address, mobileNumber, email, dateOfJoining, dateOfBirth,
					  qualification, bloodGroup, trainingExperience);
	    out.println("<h6>");
	    out.println(request.getParameter("name") + " Inserted Successfully"); 
	    out.println("</h6>");
	    RequestDispatcher rd=request.getRequestDispatcher("/addTrainer.html");  
            rd.include(request, response);  
	} catch (BadRequest e) {
	    out.println("<h6>");
	    out.println(e.getMessage());
	    out.println("</h6>");
            RequestDispatcher rd=request.getRequestDispatcher("/addTrainer.html");  
            rd.include(request, response);             
	} finally {
	    out.close();
	}
    }

    public void viewTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	List<Trainer> trainers = trainerServiceImpl.getTrainers();
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	out.println("<h1> Trainers List </h1>");
	if (trainers.size() < 0) {
	    out.println("No Data Found to Display");
	} else {
	    out.println("<table><tr>");
	    out.println("<th>Id</th><th>Name</th><th>Address</th><th>Date of Birth</th><th>Date of Joining</th>" 
		      + "<th>Email</th><th>Mobile Number</th><th>Qualification</th><th>Blood Group</th>"
		      + "<th>Training Experience</th>");
	    out.println("</tr>");
	    for (Trainer trainer : trainers) {
		out.println("<tr>");
		out.println("<td>" + trainer.getEmployee().getId() + "</td>");
		out.println("<td>" + trainer.getEmployee().getName() + "</td>");
		out.println("<td>" + trainer.getEmployee().getAddress() + "</td>");
		out.println("<td>" + trainer.getEmployee().getDateOfBirth() + "</td>");
		out.println("<td>" + trainer.getEmployee().getDateOfJoining() + "</td>");
		out.println("<td>" + trainer.getEmployee().getEmail() + "</td>");
		out.println("<td>" + trainer.getEmployee().getMobileNumber() + "</td>");
		out.println("<td>" + trainer.getEmployee().getQualification().getDescription() + "</td>");
		out.println("<td>" + trainer.getEmployee().getBloodGroup() + "</td>");
		out.println("<td>" + trainer.getTrainingExperience() + "</td>");
	    	out.println("</tr>");
	    }
	    out.println("</table>");
	}
    }

    public void updateTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	int id = Integer.parseInt(request.getParameter("id"));
	Trainer trainer;
	try {
	    trainer = trainerServiceImpl.getTrainerById(id);
	    request.setAttribute("name", trainer.getEmployee().getName());
	    RequestDispatcher rd=request.getRequestDispatcher("/addTrainer.html");  
            rd.forward(request, response);
	} catch (TrainerNotFound e) {
	    out.println(e.getMessage());
	    RequestDispatcher rd=request.getRequestDispatcher("/update.html");  
            rd.include(request, response);
	}
    }

    public void removeTrainer(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

    }

    public void addTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String mobileNumber = request.getParameter("mobileNumber");
	String email = request.getParameter("email");
	String dateOfJoining = request.getParameter("dateOfJoining");
	String dateOfBirth = request.getParameter("dateOfBirth");
	String bloodGroup = request.getParameter("bloodGroup");
	String qualification = request.getParameter("qualification");
	String trainingExperience = request.getParameter("trainingExperience");
	try {
	    trainerServiceImpl.addTrainer(name, address, mobileNumber, email, dateOfJoining, dateOfBirth,
					  qualification, bloodGroup, trainingExperience);
	    out.println("<h6>");
	    out.println(request.getParameter("name") + " Inserted Successfully"); 
	    out.println("</h6>");
	    RequestDispatcher rd=request.getRequestDispatcher("/addTrainer.html");  
            rd.include(request, response);  
	} catch (BadRequest e) {
	    out.println("<h6>");
	    out.println(e.getMessage());
	    out.println("</h6>");
            RequestDispatcher rd=request.getRequestDispatcher("/addTrainer.html");  
            rd.include(request, response);             
	} finally {
	    out.close();
	}
    }

    public void viewTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
	List<Trainee> trainees = traineeServiceImpl.getTrainees();
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter();
	out.println("<h1> Trainees List </h1>");
	if (trainees.size() < 0) {
	    out.println("No Data Found to Display");
	} else {
	    out.println("<table><tr>");
	    out.println("<th>Id</th><th>Name</th><th>Address</th><th>Date of Birth</th><th>Date of Joining</th><th>Email</th><th>Mobile Number</th><th>Qualification</th><th>Blood Group</th><th>Training Period</th><th>Course</th><th>Batch Number</th>");
	    out.println("</tr>");
	    for (Trainee trainee : trainees) {
		out.println("<tr>");
		out.println("<td>" + trainee.getEmployee().getId() + "</td>");
		out.println("<td>" + trainee.getEmployee().getName() + "</td>");
		out.println("<td>" + trainee.getEmployee().getAddress() + "</td>");
		out.println("<td>" + trainee.getEmployee().getDateOfBirth() + "</td>");
		out.println("<td>" + trainee.getEmployee().getDateOfJoining() + "</td>");
		out.println("<td>" + trainee.getEmployee().getEmail() + "</td>");
		out.println("<td>" + trainee.getEmployee().getMobileNumber() + "</td>");
		out.println("<td>" + trainee.getEmployee().getQualification().getDescription() + "</td>");
		out.println("<td>" + trainee.getEmployee().getBloodGroup() + "</td>");
		out.println("<td>" + trainee.getTrainingPeriod() + "</td>");
		out.println("<td>" + trainee.getCourse() + "</td>");
		out.println("<td>" + trainee.getBatchNumber() + "</td>");
	    	out.println("</tr>");
	    }
	    out.println("</table>");
	}
    }

    public void updateTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

    }

    public void removeTrainee(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

    }
  
}  
