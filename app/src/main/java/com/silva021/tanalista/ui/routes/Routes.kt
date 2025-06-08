package com.silva021.tanalista.ui.routes

import androidx.navigation.NavController

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
        fun navigateToCreateListScreen(navController: NavController) {
            navController.navigate(CreateListScreen.route)
        }
        fun navigateToProductStockListScreen(navController: NavController) {
            navController.navigate(ProductStockListScreen.route)
        }
    }

    object CreateListScreen : Routes("create_list_screen")

    object AddShoppingItemScreen : Routes("add_shopping_item_screen")
    object ProductStockListScreen : Routes("product_stock_shopping_list_screen") {
        fun navigateToAddShoppingItemScreen(navController: NavController) {
            navController.navigate(AddShoppingItemScreen.route)
        }
    }

}
