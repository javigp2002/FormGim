package com.example.formgim

import MyAlertDialog
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.formgim.navigation.Navigation
import com.example.formgim.ui.theme.FormGimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val latestNewsViewModel: MainActivityViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormGimTheme(dynamicColor = false) {
                var showDialog by remember { mutableStateOf(false) }

                latestNewsViewModel.isUserAuth.observe(this) { isUserAuth ->
                    if (!isUserAuth) {
                        showDialog = true
                    }
                }

                if (showDialog) {
                    MyAlertDialog(
                        title = "Error",
                        message = "Your session has expired. Please log in again.",
                        acceptOption = {
                            val context = this@MainActivity
                            val packageManager: PackageManager = context.packageManager
                            val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
                            val componentName: ComponentName = intent.component!!
                            val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
                            context.startActivity(restartIntent)
                            Runtime.getRuntime().exit(0)
                        }
                    )
                } else {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FormGimTheme {
        Greeting("Android")
    }
}