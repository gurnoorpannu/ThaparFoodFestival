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
import coil.compose.AsyncImage
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.tooling.preview.Preview
import com.example.thaparfoodfestival.R

// Caveat font family
val caveatFont = FontFamily(
    Font(R.font.caveat, FontWeight.Normal)
)

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

    BoxWithConstraints(
        modifier = modifier
    ) {
        val screenHeight = maxHeight
        val cardHeight = minOf(screenHeight * 0.35f, 290.dp)

        Box(
            modifier = Modifier.height(cardHeight)
        ) {
            // Background card frame (your imported PNG)
            Image(
                painter = painterResource(id = R.drawable.teamcard), // Your PNG here
                contentDescription = "Card frame",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )

            // Content overlay
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                val cardWidth = maxWidth
                val cardHeight = maxHeight

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = cardHeight * 0.14f,
                            start = cardWidth * 0.16f,
                            end = cardWidth * 0.12f,
                            bottom = cardHeight * 0.14f
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Photo area (black rounded rectangle)
                    Box(
                        modifier = Modifier
                            .padding(top = cardHeight * 0.15f)
                            .width(cardWidth * 0.60f)
                            .height(cardHeight * 0.26f)
                            .clip(RoundedCornerShape(cardWidth * 0.075f))
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
                                modifier = Modifier.size(cardWidth * 0.75f),
                                tint = Color.White.copy(alpha = 0.3f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(cardHeight * 0.01f))

                    // Name
                    Text(
                        text = member.name,
                        fontSize = (cardWidth.value * 0.1f).sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = caveatFont,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(cardHeight * 0.007f))

                    // Role
                    Text(
                        text = member.role,
                        fontSize = (cardWidth.value * 0.069f).sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = caveatFont,
                        textAlign = TextAlign.Center,
                        color = Color.Black.copy(alpha = 0.7f)
                    )

                    // Add extra spacing before social media icons
                    Spacer(modifier = Modifier.height(cardHeight * 0.007f))

                    // Social media icons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(cardWidth * 0.1f),
                        modifier = Modifier.padding(bottom = cardHeight * 0.03f)
                    ) {
                        // Instagram icon
                        IconButton(
                            onClick = {
                                val intent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(member.instagramUrl))
                                context.startActivity(intent)
                            },
                            modifier = Modifier.size(cardWidth * 0.175f)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.instagram),
                                contentDescription = "Instagram",
                                modifier = Modifier.size(cardWidth * 0.125f),
                                tint = Color.Unspecified
                            )
                        }

                        // LinkedIn icon
                        IconButton(
                            onClick = {
                                val intent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse(member.linkedinUrl))
                                context.startActivity(intent)
                            },
                            modifier = Modifier.size(cardWidth * 0.175f)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.linkedin),
                                contentDescription = "LinkedIn",
                                modifier = Modifier.size(cardWidth * 0.125f),
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
            }
        }
    }
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
}