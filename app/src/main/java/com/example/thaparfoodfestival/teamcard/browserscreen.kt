package com.example.thaparfoodfestival.teamcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import com.example.thaparfoodfestival.R

// Define Source Serif font family
val sourceSerifFontFamily = FontFamily(
    Font(R.font.source_serrif4, FontWeight.Normal)
)

@Composable
fun BrowserWindow(
    pageName: String,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    // Calculate responsive dimensions - wider and taller
    val windowWidth = (screenWidth * 0.98f).coerceAtMost(500.dp)
    val windowHeight = (screenHeight * 0.88f).coerceAtMost(950.dp)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .width(windowWidth)
                .height(windowHeight)
        ) {
            // Top Bar
            TopBar(
                pageName = pageName,
                onMenuClick = onMenuClick,
                modifier = Modifier.fillMaxWidth()
            )

            // Main Content Area with Scrollbar
            BrowserContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                content = content
            )
        }
    }
}

@Composable
private fun TopBarBackground(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val cornerRadius = 16.dp.toPx()
        val cutoutTopY = 0f // Start at the very top
        val horizontalLength = 50.dp.toPx() // How far RIGHT before the slant
        val slantEndX = 73.dp.toPx() // Where the slant ends on X
        val slantEndY = 16.dp.toPx() // Where the slant ends on Y

        // Purple border path
        val borderPath = Path().apply {
            // Start from bottom-left
            moveTo(0f, size.height)

            // Go UP the left edge to the very TOP
            lineTo(0f, cutoutTopY)

            // Go RIGHT horizontally at the top
            lineTo(horizontalLength, cutoutTopY)

            // Diagonal slant going DOWN-RIGHT
            lineTo(slantEndX, slantEndY)

            // Continue horizontally along the top bar to the right
            lineTo(size.width - cornerRadius, slantEndY)

            // Curve around top-right corner
            quadraticBezierTo(
                size.width, slantEndY,
                size.width, slantEndY + cornerRadius
            )

            // Right edge down
            lineTo(size.width, size.height)

            // Bottom edge back to start
            lineTo(0f, size.height)

            close()
        }

        // Draw purple border
        drawPath(
            path = borderPath,
            color = Color(0xFF7C0787),
            style = Stroke(width = 4.dp.toPx())
        )

        // White background (inset to show border) - fills entire area including above search
        val bgPath = Path().apply {
            val inset = 2.5.dp.toPx()

            moveTo(inset, size.height - inset)
            lineTo(inset, cutoutTopY + inset)
            lineTo(horizontalLength - inset, cutoutTopY + inset)
            lineTo(slantEndX - inset, slantEndY + inset)
            lineTo(size.width - cornerRadius - inset, slantEndY + inset)
            quadraticBezierTo(
                size.width - inset, slantEndY + inset,
                size.width - inset, slantEndY + cornerRadius
            )
            lineTo(size.width - inset, size.height - inset)
            lineTo(inset, size.height - inset)
            close()
        }

        drawPath(
            path = bgPath,
            color = Color.White
        )
    }
}
@Composable
private fun TopBar(
    pageName: String,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(78.dp)
            .fillMaxWidth()
    ) {
        // Custom background with triangular cutout
        TopBarBackground(
            modifier = Modifier.fillMaxSize()
        )

        // Content
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Left section with icons
            Row(
                modifier = Modifier.offset(y = 18.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hamburger Menu Button
                IconButton(
                    onClick = onMenuClick,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color(0xFF9F0A74),
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Refresh Button
                IconButton(
                    onClick = { /* Visual only */ },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        tint = Color(0xFF9F0A74),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Page Name Input Field
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(36.dp)
                    .offset(y = 18.dp),
                color = Color.White,
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(3.dp, Color(0xFF9F0A74)),
                shadowElevation = 2.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White, RoundedCornerShape(18.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = pageName,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                fontFamily = sourceSerifFontFamily,
                                color = Color(0xFF000000),
                            )
                        )

                        // Close Button
                        IconButton(
                            onClick = { /* Visual only */ },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color(0xFF9F0A74),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BrowserContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .border(
                width = 4.dp,
                color = Color(0xFF7C0787),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .padding(4.dp)
            .background(
                color = Color(0xF2FFFFFF),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Scrollable Content Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    item {
                        content()
                    }
                }
            }

            // Custom Scrollbar
            CustomScrollbar(
                listState = listState,
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .padding(vertical = 4.dp, horizontal = 2.dp)
            )
        }
    }
}

@Composable
private fun CustomScrollbar(
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    val layoutInfo = listState.layoutInfo
    val viewportHeight = layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset
    val totalHeight = layoutInfo.totalItemsCount * (layoutInfo.visibleItemsInfo.firstOrNull()?.size ?: 0)

    // Calculate scrollbar thumb size and position
    val scrollbarHeight = if (totalHeight > 0) {
        ((viewportHeight.toFloat() / totalHeight) * viewportHeight).coerceIn(40f, viewportHeight.toFloat())
    } else {
        viewportHeight.toFloat()
    }

    val scrollOffset = if (totalHeight > viewportHeight) {
        (listState.firstVisibleItemIndex.toFloat() / layoutInfo.totalItemsCount) * viewportHeight +
                (listState.firstVisibleItemScrollOffset.toFloat() / totalHeight) * viewportHeight
    } else {
        0f
    }

    Box(
        modifier = modifier
            .background(
                color = Color(0xFF7C0787).copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        // Scrollbar thumb
        Box(
            modifier = Modifier
                .offset(y = scrollOffset.dp)
                .width(12.dp)
                .height(scrollbarHeight.dp)
                .background(
                    color = Color(0xFF7C0787),
                    shape = RoundedCornerShape(6.dp)
                )
                .align(Alignment.TopCenter)
        )
    }
}

// Demo Team Card (you can replace this with your actual card later)
@Composable
fun DemoTeamCard(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7C0787)
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = role,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore.",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            )
        }
    }
}

// Example Usage
@Composable
fun BrowserWindowExample() {
    BrowserWindow(
        pageName = "MEET THE TEAM",
        onMenuClick = {
            // Handle drawer open
            println("Menu clicked - Open drawer here")
        }
    ) {
        // Empty content - ready for your team cards
    }
}
// Simple triangular cutout shape matching Figma design exactly
class TriangularCutoutShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val cutoutWidth = with(density) { 60.dp.toPx() }
            val cutoutHeight = with(density) { 25.dp.toPx() }
            val cornerRadius = with(density) { 16.dp.toPx() }
            
            // Start from the cutout point (where triangle ends)
            moveTo(cutoutWidth, 0f)
            
            // Top edge to the right
            lineTo(size.width - cornerRadius, 0f)
            
            // Top-right corner
            quadraticBezierTo(size.width, 0f, size.width, cornerRadius)
            
            // Right edge
            lineTo(size.width, size.height)
            
            // Bottom edge
            lineTo(0f, size.height)
            
            // Left edge up to cutout
            lineTo(0f, cutoutHeight)
            
            // Diagonal line creating triangular cutout (top-left to bottom-right direction)
            lineTo(cutoutWidth, 0f)
            
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
private fun SimpleLayeredBackground(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val cutoutWidth = 60.dp.toPx()
        val cutoutHeight = 25.dp.toPx()
        val cornerRadius = 16.dp.toPx()
        
        // Outer light border (3D effect)
        val outerPath = Path().apply {
            moveTo(cutoutWidth + 3f, -2f)
            lineTo(size.width - cornerRadius + 2f, -2f)
            quadraticBezierTo(size.width + 2f, -2f, size.width + 2f, cornerRadius)
            lineTo(size.width + 2f, size.height + 2f)
            lineTo(-2f, size.height + 2f)
            lineTo(-2f, cutoutHeight + 2f)
            lineTo(cutoutWidth + 3f, -2f)
            close()
        }
        
        // Inner purple border
        val innerPath = Path().apply {
            moveTo(cutoutWidth + 1f, 0f)
            lineTo(size.width - cornerRadius, 0f)
            quadraticBezierTo(size.width, 0f, size.width, cornerRadius)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            lineTo(0f, cutoutHeight)
            lineTo(cutoutWidth + 1f, 0f)
            close()
        }
        
        // Main background
        val mainPath = Path().apply {
            moveTo(cutoutWidth, 2f)
            lineTo(size.width - cornerRadius, 2f)
            quadraticBezierTo(size.width - 2f, 2f, size.width - 2f, cornerRadius)
            lineTo(size.width - 2f, size.height - 2f)
            lineTo(2f, size.height - 2f)
            lineTo(2f, cutoutHeight)
            lineTo(cutoutWidth, 2f)
            close()
        }
        
        // Draw layers
        drawPath(
            path = outerPath,
            color = Color(0xFFE8E8E8),
            style = Stroke(width = 3.dp.toPx())
        )
        
        drawPath(
            path = innerPath,
            color = Color(0xFF7C0787),
            style = Stroke(width = 2.dp.toPx())
        )
        
        drawPath(
            path = mainPath,
            color = Color(0xFF9F0A74)
        )
    }
}
