package com.silva021.tanalista

import android.os.Bundle
import android.util.Log
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
import com.silva021.tanalista.domain.model.ListColor
import com.silva021.tanalista.domain.model.ShoppingItem
import com.silva021.tanalista.domain.model.ShoppingList
import com.silva021.tanalista.domain.model.StockItem
import com.silva021.tanalista.domain.model.StockStatus
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.ui.routes.Routes
import com.silva021.tanalista.ui.screen.forgotpassword.ForgotPasswordScreen
import com.silva021.tanalista.ui.screen.login.LoginScreen
import com.silva021.tanalista.ui.screen.register.RegisterScreen
import com.silva021.tanalista.ui.screen.shopping.add.list.CreateListScreen
import com.silva021.tanalista.ui.screen.shopping.add.shopping.AddShoppingItemScreen
import com.silva021.tanalista.ui.screen.shopping.mylist.MyListsScreen
import com.silva021.tanalista.ui.screen.stock.ProductStockListScreen
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
                            val shoppingLists = listOf(
                                ShoppingList(
                                    name = "Mercado",
                                    items = listOf(
                                        ShoppingItem(
                                            name = "Arroz",
                                            quantity = 2,
                                            unitType = UnitType.KILOGRAM,
                                        ),
                                        ShoppingItem(
                                            name = "Feijão",
                                            quantity = 1,
                                            unitType = UnitType.KILOGRAM,
                                        ),
                                        ShoppingItem(
                                            name = "Leite",
                                            quantity = 2,
                                            unitType = UnitType.LITER,
                                        ),
                                        ShoppingItem(
                                            name = "Ovos",
                                            quantity = 12,
                                            unitType = UnitType.UNIT,
                                        ),
                                        ShoppingItem(
                                            name = "Pão",
                                            quantity = 1,
                                            unitType = UnitType.UNIT,
                                        )
                                    ),
                                    color = ListColor.MINT_GREEN,
                                    type = CategoryType.GROCERY
                                ),
                                ShoppingList(
                                    name = "Farmácia",
                                    items = listOf(
                                        ShoppingItem(
                                            name = "Dipirona",
                                            quantity = 1,
                                            unitType = UnitType.BOX
                                        ),
                                        ShoppingItem(
                                            name = "Curativo",
                                            quantity = 1,
                                            unitType = UnitType.PACKAGE
                                        )
                                    ),
                                    color = ListColor.PEACH,
                                    type = CategoryType.PHARMACY
                                )
                            )

                            MyListsScreen(
                                onDeleteClick = { /* TODO */ },
                                onCardClick = {
                                    Routes.MyListsScreen.navigateToProductStockListScreen(
                                        navController
                                    )
                                },
                                onEditClick = {
                                    Routes.MyListsScreen.navigateToProductStockListScreen(
                                        navController
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

                        composable(Routes.AddShoppingItemScreen.route) {
                            var name by remember { mutableStateOf("") }
                            var quantity by remember { mutableStateOf("") }
                            var unit by remember { mutableStateOf("kg") }
                            var category by remember { mutableStateOf("Alimentos") }
                            var trackStock by remember { mutableStateOf(false) }

                            AddShoppingItemScreen(
                                name = name,
                                onNameChange = { name = it },
                                quantity = quantity,
                                onQuantityChange = { quantity = it },
                                unit = unit,
                                onUnitChange = { unit = it },
                                selectedCategory = category,
                                onCategoryChange = { category = it },
                                trackStock = trackStock,
                                onTrackStockChange = { trackStock = it },
                                onSave = { /* salvar */ },
                                onBackPressed = {
                                    Routes.AddShoppingItemScreen.popBackStack(navController)
                                }
                            )
                        }

                        composable(Routes.ProductStockListScreen.route) {
                            val stockItems = listOf(
                                StockItem(
                                    id = "arroz",
                                    name = "Arroz",
                                    currentQuantity = 2.0f,
                                    unitType = UnitType.KILOGRAM,
                                    minRequired = 5.0f,
                                    status = StockStatus.LOW
                                ),
                                StockItem(
                                    id = "feijao",
                                    name = "Feijão",
                                    currentQuantity = 1.0f,
                                    unitType = UnitType.KILOGRAM,
                                    minRequired = 3.0f,
                                    status = StockStatus.CRITICAL
                                ),
                                StockItem(
                                    id = "leite",
                                    name = "Leite",
                                    currentQuantity = 2.0f,
                                    unitType = UnitType.LITER,
                                    minRequired = 2.0f,
                                    status = StockStatus.OK
                                ),
                                StockItem(
                                    id = "ovos",
                                    name = "Ovos",
                                    currentQuantity = 12.0f,
                                    unitType = UnitType.UNIT,
                                    minRequired = 18.0f,
                                    status = StockStatus.LOW
                                ),
                                StockItem(
                                    id = "pao",
                                    name = "Pão",
                                    currentQuantity = 1.0f,
                                    unitType = UnitType.UNIT,
                                    minRequired = 4.0f,
                                    status = StockStatus.CRITICAL
                                )
                            )

                            ProductStockListScreen(
                                items = stockItems,
                                onAdd = {
                                    Routes.ProductStockListScreen.navigateToAddShoppingItemScreen(
                                        navController
                                    )
                                },
                                onEditClick = {},
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
