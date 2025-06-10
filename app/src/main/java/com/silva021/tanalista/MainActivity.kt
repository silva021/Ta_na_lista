package com.silva021.tanalista

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.silva021.tanalista.domain.model.CategoryType
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.model.StockStatus
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.ui.routes.Routes
import com.silva021.tanalista.ui.screen.forgotpassword.ForgotPasswordScreen
import com.silva021.tanalista.ui.screen.login.LoginScreen
import com.silva021.tanalista.ui.screen.register.RegisterScreen
import com.silva021.tanalista.ui.screen.shopping.add.list.CreateListScreen
import com.silva021.tanalista.ui.screen.shopping.add.shopping.AddShoppingItemScreen
import com.silva021.tanalista.ui.screen.shopping.mylist.MyListsScreen
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
                            MyListsScreen(
                                onCardClick = {
                                    Routes.MyListsScreen.navigateToProductStockListScreen(
                                        navController,
                                        it.id
                                    )
                                },
                                onEditClick = {
                                    Routes.MyListsScreen.navigateToProductStockListScreen(
                                        navController,
                                        it.id
                                    )
                                },
                                onAddClick = {
                                    Routes.MyListsScreen.navigateToCreateListScreen(navController)
                                }
                            )
                        }
                        composable(Routes.CreateListScreen.route) {
                            CreateListScreen(
                                onBackPressed = {
                                    Routes.CreateListScreen.popBackStack(
                                        navController
                                    )
                                }
                            )
                        }

                        composable(Routes.AddShoppingItemScreen.route) { backStackEntry ->
                            val listId = backStackEntry.arguments?.getString(Routes.ProductStockListScreen.LIST_ID).orEmpty()

                            AddShoppingItemScreen(
                                listId = listId,
                                onBack = {
                                    Routes.AddShoppingItemScreen.popBackStack(navController)
                                }
                            )
                        }

                        composable(Routes.ProductStockListScreen.route) { backStackEntry ->
                            val listId = backStackEntry.arguments?.getString(Routes.ProductStockListScreen.LIST_ID).orEmpty()

                            ProductStockListScreen(
                                listId = listId,
                                onAdd = {
                                    Routes.ProductStockListScreen.navigateToAddShoppingItemScreen(
                                        navController,
                                        listId
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
