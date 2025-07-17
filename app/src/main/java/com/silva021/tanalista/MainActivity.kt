package com.silva021.tanalista

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
import com.silva021.tanalista.ui.routes.Routes
import com.silva021.tanalista.ui.routes.Routes.AddShoppingItemScreen.navigateToAddShoppingItemScreen
import com.silva021.tanalista.ui.routes.Routes.AddShoppingListScreen.navigateToCreateListScreen
import com.silva021.tanalista.ui.routes.Routes.ShoppingListsScreen.ITEM_ID
import com.silva021.tanalista.ui.routes.Routes.ShoppingListsScreen.LIST_ID
import com.silva021.tanalista.ui.routes.Routes.ProductStockListScreen.navigateToProductStockListScreen
import com.silva021.tanalista.ui.screen.forgotpassword.ForgotPasswordScreen
import com.silva021.tanalista.ui.screen.login.LoginScreen
import com.silva021.tanalista.ui.screen.register.RegisterScreen
import com.silva021.tanalista.ui.screen.shopping.add.list.AddShoppingListScreen
import com.silva021.tanalista.ui.screen.shopping.add.shopping.AddShoppingItemScreen
import com.silva021.tanalista.ui.screen.shopping.mylist.ShoppingListsScreen
import com.silva021.tanalista.ui.screen.shopping.stock.ProductStockListScreen
import com.silva021.tanalista.ui.screen.welcome.WelcomeScreen
import com.silva021.tanalista.ui.theme.Scaffold
import com.silva021.tanalista.ui.theme.TaNaListaTheme
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
                            )
                        }
                        composable(Routes.ForgotPasswordScreen.route) {
                            ForgotPasswordScreen(
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
                                onBack = {
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
                                        itemId = it
                                    )
                                },
                                onBackPressed = {
                                    Routes.ProductStockListScreen.popBackStack(navController)
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
        val isShowWelcomeScreen = PreferencesManager(applicationContext).isWelcomeShown()
        return if (isShowWelcomeScreen) {
            Routes.LoginScreen.route
        } else {
            Routes.WelcomeScreen.route
        }
    }
}
