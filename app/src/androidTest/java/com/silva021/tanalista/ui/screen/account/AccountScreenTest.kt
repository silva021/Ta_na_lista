package com.silva021.tanalista.ui.screen.account

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.silva021.tanalista.clickInText
import com.silva021.tanalista.domain.usecase.DeleteUserAccountUseCase
import com.silva021.tanalista.domain.usecase.LogoutUserUseCase
import com.silva021.tanalista.isDisplayed
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @MockK
    lateinit var logout: LogoutUserUseCase

    @MockK
    lateinit var deleteAccount: DeleteUserAccountUseCase

    @InjectMockKs
    lateinit var viewModel: AccountViewModel

    @Test
    fun whenOpenScreenShowAllOption() {
        composeTestRule.setContent {
            AccountScreen(
                viewModel,
                onRateApp = {},
                onAboutApp = {},
                onTermsAndConditions = {},
                onPolicyPrivacy = {},
                onResetPassword = {},
                navigateToLogin = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Perfil".isDisplayed(this)
            "Alterar Senha".isDisplayed(this)
            "Sair da Conta".isDisplayed(this)
            "Sobre".isDisplayed(this)
            "Termos de Uso".isDisplayed(this)
            "Política de Privacidade".isDisplayed(this)
            "Avaliar Aplicativo".isDisplayed(this)
            "Sobre o app".isDisplayed(this)
            "Excluir Conta".isDisplayed(this)
        }
    }

    @Test
    fun givenLogoutIsSuccessWhenClickInLogoutThenShowScreen() {
        coEvery { logout.invoke() }  returns Result.success(Unit)

        composeTestRule.setContent {
            AccountScreen(
                viewModel,
                onRateApp = {},
                onAboutApp = {},
                onTermsAndConditions = {},
                onPolicyPrivacy = {},
                onResetPassword = {},
                navigateToLogin = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Sair da Conta".clickInText(this)
            "Tem certeza de que deseja sair da sua conta?".isDisplayed(this)
            "Confirmar".clickInText(this)
            "Você saiu da sua conta com sucesso.".isDisplayed(this)

            coVerify(exactly = 1) {
                logout.invoke()
            }
        }
    }

    @Test
    fun givenLogoutIsFailureWhenClickInLogoutThenShowScreen() {
        coEvery { logout.invoke() }  returns Result.failure(Exception())

        composeTestRule.setContent {
            AccountScreen(
                viewModel,
                onRateApp = {},
                onAboutApp = {},
                onTermsAndConditions = {},
                onPolicyPrivacy = {},
                onResetPassword = {},
                navigateToLogin = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Sair da Conta".clickInText(this)
            "Tem certeza de que deseja sair da sua conta?".isDisplayed(this)
            "Confirmar".clickInText(this)
            "Não foi possível sair da sua conta. Tente novamente.".isDisplayed(this)

            coVerify(exactly = 1) {
                logout.invoke()
            }
        }
    }

    @Test
    fun givenDeleteAccountIsSuccessWhenClickInDeleteAccountThenShowScreen() {
        coEvery { deleteAccount.invoke() }  returns Result.success(Unit)

        composeTestRule.setContent {
            AccountScreen(
                viewModel,
                onRateApp = {},
                onAboutApp = {},
                onTermsAndConditions = {},
                onPolicyPrivacy = {},
                onResetPassword = {},
                navigateToLogin = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Excluir Conta".clickInText(this)
            "Tem certeza de que deseja excluir sua conta? Esta ação é irreversível.".isDisplayed(this)
            "Confirmar".clickInText(this)
            "Sua conta foi excluída com sucesso.".isDisplayed(this)

            coVerify(exactly = 1) {
                deleteAccount.invoke()
            }
        }
    }

    @Test
    fun givenDeleteAccountIsErrorWhenClickInDeleteAccountThenShowScreen() {
        coEvery { deleteAccount.invoke() }  returns Result.failure(Exception())

        composeTestRule.setContent {
            AccountScreen(
                viewModel,
                onRateApp = {},
                onAboutApp = {},
                onTermsAndConditions = {},
                onPolicyPrivacy = {},
                onResetPassword = {},
                navigateToLogin = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Excluir Conta".clickInText(this)
            "Tem certeza de que deseja excluir sua conta? Esta ação é irreversível.".isDisplayed(this)
            "Confirmar".clickInText(this)
            "Não foi possível excluir sua conta. Tente novamente.".isDisplayed(this)


            coVerify(exactly = 1) {
                deleteAccount.invoke()
            }
        }
    }

    @Test
    fun givenDeleteAccountIsRecentLoginRequiredWhenClickInDeleteAccountThenShowScreen() {
        coEvery { deleteAccount.invoke() }  returns Result.failure(
            FirebaseAuthRecentLoginRequiredException(
                "Recent login required",
                "You need to log in again to delete your account."
            )
        )

        composeTestRule.setContent {
            AccountScreen(
                viewModel,
                onRateApp = {},
                onAboutApp = {},
                onTermsAndConditions = {},
                onPolicyPrivacy = {},
                onResetPassword = {},
                navigateToLogin = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Excluir Conta".clickInText(this)
            "Tem certeza de que deseja excluir sua conta? Esta ação é irreversível.".isDisplayed(this)
            "Confirmar".clickInText(this)
            "Para a excluir sua conta, você precisa sair e fazer login novamente.".isDisplayed(this)

            coVerify(exactly = 1) {
                deleteAccount.invoke()
            }
        }
    }

    @Test
    fun whenClickInAboutAppThenNavigateToAboutApp() {
        val onAboutApp = mockk<() -> Unit>(relaxed = true)

        composeTestRule.setContent {
            AccountScreen(
                viewModel,
                onRateApp = {},
                onAboutApp = onAboutApp,
                onTermsAndConditions = {},
                onPolicyPrivacy = {},
                onResetPassword = {},
                navigateToLogin = {},
                onBackPressed = {}
            )
        }

        with(composeTestRule) {
            "Sobre o app".clickInText(this)
            coVerify(exactly = 1) { onAboutApp.invoke() }
        }
    }
}
