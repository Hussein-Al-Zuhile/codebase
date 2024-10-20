package com.hussein.codebase.data.repository

import com.hussein.codebase.data.base.BaseRepository
import com.hussein.codebase.data.datasource.remote.MainService
import com.hussein.codebase.domain.model.Student

class MainRepository(
    private val mainService: MainService,
) : BaseRepository() {
    suspend fun getStudents() = sendRemoteRequest<List<Student>> { mainService.getAllStudents() }

    suspend fun getStudent(id: Int) = sendRemoteRequest<Student> { mainService.getStudent(id) }

    suspend fun searchStudents(query: String) = sendRemoteRequest<List<Student>> { mainService.searchStudents(query) }
}
