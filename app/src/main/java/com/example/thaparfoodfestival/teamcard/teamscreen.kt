package com.example.thaparfoodfestival.teamcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thaparfoodfestival.components.BrowserBackground

// Team member data class
data class TeamMember(
    val name: String,
    val role: String,
    val department: String,
    val email: String,
    val phone: String,
    val imageRes: Int? = null,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(
    onBackClick: () -> Unit = {}
) {
    BrowserBackground(
        pageName = "MEET THE TEAM",
        onMenuClick = onBackClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

        }
    }
}