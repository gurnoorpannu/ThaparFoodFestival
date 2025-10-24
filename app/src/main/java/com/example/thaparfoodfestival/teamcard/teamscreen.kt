package com.example.thaparfoodfestival.teamcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.thaparfoodfestival.R

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
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-40).dp),
            verticalArrangement = Arrangement.spacedBy((-78).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                // First row with 2 cards
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    TeamCard(
                        member = TeamMember(
                            name = "Arsh Chatrath",
                            role = "Overall Student Coordinator",
                            photoResId = R.drawable.arsh,
                            instagramUrl = "https://instagram.com/arsh",
                            linkedinUrl = "https://linkedin.com/in/arsh"
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    
                    TeamCard(
                        member = TeamMember(
                            name = "Anant Tyaagi",
                            role = "Creative Director",
                            photoResId = R.drawable.arsh,
                            instagramUrl = "https://instagram.com/anant",
                            linkedinUrl = "https://linkedin.com/in/anant"
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                // Second row with 2 cards
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    TeamCard(
                        member = TeamMember(
                            name = "Jeevant Verma",
                            role = "Technical Head",
                            photoResId = R.drawable.arsh,
                            instagramUrl = "https://instagram.com/priya",
                            linkedinUrl = "https://linkedin.com/in/priya"
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    
                    TeamCard(
                        member = TeamMember(
                            name = "Aditi",
                            role = "Random Role in team",
                            photoResId = R.drawable.aditi,
                            instagramUrl = "https://instagram.com/rohit",
                            linkedinUrl = "https://linkedin.com/in/rohit"
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                // Third row with 2 cards
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    TeamCard(
                        member = TeamMember(
                            name = "Mahak Vijay",
                            role = "Random Role",
                            photoResId = R.drawable.mahakvijay,
                            instagramUrl = "https://instagram.com/sneha",
                            linkedinUrl = "https://linkedin.com/in/sneha"
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    
                    TeamCard(
                        member = TeamMember(
                            name = "Varunavi Bhatnagar",
                            role = "Random Role",
                            photoResId = R.drawable.varunavi,
                            instagramUrl = "https://instagram.com/vikash",
                            linkedinUrl = "https://linkedin.com/in/vikash"
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

        }
    }
}