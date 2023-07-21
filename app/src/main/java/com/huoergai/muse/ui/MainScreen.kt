package com.huoergai.muse.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huoergai.muse.R

/**
 * D&T: 2023-05-17 16:05
 * DES:
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(titleName: String = stringResource(id = R.string.app_name)) {
    val title by remember { mutableStateOf(titleName) }
    TopAppBar(title = {
        Text(
            text = title,
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }, modifier = Modifier.height(58.dp))
}