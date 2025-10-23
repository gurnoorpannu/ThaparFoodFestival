package com.example.thaparfoodfestival.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thaparfoodfestival.R
import com.example.thaparfoodfestival.teamcard.BrowserWindow

/**
 * Reusable browser background component that displays the bg.png background
 * with a browser window overlay. Just pass your content and it handles the rest.
 * 
 * @param pageName The name to display in the browser address bar
 * @param onMenuClick Callback for when the menu button is clicked
 * @param modifier Optional modifier for the entire component
 * @param content The content to display inside the browser window
 */
@Composable
fun BrowserBackground(
    pageName: String,
    onMenuClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Background Image (bg.png) - same as teamscreen.kt
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        
        // Browser window overlay with your content
        BrowserWindow(
            pageName = pageName,
            onMenuClick = onMenuClick
        ) {
            content()
        }
    }
}