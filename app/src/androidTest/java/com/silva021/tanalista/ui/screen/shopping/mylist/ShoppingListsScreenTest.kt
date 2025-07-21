package com.silva021.tanalista.ui.screen.shopping.mylist

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.silva021.tanalista.domain.usecase.DeleteShoppingListsUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingListsUseCase
import com.silva021.tanalista.factory.ShoppingFactory
import com.silva021.tanalista.isExist
import com.silva021.tanalista.isTagExist
import com.silva021.tanalista.isTagNotExist
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShoppingListsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @MockK
    lateinit var getLists: GetShoppingListsUseCase

    @MockK
    lateinit var deleteShoppingLists: DeleteShoppingListsUseCase

    @InjectMockKs
    lateinit var viewModel: ShoppingListsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun givenShoppingListsWithListSharedThenShowPeopleIcon() {
        coEvery { getLists.invoke() } returns Result.success(
            listOf(
                ShoppingFactory.createShoppingList(isMine = true)
            )
        )

        composeTestRule.setContent {
            ShoppingListsScreen(
                viewModel = viewModel,
                onCardClick = {},
                onAddClick = { /* TODO */ },
                onEditClick = {},
                onBackPressed = { /* TODO */ },
                onAccountClick = { /* TODO */ },
                finishApp = { /* TODO */ }
            )
        }

        "shared_list_icon".isTagNotExist(testRule = composeTestRule)
    }

    @Test
    fun givenShoppingListsWithListNotSharedThenShowNoPeopleIcon() {
        coEvery { getLists.invoke() } returns Result.success(
            listOf(
                ShoppingFactory.createShoppingList(isMine = false)
            )
        )

        composeTestRule.setContent {
            ShoppingListsScreen(
                viewModel = viewModel,
                onCardClick = {},
                onAddClick = { /* TODO */ },
                onEditClick = {},
                onBackPressed = { /* TODO */ },
                onAccountClick = { /* TODO */ },
                finishApp = { /* TODO */ }
            )
        }

        "shared_list_icon".isTagExist(testRule = composeTestRule)
    }
}