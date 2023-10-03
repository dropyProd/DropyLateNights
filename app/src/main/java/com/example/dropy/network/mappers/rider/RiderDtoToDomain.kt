package com.example.dropy.network.mappers.rider

import com.example.dropy.network.models.acceptjob.AcceptJobResponse
import com.example.dropy.network.models.canceljob.CancelIncomingJobResponse
import com.example.dropy.network.models.commondataclasses.ActionResultDataClass
import com.example.dropy.network.models.pools.RiderPoolsResponse
import com.example.dropy.network.models.riderincomingjob.RiderIncomingJobResponse

internal fun RiderIncomingJobResponse.toDomain(): RiderIncomingJobResponse {
    return RiderIncomingJobResponse(
        job_count = job_count,
        jobs = jobs,
        status = status
    )
}
internal fun AcceptJobResponse.toDomain(): AcceptJobResponse {
    return AcceptJobResponse(

    )
}

internal fun CancelIncomingJobResponse.toDomain(): CancelIncomingJobResponse {
    return CancelIncomingJobResponse(
        message = message, status = status
    )
}

internal fun RiderPoolsResponse.toDomain(): RiderPoolsResponse {
    return RiderPoolsResponse()
}