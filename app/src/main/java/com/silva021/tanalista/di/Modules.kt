package com.silva021.tanalista.di

import com.silva021.tanalista.domain.usecase.AcceptInviteShoppingListUseCase
import com.silva021.tanalista.ui.screen.forgotpassword.ForgotPasswordViewModel
import com.silva021.tanalista.ui.screen.register.RegisterViewModel
import com.silva021.tanalista.domain.usecase.AddShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.AddShoppingListUseCase
import com.silva021.tanalista.domain.usecase.CreateUserUseCase
import com.silva021.tanalista.domain.usecase.DeleteShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.DeleteShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.DeleteUserAccountUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemByIdUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemsUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListByIdUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.IsUserLoggedInUseCase
import com.silva021.tanalista.domain.usecase.LoginUseCase
import com.silva021.tanalista.domain.usecase.LogoutUserUseCase
import com.silva021.tanalista.domain.usecase.ResetPasswordUseCase
import com.silva021.tanalista.domain.usecase.UpdateLastUpdateInShoppingListUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingListUseCase
import com.silva021.tanalista.domain.usecase.UpdateUserUseCase
import com.silva021.tanalista.ui.screen.account.AccountViewModel
import com.silva021.tanalista.ui.screen.login.LoginViewModel
import com.silva021.tanalista.ui.screen.shopping.add.list.AddShoppingListViewModel
import com.silva021.tanalista.ui.screen.shopping.add.item.AddShoppingItemViewModel
import com.silva021.tanalista.ui.screen.shopping.mylist.ShoppingListsViewModel
import com.silva021.tanalista.ui.screen.shopping.showinvite.ShowInviteShoppingListViewModel
import com.silva021.tanalista.ui.screen.shopping.stock.ProductStockListViewModel
import com.silva021.tanalista.ui.screen.welcome.WelcomeViewModel
import com.silva021.tanalista.util.helper.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { ShoppingListsViewModel(get(), get()) }
    viewModel { AddShoppingListViewModel(get(), get(), get()) }
    viewModel { ProductStockListViewModel(get(), get(), get()) }
    viewModel { AddShoppingItemViewModel(get(), get(), get()) }
    viewModel { WelcomeViewModel(get()) }
    viewModel { ShowInviteShoppingListViewModel(get(), get()) }
    viewModel { AccountViewModel(get(), get()) }
}

val usecasesModule = module {
//    GET
    factory { LogoutUserUseCase() }
    factory { DeleteUserAccountUseCase() }
    factory { ResetPasswordUseCase() }
    factory { LoginUseCase() }

//    UPDATE OR INSERT
    factory { CreateUserUseCase() }
    factory { IsUserLoggedInUseCase() }
    factory { UpdateUserUseCase() }

    factory { GetShoppingListsUseCase() }
    factory { AddShoppingListUseCase() }
    factory { UpdateShoppingListUseCase() }
    factory { DeleteShoppingListsUseCase() }
    factory { GetShoppingListByIdUseCase() }

    factory { GetShoppingItemsUseCase() }
    factory { AddShoppingItemUseCase(get()) }
    factory { DeleteShoppingItemUseCase() }
    factory { UpdateShoppingItemUseCase(get()) }
    factory { GetShoppingItemByIdUseCase() }

    factory { AcceptInviteShoppingListUseCase() }
    factory { UpdateLastUpdateInShoppingListUseCase() }
}

val sharedPreferences = module {
    single { PreferencesManager(androidContext()) }
}