    package com.unos.finends.features.home

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material3.Button
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.draw.shadow
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextOverflow
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.compose.ui.zIndex
    import androidx.navigation.NavHostController
    import com.google.firebase.auth.FirebaseUser
    import coil.compose.AsyncImage
    import coil.request.ImageRequest
    import com.unos.finends.R
    import com.unos.finends.components.button.Bottom
    import com.unos.finends.core.navigation.NavigationItems
    import com.unos.finends.ui.theme.SoftJade
    import com.unos.finends.ui.theme.YelGreen
    import com.unos.finends.features.home.tracker.BoxTracker

    @Composable
    fun HomeScreen(
        modifier: Modifier = Modifier,
        navController: NavHostController,
        currentUser: FirebaseUser?,
        onSignOutClick: () -> Unit,
    ) {
        val textStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )

        Surface(
            modifier = modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Main content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Column(modifier = Modifier.padding(horizontal = 25.dp, vertical = 50.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
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
                                        text = "Hello, $name!",
                                        style = textStyle,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SoftJade,
                                    )
                                    Spacer(modifier = Modifier.size(16.dp))
                                }
                            }

                            Button(onClick = { onSignOutClick() }) {
                                Text(
                                    text = "Sign Out",
                                    style = textStyle.copy(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "See your current financial situation here",
                            fontSize = 14.sp,
                            color = SoftJade
                        )
                        Spacer(modifier = Modifier.height(25.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Current Balance",
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "Rp 20.000.000,00",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = SoftJade,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    // Box Tracker
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
                            )
                            .shadow(
                                elevation = 20.dp,
                                shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
                                clip = false
                            )
                            .background(color = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        ) {
                            Spacer(modifier = Modifier.height(30.dp))
                            Text(
                                text = "Bucket List",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Category()
                            Spacer(modifier = Modifier.height(25.dp))
                            Column(
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.spacedBy(25.dp)
                            ) {
                                BoxTracker(title = "HRV", totalAmount = "500.000", currentAmount = "4.000.000", progress = 0.6f,  imageResId = R.drawable.tes, onAddClick = {})
                                BoxTracker(title = "Trip to Seoul", totalAmount = "3.000.000", currentAmount = "1.500.000", progress = 0.5f, imageResId = R.drawable.tes, onAddClick = {})
                                BoxTracker(title = "HRV", totalAmount = "500.000", currentAmount = "4.000.000", progress = 0.6f, imageResId = R.drawable.tes, onAddClick = {})
                                BoxTracker(title = "HRV", totalAmount = "500.000", currentAmount = "4.000.000", progress = 0.6f, imageResId = R.drawable.tes, onAddClick = {})
                                BoxTracker(title = "HRV", totalAmount = "500.000", currentAmount = "4.000.000", progress = 0.6f, imageResId = R.drawable.tes, onAddClick ={} )

                            }
                            Spacer(modifier = Modifier.height(25.dp))
                        }
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
        }
    }

//    @Composable
//    private fun Bottom(navController: NavHostController, modifier: Modifier = Modifier) {
//        Box(
//            modifier = Modifier
//                .padding(horizontal = 20.dp)
//                .zIndex(30f)
//                .clip(RoundedCornerShape(30.dp))
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(YelGreen)
//                    .clip(RoundedCornerShape(30.dp))
//                    .zIndex(30f),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                IconButton(onClick = { navController.navigate(NavigationItems.Home.route) }) {
//                    Icon(
//                        painter = painterResource(R.drawable.iconhome),
//                        contentDescription = "Home",
//                        modifier = Modifier.size(20.dp)
//                    )
//                }
//                IconButton(onClick = { navController.navigate(NavigationItems.History.route) }) {
//                    Icon(
//                        painter = painterResource(R.drawable.history),
//                        contentDescription = "History",
//                        modifier = Modifier.size(20.dp)
//                    )
//                }
//                IconButton(onClick = { navController.navigate(NavigationItems.Statistic.route) }) {
//                    Icon(
//                        painter = painterResource(R.drawable.statis),
//                        contentDescription = "Statistic",
//                        modifier = Modifier.size(20.dp)
//                    )
//                }
//
//                IconButton(onClick = { navController.navigate(NavigationItems.Profile.route) }) {
//                    Icon(
//                        painter = painterResource(R.drawable.userr),
//                        contentDescription = "Profile",
//                        modifier = Modifier.size(20.dp)
//                    )
//                }
//            }
//        }
//    }

//@Composable
//fun BottomNavigation(navController: NavHostController) {
//    TODO("Not yet implemented")
//}
//

//fun BottomBar(navController: NavHostController) {
//    val screens : ListOf(
//        BottomNavigation.Home,
//        BottomNavigation.History,
//        BottomNavigation.Settings
//    )
//}


//user.email?.let { mailId : String ->
//                Text(text = "Mail ID : $mailId",
//                    style = textStyle,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                    )
//
//                Spacer(modifier = Modifier.size(16.dp))
//            }

//        Spacer(modifier = Modifier.size(16.dp))
//        Button(onClick = { onSignOutClick() }) {
//            Text(text = "Sign Out",
//                style = textStyle.copy(
//                    fontWeight = FontWeight.SemiBold
//                ))
//
//        }
//        }


