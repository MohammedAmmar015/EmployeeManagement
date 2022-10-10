package com.ideas2it.employee.controller;

import com.ideas2it.employee.models.Trainee;
import com.ideas2it.employee.models.Trainer;
import com.ideas2it.employee.service.inter.TraineeService;
import com.ideas2it.employee.service.inter.TrainerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class EmployeeController {
	@Autowired
    private TrainerService trainerServiceImpl;
	@Autowired
    private TraineeService traineeServiceImpl;

	private Logger logger = LogManager.getLogger(EmployeeController.class);


	/**
	 * To Show Index Page
	 * @return index page
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	/**
	 * To show Trainer Form
	 * @return modelAndView - to show addOrUpdateTrainer page
	 */
	@GetMapping("/trainerForm")
	public ModelAndView showTrainerForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainer", new Trainer());
		modelAndView.addObject("action", "addTrainer");
		modelAndView.setViewName("addOrUpdateTrainer");
		return modelAndView;
	}

	/**
	 *
	 * @param trainer get Trainer object from user
	 * @param action addTrainer or updateTrainer Operation
	 * @param redirectAttributes
	 * @return to Redirect to ViewTrainer page
	 */
	@RequestMapping("/addOrUpdateTrainer")
	public String addOrUpdateTrainer(@ModelAttribute Trainer trainer, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
		try {
			trainerServiceImpl.addOrModifyTrainer(trainer);
			if ("addTrainer".equals(action)) {
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
	public ModelAndView viewTrainer() {
		List<Trainer> trainers = trainerServiceImpl.getTrainers();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainers", trainers);
		modelAndView.setViewName("viewTrainer");
		return modelAndView;
	}

	@GetMapping("/updateTrainer")
	public String getTrainerById(@RequestParam("id") int trainerId, Model model) {
		Trainer trainer = trainerServiceImpl.getTrainerById(trainerId);
		model.addAttribute("trainer", trainer);
		model.addAttribute("action", "updateTrainer");
		return "addOrUpdateTrainer";
	}

	@GetMapping("/deleteTrainer")
	public String removeTrainer(@RequestParam("id") int trainerId, RedirectAttributes redirectAttributes) {
		trainerServiceImpl.removeTrainerById(trainerId);
		redirectAttributes.addFlashAttribute("msg", trainerId + " SuccessFully Deleted");
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
	public String addOrUpdateTrainee(@ModelAttribute("trainee") Trainee trainee, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
		try {
			traineeServiceImpl.addOrModifyTrainee(trainee);
			if ("addTrainee".equals(action)) {
				redirectAttributes.addFlashAttribute("msg", trainee.getEmployee().getName() + " Inserted Successfully");
			} else {
				redirectAttributes.addFlashAttribute("msg", trainee.getEmployee().getName() + " Updated Successfully");
			}
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
	public String getTraineeById(@RequestParam("id") int traineeId, Model model) {
		Trainee trainee = traineeServiceImpl.getTraineeById(traineeId);
		model.addAttribute("trainers", trainerServiceImpl.getTrainers());
		model.addAttribute("trainee", trainee);
		model.addAttribute("action", "updateTrainee");
		return "addOrUpdateTrainee";
	}

	@GetMapping("/deleteTrainee")
	public String removeTrainee(@RequestParam("id") int traineeId, RedirectAttributes redirectAttributes) {
		traineeServiceImpl.removeTraineeById(traineeId);
		redirectAttributes.addFlashAttribute("msg", traineeId + " Successfully Deleted");
		return "redirect:/viewTrainee";
	}
}  
