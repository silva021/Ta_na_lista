package com.silva021.tanalista

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.silva021.designsystem.theme.Scaffold
import com.silva021.designsystem.theme.TaNaListaTheme
import com.silva021.tanalista.ui.routes.Routes
import com.silva021.tanalista.ui.routes.Routes.AddShoppingItemScreen.navigateToAddShoppingItemScreen
import com.silva021.tanalista.ui.routes.Routes.AddShoppingListScreen.navigateToCreateListScreen
import com.silva021.tanalista.ui.routes.Routes.ProductStockListScreen.navigateToProductStockListScreen
import com.silva021.tanalista.ui.routes.Routes.ShoppingListsScreen.ITEM_ID
import com.silva021.tanalista.ui.routes.Routes.ShoppingListsScreen.LIST_ID
import com.silva021.tanalista.ui.routes.Routes.WebScreen.URL_ID
import com.silva021.tanalista.ui.screen.account.AccountScreen
import com.silva021.tanalista.ui.screen.forgotpassword.ForgotPasswordScreen
import com.silva021.tanalista.ui.screen.login.LoginScreen
import com.silva021.tanalista.ui.screen.register.RegisterScreen
import com.silva021.tanalista.ui.screen.shopping.add.item.AddShoppingItemScreen
import com.silva021.tanalista.ui.screen.shopping.add.list.AddShoppingListScreen
import com.silva021.tanalista.ui.screen.shopping.mylist.ShoppingListsScreen
import com.silva021.tanalista.ui.screen.shopping.sharelist.ShareListScreen
import com.silva021.tanalista.ui.screen.shopping.showinvite.ShowInviteShoppingListScreen
import com.silva021.tanalista.ui.screen.shopping.stock.ProductStockListScreen
import com.silva021.tanalista.ui.screen.web.WebScreen
import com.silva021.tanalista.BuildConfig
import com.silva021.tanalista.ui.routes.Routes.AboutAppScreen
import com.silva021.tanalista.ui.screen.account.aboutapp.AboutAppScreen
import com.silva021.tanalista.ui.screen.welcome.WelcomeScreen
import com.silva021.tanalista.util.helper.PreferencesManager

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val startDestination = getStartDestination()
            navController = rememberNavController()

            TaNaListaTheme {
                Scaffold {
                    NavHost(
                        navController = navController, startDestination = startDestination
                    ) {
                        composable(Routes.LoginScreen.route) {
                            LoginScreen(
                                navigateToRegisterScreen = {
                                    Routes.RegisterScreen.navigateToRegisterScreen(navController)
                                }, isLoggedListener = {
                                    Routes.ShoppingListsScreen.navigateToList(navController)
                                }, navigateToForgotPasswordScreen = {
                                    Routes.ForgotPasswordScreen.navigateToForgotPasswordScreen(
                                        navController
                                    )
                                }
                            )
                        }
                        composable(Routes.RegisterScreen.route) {
                            RegisterScreen(
                                onBackPressed = {
                                    Routes.RegisterScreen.popBackStack(navController)
                                },
                                navigateToMyListScreen = {
                                    Routes.ShoppingListsScreen.navigateToList(navController)
                                },
                                navigateToTermsAndConditions = {
                                    Routes.WebScreen.navigateToWebScreen(navController, "politica-de-privacidade.html")
                                }
                            )
                        }
                        composable(Routes.ForgotPasswordScreen.route) {
                            ForgotPasswordScreen(
                                onNavigateToLogin = {
                                    Routes.LoginScreen.navigateToLoginScreen(navController)
                                },
                                onBackPressed = {
                                    Routes.ForgotPasswordScreen.popBackStack(navController)
                                })
                        }

                        composable(Routes.WelcomeScreen.route) {
                            WelcomeScreen(
                                onStartClick = { Routes.WelcomeScreen.navigateToLogin(navController) }
                            )
                        }
                        composable(Routes.ShoppingListsScreen.route) {
                            ShoppingListsScreen(
                                onCardClick = {
                                    navigateToProductStockListScreen(
                                        navController,
                                        it.id
                                    )
                                },
                                onEditClick = {
                                    navigateToCreateListScreen(
                                        navController,
                                        it.id
                                    )
                                },
                                onAddClick = {
                                    navigateToCreateListScreen(navController)
                                },
                                onAccountClick = {
                                    Routes.AccountScreen.navigateToAccountScreen(navController)
                                },
                                onBackPressed = {
                                    Routes.ShoppingListsScreen.popBackStack(navController)
                                }
                            )
                        }
                        composable(
                            route = Routes.AddShoppingListScreen.route,
                            arguments = listOf(
                                navArgument(LIST_ID) { type = NavType.StringType; nullable = true },
                            )
                        ) { backStackEntry ->
                            val listId = backStackEntry.arguments?.getString(LIST_ID).orEmpty()
                            AddShoppingListScreen(
                                listId = listId,
                                onBackPressed = {
                                    Routes.AddShoppingListScreen.popBackStack(
                                        navController
                                    )
                                }
                            )
                        }

                        composable(
                            route = Routes.AddShoppingItemScreen.route,
                            arguments = listOf(
                                navArgument(LIST_ID) { type = NavType.StringType },
                                navArgument(ITEM_ID) {
                                    type = NavType.StringType; nullable = true
                                }
                            )
                        ) { backStackEntry ->
                            val listId =
                                backStackEntry.arguments?.getString(LIST_ID)
                                    .orEmpty()

                            val itemId =
                                backStackEntry.arguments?.getString(ITEM_ID)
                                    .orEmpty()

                            AddShoppingItemScreen(
                                listId = listId,
                                itemId = itemId,
                                onBackPressed = {
                                    Routes.AddShoppingItemScreen.popBackStack(navController)
                                }
                            )
                        }

                        composable(
                            route = Routes.ProductStockListScreen.route
                        ) { backStackEntry ->
                            val listId =
                                backStackEntry.arguments?.getString(LIST_ID)
                                    .orEmpty()

                            ProductStockListScreen(
                                listId = listId,
                                onAdd = {
                                    navigateToAddShoppingItemScreen(
                                        navController,
                                        listId
                                    )
                                },
                                onEditShoppingItem = {
                                    navigateToAddShoppingItemScreen(
                                        navController = navController,
                                        listId = listId,
                                        itemId = it.id
                                    )
                                },
                                onShareList = { listId ->
                                    Routes.ShareListScreen.navigateToShareListScreen(
                                        navController,
                                        listId
                                    )
                                },
                                onBackPressed = {
                                    Routes.ProductStockListScreen.popBackStack(navController)
                                }
                            )
                        }

                        composable(
                            route = Routes.ShareListScreen.route,
                            arguments = listOf(
                                navArgument(LIST_ID) { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val listId =
                                backStackEntry.arguments?.getString(LIST_ID)
                                    .orEmpty()

                            ShareListScreen(
                                listId,
                                onBackPressed = {
                                    Routes.ShareListScreen.popBackStack(navController)
                                }
                            )
                        }

                        composable(
                            route = Routes.ShowInviteShoppingListScreen.route,
                            arguments = listOf(
                                navArgument(LIST_ID) { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val listId =
                                backStackEntry.arguments?.getString(LIST_ID)
                                    .orEmpty()

                            ShowInviteShoppingListScreen(
                                listId = listId,
                                navigateToHome = {
                                    Routes.ShoppingListsScreen.navigateToList(navController)
                                },
                                onBackPressed = {
                                    Routes.ShowInviteShoppingListScreen.popBackStack(
                                        navController
                                    )
                                }
                            )
                        }
                        composable(Routes.AccountScreen.route) {
                            AccountScreen(
                                onRateApp = {
                                    val appId = applicationContext.packageName
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("market://details?id=$appId")
                                    ).apply {
                                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    }
                                    applicationContext.startActivity(intent)
                                },
                                onTermsAndConditions = {
                                    Routes.WebScreen.navigateToWebScreen(
                                        navController,
                                        "termos-de-uso.html"
                                    )
                                },
                                onPolicyPrivacy = {
                                    Routes.WebScreen.navigateToWebScreen(
                                        navController,
                                        "politica-de-privacidade.html"
                                    )
                                },
                                onResetPassword = {
                                    Routes.ForgotPasswordScreen.navigateToForgotPasswordScreen(
                                        navController
                                    )
                                },
                                onAboutApp = {
                                    Routes.AboutAppScreen.navigateToAboutAppScreen(navController)
                                },
                                navigateToLogin = {
                                    Routes.LoginScreen.navigateToLoginScreen(navController)
                                },
                                onBackPressed = {
                                    Routes.ForgotPasswordScreen.popBackStack(navController)
                                }
                            )
                        }

                        composable(
                            route = Routes.WebScreen.route,
                            arguments = listOf(
                                navArgument(URL_ID) { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val url =
                                backStackEntry.arguments?.getString(URL_ID)
                                    .orEmpty()
                            WebScreen(
                                url = url,
                                onBackPressed = {
                                    Routes.WebScreen.popBackStack(navController)
                                }
                            )
                        }

                        composable(
                            route = Routes.AboutAppScreen.route
                        ) {
                            AboutAppScreen(
                                onBackPressed = {
                                    Routes.AboutAppScreen.popBackStack(navController)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun getStartDestination(): String {
        intent.data?.let { uri ->
            Firebase.auth.currentUser?.let {
                if (uri.scheme == "tanalista" && uri.host == "shared_list") {
                    val listId = uri.getQueryParameter("listId")
                    if (!listId.isNullOrBlank()) {
                        return Routes.ShowInviteShoppingListScreen.route.replace(
                            "{list_id}",
                            listId.replace(Regex("[{}]"), "")
                        )
                    }
                }
            }
        }

        val isShowWelcomeScreen = PreferencesManager(applicationContext).isWelcomeShown()
        return if (isShowWelcomeScreen) {
            Routes.LoginScreen.route
        } else {
            Routes.WelcomeScreen.route
        }
    }
}
