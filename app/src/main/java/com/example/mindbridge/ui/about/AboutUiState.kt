package com.example.mindbridge.ui.about

data class AboutLink(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconEmoji: String
)

/**
 * UI State for About MindBridge AI Screen adhering to MVVM architecture.
 */
data class AboutUiState(
    val appName: String = "MindBridge AI",
    val tagline: String = "Helping memories stay connected.",
    val versionText: String = "Version X.X - Placeholder",
    val descriptionText: String = "A warm companion for everyday routines, familiar people, and the stories that matter to you.",
    val disclaimerText: String = "MindBridge AI supports everyday routines and meaningful memories. It does not replace professional medical advice.",
    val links: List<AboutLink> = listOf(
        AboutLink("1", "Terms", "Read app terms", "📄"),
        AboutLink("2", "Privacy Information", "Understand use and sharing", "🛡️"),
        AboutLink("3", "Contact Support", "Get a little help", "💬")
    ),
    val footerQuote: String = "One moment. One memory. Together.",
    val selectedTab: Int = 3
)
