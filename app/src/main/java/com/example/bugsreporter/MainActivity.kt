package com.example.bugsreporter

import MainWidget
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.bugsreporter.ViewModels.AppViewModel
import com.example.bugsreporter.ViewModels.Factory
import com.example.bugsreporter.ui.theme.BugsReporterTheme

class MainActivity : ComponentActivity() {

    private val vm by viewModels<AppViewModel> {
        Factory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            BugsReporterTheme {
                // A surface container using the 'background' color from the theme
                MainWidget(vm)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BugsReporterTheme {
        MainWidget()
    }
}