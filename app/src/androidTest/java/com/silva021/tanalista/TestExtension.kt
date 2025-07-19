package com.silva021.tanalista

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

fun String.isDisplayed(testRule: ComposeTestRule) {
    testRule.onNodeWithText(this).assertIsDisplayed()
}

fun String.isNotExist(testRule: ComposeTestRule) {
    testRule.onNodeWithText(this).assertDoesNotExist()
}

fun String.isExist(testRule: ComposeTestRule) {
    testRule.onNodeWithText(this).assertExists()
}

fun String.isTagExist(testRule: ComposeTestRule) {
    testRule.onNodeWithTag(this, useUnmergedTree = true).assertExists()
}

fun String.isTagNotExist(testRule: ComposeTestRule) {
    testRule.onNodeWithTag(this).assertDoesNotExist()
}

fun String.clickInText(testRule: ComposeTestRule) {
    testRule.onNodeWithText(this).performClick()
}

fun String.clickInTag(testRule: ComposeTestRule) {
    testRule.onNodeWithTag(this).performClick()
}

@Composable
fun Int.isDisplayed(testRule: ComposeTestRule) {
    testRule.onNodeWithText(stringResource(this)).assertIsDisplayed()
}