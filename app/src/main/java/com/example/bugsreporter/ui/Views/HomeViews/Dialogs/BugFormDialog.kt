import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bugsreporter.ViewModels.AppViewModel

@Composable
fun BugFormDialog(
vm:AppViewModel
){
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        dismissButton = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Save")
                        }
                        },
        title={
            Text(text = "lalo malo carpoty")
        },
        text = {
            Text(text = "kfkwan fuwbaiufwba iunfoiwnifowan unwfoiwanfoinwaf iofwjoifjwoifwjo oinfoiwnoifwna ofwpa opwfmpowa powakfpo")
        },

        )
}