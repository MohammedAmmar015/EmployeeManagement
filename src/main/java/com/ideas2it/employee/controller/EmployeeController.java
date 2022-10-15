package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.TraineeDto;
import com.ideas2it.employee.dto.TrainerDto;
import com.ideas2it.employee.service.TraineeService;
import com.ideas2it.employee.service.TrainerService;
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

	private Logger logger = LogManager.getLogger(EmployeeController.class);

    private TrainerService trainerServiceImpl;
    private TraineeService traineeServiceImpl;
	@Autowired
	public EmployeeController(TrainerService trainerServiceImpl,
							  TraineeService traineeServiceImpl) {
		this.trainerServiceImpl = trainerServiceImpl;
		this.traineeServiceImpl = traineeServiceImpl;
	}

	/**
	 * <p>
	 * 	To Show Index Page
	 * 	</p>
	 * @return index page
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	/**
	 * <p>
	 * To show Trainer Form
	 * </p>
	 * @return modelAndView - to show addOrUpdateTrainer page
	 */
	@GetMapping("/trainerForm")
	public ModelAndView showTrainerForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainerDto", new TrainerDto());
		modelAndView.addObject("action", "addTrainer");
		modelAndView.setViewName("addOrUpdateTrainer");
		return modelAndView;
	}

	/**
	 *<p>
	 *     to add trainer details
	 *</p>
	 * @param trainerDto get Trainer object from user
	 * @param action addTrainer or updateTrainer Operation
	 * @param redirectAttributes it holds error message to Redirect
	 * @return to Redirect to ViewTrainer page
	 */
	@RequestMapping("/addOrUpdateTrainer")
	public String addOrUpdateTrainer(@ModelAttribute TrainerDto trainerDto, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
		try {
			int savedTrainerId = trainerServiceImpl.addOrModifyTrainer(trainerDto);
			if ("addTrainer".equals(action)) {
				redirectAttributes.addFlashAttribute("msg", savedTrainerId + " Inserted Successfully");
			} else {
				redirectAttributes.addFlashAttribute("msg", savedTrainerId + " Updated Successfully");
			}
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("msg", exception.getMessage());
		}
		return "redirect:/viewTrainer";
	}

	/**
	 * <p>
	 *     To View List of Trainers
	 * </p>
	 * @return
	 */
	@GetMapping( value = "/viewTrainer")
	public ModelAndView viewTrainer() {
		List<TrainerDto> trainersDto = trainerServiceImpl.getTrainers();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainersDto", trainersDto);
		modelAndView.setViewName("viewTrainer");
		return modelAndView;
	}

	/**
	 * <p>
	 *     To Update Trainer Details
	 * </p>
	 * @param trainerId
	 * @param model
	 * @return
	 */
	@GetMapping("/updateTrainer")
	public String getTrainerById(@RequestParam("id") int trainerId, Model model) {
		TrainerDto trainerDto = trainerServiceImpl.getTrainerById(trainerId);
		model.addAttribute("trainerDto", trainerDto	);
		model.addAttribute("action", "updateTrainer");
		return "addOrUpdateTrainer";
	}

	/**
	 * <p>
	 *     To delete Trainer details
	 * </p>
	 * @param trainerId
	 * @param redirectAttributes
	 * @return
	 */
	@GetMapping("/deleteTrainer")
	public String removeTrainer(@RequestParam("id") int trainerId, RedirectAttributes redirectAttributes) {
		trainerServiceImpl.removeTrainerById(trainerId);
		redirectAttributes.addFlashAttribute("msg", trainerId + " SuccessFully Deleted");
		return "redirect:/viewTrainer";
	}

	/**
	 * <p>
	 * 	To show Trainee Form
	 * </p>
	 * @return modelAndView - to show addOrUpdateTrainee page
	 */
	@GetMapping("/traineeForm")
	public ModelAndView showTraineeForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("trainersDto", trainerServiceImpl.getTrainers());
		modelAndView.addObject("traineeDto", new TraineeDto());
		modelAndView.addObject("action", "addTrainee");
		modelAndView.setViewName("addOrUpdateTrainee");
		return modelAndView;
	}

	/**
	 *<p>
	 *     to add trainee details
	 *</p>
	 * @param traineeDto get Trainee object from user
	 * @param action addTrainee or updateTrainee Operation
	 * @param redirectAttributes it holds error message to Redirect
	 * @return to Redirect to ViewTrainee page
	 */
	@RequestMapping("/addOrUpdateTrainee")
	public String addOrUpdateTrainee(@ModelAttribute("traineeDto") TraineeDto traineeDto, @RequestParam("action") String action, RedirectAttributes redirectAttributes) {
		try {
			int savedTraineeId = traineeServiceImpl.addOrModifyTrainee(traineeDto);
			if ("addTrainee".equals(action)) {
				redirectAttributes.addFlashAttribute("msg", savedTraineeId + " Inserted Successfully");
			} else {
				redirectAttributes.addFlashAttribute("msg", savedTraineeId + " Updated Successfully");
			}
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("msg", exception.getMessage());
		}
		return "redirect:/viewTrainee";
	}

	/**
	 * <p>
	 *     To View List of Trainees
	 * </p>
	 * @return
	 */
	@GetMapping( value = "/viewTrainee")
	public ModelAndView viewTrainee() {
		List<TraineeDto> traineesDto = traineeServiceImpl.getTrainees();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("traineesDto", traineesDto);
		modelAndView.setViewName("viewTrainee");
		return modelAndView;
	}

	/**
	 * <p>
	 *     To Update Trainee Details
	 * </p>
	 * @param traineeId
	 * @param model
	 * @return
	 */
	@GetMapping("/updateTrainee")
	public String getTraineeById(@RequestParam("id") int traineeId, Model model) {
		TraineeDto traineeDto = traineeServiceImpl.getTraineeById(traineeId);
		List<TrainerDto> trainersDto = trainerServiceImpl.getTrainers();
		model.addAttribute("trainersDto", trainersDto);
		model.addAttribute("traineeDto", traineeDto);
		model.addAttribute("action", "updateTrainee");
		return "addOrUpdateTrainee";
	}

	/**
	 * <p>
	 *     To delete Trainee details
	 * </p>
	 * @param traineeId
	 * @param redirectAttributes
	 * @return
	 */
	@GetMapping("/deleteTrainee")
	public String removeTrainee(@RequestParam("id") int traineeId, RedirectAttributes redirectAttributes) {
		traineeServiceImpl.removeTraineeById(traineeId);
		redirectAttributes.addFlashAttribute("msg", traineeId + " Successfully Deleted");
		return "redirect:/viewTrainee";
	}
}  
