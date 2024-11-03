package com.unos.finends.features.profile

import InfoPersonal
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseUser
import com.unos.finends.R
import com.unos.finends.components.button.Bottom
import com.unos.finends.core.navigation.NavigationItems
import com.unos.finends.ui.theme.SoftJade
import com.unos.finends.ui.theme.YelGreen

@Composable
fun ProfileScreen (modifier: Modifier = Modifier,
                   navController: NavHostController, currentUser: FirebaseUser?,){
    Surface(modifier = Modifier
        .fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 60.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(text = "Profile Settings", fontWeight = FontWeight.Medium)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                     ,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                    Column() {


                    currentUser?.let { user ->
                        user.photoUrl?.let {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "User Photo",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(99.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                        }
                        user.displayName?.let { name ->
                            Text(
                                text = "hay$name",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 16.sp,
                                color = SoftJade,
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                        }
                    }}
                    InfoPersonal(
                        name = "John Doe",
                        phone = 1234567890,
                        email = "ss",
                        country = "Country Name")

                    Notification()
                    Settings()
                }

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
    }}

//@Composable
//private fun Bottom(navController: NavHostController, modifier: Modifier = Modifier) {
//    Box(
//        modifier = Modifier
//            .padding(horizontal = 20.dp)
//            .zIndex(30f)
//            .clip(RoundedCornerShape(30.dp))
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(YelGreen)
//                .clip(RoundedCornerShape(30.dp))
//                .zIndex(30f),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            IconButton(onClick = { navController.navigate(NavigationItems.Home.route) }) {
//                Icon(
//                    painter = painterResource(R.drawable.iconhome),
//                    contentDescription = "Home",
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//            IconButton(onClick = { navController.navigate(NavigationItems.History.route) }) {
//                Icon(
//                    painter = painterResource(R.drawable.history),
//                    contentDescription = "History",
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//            IconButton(onClick = { navController.navigate(NavigationItems.Statistic.route) }) {
//                Icon(
//                    painter = painterResource(R.drawable.statis),
//                    contentDescription = "Statistic",
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//
//            IconButton(onClick = { navController.navigate(NavigationItems.Profile.route) }) {
//                Icon(
//                    painter = painterResource(R.drawable.userr),
//                    contentDescription = "Profile",
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//        }
//    }
//}
