import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.aradsheybak.goodfood.components.connectivity.NetworkDialog
import com.aradsheybak.goodfood.components.connectivity.NetworkViewModel
import kotlinx.coroutines.delay

@Composable
fun MainNetworkMonitor(
    networkViewModel: NetworkViewModel = remember { NetworkViewModel() }
) {
    val context = LocalContext.current
    val isConnected by networkViewModel.isConnected.collectAsState()
    var initialized by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        networkViewModel.startMonitoring(context)
        delay(300)
        initialized = true
    }

    DisposableEffect(Unit) {
        onDispose {
            networkViewModel.stopMonitoring()
        }
    }

    if (initialized && !isConnected) {
        NetworkDialog(
            isConnected = isConnected,
            onDismiss = {

            }
        )
    }
}