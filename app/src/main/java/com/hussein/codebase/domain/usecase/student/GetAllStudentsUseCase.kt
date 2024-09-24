package com.hussein.codebase.domain.usecase.student

import com.hussein.codebase.data.repository.MainRepository
import com.hussein.codebase.domain.base.BaseUseCase
import com.hussein.codebase.domain.model.Student


class GetAllStudentsUseCase(private val repository: MainRepository) : BaseUseCase<List<Student>>() {
    operator fun invoke() = execute {
        repository.getStudents()
    }
}