package com.hussein.codebase.presentation.main

import com.hussein.codebase.domain.usecase.student.GetAllStudentsUseCase
import com.hussein.codebase.domain.usecase.student.GetStudentUseCase
import com.hussein.codebase.presentation.base.BaseViewModel

class MainViewModel(
    private val getAllStudentsUseCase: GetAllStudentsUseCase,
    private val getStudentUseCase: GetStudentUseCase,
) : BaseViewModel() {
    fun getAllStudents() = getAllStudentsUseCase()
}
