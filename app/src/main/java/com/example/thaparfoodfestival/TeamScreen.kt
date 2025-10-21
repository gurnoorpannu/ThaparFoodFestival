// UtilitiesScreen.kt
package com.example.thaparfoodfestival

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.absoluteValue
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.airbnb.lottie.compose.*

// Color definitions
val CyanAccent = Color(0xFF00FFFF)
val DarkGray = Color(0xFF1A1A1A)
val DarkerGray = Color(0xFF0D0D0D)
val BorderGray = Color(0xFF333333)
val TextGray = Color(0xFFB0B0B0)
val CardBackground = Color(0xFF3A4F5C)
val InstagramPink = Color(0xFFE1306C)
val LinkedInBlue = Color(0xFF0077B5)

data class FaqItem(
    val question: String,
    val answer: String
)

data class TeamMember(
    val name: String,
    val role: String,
    val imageUrl: String,
    val linkedInUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UtilitiesScreen(onMenuClick: () -> Unit = {}) {
    val scrollState = rememberScrollState()
    var isFaqExpanded by remember { mutableStateOf(false) }
    var expandedFaqIndex by remember { mutableStateOf<Int?>(null) }

    val teamSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val developersSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showTeamSheet by remember { mutableStateOf(false) }
    var showDevelopersSheet by remember { mutableStateOf(false) }

    val faqData = listOf(
        FaqItem(
            "How do I register for events?",
            "Visit the official website or instagram profile of Techniche for all events registrations."
        ),
        FaqItem(
            "Where can I find the event schedule?",
            "The event schedule is available on our official website and mobile app. You can also check our social media pages for updates."
        ),
        FaqItem(
            "Is accommodation provided?",
            "Yes, accommodation can be arranged for outstation participants. Please register early and specify your accommodation requirements."
        ),
        FaqItem(
            "Who do I contact in case of emergency?",
            "For emergencies, please contact our 24/7 helpline or visit the IITG Hospital. Emergency contacts are available in the Quick Contacts section."
        )
    )

    // Sample team data - replace with actual data
    val teamMembers = listOf(
        TeamMember("Rachit Shah", "Convenor", "", "https://linkedin.com"),
        TeamMember("John Doe", "Co-Convenor", "", "https://linkedin.com"),
        TeamMember("Jane Smith", "Event Manager", "", "https://linkedin.com")
    )

    val developers = listOf(
        TeamMember("Divyanshu Tiwari", "Finance Head", "", "https://linkedin.com"),
        TeamMember("Alex Johnson", "Lead Developer", "", "https://linkedin.com"),
        TeamMember("Sarah Williams", "UI/UX Designer", "", "https://linkedin.com")
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            // Top Bar with Menu Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = CyanAccent,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            
            // Header
            Text(
                text = "Utilities & Contacts",
                color = CyanAccent,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // Team Section
            SectionItem(
                icon = Icons.Default.AccountCircle,
                title = "Team",
                onClick = { showTeamSheet = true }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Developers Section
            SectionItem(
                icon = Icons.Default.Create,
                title = "Developers",
                onClick = { showDevelopersSheet = true }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // FAQ Section
            Column {
                SectionItem(
                    icon = Icons.Default.Email,
                    title = "FAQ",
                    isExpandable = true,
                    isExpanded = isFaqExpanded,
                    onClick = { isFaqExpanded = !isFaqExpanded }
                )

                AnimatedVisibility(
                    visible = isFaqExpanded,
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Column(modifier = Modifier.padding(top = 8.dp)) {
                        faqData.forEachIndexed { index, faq ->
                            FaqItemCard(
                                faq = faq,
                                isExpanded = expandedFaqIndex == index,
                                onClick = {
                                    expandedFaqIndex = if (expandedFaqIndex == index) null else index
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Quick Links
            Text(
                text = "Quick Links",
                color = TextGray,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                SocialLink(
                    iconResource = R.drawable.instagram,
                    label = "Instagram",
                    color = InstagramPink,
                    url = "https://www.instagram.com/thaparfoodfestival/?hl=en"
                )

                Spacer(modifier = Modifier.width(32.dp))

                SocialLink(
                    iconResource = R.drawable.linkedin,
                    label = "LinkedIn",
                    color = LinkedInBlue,
                    url = "https://www.linkedin.com/company/thaparfoodfestival/?originalSubdomain=in"
                )
            }
        }

        // Team Bottom Sheet
        if (showTeamSheet) {
            ModalBottomSheet(
                onDismissRequest = { showTeamSheet = false },
                sheetState = teamSheetState,
                containerColor = Color.Black,
                dragHandle = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            modifier = Modifier
                                .width(40.dp)
                                .height(4.dp),
                            shape = RoundedCornerShape(2.dp),
                            color = TextGray
                        ) {}
                    }
                }
            ) {
                TeamBottomSheetContent(
                    title = "Meet the Team",
                    members = teamMembers,
                    onClose = { showTeamSheet = false }
                )
            }
        }

        // Developers Bottom Sheet
        if (showDevelopersSheet) {
            ModalBottomSheet(
                onDismissRequest = { showDevelopersSheet = false },
                sheetState = developersSheetState,
                containerColor = Color.Black,
                dragHandle = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            modifier = Modifier
                                .width(40.dp)
                                .height(4.dp),
                            shape = RoundedCornerShape(2.dp),
                            color = TextGray
                        ) {}
                    }
                }
            ) {
                TeamBottomSheetContent(
                    title = "Meet the Team",
                    members = developers,
                    onClose = { showDevelopersSheet = false }
                )
            }
        }
    }
}

@Composable
fun TeamBottomSheetContent(
    title: String,
    members: List<TeamMember>,
    onClose: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val density = LocalDensity.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp) // Reduced height for more compact bottom sheet
            .background(Color.Black)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Team Members Horizontal Scroll with Tilt Effect and Snap Behavior
        val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState)
        
        // Calculate current focused card index for dots indicator
        val layoutInfo = lazyListState.layoutInfo
        val visibleItemsInfo = layoutInfo.visibleItemsInfo
        val currentFocusedIndex = visibleItemsInfo.minByOrNull { itemInfo ->
            val itemCenter = itemInfo.offset + itemInfo.size / 2
            val screenCenter = layoutInfo.viewportSize.width / 2
            kotlin.math.abs(itemCenter - screenCenter)
        }?.index ?: 0
        
        LazyRow(
            state = lazyListState,
            flingBehavior = snapFlingBehavior,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .weight(1f), // Take available space
            horizontalArrangement = Arrangement.spacedBy(24.dp), // Increased spacing
            contentPadding = PaddingValues(horizontal = 40.dp) // Increased padding
        ) {
            itemsIndexed(members) { index, member ->
                val itemInfo = visibleItemsInfo.find { it.index == index }
                
                val rotationZ = if (itemInfo != null) {
                    val itemCenter = itemInfo.offset + itemInfo.size / 2
                    val screenCenter = layoutInfo.viewportSize.width / 2
                    val distance = (itemCenter - screenCenter).toFloat()
                    val maxRotation = 15f
                    val deadZone = 100f // Dead zone where cards appear straight
                    
                    // Only apply rotation if the card is outside the dead zone
                    if (kotlin.math.abs(distance) < deadZone) {
                        0f // Card is in center, no rotation
                    } else {
                        // Apply rotation with adjusted distance (subtract dead zone)
                        val adjustedDistance = if (distance > 0) {
                            distance - deadZone
                        } else {
                            distance + deadZone
                        }
                        val normalizedDistance = (adjustedDistance / (screenCenter - deadZone)).coerceIn(-1f, 1f)
                        -normalizedDistance * maxRotation
                    }
                } else {
                    0f
                }

                TeamMemberCard(
                    member = member,
                    rotationZ = rotationZ
                )
            }
        }
        
        // Carousel Dots Indicator
        CarouselDots(
            totalDots = members.size,
            currentIndex = currentFocusedIndex,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun TeamMemberCard(
    member: TeamMember,
    rotationZ: Float = 0f
) {
    val context = LocalContext.current
    
    // Lottie animation composition
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.tffteamcard))
    
    // Simple continuous animation
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier
            .width(320.dp) // Increased from 280.dp to 320.dp
            .height(450.dp) // Increased from 380.dp to 450.dp
            .padding(horizontal = 8.dp)
            .graphicsLayer {
                this.rotationZ = rotationZ
                cameraDistance = 12f * density
            }
    ) {
        // Lottie Animation Background
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize()
        )
        
        // Content overlay on top of Lottie animation
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp), // Increased padding from 24.dp to 32.dp
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Profile Image
            Surface(
                modifier = Modifier
                    .size(140.dp), // Increased from 120.dp to 140.dp
                shape = CircleShape,
                color = Color.Transparent
            ) {
                if (member.imageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = member.imageUrl,
                        contentDescription = member.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White.copy(alpha = 0.1f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(70.dp), // Increased from 60.dp to 70.dp
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp)) // Increased spacing

            // Name with background for better readability
            Surface(
                modifier = Modifier.fillMaxWidth(0.95f), // Increased from 0.9f
                shape = RoundedCornerShape(10.dp), // Increased corner radius
                color = Color.Black.copy(alpha = 0.7f) // Increased opacity
            ) {
                Text(
                    text = member.name,
                    color = Color.White,
                    fontSize = 20.sp, // Increased from 18.sp
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp) // Increased padding
                )
            }

            Spacer(modifier = Modifier.height(12.dp)) // Increased spacing

            // Role with background
            Surface(
                modifier = Modifier.fillMaxWidth(0.85f), // Increased from 0.8f
                shape = RoundedCornerShape(8.dp), // Increased corner radius
                color = CyanAccent.copy(alpha = 0.25f) // Increased opacity
            ) {
                Text(
                    text = member.role,
                    color = CyanAccent,
                    fontSize = 16.sp, // Increased from 14.sp
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp) // Increased padding
                )
            }

            Spacer(modifier = Modifier.height(20.dp)) // Increased spacing

            // LinkedIn Button
            Surface(
                modifier = Modifier
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(member.linkedInUrl))
                        context.startActivity(intent)
                    }
                    .fillMaxWidth(0.75f), // Increased from 0.7f
                shape = RoundedCornerShape(14.dp), // Increased corner radius
                color = Color.Black.copy(alpha = 0.8f) // Increased opacity
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp), // Increased padding
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "LinkedIn",
                        tint = CyanAccent,
                        modifier = Modifier.size(20.dp) // Increased from 18.dp
                    )

                    Spacer(modifier = Modifier.width(8.dp)) // Increased spacing

                    Text(
                        text = "LinkedIn",
                        color = CyanAccent,
                        fontSize = 16.sp, // Increased from 14.sp
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun CarouselDots(
    totalDots: Int,
    currentIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { index ->
            val isSelected = index == currentIndex
            
            Surface(
                modifier = Modifier
                    .size(if (isSelected) 12.dp else 8.dp)
                    .padding(horizontal = 4.dp),
                shape = CircleShape,
                color = if (isSelected) CyanAccent else Color.White.copy(alpha = 0.4f)
            ) {}
        }
    }
}

@Composable
fun SectionItem(
    icon: ImageVector,
    title: String,
    isExpandable: Boolean = false,
    isExpanded: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = DarkGray,
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderGray)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = CyanAccent,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                color = CyanAccent,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = if (isExpandable) {
                    if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                } else {
                    Icons.Default.KeyboardArrowRight
                },
                contentDescription = null,
                tint = CyanAccent,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun FaqItemCard(
    faq: FaqItem,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        color = DarkerGray,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF2A2A2A))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = faq.question,
                    color = CyanAccent,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = CyanAccent,
                    modifier = Modifier.size(20.dp)
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Text(
                    text = faq.answer,
                    color = TextGray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Composable
fun SocialLink(
    iconResource: Int,
    label: String,
    color: Color,
    url: String
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
            .padding(12.dp)
    ) {
        Surface(
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            color = Color(0xFF2A2A2A)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = iconResource),
                    contentDescription = label,
                    modifier = Modifier.size(32.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = label,
            color = color,
            fontSize = 14.sp
        )
    }
}

// ============================================
// build.gradle.kts (Module level) - Dependencies
// ============================================
/*
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("io.coil-kt:coil-compose:2.5.0")
    
    // Lottie Animation
    implementation("com.airbnb.android:lottie-compose:6.1.0")
}
*/