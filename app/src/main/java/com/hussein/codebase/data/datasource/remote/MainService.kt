package com.hussein.codebase.data.datasource.remote

import com.hussein.codebase.data.base.BaseRemoteService
import io.ktor.client.HttpClient


class MainService(client: HttpClient) : BaseRemoteService(client) {

    suspend fun getAllStudents() = get(Students.All())

    suspend fun getStudent(id: Int) = get(Students.Id(id = 1))

    suspend fun searchStudents(query: String) = get(Students.Search(query = query))
}