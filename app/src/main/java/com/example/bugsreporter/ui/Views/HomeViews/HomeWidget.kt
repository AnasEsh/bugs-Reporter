import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bugsreporter.ViewModels.AppViewModel
import com.example.bugsreporter.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainWidget(vm:AppViewModel?=null){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary),
                modifier =
                    Modifier
                    .background(color = Purple80),
                title = {
                    Text(
                        "Bugs Reporter",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,color=MaterialTheme.colorScheme.onPrimary,
                        modifier =
                            Modifier.fillMaxWidth()
                    )
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            for(i in 0 until 6) {
                Box(modifier=Modifier.padding(PaddingValues(horizontal = 8.dp))){ BugWidget() }
                Divider()
            }
        }
    }

}