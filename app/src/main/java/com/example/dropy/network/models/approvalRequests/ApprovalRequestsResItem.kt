package com.example.dropy.network.models.approvalRequests

data class ApprovalRequestsResItem(
    val driver: String,
    val id: String,
    val is_active: Boolean,
    val is_approved: Boolean,
    val is_seen_by_vendor: Boolean,
    val license_number: String,
    val truck: String
)