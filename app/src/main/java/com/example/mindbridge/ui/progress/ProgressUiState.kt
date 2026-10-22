package com.example.mindbridge.ui.progress

data class MetricItem(
    val id: String,
    val title: String,
    val valueText: String,
    val subtitle: String,
    val progress: Float,
    val iconEmoji: String
)

/**
 * UI State for the Activity Progress Screen adhering to MVVM architecture.
 */
data class ProgressUiState(
    val dateRange: String = "5-11 October 2026",
    val bannerMessage: String = "You spent time on activities you enjoy this week.",
    val metrics: List<MetricItem> = listOf(
        MetricItem("1", "Completed activities", "12", "Walks, music and garden time", 0.8f, "📊"),
        MetricItem("2", "Games played", "4", "A few gentle brain games", 0.4f, "🧩"),
        MetricItem("3", "Memories added", "2", "Stories to enjoy again", 0.25f, "🖼️"),
        MetricItem("4", "Routines followed", "5 days", "Time for familiar daily moments", 0.6f, "📅")
    ),
    val footerText: String = "Every moment counts. Enjoy activities at your own pace.",
    val selectedTab: Int = 2
)
