package com.example.myprofileapp.data

data class Profile(
    val name: String,
    val role: String,
    val bio: String,
    val email: String,
    val phone: String,
    val location: String
)

object SampleData {
    val profile = Profile(
        name = "Muharyan Syaifullah",
        role = "Mobile Developer Student",
        bio = "Mahasiswa yang sedang belajar Kotlin Multiplatform dan Compose Multiplatform untuk membangun UI modern yang reusable.",
        email = "muharyansyaifullah@gmail.com",
        phone = "081387118356",
        location = "Jakarta, Indonesia"
    )
}
