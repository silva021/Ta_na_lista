package com.silva021.tanalista.ui.screen.shopping.stock

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.silva021.tanalista.clickInTag
import com.silva021.tanalista.domain.model.UnitType
import com.silva021.tanalista.domain.usecase.DeleteShoppingItemUseCase
import com.silva021.tanalista.domain.usecase.GetShoppingItemsUseCase
import com.silva021.tanalista.domain.usecase.UpdateShoppingItemUseCase
import com.silva021.tanalista.factory.ShoppingFactory.createShoppingItem
import com.silva021.tanalista.isDisplayed
import com.silva021.tanalista.isNotExist
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductStockListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @MockK
    lateinit var getLists: GetShoppingItemsUseCase

    @MockK
    lateinit var updateShoppingItem: UpdateShoppingItemUseCase

    @MockK
    lateinit var deleteShoppingItem: DeleteShoppingItemUseCase

    @InjectMockKs
    lateinit var viewModel: ProductStockListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun givenListEmpty_whenOpenScreen_thenShowEmptyListMessage() {
        coEvery { getLists.invoke(any()) } returns Result.success(listOf())

        composeTestRule.setContent {
            ProductStockListScreen(
                viewModel = viewModel,
                listId = "20",
                onAdd = {},
                onShareList = {},
                onEditShoppingItem = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Não tem nenhum item cadastrado na sua lista".isDisplayed(this)
        }
    }

    @Test
    fun givenShoppingItemsList_whenOpenScreen_thenShowList() {
        coEvery { getLists.invoke(any()) } returns Result.success(
            listOf(
                createShoppingItem()
            )
        )

        composeTestRule.setContent {
            ProductStockListScreen(
                viewModel = viewModel,
                listId = "20",
                onAdd = {},
                onShareList = {},
                onEditShoppingItem = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Novo Item".isDisplayed(this)
            "Não tem nenhum item cadastrado na sua lista".isNotExist(this)
        }
    }

    @Test
    fun givenGetListIsFailure_whenOpenScreen_thenShowErrorMessage() {
        coEvery { getLists.invoke(any()) } returns Result.failure(Exception(""))

        composeTestRule.setContent {
            ProductStockListScreen(
                viewModel = viewModel,
                listId = "20",
                onAdd = {},
                onShareList = {},
                onEditShoppingItem = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Não foi possível carregar sua lista de itens, tente novamente.".isDisplayed(this)
        }
    }

    @Test
    fun givenShoppingItem_whenClickInAdd0rRemove_thenShowQuantityAndStockStatus() {
        var shoppingItem = createShoppingItem(
            listId = "listId",
            id = "itemId",
            quantity = 0f,
            minRequired = 2f,
            unitType = UnitType.PACKAGE
        )


        coEvery { getLists.invoke(any()) } returnsMany listOf(
            Result.success(listOf(shoppingItem)),
            Result.success(listOf(shoppingItem.copy(quantity = 1f))),
            Result.success(listOf(shoppingItem.copy(quantity = 2f))),
        )

        coEvery { updateShoppingItem.invoke(any()) } returns Result.success(Unit)

        composeTestRule.setContent {
            ProductStockListScreen(
                viewModel = viewModel,
                listId = "20",
                onAdd = {},
                onShareList = {},
                onEditShoppingItem = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "0 Pacotes".isDisplayed(this)
            "Sem estoque".isDisplayed(this)
            "increase_button".clickInTag(this)

            "1 Pacotes".isDisplayed(this)
            "Baixo".isDisplayed(this)
            "increase_button".clickInTag(this)

            "2 Pacotes".isDisplayed(this)
            "Bom estoque".isDisplayed(this)

            coVerifyOrder {
                getLists.invoke(any())
                updateShoppingItem.invoke(shoppingItem.copy(quantity = 1f))
                getLists.invoke(any())
                updateShoppingItem.invoke(shoppingItem.copy(quantity = 2f))
                getLists.invoke(any())
            }
        }
    }

    @Test
    fun givenShoppingItem_whenClickInDelete_thenDeleteItem() {
        val shoppingItem = createShoppingItem(
            listId = "listId",
            id = "itemId",
            quantity = 0f,
            minRequired = 2f,
            unitType = UnitType.PACKAGE
        )

        coEvery { getLists.invoke(any()) } returnsMany listOf(
            Result.success(listOf(shoppingItem)),
            Result.success(listOf())
        )
        coEvery { deleteShoppingItem.invoke(any()) } returns Result.success(Unit)

        composeTestRule.setContent {
            ProductStockListScreen(
                viewModel = viewModel,
                listId = "20",
                onAdd = {},
                onShareList = {},
                onEditShoppingItem = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "delete_button".clickInTag(this)
            "Não tem nenhum item cadastrado na sua lista".isDisplayed(this)

            coVerifyOrder {
                getLists.invoke(any())
                deleteShoppingItem.invoke(shoppingItem)
                getLists.invoke(any())
            }
        }
    }
}
