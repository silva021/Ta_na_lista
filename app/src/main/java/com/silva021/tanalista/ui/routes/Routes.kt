package com.silva021.tanalista.ui.routes

import androidx.navigation.NavController
import com.silva021.tanalista.ui.routes.Routes.MyListsScreen.ITEM_ID
import com.silva021.tanalista.ui.routes.Routes.MyListsScreen.LIST_ID

sealed class Routes(val route: String) {
    fun popBackStack(navController: NavController) {
        navController.popBackStack()
    }

    object LoginScreen : Routes("login") {
        fun navigateToLoginScreen(navController: NavController) {
            navController.navigate(LoginScreen.route)
        }

        fun navigateToList(navController: NavController) {
            navController.navigate(MyListsScreen.route)
        }
    }
    object RegisterScreen : Routes("register") {
        fun navigateToRegisterScreen(navController: NavController) {
            navController.navigate(RegisterScreen.route)
        }
    }
    object ForgotPasswordScreen : Routes("forgot_password") {
        fun navigateToForgotPasswordScreen(navController: NavController) {
            navController.navigate(ForgotPasswordScreen.route)
        }
    }

    object WelcomeScreen : Routes("welcome_screen") {
        fun navigateToLogin(navController: NavController) {
            navController.navigate(LoginScreen.route)
        }
    }

    object MyListsScreen : Routes("my_lists_screen") {
        const val LIST_ID = "list_id"
        const val ITEM_ID = "item_id"
    }

    object CreateListScreen : Routes("create_list_screen?$LIST_ID={list_id}") {
        fun navigateToCreateListScreen(navController: NavController, listId: String = "") {
            navController.navigate(CreateListScreen.route.replace("{$LIST_ID}", listId))
        }
    }

    object AddShoppingItemScreen : Routes("add_shopping_item_screen?$LIST_ID={list_id}&$ITEM_ID={item_id}") {
        fun navigateToAddShoppingItemScreen(navController: NavController, listId: String, itemId: String = "") {
//            navController.navigate("add_shopping_item_screen?list_id=$listId&item_id=$itemId")
            navController.navigate(AddShoppingItemScreen.route.replace("{$LIST_ID}", listId).replace("{$ITEM_ID}", itemId))
        }
    }

    object ProductStockListScreen : Routes("product_stock_shopping_list_screen/{list_id}") {
        fun navigateToProductStockListScreen(navController: NavController, listId: String) {
            navController.navigate(ProductStockListScreen.route.replace("{$LIST_ID}", listId))
        }
    }

}
