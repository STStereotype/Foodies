package com.myproject.foodies.screens.foodies.widgets.header

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.myproject.domain.models.categoty.Category
import com.myproject.foodies.R
import com.myproject.foodies.screens.foodies.models.state.displayFoodiesState.header.FoodiesHeaderState
import com.myproject.foodies.ui.theme.FoodiesTheme

@Composable
fun FoodiesWidgetsHeader(
    headerState: FoodiesHeaderState,
    onFilter: () -> Unit,
    onSearch: () -> Unit
) {
    val categoryState = headerState.foodiesCategoryState

    val idSelectedCategory = categoryState.idSelectedCategory.collectAsState().value
    val categoryFeed = categoryState.categoryFeed.collectAsState().value
    val scrollState = categoryState.scrollState.collectAsState().value
    val onCategory = categoryState.onCategory

    val countActiveFilter = headerState.foodiesFilterState.activeFilters.collectAsState().value.size

    Column {
        HeaderHead(
            countActiveFilter = countActiveFilter,
            onFilter = { onFilter.invoke() },
            onSearch = { onSearch.invoke() }
        )
        CategoryFeed(
            idSelectedCategory = idSelectedCategory,
            categoryFeed = categoryFeed,
            scrollState = scrollState,
            onCategory = onCategory
        )
    }
}

@Composable
private fun HeaderHead(
    countActiveFilter: Int,
    onFilter: () -> Unit,
    onSearch: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 6.dp, end = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                onClick = { onFilter.invoke() },
            ) {
                Box() {
                    Icon(
                        modifier = Modifier
                            .size(44.dp),
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = null
                    )
                }
            }
            if (countActiveFilter > 0)
                Box(
                    modifier = Modifier
                        .size(17.dp)
                        .background(
                            shape = RoundedCornerShape(9.dp),
                            color = FoodiesTheme.colors.main
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        style = FoodiesTheme.typography.countActiveFilters,
                        text = countActiveFilter.toString()
                    )
                }
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            tint = FoodiesTheme.colors.main,
            contentDescription = null
        )
        IconButton(
            onClick = { onSearch.invoke() },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun CategoryFeed(
    idSelectedCategory: Int,
    categoryFeed: List<Category>,
    scrollState: ScrollState,
    onCategory: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 16.dp, bottom = 12.dp)
            .horizontalScroll(scrollState)
    ) {
        categoryFeed.forEach { category ->
            CategoryButton(
                isSelected = category.id == idSelectedCategory,
                title = category.name,
                productId = category.id,
                onCategory = onCategory
            )
        }
    }
}

@Composable
private fun CategoryButton(
    isSelected: Boolean,
    title: String,
    productId: Int,
    onCategory: (Int) -> Unit
) {
    val colorBackground =
        if (isSelected) FoodiesTheme.colors.main
        else FoodiesTheme.colors.white.copy(alpha = 0f)
    val textStyle =
        if (isSelected) FoodiesTheme.typography.primaryWhite
        else FoodiesTheme.typography.primaryDark

    Button(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        elevation = null,
        modifier = Modifier
            .padding(start = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorBackground),
        shape = FoodiesTheme.shapes.button,
        onClick = {
            onCategory.invoke(productId)
        }
    ) {
        Text(
            text = title,
            style = textStyle,
        )
    }
}
