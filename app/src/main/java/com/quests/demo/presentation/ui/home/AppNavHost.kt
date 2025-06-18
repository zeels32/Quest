package com.quests.demo.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.quests.demo.events.presentation.ui.events.event.compose.EventScreen
import com.quests.demo.events.presentation.ui.events.event.model.EventUiModel
import com.quests.demo.events.presentation.ui.events.event_detail.compose.EventDetailScreen
import com.quests.demo.presentation.ui.home.compose.HomeScreen
import com.quests.demo.presentation.util.fromJson
import com.quests.demo.presentation.util.toJson
import com.quests.demo.products.presentation.ui.search.compose.ProductScreen

enum class Screens(val type: String) {
    HOME("home"),
    EVENT("event"),
    EVENT_DETAIL("eventDetail/{event}"),
    PRODUCT("product"),
}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screens.HOME.type)
    object Event : NavigationItem(Screens.EVENT.type)
    object EventDetail : NavigationItem(Screens.EVENT_DETAIL.type)
    object Product : NavigationItem(Screens.PRODUCT.type)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route
) {


    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.Home.route) {
            HomeScreen(
                navigateToEvent = {
                    navController.navigate(NavigationItem.Event.route)
                },
                navigateToProduct = {
                    navController.navigate(NavigationItem.Product.route)
                }
            )
        }

        composable(NavigationItem.Product.route) {
            ProductScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavigationItem.Event.route) {
            // Event Screen Composable
            EventScreen(
                onItemClick = {
                    navController.navigate(NavigationItem.EventDetail.route.replace("{event}", it.toJson()))
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = NavigationItem.EventDetail.route,
        ) {

            it.arguments?.getString("event").takeIf { it?.isEmpty()?.not() == true }.let { eventJson ->
                eventJson?.let { json ->
                    val event = json.fromJson<EventUiModel>(json)

                    EventDetailScreen(
                        eventUiModel = event,
                        onBack = {
                            navController.popBackStack()
                        }
                    )

                }
            }

        }

    }

}