package io.todos.redis

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.util.ObjectUtils
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class TodosAPI(@Autowired val repo: TodosRepo) {

    @GetMapping("/")
    fun retrieve(): List<Todo> {
        return this.repo.findAll().toList()
    }

    @PostMapping("/")
    fun create(@RequestBody todo: Todo): Todo {
        return this.repo.save(todo)
    }

    @DeleteMapping("/")
    fun deleteAll() {
        this.repo.deleteAll()
    }

    @GetMapping("/{id}")
    fun retrieve(@PathVariable("id") id: String): Todo {
        return this.repo.findById(id).orElseThrow {
            throw ResponseStatusException(NOT_FOUND, "todo.id=${id}")
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String){
        this.repo.deleteById(id)
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable("id") id: String, @RequestBody todo: Todo): Todo {
        val existing = retrieve(id)
        if (!ObjectUtils.isEmpty(todo.completed)) {
            existing.completed = todo.completed
        }
        if (!StringUtils.isEmpty(todo.title)) {
            existing.title = todo.title
        }
        return this.repo.save(existing)
    }
}
