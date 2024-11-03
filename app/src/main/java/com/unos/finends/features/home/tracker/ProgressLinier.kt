import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unos.finends.ui.theme.SoftGray
import com.unos.finends.ui.theme.YelGreen

@Composable
fun ProgressBarWithLabel(progress: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(0.1f)
                .height(4.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.LightGray)
        ) {
            LinearProgressIndicator(
                progress = {
                    progress
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50)),
                color = YelGreen,
                trackColor = Color.LightGray,
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "${(progress * 100).toInt()}%",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SoftGray
        )
    }
}
