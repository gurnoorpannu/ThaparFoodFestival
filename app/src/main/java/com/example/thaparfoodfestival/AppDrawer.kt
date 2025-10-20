package com.example.thaparfoodfestival

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun CustomDrawerApp() {
    var isDrawerOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Drawer Background (always behind, full screen)
        DrawerContent(
            isOpen = isDrawerOpen,
            onClose = { isDrawerOpen = false }
        )

        // Main Content (scales down and moves to right)
        MainContent(
            isDrawerOpen = isDrawerOpen,
            onMenuClick = { isDrawerOpen = true }
        )
    }
}

@Composable
fun MainContent(
    isDrawerOpen: Boolean,
    onMenuClick: () -> Unit
) {
    // Animate scale, translation, and corner radius
    val scale by animateFloatAsState(
        targetValue = if (isDrawerOpen) 0.65f else 1f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "content_scale"
    )

    val offsetX by animateFloatAsState(
        targetValue = if (isDrawerOpen) 0.35f else 0f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "content_translation"
    )

    val cornerRadius by animateDpAsState(
        targetValue = if (isDrawerOpen) 24.dp else 0.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "corner_radius"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = size.width * offsetX
                    clip = true
                    shape = RoundedCornerShape(cornerRadius)
                    shadowElevation = if (isDrawerOpen) 16.dp.toPx() else 0f
                }
                .clip(RoundedCornerShape(cornerRadius))
                .background(Color.White)
                .clickable(enabled = isDrawerOpen, onClick = {})
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Top Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color(0xFF1E293B)
                        )
                    }

                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE2E8F0))
                                .padding(6.dp)
                        )
                    }
                }

                // Courses Section
                Text(
                    text = "Courses",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                // Course Cards
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CourseCard(
                        title = "Animations in SwiftUI",
                        subtitle = "Build and animate an iOS app from scratch",
                        duration = "20 SECTIONS - 3 HOURS",
                        color = Color(0xFF8B5CF6),
                        badge = "iOS"
                    )

                    CourseCard(
                        title = "Build Quality Apps with SwiftUI",
                        subtitle = "Apply your SwiftUI knowledge",
                        duration = "47 SECTIONS",
                        color = Color(0xFF3B82F6),
                        badge = null
                    )
                }

                Text(
                    text = "Recent",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    RecentCard("State Machine", "Watch video - 15 mins", Color(0xFF93C5FD))
                    RecentCard("Animated Menu", "Watch video - 10 mins", Color(0xFFA78BFA))
                }
            }
        }
    }
}

@Composable
fun DrawerContent(
    isOpen: Boolean,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .zIndex(0f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
        ) {
            // Logo Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tfflogo),
                    contentDescription = "TFF Logo",
                    modifier = Modifier
                        .height(80.dp)  // Increased from 48.dp
                        .width(200.dp)  // Increased from 120.dp
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )

                if (isOpen) {
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Navigation Section
            Text(
                text = "NAVIGATION",
                color = Color(0xFF64748B),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Navigation items with max width to prevent overlap
            Column(
                modifier = Modifier.fillMaxWidth(0.65f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DrawerItem(
                    icon = Icons.Default.Home,
                    text = "About us",
                    isSelected = true,
                    onClick = onClose
                )
                DrawerItem(
                    icon = Icons.Default.DateRange,
                    text = "Events",
                    isSelected = false,
                    onClick = onClose
                )
                DrawerItem(
                    icon = Icons.Default.Person,
                    text = "Team",
                    isSelected = false,
                    onClick = onClose
                )
                DrawerItem(
                    icon = Icons.Default.Star,
                    text = "Sponsors",
                    isSelected = false,
                    onClick = onClose
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DrawerItem(
    icon: ImageVector,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color.Cyan else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun CourseCard(
    title: String,
    subtitle: String,
    duration: String,
    color: Color,
    badge: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color)
            .padding(20.dp)
    ) {
        Column {
            Text(
                text = title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = duration,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp
            )
        }

        if (badge != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.3f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = badge,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun RecentCard(title: String, subtitle: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )
        }
    }
}