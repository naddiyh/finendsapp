import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unos.finends.ui.theme.SoftGray

@Composable
fun InfoPersonal(name: String, email: String, phone: Int, country: String) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = "Personal Information",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(0.5.dp, SoftGray), RoundedCornerShape(12.dp))
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Name", fontSize = 14.sp, )
                Text(text = name, fontSize = 14.sp , color = Color.Gray) // Removed the dollar sign for direct interpolation

            }
            Divider(color = SoftGray, thickness = 0.6.dp)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Email Address", fontSize = 14.sp, )
                Text(text = email, fontSize = 14.sp,  color = Color.Gray)
            }
            Divider(color = SoftGray, thickness = 0.6.dp)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Phone Number", fontSize = 14.sp, )
                Text(text = phone.toString(), fontSize = 14.sp,  color = Color.Gray)
            }
            Divider(color = SoftGray, thickness = 0.6.dp)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Country", fontSize = 14.sp)
                Text(text = country, fontSize = 14.sp,  color = Color.Gray)
            }
        }
    }
}
