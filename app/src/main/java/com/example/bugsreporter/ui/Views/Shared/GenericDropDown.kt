import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> GenericDropDown(
    choices: Iterable<T>,
    initialState:T,
    onChanged: (T) -> Unit,
    label:String
){
    var state by remember {mutableStateOf(initialState)};

    var expanded by remember{ mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded=it}) {
        TextField(
            value = state.toString(),
            onValueChange ={},
            readOnly = true,
            label = { Text(text = label)},
            modifier = Modifier.menuAnchor() )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded=false }) {
            for(c in choices)
                DropdownMenuItem(
                    text = { Text(c.toString()) },
                    onClick = {
                        state=c;
                        onChanged(c)
                        expanded=false;
                    })

        }
    }
}