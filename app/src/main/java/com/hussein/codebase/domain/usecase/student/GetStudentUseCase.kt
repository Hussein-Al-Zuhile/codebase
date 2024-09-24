package com.hussein.codebase.domain.usecase.student

import com.hussein.codebase.data.repository.MainRepository
import com.hussein.codebase.domain.base.BaseUseCase
import com.hussein.codebase.domain.model.Student


class GetStudentUseCase(private val repository: MainRepository) : BaseUseCase<Student>() {
    operator fun invoke() = execute {
        repository.getStudent(1)
    }
}