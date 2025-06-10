package com.silva021.tanalista.di

import androidx.room.Room
import com.pgolcursos.biblequiz.ui.screen.auth.forgotpassword.ForgotPasswordViewModel
import com.pgolcursos.biblequiz.ui.screen.auth.register.RegisterViewModel
import com.silva021.tanalista.data.local.room.AppDatabase
import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.data.repository.ShoppingRepositoryImpl
import com.silva021.tanalista.domain.usecase.AddShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.AddShoppingListUseCase
import com.silva021.tanalista.domain.usecase.CreateUserUseCase
import com.silva021.tanalista.domain.usecase.DeleteShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.DeleteUserAccountUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemsUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.GetUserUseCase
import com.silva021.tanalista.domain.usecase.IsUserLoggedInUseCase
import com.silva021.tanalista.domain.usecase.LoginUseCase
import com.silva021.tanalista.domain.usecase.LogoutUserUseCase
import com.silva021.tanalista.domain.usecase.ResetPasswordUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.UpdateUserUseCase
import com.silva021.tanalista.ui.screen.login.LoginViewModel
import com.silva021.tanalista.ui.screen.shopping.add.list.CreateListViewModel
import com.silva021.tanalista.ui.screen.shopping.add.shopping.AddShoppingItemViewModel
import com.silva021.tanalista.ui.screen.shopping.mylist.MyListsViewModel
import com.silva021.tanalista.ui.screen.shopping.stock.ProductStockListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { MyListsViewModel(get(), get()) }
    viewModel { CreateListViewModel(get()) }
    viewModel { ProductStockListViewModel(
        get(),
        get()
    ) }
    viewModel { AddShoppingItemViewModel(get()) }

}

val roomModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "tanalista.db").build()
    }
    single { (get() as AppDatabase).shoppingListDao() }
}

val usecasesModule = module {
//    GET
    factory { LogoutUserUseCase() }
    factory { DeleteUserAccountUseCase() }
    factory { ResetPasswordUseCase() }
    factory { GetUserUseCase() }
    factory { LoginUseCase() }

//    UPDATE OR INSERT
    factory { CreateUserUseCase() }
    factory { IsUserLoggedInUseCase() }
    factory { UpdateUserUseCase() }

    factory { GetShoppingListsUseCase(get()) }
    factory { AddShoppingListUseCase(get()) }
    factory { AddShoppingItemUseCase(get()) }
    factory { DeleteShoppingListsUseCase(get()) }
    factory { GetShoppingItemsUseCase(get()) }
    factory { UpdateShoppingItemUseCase(get()) }


    single<ShoppingRepository> { ShoppingRepositoryImpl(get()) }
}
