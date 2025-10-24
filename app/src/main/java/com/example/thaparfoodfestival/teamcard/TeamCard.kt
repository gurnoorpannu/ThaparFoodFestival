package com.example.thaparfoodfestival.teamcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import android.content.Intent
import android.net.Uri
import com.example.thaparfoodfestival.R

// Caveat font family - using default font for now to avoid R reference issues
val caveatFont = FontFamily.Default

data class TeamMember(
    val name: String,
    val role: String,
    val photoResId: Int? = null, // Drawable resource ID
    val instagramUrl: String,
    val linkedinUrl: String
)

@Composable
fun TeamCard(
    member: TeamMember,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .width(350.dp)
            .height(500.dp)
    ) {
        // Background card frame using teamcard.png
        Image(
            painter = painterResource(id = R.drawable.teamcard),
            contentDescription = "Team Card Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        // Content overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 40.dp, end = 40.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Photo area (black rounded rectangle)
            Box(
                modifier = Modifier
                    .width(260.dp)
                    .height(260.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                if (member.photoResId != null) {
                    Image(
                        painter = painterResource(id = member.photoResId),
                        contentDescription = "${member.name}'s photo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Default photo",
                        modifier = Modifier.size(120.dp),
                        tint = Color.White.copy(alpha = 0.3f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Name
            Text(
                text = member.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = caveatFont,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Role
            Text(
                text = member.role,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = caveatFont,
                textAlign = TextAlign.Center,
                color = Color.Black.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Social media icons
            Row(
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                // Instagram icon - using text for now until R reference is fixed
                IconButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(member.instagramUrl))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Text(
                        text = "IG",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                // LinkedIn icon - using text for now until R reference is fixed
                IconButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(member.linkedinUrl))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Text(
                        text = "LI",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

// Usage example
@Composable
fun TeamCardScreen() {
    val sampleMember = TeamMember(
        name = "Arsh Chatrath",
        role = "Overall Student Co-ordinator",
        photoResId = R.drawable.arsh, // Your drawable here
        instagramUrl = "https://instagram.com/username",
        linkedinUrl = "https://linkedin.com/in/username"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        TeamCard(member = sampleMember)
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun TeamCardPreview() {
    TeamCardScreen()
}