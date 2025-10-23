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
    // Sample team data - replace with your actual team data
    val teamMembers = listOf(
        TeamMember(
            name = "Rachit Shah",
            role = "Convenor",
            department = "Event Management",
            email = "rachit@thaparfoodfestival.com",
            phone = "+91 98765 43210",
            description = "Leading the entire food festival with passion and dedication. Ensuring every event runs smoothly and creates memorable experiences."
        ),
        TeamMember(
            name = "Priya Sharma",
            role = "Co-Convenor",
            department = "Operations",
            email = "priya@thaparfoodfestival.com",
            phone = "+91 98765 43211",
            description = "Managing day-to-day operations and coordinating between different teams to ensure seamless execution of all festival activities."
        ),
        TeamMember(
            name = "Arjun Patel",
            role = "Event Manager",
            department = "Event Coordination",
            email = "arjun@thaparfoodfestival.com",
            phone = "+91 98765 43212",
            description = "Overseeing all event logistics, vendor coordination, and ensuring every food stall meets our quality standards."
        ),
        TeamMember(
            name = "Sneha Gupta",
            role = "Marketing Head",
            department = "Marketing & PR",
            email = "sneha@thaparfoodfestival.com",
            phone = "+91 98765 43213",
            description = "Driving marketing campaigns, social media presence, and building partnerships to make the festival a grand success."
        ),
        TeamMember(
            name = "Vikram Singh",
            role = "Finance Head",
            department = "Finance & Accounts",
            email = "vikram@thaparfoodfestival.com",
            phone = "+91 98765 43214",
            description = "Managing budgets, financial planning, and ensuring transparent financial operations for the entire festival."
        ),
        TeamMember(
            name = "Ananya Reddy",
            role = "Volunteer Coordinator",
            department = "Human Resources",
            email = "ananya@thaparfoodfestival.com",
            phone = "+91 98765 43215",
            description = "Recruiting, training, and coordinating volunteers to ensure we have adequate support for all festival activities."
        )
    )

    BrowserBackground(
        pageName = "MEET THE TEAM",
        onMenuClick = onBackClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF7C0787).copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🍽️ Thapar Food Festival Team",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF7C0787),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Meet the passionate individuals who make this festival possible",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Team members
            teamMembers.forEach { member ->
                TeamMemberCard(
                    member = member,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun TeamMemberCard(
    member: TeamMember,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { /* Handle click if needed */ },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Profile picture placeholder
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF7C0787),
                                    Color(0xFF9F0A74)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (member.imageRes != null) {
                        Image(
                            painter = painterResource(id = member.imageRes),
                            contentDescription = member.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

                // Member info
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = member.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF7C0787)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = member.role,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF9F0A74)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = member.department,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Description
            Text(
                text = member.description,
                fontSize = 14.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Contact info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Email
                ContactChip(
                    icon = Icons.Default.Email,
                    text = "Email",
                    modifier = Modifier.weight(1f)
                )

                // Phone
                ContactChip(
                    icon = Icons.Default.Phone,
                    text = "Call",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ContactChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable { /* Handle contact action */ },
        color = Color(0xFF7C0787).copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF7C0787),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF7C0787)
            )
        }
    }
}