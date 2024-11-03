package com.unos.finends.features.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unos.finends.ui.theme.YelGreen

@Composable
fun Category(
    onAllClick: () -> Unit = { /* Default action for "All" */ },
    onHolidaysClick: () -> Unit = { /* Default action for "Holidays" */ },
    onCarsClick: () -> Unit = { /* Default action for "Cars" */ }
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        OutlinedButton(  modifier = Modifier.height(28.dp),
                onClick = onAllClick,
            border = BorderStroke(0.5.dp, YelGreen),

            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            )
        ) {
            Text(text = "All", lineHeight = 2.sp, fontSize = 10.sp, fontWeight = FontWeight.Normal)
        }

        OutlinedButton(modifier = Modifier.height(28.dp),
            onClick = onAllClick,
            border = BorderStroke(0.5.dp, YelGreen),

            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            )

        ) {
            Text(text = "Holidays",   lineHeight = 12.sp, fontSize = 12.sp,fontWeight = FontWeight.Normal,  )
        }

        OutlinedButton(modifier = Modifier.height(28.dp),
            onClick = onAllClick,
            border = BorderStroke(0.5.dp, YelGreen),

            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            )

        ) {
            Text(text = "Cars",  lineHeight = 2.sp, fontSize = 12.sp, fontWeight = FontWeight.Normal,  )

        }
    }
}
