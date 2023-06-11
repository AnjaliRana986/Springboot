package com.anjali.springboot.webapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {


    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }
    private TodoService todoService;

    //list - todos
    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap model){
        List<Todo> todos = todoService.findBtUsername("anjali");
//        ModelMap todos1 = model.addAllAttributes("todos", todos);
        model.addAttribute("todos",todos);
        return "listTodos";
    }
    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        String username = (String)model.get("name");
        Todo todo= new Todo(0, username,"",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(),false);
        return "redirect:list-todos";
    }
    @RequestMapping("/delete-todo")
    public String deleteAllTodo(@RequestParam int id){
//        Delete todo
        todoService.deleteById(id);
        return "redirect:list-todos";
    }
    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model){
        Todo todo = todoService.findById(id);
//        update todo
        model.addAttribute("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String UpdateTodoPage(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

}
