package com.fady.wordwiz.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fady.wordwiz.R

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier, text: String = stringResource(id = R.string.error_message)
) {
    Box(
        modifier = modifier,
    ) {
        Column {
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                contentDescription = null,
                painter = painterResource(id = R.drawable.ic_search),
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                color = Color(0xFF625b71),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.weight(1f))
        }


    }
}


@Preview
@Composable
fun EmptyScreenPreview() {
    EmptyScreen()
}