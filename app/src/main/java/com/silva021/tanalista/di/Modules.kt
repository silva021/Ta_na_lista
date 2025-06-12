package com.silva021.tanalista.di

import androidx.room.Room
import com.silva021.tanalista.ui.screen.forgotpassword.ForgotPasswordViewModel
import com.silva021.tanalista.ui.screen.register.RegisterViewModel
import com.silva021.tanalista.data.local.room.AppDatabase
import com.silva021.tanalista.data.repository.ShoppingRepository
import com.silva021.tanalista.data.repository.ShoppingRepositoryImpl
import com.silva021.tanalista.data.repository.ReminderRepository
import com.silva021.tanalista.data.repository.ReminderRepositoryImpl
import com.silva021.tanalista.domain.usecase.AddShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.AddShoppingListUseCase
import com.silva021.tanalista.domain.usecase.CreateUserUseCase
import com.silva021.tanalista.domain.usecase.DeleteShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.DeleteUserAccountUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemByIdUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemsUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListByIdUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.GetUserUseCase
import com.silva021.tanalista.domain.usecase.IsUserLoggedInUseCase
import com.silva021.tanalista.domain.usecase.LoginUseCase
import com.silva021.tanalista.domain.usecase.LogoutUserUseCase
import com.silva021.tanalista.domain.usecase.ResetPasswordUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingListUseCase
import com.silva021.tanalista.domain.usecase.GetRemindersUseCase
import com.silva021.tanalista.domain.usecase.AddReminderUseCase
import com.silva021.tanalista.domain.usecase.UpdateReminderUseCase
import com.silva021.tanalista.domain.usecase.DeleteReminderUseCase
import com.silva021.tanalista.domain.usecase.UpdateUserUseCase
import com.silva021.tanalista.ui.screen.login.LoginViewModel
import com.silva021.tanalista.ui.screen.shopping.add.list.CreateListViewModel
import com.silva021.tanalista.ui.screen.shopping.add.shopping.AddShoppingItemViewModel
import com.silva021.tanalista.ui.screen.shopping.mylist.ShoppingListsViewModel
import com.silva021.tanalista.ui.screen.shopping.stock.ProductStockListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { ShoppingListsViewModel(get(), get()) }
    viewModel { CreateListViewModel(get(), get(), get()) }
    viewModel { ProductStockListViewModel(get(), get()) }
    viewModel { AddShoppingItemViewModel(get(), get(), get()) }

}

val roomModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "tanalista.db").build()
    }
    single { (get() as AppDatabase).shoppingListDao() }
    single { (get() as AppDatabase).shoppingReminderDao() }
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
    factory { GetShoppingItemByIdUseCase(get()) }
    factory { GetShoppingListByIdUseCase(get()) }
    factory { UpdateShoppingListUseCase(get()) }

    factory { GetRemindersUseCase(get()) }
    factory { AddReminderUseCase(get()) }
    factory { UpdateReminderUseCase(get()) }
    factory { DeleteReminderUseCase(get()) }

    single<ShoppingRepository> { ShoppingRepositoryImpl(get()) }
    single<ReminderRepository> { ReminderRepositoryImpl(get()) }
}
