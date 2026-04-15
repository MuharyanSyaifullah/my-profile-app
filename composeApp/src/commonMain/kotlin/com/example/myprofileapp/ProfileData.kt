package com.example.myprofileapp

data class Profile(
    val name: String,
    val role: String,
    val bio: String,
    val email: String,
    val phone: String,
    val location: String
)

fun sampleProfile(): Profile {
    return Profile(
        name = "Muharyan Syaifullah",
        role = "Mobile Developer Student",
        bio = "Mahasiswa yang sedang belajar Kotlin Multiplatform dan Compose Multiplatform untuk membangun UI modern yang reusable.",
        email = "muharyansyaifullah@gmail.com",
        phone = "081387118356",
        location = "Jakarta, Indonesia"
    )
}