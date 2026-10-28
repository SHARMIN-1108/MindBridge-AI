package com.example.mindbridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mindbridge.ui.addmemory.AddMemoryScreen
import com.example.mindbridge.ui.addmemory.AddMemoryViewModel
import com.example.mindbridge.ui.album.AlbumScreen
import com.example.mindbridge.ui.album.AlbumViewModel
import com.example.mindbridge.ui.caregiver.CaregiverScreen
import com.example.mindbridge.ui.caregiver.CaregiverViewModel
import com.example.mindbridge.ui.companion.CompanionScreen
import com.example.mindbridge.ui.companion.CompanionViewModel
import com.example.mindbridge.ui.games.GamesScreen
import com.example.mindbridge.ui.games.GamesViewModel
import com.example.mindbridge.ui.home.HomeScreen
import com.example.mindbridge.ui.home.HomeViewModel
import com.example.mindbridge.ui.login.LoginScreen
import com.example.mindbridge.ui.login.LoginViewModel
import com.example.mindbridge.ui.memory.MemoryScreen
import com.example.mindbridge.ui.memory.MemoryViewModel
import com.example.mindbridge.ui.places.PlacesScreen
import com.example.mindbridge.ui.places.PlacesViewModel
import com.example.mindbridge.ui.profile.ProfileScreen
import com.example.mindbridge.ui.profile.ProfileViewModel
import com.example.mindbridge.ui.progress.ProgressScreen
import com.example.mindbridge.ui.progress.ProgressViewModel
import com.example.mindbridge.ui.register.RegisterScreen
import com.example.mindbridge.ui.register.RegisterViewModel
import com.example.mindbridge.ui.addmemory.AddMemoryScreen
import com.example.mindbridge.ui.addmemory.AddMemoryViewModel
import com.example.mindbridge.ui.aiconversations.AiConversationsScreen
import com.example.mindbridge.ui.aiconversations.AiConversationsViewModel
import com.example.mindbridge.ui.dailycheckin.DailyCheckInScreen
import com.example.mindbridge.ui.dailycheckin.DailyCheckInViewModel
import com.example.mindbridge.ui.familyupdates.FamilyUpdatesScreen
import com.example.mindbridge.ui.familyupdates.FamilyUpdatesViewModel
import com.example.mindbridge.ui.helpfulinformation.HelpfulInformationScreen
import com.example.mindbridge.ui.helpfulinformation.HelpfulInformationViewModel
import com.example.mindbridge.ui.importantpeople.ImportantPeopleScreen
import com.example.mindbridge.ui.importantpeople.ImportantPeopleViewModel
import com.example.mindbridge.ui.memorydetails.MemoryDetailsScreen
import com.example.mindbridge.ui.memorydetails.MemoryDetailsViewModel
import com.example.mindbridge.ui.preferences.PreferencesScreen
import com.example.mindbridge.ui.preferences.PreferencesViewModel
import com.example.mindbridge.ui.register.UserRole
import com.example.mindbridge.ui.reminders.RemindersScreen
import com.example.mindbridge.ui.reminders.RemindersViewModel
import com.example.mindbridge.ui.schedule.ScheduleScreen
import com.example.mindbridge.ui.schedule.ScheduleViewModel
import com.example.mindbridge.ui.settings.SettingsScreen
import com.example.mindbridge.ui.settings.SettingsViewModel
import com.example.mindbridge.ui.splash.SplashScreen
import com.example.mindbridge.ui.splash.SplashUiState
import com.example.mindbridge.ui.splash.SplashViewModel
import com.example.mindbridge.ui.support.SupportScreen
import com.example.mindbridge.ui.support.SupportViewModel
import com.example.mindbridge.ui.theme.MindbridgeTheme
import com.example.mindbridge.ui.voice.VoiceScreen
import com.example.mindbridge.ui.voice.VoiceViewModel
import com.example.mindbridge.ui.welcome.WelcomeScreen
import com.example.mindbridge.ui.welcome.WelcomeUiState
import com.example.mindbridge.ui.welcome.WelcomeViewModel

enum class Screen {
<<<<<<< HEAD
    SPLASH, WELCOME, LOGIN, REGISTER, HOME, SCHEDULE, COMPANION, GAMES, MEMORY, ALBUM, VOICE, REMINDERS, PLACES, PROGRESS, CAREGIVER, SUPPORT, MY_PROFILE, SETTINGS, ADD_MEMORY
=======
    SPLASH, WELCOME, LOGIN, REGISTER, HOME, SCHEDULE, COMPANION, GAMES, MEMORY, ALBUM, VOICE, REMINDERS, PLACES, PROGRESS, CAREGIVER, SUPPORT, PROFILE, SETTINGS, ADD_MEMORY, MEMORY_DETAILS, AI_CONVERSATIONS, PREFERENCES, IMPORTANT_PEOPLE, HELPFUL_INFORMATION, DAILY_CHECK_IN, FAMILY_UPDATES
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
}

class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()
    private val welcomeViewModel: WelcomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val scheduleViewModel: ScheduleViewModel by viewModels()
    private val companionViewModel: CompanionViewModel by viewModels()
    private val gamesViewModel: GamesViewModel by viewModels()
    private val memoryViewModel: MemoryViewModel by viewModels()
    private val albumViewModel: AlbumViewModel by viewModels()
    private val voiceViewModel: VoiceViewModel by viewModels()
    private val remindersViewModel: RemindersViewModel by viewModels()
    private val placesViewModel: PlacesViewModel by viewModels()
    private val progressViewModel: ProgressViewModel by viewModels()
    private val caregiverViewModel: CaregiverViewModel by viewModels()
    private val supportViewModel: SupportViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()
    private val addMemoryViewModel: AddMemoryViewModel by viewModels()
<<<<<<< HEAD
=======
    private val memoryDetailsViewModel: MemoryDetailsViewModel by viewModels()
    private val aiConversationsViewModel: AiConversationsViewModel by viewModels()
    private val preferencesViewModel: PreferencesViewModel by viewModels()
    private val importantPeopleViewModel: ImportantPeopleViewModel by viewModels()
    private val helpfulInformationViewModel: HelpfulInformationViewModel by viewModels()
    private val dailyCheckInViewModel: DailyCheckInViewModel by viewModels()
    private val familyUpdatesViewModel: FamilyUpdatesViewModel by viewModels()
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindbridgeTheme {
                val splashState by splashViewModel.uiState.collectAsStateWithLifecycle()
                val welcomeState by welcomeViewModel.uiState.collectAsStateWithLifecycle()
                val loginState by loginViewModel.uiState.collectAsStateWithLifecycle()
                val registerState by registerViewModel.uiState.collectAsStateWithLifecycle()

                var currentScreen by remember { mutableStateOf(Screen.SPLASH) }

                // Handle initial splash transition
                LaunchedEffect(splashState) {
                    if (splashState is SplashUiState.Completed) {
                        currentScreen = Screen.WELCOME
                    }
                }

                // Handle welcome screen navigation actions
                LaunchedEffect(welcomeState) {
                    when (welcomeState) {
                        is WelcomeUiState.NavigateToGetStarted -> currentScreen = Screen.REGISTER
                        is WelcomeUiState.NavigateToLogin -> currentScreen = Screen.LOGIN
                        else -> {}
                    }
                }

                // Handle login success or navigation to register
                LaunchedEffect(loginState) {
                    if (loginState.isLoginSuccess) {
                        currentScreen = Screen.HOME
                    } else if (loginState.navigateToRegister) {
                        currentScreen = Screen.REGISTER
                    }
                }

                // Handle register success or navigation to login
                LaunchedEffect(registerState) {
                    if (registerState.isRegisterSuccess) {
                        currentScreen = if (registerState.role == UserRole.FAMILY_CAREGIVER) {
                            Screen.CAREGIVER
                        } else {
                            Screen.HOME
                        }
                    } else if (registerState.navigateToLogin) {
                        currentScreen = Screen.LOGIN
                    }
                }

                Surface(modifier = Modifier.fillMaxSize()) {
                    when (currentScreen) {
                        Screen.SPLASH -> {
                            SplashScreen()
                        }
                        Screen.WELCOME -> {
                            WelcomeScreen(
                                onGetStartedClick = { welcomeViewModel.onGetStartedClicked() },
                                onLoginClick = { welcomeViewModel.onLoginClicked() }
                            )
                        }
                        Screen.LOGIN -> {
                            LoginScreen(
                                viewModel = loginViewModel,
                                onBackClick = {
                                    loginViewModel.resetState()
                                    welcomeViewModel.resetState()
                                    currentScreen = Screen.WELCOME
                                },
                                onLoginSuccess = {
                                    currentScreen = Screen.HOME
                                },
                                onCreateAccountClick = {
                                    loginViewModel.resetState()
                                    currentScreen = Screen.REGISTER
                                }
                            )
                        }
                        Screen.REGISTER -> {
                            RegisterScreen(
                                viewModel = registerViewModel,
                                onBackClick = {
                                    registerViewModel.resetState()
                                    welcomeViewModel.resetState()
                                    currentScreen = Screen.WELCOME
                                },
                                onRegisterSuccess = {
                                    currentScreen = Screen.HOME
                                },
                                onLoginClick = {
                                    registerViewModel.resetState()
                                    currentScreen = Screen.LOGIN
                                }
                            )
                        }
                        Screen.HOME -> {
                            HomeScreen(
                                viewModel = homeViewModel,
                                onScheduleClick = {
                                    currentScreen = Screen.SCHEDULE
                                },
                                onCompanionClick = {
                                    currentScreen = Screen.COMPANION
                                },
                                onGamesClick = {
                                    currentScreen = Screen.GAMES
                                },
                                onMemoryClick = {
                                    currentScreen = Screen.MEMORY
                                },
                                onRemindersClick = {
                                    currentScreen = Screen.REMINDERS
                                }
                            )
                        }
                        Screen.SCHEDULE -> {
                            ScheduleScreen(
                                viewModel = scheduleViewModel,
                                onBackClick = {
                                    scheduleViewModel.resetState()
                                    currentScreen = Screen.HOME
                                }
                            )
                        }
                        Screen.COMPANION -> {
                            CompanionScreen(
                                viewModel = companionViewModel,
                                onBackClick = {
                                    companionViewModel.resetState()
                                    currentScreen = Screen.HOME
                                }
                            )
                        }
                        Screen.GAMES -> {
                            GamesScreen(
                                viewModel = gamesViewModel,
                                onBackClick = {
                                    gamesViewModel.resetState()
                                    currentScreen = Screen.HOME
                                },
                                onProgressClick = {
                                    currentScreen = Screen.PROGRESS
                                }
                            )
                        }
                        Screen.MEMORY -> {
                            MemoryScreen(
                                viewModel = memoryViewModel,
                                onBackClick = {
                                    memoryViewModel.resetState()
                                    currentScreen = Screen.HOME
                                },
                                onAlbumClick = {
                                    currentScreen = Screen.ALBUM
                                },
                                onAddMemoryClick = {
                                    currentScreen = Screen.ADD_MEMORY
                                }
                            )
                        }
                        Screen.ALBUM -> {
                            AlbumScreen(
                                viewModel = albumViewModel,
                                onBackClick = {
                                    albumViewModel.resetState()
                                    currentScreen = Screen.MEMORY
                                },
                                onVoiceClick = {
                                    currentScreen = Screen.VOICE
                                },
                                onPlacesClick = {
                                    currentScreen = Screen.PLACES
                                }
                            )
                        }
                        Screen.VOICE -> {
                            VoiceScreen(
                                viewModel = voiceViewModel,
                                onBackClick = {
                                    voiceViewModel.resetState()
                                    currentScreen = Screen.ALBUM
                                }
                            )
                        }
                        Screen.REMINDERS -> {
                            RemindersScreen(
                                viewModel = remindersViewModel,
                                onBackClick = {
                                    remindersViewModel.resetState()
                                    currentScreen = Screen.HOME
                                }
                            )
                        }
                        Screen.PLACES -> {
                            PlacesScreen(
                                viewModel = placesViewModel,
                                onBackClick = {
                                    placesViewModel.resetState()
                                    currentScreen = Screen.ALBUM
                                }
                            )
                        }
                        Screen.PROGRESS -> {
                            ProgressScreen(
                                viewModel = progressViewModel,
                                onBackClick = {
                                    progressViewModel.resetState()
                                    currentScreen = Screen.GAMES
                                }
                            )
                        }
                        Screen.CAREGIVER -> {
                            CaregiverScreen(
                                viewModel = caregiverViewModel,
                                onBackClick = {
                                    caregiverViewModel.resetState()
                                    currentScreen = Screen.HOME
                                },
                                onViewMemoriesClick = {
                                    caregiverViewModel.resetState()
                                    currentScreen = Screen.MEMORY
                                },
                                onManageRemindersClick = {
                                    caregiverViewModel.resetState()
                                    currentScreen = Screen.REMINDERS
                                },
                                onSupportClick = {
                                    currentScreen = Screen.SUPPORT
                                },
                                onProfileClick = {
                                    currentScreen = Screen.MY_PROFILE
                                }
                            )
                        }
                        Screen.SUPPORT -> {
                            SupportScreen(
                                viewModel = supportViewModel,
                                onBackClick = {
                                    supportViewModel.resetState()
                                    currentScreen = Screen.CAREGIVER
                                }
                            )
                        }
<<<<<<< HEAD
                        Screen.MY_PROFILE -> {
=======
                        Screen.PROFILE -> {
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
                            ProfileScreen(
                                viewModel = profileViewModel,
                                onBackClick = {
                                    profileViewModel.resetState()
<<<<<<< HEAD
                                    currentScreen = Screen.CAREGIVER
                                },
                                onSettingsClick = {
                                    currentScreen = Screen.SETTINGS
=======
                                    currentScreen = Screen.HOME
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
                                }
                            )
                        }
                        Screen.SETTINGS -> {
                            SettingsScreen(
                                viewModel = settingsViewModel,
                                onBackClick = {
                                    settingsViewModel.resetState()
<<<<<<< HEAD
                                    currentScreen = Screen.MY_PROFILE
                                },
                                onLogoutClick = {
                                    settingsViewModel.resetState()
                                    currentScreen = Screen.WELCOME
=======
                                    currentScreen = Screen.PROFILE
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
                                }
                            )
                        }
                        Screen.ADD_MEMORY -> {
                            AddMemoryScreen(
                                viewModel = addMemoryViewModel,
                                onBackClick = {
                                    addMemoryViewModel.resetState()
                                    currentScreen = Screen.MEMORY
<<<<<<< HEAD
                                },
                                onSaveSuccess = {
                                    addMemoryViewModel.resetState()
=======
                                }
                            )
                        }
                        Screen.MEMORY_DETAILS -> {
                            MemoryDetailsScreen(
                                viewModel = memoryDetailsViewModel,
                                onBackClick = {
                                    memoryDetailsViewModel.resetState()
                                    currentScreen = Screen.ALBUM
                                }
                            )
                        }
                        Screen.AI_CONVERSATIONS -> {
                            AiConversationsScreen(
                                viewModel = aiConversationsViewModel,
                                onBackClick = {
                                    aiConversationsViewModel.resetState()
                                    currentScreen = Screen.HOME
                                }
                            )
                        }
                        Screen.PREFERENCES -> {
                            PreferencesScreen(
                                viewModel = preferencesViewModel,
                                onBackClick = {
                                    preferencesViewModel.resetState()
                                    currentScreen = Screen.PROFILE
                                }
                            )
                        }
                        Screen.IMPORTANT_PEOPLE -> {
                            ImportantPeopleScreen(
                                viewModel = importantPeopleViewModel,
                                onBackClick = {
                                    importantPeopleViewModel.resetState()
                                    currentScreen = Screen.HOME
                                }
                            )
                        }
                        Screen.HELPFUL_INFORMATION -> {
                            HelpfulInformationScreen(
                                viewModel = helpfulInformationViewModel,
                                onBackClick = {
                                    helpfulInformationViewModel.resetState()
                                    currentScreen = Screen.PROFILE
                                }
                            )
                        }
                        Screen.DAILY_CHECK_IN -> {
                            DailyCheckInScreen(
                                viewModel = dailyCheckInViewModel,
                                onBackClick = {
                                    dailyCheckInViewModel.resetState()
                                    currentScreen = Screen.HOME
                                }
                            )
                        }
                        Screen.FAMILY_UPDATES -> {
                            FamilyUpdatesScreen(
                                viewModel = familyUpdatesViewModel,
                                onBackClick = {
                                    familyUpdatesViewModel.resetState()
>>>>>>> ab6c07414d24d138d6747023711a7174b13410a3
                                    currentScreen = Screen.MEMORY
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
