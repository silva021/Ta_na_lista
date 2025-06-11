package com.silva021.tanalista

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.silva021.tanalista.ui.routes.Routes
import com.silva021.tanalista.ui.routes.Routes.AddShoppingItemScreen.navigateToAddShoppingItemScreen
import com.silva021.tanalista.ui.routes.Routes.CreateListScreen.navigateToCreateListScreen
import com.silva021.tanalista.ui.routes.Routes.MyListsScreen.ITEM_ID
import com.silva021.tanalista.ui.routes.Routes.MyListsScreen.LIST_ID
import com.silva021.tanalista.ui.routes.Routes.ProductStockListScreen.navigateToProductStockListScreen
import com.silva021.tanalista.ui.screen.forgotpassword.ForgotPasswordScreen
import com.silva021.tanalista.ui.screen.login.LoginScreen
import com.silva021.tanalista.ui.screen.register.RegisterScreen
import com.silva021.tanalista.ui.screen.shopping.add.list.CreateListScreen
import com.silva021.tanalista.ui.screen.shopping.add.shopping.AddShoppingItemScreen
import com.silva021.tanalista.ui.screen.shopping.mylist.ShoppingListsScreen
import com.silva021.tanalista.ui.screen.shopping.stock.ProductStockListScreen
import com.silva021.tanalista.ui.screen.welcome.WelcomeScreen
import com.silva021.tanalista.ui.theme.Scaffold
import com.silva021.tanalista.ui.theme.TaNaListaTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val startDestination = Routes.MyListsScreen.route
//            val startDestination = Routes.WelcomeScreen.route
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
//                                    if (BuildConfig.FLAVOR == "admin") {
//                                        Routes.AdminScreen.navigateToAdminScreen(navController)
//                                    } else {
//                                        Routes.StageSelector.navigateToStageSelectorScreen(navController)
//                                    }/
                                }, navigateToForgotPasswordScreen = {
                                    Routes.ForgotPasswordScreen.navigateToForgotPasswordScreen(
                                        navController
                                    )
                                }, navigateToMyListsScreen = {
                                    Routes.LoginScreen.navigateToList(navController)
                                })
                        }
                        composable(Routes.RegisterScreen.route) {
                            RegisterScreen(
                                onBackPressed = {
                                    Routes.RegisterScreen.popBackStack(navController)
                                },
                                navigateToStageSelectorScreen = {},
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
                        composable(Routes.MyListsScreen.route) {
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
                                }
                            )
                        }
                        composable(
                            route = Routes.CreateListScreen.route,
                            arguments = listOf(
                                navArgument(LIST_ID) { type = NavType.StringType; nullable = true },
                            )
                        ) { backStackEntry ->
                            val listId =
                                backStackEntry.arguments?.getString(Routes.MyListsScreen.LIST_ID)
                                    .orEmpty()
                            CreateListScreen(
                                listId = listId,
                                onBackPressed = {
                                    Routes.CreateListScreen.popBackStack(
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
                                backStackEntry.arguments?.getString(Routes.MyListsScreen.LIST_ID)
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
}
