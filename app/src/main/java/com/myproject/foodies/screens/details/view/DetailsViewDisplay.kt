package com.myproject.foodies.screens.details.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.myproject.domain.models.dish.DishDetails
import com.myproject.foodies.R
import com.myproject.foodies.navigation.destination.FoodiesGraphDestinations
import com.myproject.foodies.screens.details.DetailsViewModel
import com.myproject.foodies.screens.details.models.DetailsViewState
import com.myproject.foodies.screens.foodies.widgets.cart.CartButton
import com.myproject.foodies.ui.theme.FoodiesTheme
import com.myproject.foodies.ui.theme.modifier.shadow
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailsViewDisplay(
    state: DetailsViewState.DisplayDetails,
    navController: NavController,
    onAddDishToCart: () -> Unit,
) {
    val dishDescription = state.dish.value

    Column {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            item {
                Box {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(id = R.drawable.dish),
                        contentDescription = null
                    )
                    IconButton(
                        modifier = Modifier
                            .padding(top = 16.dp, start = 16.dp)
                            .size(44.dp)
                            .shadow(
                                color = Color(0x201F1F1F),
                                offsetY = 10.dp
                            )
                            .background(
                                shape = RoundedCornerShape(30.dp),
                                color = FoodiesTheme.colors.white
                            ),
                        onClick = {
                            navController.navigate(
                                route = FoodiesGraphDestinations.Foodies.destination,
                                navOptions = navOptions {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_cart_back
                            ),
                            contentDescription = null
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                ) {
                    Text(
                        style = FoodiesTheme.typography.bigTitle,
                        text = dishDescription.dish.name
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp),
                        style = FoodiesTheme.typography.description,
                        text = dishDescription.description
                    )
                }
            }
            item {
                ParametersItem(
                    name = "Вес",
                    value = dishDescription.dish.measure.toString(),
                    measureUnit = dishDescription.dish.measure_unit ?: "",
                    topWidthBorder = 105.dp
                )
            }
            item {
                ParametersItem(
                    name = "Энерг. ценность",
                    value = dishDescription.energy_per_100_grams.toString(),
                    measureUnit = "Ккал",
                    topWidthBorder = 242.dp,
                    bottomWidthBorder = 242.dp
                )
            }
            item {
                ParametersItem(
                    name = "Белки",
                    value = dishDescription.proteins_per_100_grams.toString(),
                    measureUnit = dishDescription.dish.measure_unit ?: "",
                )
            }
            item {
                ParametersItem(
                    name = "Жиры",
                    value = dishDescription.fats_per_100_grams.toString(),
                    measureUnit = dishDescription.dish.measure_unit ?: "",
                    topWidthBorder = 118.dp,
                )
            }
            item {
                ParametersItem(
                    name = "Углеводы",
                    value = dishDescription.carbohydrates_per_100_grams.toString(),
                    measureUnit = dishDescription.dish.measure_unit ?: "",
                    topWidthBorder = 155.dp,
                    bottomWidthBorder = 155.dp
                )
            }
        }
        CartButton(textButton = "В корзину за ${dishDescription.dish.price_current}", onCart = {
            navController.navigate(
                route = FoodiesGraphDestinations.Foodies.destination,
                navOptions = navOptions {
                    onAddDishToCart()
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                })
        })
    }
}

@Composable
private fun ParametersItem(
    name: String,
    value: String,
    measureUnit: String,
    topWidthBorder: Dp? = null,
    bottomWidthBorder: Dp? = null,
) {
    Column {
        Spacer(
            modifier = Modifier
                .background(FoodiesTheme.colors.black12)
                .height(1.dp)
                .width(topWidthBorder ?: 0.dp)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 13.dp)
        ) {
            Text(
                style = FoodiesTheme.typography.nameParam,
                text = name
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                style = FoodiesTheme.typography.valueParam,
                text = "$value $measureUnit"
            )
        }
        Spacer(
            modifier = Modifier
                .background(FoodiesTheme.colors.black12)
                .height(1.dp)
                .width(bottomWidthBorder ?: 0.dp)
        )
    }
}
