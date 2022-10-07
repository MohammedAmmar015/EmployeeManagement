package com.ideas2it.employee.controller;

import com.ideas2it.employee.exception.TraineeNotFound;
import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.service.inter.TraineeService;
import com.ideas2it.employee.service.inter.TrainerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String showIndex() {
		return "index";
	}

	@GetMapping("/trainerForm")
	public ModelAndView showTrainerForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainer", new Trainer());
		modelAndView.addObject("action", "addTrainer");
		modelAndView.setViewName("addOrUpdateTrainer");
		return modelAndView;
	}

	@RequestMapping("/addOrUpdateTrainer")
	public String addOrUpdateTrainer(@ModelAttribute("trainer") Trainer trainer, RedirectAttributes redirectAttributes) {
		try {
			trainerServiceImpl.addOrModifyTrainer(trainer);
			if (trainer.getEmployee().getId() == 0) {
				redirectAttributes.addFlashAttribute("msg", trainer.getEmployee().getName() + " Inserted Successfully");
			} else {
				redirectAttributes.addFlashAttribute("msg", trainer.getEmployee().getName() + " Updated Successfully");
			}
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("msg", exception.getMessage());
		}
		return "redirect:/viewTrainer";
	}

	@GetMapping( value = "/viewTrainer")
	public ModelAndView viewTrainer() throws ServletException,IOException {
		List<Trainer> trainers = trainerServiceImpl.getTrainers();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainers", trainers);
		modelAndView.setViewName("viewTrainer");
		return modelAndView;
	}

	@GetMapping("/updateTrainer")
	public String getTrainerById(@RequestParam("id") String id, RedirectAttributes redirectAttributes, Model model) throws ServletException,IOException {
		int trainerId = Integer.parseInt(id);
		try {
			Trainer trainer = trainerServiceImpl.getTrainerById(trainerId);
			model.addAttribute("trainer", trainer);
			model.addAttribute("action","updateTrainer");
		} catch (TraineeNotFound e) {
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
			logger.error(e.getMessage());
			return "redirect:/viewTrainer";
		}
		return "addOrUpdateTrainer";
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

	@GetMapping("/traineeForm")
	public ModelAndView showTraineeForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainers", trainerServiceImpl.getTrainers());
		modelAndView.addObject("trainee", new Trainee());
		modelAndView.addObject("action", "addTrainee");
		modelAndView.setViewName("addOrUpdateTrainee");
		return modelAndView;
	}

	@RequestMapping("/addOrUpdateTrainee")
	public String addOrUpdateTrainee(@ModelAttribute("trainee") Trainee trainee, RedirectAttributes redirectAttributes) {
		try {
			traineeServiceImpl.addOrModifyTrainee(trainee);
			redirectAttributes.addFlashAttribute("msg", trainee.getEmployee().getName() + " Inserted Successfully");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("msg", exception.getMessage());
		}
		return "redirect:/viewTrainee";
	}

	@GetMapping( value = "/viewTrainee")
	public ModelAndView viewTrainee() {
		List<Trainee> trainees = traineeServiceImpl.getTrainees();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainees", trainees);
		modelAndView.setViewName("viewTrainee");
		return modelAndView;
	}
	@GetMapping("/updateTrainee")
	public String getTraineeById(@RequestParam("id") String id, RedirectAttributes redirectAttributes, Model model) throws ServletException,IOException {
		int traineeId = Integer.parseInt(id);
		try {
			Trainee trainee = traineeServiceImpl.getTraineeById(traineeId);
			model.addAttribute("trainers", trainerServiceImpl.getTrainers());
			model.addAttribute("trainee", trainee);
			model.addAttribute("action","updateTrainee");
		} catch (TraineeNotFound e) {
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
			logger.error(e.getMessage());
			return "redirect:/viewTrainee";
		}
		return "addOrUpdateTrainee";
	}
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
}  
