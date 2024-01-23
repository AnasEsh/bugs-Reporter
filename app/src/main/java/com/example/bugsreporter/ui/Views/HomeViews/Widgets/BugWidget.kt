import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.bugsreporter.Models.Bug
import com.example.bugsreporter.ViewModels.AppViewModel

@Composable
fun BugWidget(
    vm:AppViewModel,
    bug: Bug
){
    Row (

        modifier= Modifier
            .padding(PaddingValues(horizontal = 5.dp)),
    ){
        Column(
            modifier=Modifier
                    .weight(5f)
        ){
            Text(
                text = bug.title,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = bug.description,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Column (horizontalAlignment = Alignment.CenterHorizontally ){
            Text(
                text = bug.priority.name,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.errorContainer)
                            .padding(PaddingValues(vertical = 3.dp, horizontal = 5.dp))
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                IconButton(
                    onClick = { vm.deleteBug(bug) },

                ) {
                    Icon(Icons.Default.Delete,"Delete",tint=MaterialTheme.colorScheme.error)
                }
                IconButton(
                    onClick = { vm.modifyBug(bug) })
                {
                    Icon(Icons.Default.Edit, contentDescription =  "Edit",)
                }
            }
        }
    }
}