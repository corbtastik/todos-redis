package io.todos.redis

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository("todos")
interface TodosRepo : PagingAndSortingRepository<Todo, String> {
    fun findByTitle(title: String): List<Todo>
}