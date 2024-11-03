package com.unos.finends.features.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unos.finends.ui.theme.SoftGray


@Composable
fun Notification() {


    Column(verticalArrangement = Arrangement.spacedBy(6.dp)){

        Text(text = "Notifications", fontSize = 14.sp, fontWeight = FontWeight.Normal
            , modifier = Modifier.padding(horizontal = 10.dp), )
    Column( modifier = Modifier
        .fillMaxWidth()
        .border(BorderStroke(0.5.dp, SoftGray), RoundedCornerShape(12.dp))
        .padding(horizontal = 14.dp, vertical = 10.dp)
        , verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Push Notification",fontSize = 14.sp, fontWeight = FontWeight.Normal)
//            Button(onClick = { setNotificationsEnabled(!isNotificationsEnabled) }) {
//                // Use an icon to indicate notification state
//                Icon(
//                    imageVector = if (isNotificationsEnabled) Icons.Filled.Notifications else Icons.Filled.NotificationsOff,
//                    contentDescription = if (isNotificationsEnabled) "Disable Notifications" else "Enable Notifications",
//                    tint = Color.Unspecified // Change this if you want a specific color
//                )
//            }
        }
        Divider(color = SoftGray, thickness = 0.6.dp)
        Text(text = "Goal Milestone Alerts",fontSize = 14.sp, fontWeight = FontWeight.Normal)
    }}
}
