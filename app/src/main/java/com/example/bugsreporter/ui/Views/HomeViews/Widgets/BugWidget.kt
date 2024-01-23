import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BugWidget(){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(modifier=Modifier.weight(5f)){
            Text(
                text = "Bug title",
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "Bug description is that wawy eafafwa fawaf fawa...",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text(
            text = "Medium",
            color=MaterialTheme.colorScheme.onErrorContainer,
            modifier = Modifier.background(MaterialTheme.colorScheme.errorContainer)
        )
    }
}