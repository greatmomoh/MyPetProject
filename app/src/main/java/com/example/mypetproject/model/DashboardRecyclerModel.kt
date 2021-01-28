package com.example.mypetproject.model


//Planned on using this for a shared recyclerView on the dashboard screen.
data class DashboardRecyclerModel(

    val lessons: List<LessonModel>,
    val subjects: List<SubjectModel>
)