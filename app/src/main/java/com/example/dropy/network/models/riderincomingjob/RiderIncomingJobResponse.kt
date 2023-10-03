package com.example.dropy.network.models.riderincomingjob

data class RiderIncomingJobResponse(
    val job_count: Int?,
    val jobs: List<Job>?,
    val status: Int?
)