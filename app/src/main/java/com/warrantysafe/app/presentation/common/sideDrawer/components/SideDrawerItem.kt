package com.warrantysafe.app.presentation.common.sideDrawer.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.warrantysafe.app.R

@Composable
fun SideDrawerItem(
    item: String,
    @DrawableRes itemImg: Int,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .padding(end = 32.dp)
            .clickable { onClick(item) },
        horizontalArrangement = Arrangement.Absolute.Right,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(itemImg), // Replace with relevant icons
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 8.dp),
            text = item,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}