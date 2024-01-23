import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bugsreporter.Models.Priority
import com.example.bugsreporter.ViewModels.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BugFormDialog(
vm:AppViewModel
){
    val bug=vm.chosenBug.value!!;

    //If we are updating a bug, it will have real (id>0)

    val updating=(bug.id?:0)>0;

    val dismiss={
        vm.toggleDialog()
    }
    val titleState= remember {
        mutableStateOf(bug.title ?:"")
    }
    val descriptionState= remember {
        mutableStateOf(bug?.description?:"")
    }

    var priorityState= remember {
        mutableStateOf(bug?.priority?:Priority.LOW)
    }


    AlertDialog(
        onDismissRequest = dismiss,
        dismissButton = {
            Button(onClick =dismiss,colors=ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
                        Button(onClick = {
                            bug.apply {
                                title = titleState.value;
                                description = descriptionState.value;
                                priority = priorityState.value;
                            }
                            vm.commitChange()
                        }) {
                            Text(
                                text = if(updating) "Save" else "Add"
                            )
                        }
                        },
        title={
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(text = if (updating) bug.title else "Report a Bug")
                Spacer(modifier=Modifier.size(8.dp))
                Icon(Icons.Filled.Warning,"", modifier = Modifier.size(33.dp))
            }
        },
        text = {
//            Text(text = "kfkwan fuwbaiufwba iunfoiwnifowan unwfoiwanfoinwaf iofwjoifjwoifwjo oinfoiwnoifwna ofwpa opwfmpowa powakfpo")
            Column{
                OutlinedTextField(
                    label = {
                        Text(text = "Title")
                            },
                    value = titleState.value,
                    onValueChange ={
                    titleState.value=it
                    } )
                OutlinedTextField(
                        maxLines = 5,
                label = {
                    Text(text = "Description")
                },
                value = descriptionState.value,
                onValueChange ={
                    descriptionState.value=it;
                } )
                GenericDropDown(
                    choices = Priority.values().toList(),
                    initialState = bug.priority,
                    label = "Priority",
                    onChanged = {
                        priorityState.value=it;
                    }
                )
            }
               },

        )
}