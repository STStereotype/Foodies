package com.myproject.foodies.screens.foodies.widgets.header

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.myproject.domain.models.filter.Filter
import com.myproject.foodies.ui.theme.FoodiesTheme

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    filters: List<Filter>,
    activeFilters: List<Filter>,
    onClose: () -> Unit,
    onSubmit: (activeFilters: List<Filter>) -> Unit
) {

    val activeFiltersCopy = remember {
        activeFilters.toMutableList()
    }

    ModalBottomSheet(
        shape = FoodiesTheme.shapes.bottomSheet,
        containerColor = FoodiesTheme.colors.white,
        sheetState = rememberModalBottomSheetState(),
        onDismissRequest = { onClose.invoke() },
        dragHandle = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 24.dp, end = 24.dp),
                style = FoodiesTheme.typography.titleBottomSheet,
                text = "Подобрать блюда"
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            filters.forEach { filter ->
                BottomSheetItem(
                    filter = filter,
                    activeFilter = activeFilters.contains(filter),
                    onCheckBox = { isChecked ->
                        if (isChecked) activeFiltersCopy.add(filter)
                        else activeFiltersCopy.remove(filter)
                    }
                )
                if (filters.last() != filter)
                    Divider(color = FoodiesTheme.colors.black12, thickness = 1.dp)
            }
            Button(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 32.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(FoodiesTheme.colors.main),
                shape = FoodiesTheme.shapes.button,
                elevation = null,
                onClick = {
                    onSubmit.invoke(activeFiltersCopy)
                }
            ) {
                Text(
                    text = "Готово",
                    style = FoodiesTheme.typography.primaryWhite
                )
            }
        }
    }
}

@Composable
private fun BottomSheetItem(
    filter: Filter,
    activeFilter: Boolean,
    onCheckBox: (isChecked: Boolean) -> Unit
) {
    val isChecked = remember { mutableStateOf(activeFilter) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .padding(end = 16.dp),
            style = FoodiesTheme.typography.nameParamBottomSheet,
            text = filter.name
        )
        Checkbox(
            modifier = Modifier
                .size(24.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = FoodiesTheme.colors.main
            ),
            checked = isChecked.value,
            onCheckedChange = {
                isChecked.value = !isChecked.value
                onCheckBox.invoke(isChecked.value)
            }
        )
    }
}
