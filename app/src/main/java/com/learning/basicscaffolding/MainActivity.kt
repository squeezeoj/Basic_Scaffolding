package com.learning.basicscaffolding
//======================================================================
// BASIC SCAFFOLDING
//	Created: 3 May 2022 by Jason
//	Purpose: Basic, Customizable Layout for Most Projects
//======================================================================

//----------------------------------------------------------------------
// Imports
//----------------------------------------------------------------------
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.learning.basicscaffolding.ui.theme.BasicScaffoldingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//----------------------------------------------------------------------
// Main Activity
//----------------------------------------------------------------------
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BasicScaffoldingTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {
					SetupScaffold()
				}
			}
		}
	}
}

//----------------------------------------------------------------------
// Setup Scaffold
//----------------------------------------------------------------------
@Composable
fun SetupScaffold() {
	val scope = rememberCoroutineScope()
	val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
	Scaffold(
		scaffoldState = scaffoldState,
		topBar = { ScaffoldTopBar(scope, scaffoldState) },
		floatingActionButtonPosition = FabPosition.End,
		floatingActionButton = { ScaffoldFloatingActionButton() },
		drawerContent = { ScaffoldDrawerContent() },
		drawerGesturesEnabled = true,
		content = { Content() },
		bottomBar = { ScaffoldBottomBar() }
	)
}

//----------------------------------------------------------------------
// Scaffold Content
//----------------------------------------------------------------------
@Composable
fun Content() {
	Text("Content")
}

//----------------------------------------------------------------------
// Scaffold Top Bar
//----------------------------------------------------------------------
@Composable
fun ScaffoldTopBar(
	scope: CoroutineScope,
	scaffoldState: ScaffoldState
) {
	TopAppBar(
		title = {
			Text(text = "Basic Scaffold")
		},
		navigationIcon = {
			IconButton(
				onClick = {
					scope.launch {
						scaffoldState.drawerState.open()
					}
				},
			) {
				Icon(
					Icons.Rounded.Menu,
					contentDescription = "Open Menu Drawer"
				)
			}
		})
}

//----------------------------------------------------------------------
// Scaffold Drawer Content
//----------------------------------------------------------------------
@Composable
fun ScaffoldDrawerContent() {
	Text(text = "Drawer Item 1")
	Text(text = "Drawer Item 2")
	Text(text = "Drawer Item 3")
}

//----------------------------------------------------------------------
// Scaffold Floating Action Button
//----------------------------------------------------------------------
@Composable
fun ScaffoldFloatingActionButton() {
	FloatingActionButton(onClick = {}){
		Icon(
			imageVector = Icons.Default.Add,
			contentDescription = "Flaoting Actino Button"
		)
	}
}

//----------------------------------------------------------------------
// Scaffold Bottom Bar
//----------------------------------------------------------------------
@Composable
fun ScaffoldBottomBar() {
	BottomAppBar(
		backgroundColor = MaterialTheme.colors.primary
	) {
		Text("Bottom App Bar")
	}
}