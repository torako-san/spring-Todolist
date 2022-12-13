package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Todo;
import com.example.demo.form.TodoData;
import com.example.demo.repository.TodoRepository;
import com.example.demo.servise.TodoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class TodoListController {
	private final TodoRepository todoRepository;
	private final TodoService todoService;
	
	
	@GetMapping("/todo")
	public ModelAndView showTodoList(ModelAndView mv) {
		mv.setViewName("todoList");
		List<Todo>todoList = todoRepository.findAll();
		mv.addObject("todoList", todoList);
		return mv;
	}
	
	@GetMapping("/todo/create")
	public ModelAndView createTodo(ModelAndView mv) {
		mv.setViewName("todoForm");
		mv.addObject("todoData", new TodoData());
		return mv;
	}
	
	@PostMapping("/todo/create")
	public ModelAndView createTodo(@ModelAttribute @Validated TodoData todoData,
			BindingResult result, ModelAndView mv) {
		boolean isValid = todoService.isValid(todoData, result);
		if(!result.hasErrors()&& isValid) {
			Todo todo = todoData.toEntity();
			todoRepository.saveAndFlush(todo);
			return showTodoList(mv);
		} else {
			mv.setViewName("todoForm");
			//mav.addObject("todoData", todoData);
			return mv;
		}
	}
	
	@PostMapping("/todo/cancel")
	public String cancel() {
		return "redirect:/todo";
	}
}