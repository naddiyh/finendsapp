package com.unos.finends.features.home.tracker

import ProgressBarWithLabel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unos.finends.R

@Composable
fun BoxTracker(
    title: String,
    totalAmount: String,
    currentAmount: String,
    progress: Float,
    imageResId: Int,
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .shadow(5.dp, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically // Agar semua item di tengah secara vertikal
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                painter = painterResource(id = imageResId),
                contentDescription = null
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                ProgressBarWithLabel(progress = progress)
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.spacedBy(50.dp)
//                ) {
//                    Column {
//                        Text(text = "Current", fontSize = 12.sp)
//                        Text(text = "Rp $currentAmount", fontSize = 12.sp, fontWeight = FontWeight.Medium)
//                    }
//                }
            }
            Column (

            ){

//            OutlinedButton(
//                onClick = {},
//                modifier = Modifier
//                    .size(40.dp)
//                    .padding(start = 10.dp),
//                shape = CircleShape
//            ) {
//                Text(text = "+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//            }
                Image(
                    modifier = Modifier
                        .size(28.dp)
                        .padding(start = 10.dp),
                    painter = painterResource(id = R.drawable.pin) ,
                    contentDescription = null
                )

            }
        }
    }
}
