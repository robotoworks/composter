package com.robotoworks.composter.dataset;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ListRecyclerDataSetTest {

    @Test
    public void dataset_returns_0_for_item_count_if_initialised_with_null() {
        // Arrange
        ListRecyclerDataSet dataSet = new ListRecyclerDataSet(null) {
            @Override
            public int getItemViewType(int position) {

                return 0;
            }
        };

        // Act
        final int itemCount = dataSet.getItemCount();

        // Assert
        assertThat(itemCount).isEqualTo(0);
    }

    @Test
    public void dataset_returns_0_count_if_initialised_with_empty_array() {
        // Arrange
        ListRecyclerDataSet dataSet = new ListRecyclerDataSet(new ArrayList()) {
            @Override
            public int getItemViewType(int position) {

                return 0;
            }
        };

        // Act
        final int itemCount = dataSet.getItemCount();

        // Assert
        assertThat(itemCount).isEqualTo(0);
    }

    @Test
    public void dataset_returns_nonzero_if_initialised_with_nonempty_array() {
        // Arrange
        ListRecyclerDataSet dataSet = new ListRecyclerDataSet(Arrays.asList(1, 2, 3)) {
            @Override
            public int getItemViewType(int position) {

                return 0;
            }
        };

        // Act
        final int itemCount = dataSet.getItemCount();

        // Assert
        assertThat(itemCount).isEqualTo(3);
    }
}