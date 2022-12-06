package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class TodoListController {
	private final TodoRepository todoRepository;
	
	@GetMapping("/todo")
	public ModelAndView showTodoList(ModelAndView mv) {
		mv.setViewName("todoList");
		List<Todo>todoList = todoRepository.findAll();
		mv.addObject("todoList", todoList);
		return mv;
	}
}
