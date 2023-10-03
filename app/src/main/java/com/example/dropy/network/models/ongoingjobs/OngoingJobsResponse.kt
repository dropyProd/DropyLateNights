package com.example.dropy.network.models.ongoingjobs

data class OngoingJobsResponse(
    val job_count: Int?,
    val jobs: List<Job>?,
    val status: Int?
)