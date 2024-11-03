package com.unos.finends.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.unos.finends.R
import com.unos.finends.core.navigation.NavigationItems
import com.unos.finends.ui.theme.YelGreen

@Composable
 fun Bottom(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(horizontal = 50.dp)
            .zIndex(30f)
            .clip(RoundedCornerShape(30.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(YelGreen)
                .clip(RoundedCornerShape(30.dp))
                .zIndex(30f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { navController.navigate(NavigationItems.Home.route) }) {
                Icon(
                    painter = painterResource(R.drawable.iconhome),
                    contentDescription = "Home",
                    modifier = Modifier.size(23.dp),
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { navController.navigate(NavigationItems.History.route) }) {
                Icon(
                    painter = painterResource(R.drawable.history),
                    contentDescription = "History",
                    modifier = Modifier.size(23.dp),
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = { navController.navigate(NavigationItems.Statistic.route) }) {
                Icon(
                    painter = painterResource(R.drawable.statis),
                    contentDescription = "Statistic",
                    modifier = Modifier.size(23.dp),
                    tint = Color.Unspecified
                )
            }

            IconButton(onClick = { navController.navigate(NavigationItems.Profile.route) }) {
                Icon(
                    painter = painterResource(R.drawable.userr),
                    contentDescription = "Profile",
                    modifier = Modifier.size(23.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
}
