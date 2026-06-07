package com.example.myapplication.data

import com.example.myapplication.data.models.Badge
import com.example.myapplication.data.models.Mission
import com.example.myapplication.data.models.Student
import com.example.myapplication.ui.screens.procedures.Procedure
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import org.osmdroid.util.GeoPoint

data class Friend(
    val name: String,
    val department: String,
    val interests: List<String>,
    val xp: Int
)

data class CampusLocation(
    val name: String,
    val point: GeoPoint,
    val description: String
)

object MockData {
    val drngpitStudent = Student(
        id = "NGP123",
        name = "Karthik",
        email = "karthik.cse@drngpit.ac.in",
        department = "Computer Science and Engineering",
        section = "A",
        xp = 650,
        currentDay = 5,
        earnedBadges = listOf("B1", "B2")
    )

    val badges = listOf(
        Badge("B1", "Explorer", "Icons.Default.Explore"),
        Badge("B2", "First Step", "Icons.Default.DirectionsRun"),
        Badge("B3", "Scholar", "Icons.Default.AutoStories")
    )

    val drngpitMissions = listOf(
        Mission("1", "Visit Admin Block B", "Locate the main office in Block B", 50, week = 1, day = 5),
        Mission("2", "Library Registration", "Go to Central Library for ID sync", 50, week = 1, day = 5),
        Mission("3", "Check Exam Cell", "Locate the exam cell for future reference", 50, week = 1, day = 5)
    )

    val drngpitProcedures = listOf(
        Procedure(
            id = "1",
            title = "FEE PAYMENT",
            icon = Icons.Default.Payments,
            description = "Complete your semester fee payment online or offline.",
            steps = listOf(
                "Login to the student portal",
                "Select 'Fee Payment' section",
                "Choose semester and payment mode",
                "Complete transaction and save receipt"
            ),
            documents = listOf("Student ID Card", "Previous semester receipt"),
            deadline = "15th Nov 2024"
        ),
        Procedure(
            id = "2",
            title = "SCHOLARSHIP",
            icon = Icons.Default.School,
            description = "Check eligibility and apply for scholarships.",
            steps = listOf(
                "Collect application from Block B Room 102",
                "Attach required income certificates",
                "Submit to the Scholarship coordinator",
                "Collect acknowledgement slip"
            ),
            documents = listOf("Income Certificate", "Community Certificate", "Aadhar Card"),
            deadline = "30th Oct 2024"
        ),
        Procedure(
            id = "3",
            title = "LIBRARY ACCESS",
            icon = Icons.Default.LibraryBooks,
            description = "Register at Central Library with your ID card.",
            steps = listOf(
                "Visit the Central Library counter",
                "Present your student ID card",
                "Register your fingerprint for entry",
                "Collect your library borrower card"
            ),
            documents = listOf("Student ID Card", "Admission Slip"),
            deadline = "Ongoing"
        ),
        Procedure(
            id = "4",
            title = "ID CARD PROTOCOL",
            icon = Icons.Default.Badge,
            description = "Submit photos at the Admission cell in Block B.",
            steps = listOf(
                "Visit the ID cell in Block B",
                "Provide your details and blood group",
                "Get your photo taken",
                "Collect card after 3 working days"
            ),
            documents = listOf("Admission Letter", "Photo ID proof"),
            deadline = "First week of joining"
        ),
        Procedure(
            id = "5",
            title = "HOSTEL REGISTRY",
            icon = Icons.Default.Hotel,
            description = "Visit the Warden office near the blocks.",
            steps = listOf(
                "Submit hostel application form",
                "Pay caution deposit",
                "Get room allotment number",
                "Collect mess tokens"
            ),
            documents = listOf("Proof of Residence", "Medical Certificate"),
            deadline = "Before semester start"
        )
    )

    val friends = listOf(
        Friend("Rahul", "IT", listOf("Coding", "Gaming"), 800),
        Friend("Sneha", "AI&DS", listOf("AI", "Music"), 1200),
        Friend("Vikram", "Mechanical", listOf("Sports", "Gaming"), 450),
        Friend("Priya", "ECE", listOf("Music", "Coding"), 950)
    )

    val locations = listOf(
        CampusLocation("Block A", GeoPoint(11.0508, 77.0395), "Circuit Departments & Labs"),
        CampusLocation("Block B", GeoPoint(11.0505, 77.0398), "Admin Office & Principal"),
        CampusLocation("Central Library", GeoPoint(11.0502, 77.0392), "Books, Digital Resources"),
        CampusLocation("Auditorium", GeoPoint(11.0498, 77.0401), "Events & Training"),
        CampusLocation("G Mart / Canteen", GeoPoint(11.0512, 77.0405), "Food & Refreshments")
    )

    val suggestedPrompts = listOf(
        "Where is my classroom?",
        "How do I apply for a scholarship?",
        "What is today's mission?",
        "Show me the fee payment steps."
    )
}
