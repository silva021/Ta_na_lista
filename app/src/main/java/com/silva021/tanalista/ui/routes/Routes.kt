package com.silva021.tanalista.ui.routes

import androidx.navigation.NavController
import com.silva021.tanalista.ui.routes.Routes.ShoppingListsScreen.ITEM_ID
import com.silva021.tanalista.ui.routes.Routes.ShoppingListsScreen.LIST_ID

sealed class Routes(val route: String) {
    fun popBackStack(navController: NavController) {
        navController.popBackStack()
    }

    object LoginScreen : Routes("login") {
        fun navigateToLoginScreen(navController: NavController) {
            navController.navigate(LoginScreen.route)
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

    object ShoppingListsScreen : Routes("shopping_lists_screen") {
        const val LIST_ID = "list_id"
        const val ITEM_ID = "item_id"

        fun navigateToList(navController: NavController) {
            navController.navigate(ShoppingListsScreen.route)
        }
    }

    object AddShoppingListScreen : Routes("add_shopping_list_screen?$LIST_ID={list_id}") {
        fun navigateToCreateListScreen(navController: NavController, listId: String = "") {
            navController.navigate(AddShoppingListScreen.route.replace("{$LIST_ID}", listId))
        }
    }

    object AddShoppingItemScreen : Routes("add_shopping_item_screen?$LIST_ID={list_id}&$ITEM_ID={item_id}") {
        fun navigateToAddShoppingItemScreen(navController: NavController, listId: String, itemId: String = "") {
            navController.navigate(AddShoppingItemScreen.route.replace("{$LIST_ID}", listId).replace("{$ITEM_ID}", itemId))
        }
    }

    object ProductStockListScreen : Routes("product_stock_shopping_list_screen/{list_id}") {
        fun navigateToProductStockListScreen(navController: NavController, listId: String) {
            navController.navigate(ProductStockListScreen.route.replace("{$LIST_ID}", listId))
        }
    }

    object ShareListScreen : Routes("share_list_screen/{list_id}") {
        fun navigateToShareListScreen(navController: NavController, listId: String) {
            navController.navigate(ShareListScreen.route.replace("{$LIST_ID}", listId))
        }
    }

    object ShowInviteShoppingListScreen : Routes("show_invite_shopping_list_screen/{list_id}") {
        fun navigateToShowInviteShoppingListScreen(navController: NavController, listId: String) {
            navController.navigate(ShowInviteShoppingListScreen.route.replace("{$LIST_ID}", listId))
        }
    }

    object AccountScreen : Routes("account_screen") {
        fun navigateToAccountScreen(navController: NavController) {
            navController.navigate(AccountScreen.route)
        }
    }

    object WebScreen : Routes("web_screen/{url}") {
        const val URL_ID = "url"

        fun navigateToWebScreen(navController: NavController, url: String) {
            navController.navigate(WebScreen.route.replace("{$URL_ID}", url))
        }
    }

    object AboutAppScreen : Routes("about_app_screen") {
        fun navigateToAboutAppScreen(navController: NavController) {
            navController.navigate(AboutAppScreen.route)
        }
    }
}
