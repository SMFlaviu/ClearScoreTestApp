package com.flaviu.clearscoretestapp.network


data class DataResponse(
    val accountIDVStatus: String? = null,
    val augmentedCreditScore: Any? = null,
    val coachingSummary: CoachingSummary? = null,
    val creditReportInfo: CreditReportInfo? = null,
    val dashboardStatus: String? = null,
    val personaType: String? = null,
)