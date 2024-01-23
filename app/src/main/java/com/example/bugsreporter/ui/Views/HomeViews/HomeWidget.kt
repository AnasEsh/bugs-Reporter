import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bugsreporter.ViewModels.AppViewModel
import com.example.bugsreporter.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainWidget(vm:AppViewModel){
    vm?.bugs?.observeAsState()?.value

//    vm.choosenBug.observeAsState().value
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
            FloatingActionButton(onClick = {vm?.toggleDialog()}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        if(vm.bugs.value?.isEmpty()?:false)

            Row (modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                 horizontalArrangement= Arrangement.Center){
                Text(text = "No bugs Yet ", style = MaterialTheme.typography.headlineLarge)
                Icon(Icons.Default.CheckCircle,"Great",tint= Color.Green, modifier = Modifier.size(40.dp))
            }

        else
            LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(vm!!.bugs.value?.size?:0,) {
                BugWidget(
                    vm,
                    vm.bugs.value!![it]
                )
                Divider()
            }

        }

        vm?.chosenBug?.observeAsState()?.value
        if(vm?.chosenBug?.value!=null)
            BugFormDialog(vm = vm!!)
    }

}