package com.unos.finends.features.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.unos.finends.R
import com.unos.finends.components.button.Bottom
import com.unos.finends.core.navigation.NavigationItems
import com.unos.finends.ui.theme.YelGreen

@Composable
fun HistoryScreen ( modifier: Modifier = Modifier,
                    navController: NavHostController,){
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()){
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = "Ini history")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .zIndex(40f)
                    .padding(vertical = 30.dp),
            ) {
                Bottom(navController = navController)
            }
        }
    }
}

