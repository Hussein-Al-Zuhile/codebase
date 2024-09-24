package com.hussein.codebase.domain.usecase.student

import com.hussein.codebase.data.repository.MainRepository
import com.hussein.codebase.domain.base.BaseUseCase
import com.hussein.codebase.domain.base.Result
import com.hussein.codebase.domain.model.Student


class SearchStudentsUseCase(private val repository: MainRepository) : BaseUseCase<List<Student>>() {
    operator fun invoke(query:String) = execute {
        if (query.isBlank()) return@execute Result.Failure.QueryIsEmpty()
        repository.searchStudents(query)
    }
}