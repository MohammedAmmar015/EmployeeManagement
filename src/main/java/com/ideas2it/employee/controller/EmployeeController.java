package com.ideas2it.employee.controller;

import com.ideas2it.employee.exception.TraineeNotFound;
import com.ideas2it.employee.models.Employee;
import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.service.inter.TraineeService;
import com.ideas2it.employee.service.inter.TrainerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


@Controller
public class EmployeeController {
	@Autowired
    private TrainerService trainerServiceImpl;
	@Autowired
    private TraineeService traineeServiceImpl;

	private Logger logger = LogManager.getLogger(EmployeeController.class);


	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/trainerForm")
	public ModelAndView showForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainer", new Trainer());
		modelAndView.addObject("action", "addTrainer");
		modelAndView.setViewName("addOrUpdateTrainer");
		return modelAndView;
	}
	@PostMapping("/addOrUpdateTrainer")
    public String addOrUpdateTrainer(@ModelAttribute("trainer") Trainer trainer, RedirectAttributes redirectAttributes) {
		try {
			trainerServiceImpl.addOrModifyTrainer(trainer);
			redirectAttributes.addFlashAttribute("msg", trainer.getEmployee().getName() + " Inserted Successfully");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("msg", exception.getMessage());
		}
		return "redirect:/viewTrainer";
    }

	@PostMapping("/addOrUpdateTrainee")
	public String addOrUpdateTrainee(@ModelAttribute("trainee") Trainee trainee, RedirectAttributes redirectAttributes) {
		try {
			traineeServiceImpl.addOrModifyTrainee(trainee);
			redirectAttributes.addFlashAttribute("msg", trainee.getEmployee().getName() + " Inserted Successfully");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("msg", exception.getMessage());
		}
		return "redirect:/viewTrainee";
	}

	@GetMapping( value = "/viewTrainer")
    public ModelAndView viewTrainer() throws ServletException,IOException {
		List<Trainer> trainers = trainerServiceImpl.getTrainers();
		logger.info("viewTrainer() -> Controller");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainers", trainers);
		modelAndView.setViewName("viewTrainer");
		return modelAndView;
    }

	@GetMapping("/getTrainer")
	public String getTrainerById(@RequestParam("id") String id, RedirectAttributes redirectAttributes) throws ServletException,IOException {
		int trainerId = Integer.parseInt(id);
		try {
			trainerServiceImpl.removeTrainerById(trainerId);
			redirectAttributes.addFlashAttribute("msg",trainerId + " SuccessFully Deleted");
		} catch (TraineeNotFound e) {
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
			logger.error(e.getMessage());
		}
		return "redirect:/viewTrainer";
	}



	/*

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
	    logger.error(e.getMessage());
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
	    List<Integer> invalidTrainerIds = traineeServiceImpl.addOrModifyTrainee(trainee, name, address, mobileNumber, email, 
							dateOfJoining, dateOfBirth, qualification, bloodGroup, trainingPeriod, 
							course, batchNumber, trainerIds);
	    out.println("<h6>");
	    if (invalidTrainerIds.size() >= 0) {
	        out.println(invalidTrainerIds.toString() + " - Trainer Ids Not Found"); 
	    }
	    out.println(request.getParameter("name") + (trainee != null ? " Updated " : " Inserted ")  + " Successfully"); 
	    out.println("</h6>");
	    RequestDispatcher rd=request.getRequestDispatcher("/employeeServlet?action=viewTrainee");  
            rd.include(request, response);  
	} catch (BadRequest e) {
	    logger.warn(e.getMessage());
	    out.println("<h6>");
	    out.println(e.getMessage());
	    out.println("</h6>");
            RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
            rd.include(request, response);             
	} finally {
	    out.close();
	}
    } */

	@GetMapping( value = "/viewTrainee")
	public ModelAndView viewTrainee() {
		List<Trainee> trainees = traineeServiceImpl.getTrainees();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainees", trainees);
		modelAndView.setViewName("viewTrainee");
		return modelAndView;
	}
	/*

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
	    logger.error(e.getMessage());
	    out.println(e.getMessage());
	    RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
            rd.include(request, response);
	}
    } */

	@GetMapping("/deleteTrainee")
    public String removeTrainee(@RequestParam("id") String id, RedirectAttributes redirectAttributes) throws ServletException,IOException {
	int traineeId = Integer.parseInt(id);
	try {
	    traineeServiceImpl.removeTraineeById(traineeId);
		redirectAttributes.addFlashAttribute("msg", traineeId + " Successfully Deleted");
	} catch (TraineeNotFound e) {
		redirectAttributes.addFlashAttribute("msg", e.getMessage());
	    logger.error(e.getMessage());
	}
	return "redirect:/viewTrainee";
    }

	@GetMapping("/deleteTrainer")
	public String removeTrainer(@RequestParam("id") String id, RedirectAttributes redirectAttributes) throws ServletException,IOException {
		int trainerId = Integer.parseInt(id);
		try {
			trainerServiceImpl.removeTrainerById(trainerId);
			redirectAttributes.addFlashAttribute("msg",trainerId + " SuccessFully Deleted");
		} catch (TraineeNotFound e) {
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
			logger.error(e.getMessage());
		}
		return "redirect:/viewTrainer";
	}
  
}  
