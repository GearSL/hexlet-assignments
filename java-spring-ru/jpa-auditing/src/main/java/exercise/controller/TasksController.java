package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Task;
import exercise.repository.TaskRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "")
    public List<Task> index() {
        return taskRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Task show(@PathVariable long id) {

        var task =  taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        return task;
    }

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping(path = "/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> foundedTask = taskRepository.findById(id);
        if(foundedTask.isEmpty()) {
            throw new ResourceNotFoundException("Task not founded");
        }

        Task taskForUpdate = foundedTask.orElseThrow();
        taskForUpdate.setTitle(task.getTitle());
        taskForUpdate.setDescription(task.getDescription());
        return taskRepository.save(taskForUpdate);
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
}
