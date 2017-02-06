package com.robotoworks.composter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import org.junit.Before;
import org.junit.Test;
import com.robotoworks.composter.binders.ItemBinder;
import com.robotoworks.composter.binders.StaticBinder;
import com.robotoworks.composter.binders.StaticBinding;
import com.robotoworks.composter.dataset.ListRecyclerDataSet;
import com.robotoworks.composter.dataset.RecyclerDataset;
import com.robotoworks.composter.mediators.BinderManager;
import com.robotoworks.composter.mediators.BinderRegistrar;
import com.robotoworks.composter.mediators.ViewHolderFactory;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class RecyclerViewAdapterTest {

    private RecyclerViewAdapter<String> recyclerViewAdapter;
    private BinderManager mockBinderManager;
    private BinderRegistrar mockBinderRegistrar;
    private StaticBinder mockHeaderBinder;
    private ItemBinder mockItemBinder;
    private StaticBinder mockFooterBinder;

    @Before
    public void setUp() throws Exception {

        mockBinderManager = mock(BinderManager.class);
        mockBinderRegistrar = mock(BinderRegistrar.class);
        recyclerViewAdapter = new RecyclerViewAdapter<>(mockBinderManager, mockBinderRegistrar);
    }

    @Test
    public void constructor_asks_registrar_to_register_holder_factories() {
        // arrange

        // act

        // assert
        verify(mockBinderRegistrar).registerViewHolderFactories(eq(mockBinderManager));
    }

    @Test
    public void setSource_clears_all_binders() {
        // arrange

        // act
        recyclerViewAdapter.setSource(mock(RecyclerDataset.class));

        // assert
        verify(mockBinderManager).clearAllBinders();
    }

    @Test
    public void setSource_asks_registrar_to_register_binders() {
        // arrange

        // act
        recyclerViewAdapter.setSource(mock(RecyclerDataset.class));

        // assert
        verify(mockBinderRegistrar).registerBinders(eq(mockBinderManager));
    }

    @Test
    public void onCreateViewHolder_throws_exception_if_no_factory_for_viewType() {
        // arrange

        // act
        try {
            recyclerViewAdapter.onCreateViewHolder(null, 4);
            fail("Exception expected");
        } catch (IllegalStateException e) {
            // assert
            assertThat(e).hasMessage("Missing view holder factory for viewType 4!");
        }
    }

    @Test
    public void onCreateViewHolder_builds_viewHolder_when_factory_found() {
        // arrange
        ViewHolderFactory mockFactory = mock(ViewHolderFactory.class);
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        when(mockFactory.createViewHolder(any(ViewGroup.class))).thenReturn(mockViewHolder);
        when(mockBinderManager.getViewHolderFactory(eq(5))).thenReturn(mockFactory);

        // act
        final RecyclerView.ViewHolder actualViewHolder = recyclerViewAdapter.onCreateViewHolder(mock(LinearLayout.class), 5);

        // assert
        verify(mockFactory).createViewHolder(any(ViewGroup.class));
        assertThat(actualViewHolder)
                .isEqualTo(mockViewHolder);
    }

    private void arrangeDataset() {

        when(mockBinderManager.getHeaderCount()).thenReturn(3);
        RecyclerDataset<String> dataset = new ListRecyclerDataSet<String>(Arrays.asList("a", "b", "c", "d", "e")) {
            @Override
            public int getItemViewType(int position) {

                return 4;
            }
        };

        recyclerViewAdapter.setSource(dataset);
        when(mockBinderManager.getFooterCount()).thenReturn(2);

        mockHeaderBinder = mock(StaticBinder.class);
        mockItemBinder = mock(ItemBinder.class);
        mockFooterBinder = mock(StaticBinder.class);
        StaticBinding mockHeaderBinding = mock(StaticBinding.class);
        StaticBinding mockFooterBinding = mock(StaticBinding.class);
        when(mockHeaderBinding.getBinder()).thenReturn(mockHeaderBinder);
        when(mockHeaderBinding.getViewType()).thenReturn(0, 1, 2);
        when(mockFooterBinding.getBinder()).thenReturn(mockFooterBinder);
        when(mockFooterBinding.getViewType()).thenReturn(8, 9);
        when(mockBinderManager.getHeaderBinding(0)).thenReturn(mockHeaderBinding);
        when(mockBinderManager.getHeaderBinding(1)).thenReturn(mockHeaderBinding);
        when(mockBinderManager.getHeaderBinding(2)).thenReturn(mockHeaderBinding);
        when(mockBinderManager.getItemBinder(0)).thenReturn(mockItemBinder);
        when(mockBinderManager.getItemBinder(1)).thenReturn(mockItemBinder);
        when(mockBinderManager.getItemBinder(2)).thenReturn(mockItemBinder);
        when(mockBinderManager.getItemBinder(3)).thenReturn(mockItemBinder);
        when(mockBinderManager.getItemBinder(4)).thenReturn(mockItemBinder);
        when(mockBinderManager.getFooterBinding(0)).thenReturn(mockFooterBinding);
        when(mockBinderManager.getFooterBinding(1)).thenReturn(mockFooterBinding);
    }

    @Test
    public void getItemCount_returns_correct_item_count() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        arrangeDataset();

        // act
        final int itemCount = recyclerViewAdapter.getItemCount();

        // assert
        assertThat(itemCount)
                .isEqualTo(10);
    }

    @Test
    public void onBindViewHolder_throws_exception_if_no_header_binder_found() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();
        when(mockBinderManager.getHeaderBinding(anyInt())).thenReturn(null);

        // act
        try {
            recyclerViewAdapter.onBindViewHolder(mockViewHolder, 1);
            fail("Exception expected");
        } catch (IllegalStateException e) {
            // assert
            assertThat(e).hasMessage("Missing binder for header at position 1!");
        }
    }

    @Test
    public void onBindViewHolder_binds_header_if_binder_found() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 1);

        // assert
        verify(mockHeaderBinder).bind(eq(mockViewHolder));
    }

    @Test
    public void onBindViewHolder_can_bind_all_headers() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 0);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 1);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 2);

        // assert
        verify(mockHeaderBinder, times(3)).bind(eq(mockViewHolder));
    }

    @Test
    public void onBindViewHolder_does_not_bind_headers_for_non_header_positions() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 3);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 4);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 5);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 6);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 7);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 8);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 9);

        // assert
        verify(mockHeaderBinder, never()).bind(eq(mockViewHolder));
    }

    @Test
    public void onBindViewHolder_throws_exception_if_no_item_binder_found() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();
        when(mockBinderManager.getItemBinder(anyInt())).thenReturn(null);

        // act
        try {
            recyclerViewAdapter.onBindViewHolder(mockViewHolder, 5);
            fail("Exception expected");
        } catch (IllegalStateException e) {
            // assert
            assertThat(e).hasMessage("Missing binder for item of viewType 4 at position 5!");
        }
    }

    @Test
    public void onBindViewHolder_binds_item_if_binder_found() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 5);

        // assert
        verify(mockItemBinder).bindItem(eq("c"), eq(mockViewHolder));
    }

    @Test
    public void onBindViewHolder_does_not_bind_item_if_item_null() {
        // Scenario: 0 headers, null item set, 0 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        ListRecyclerDataSet dataSet = new ListRecyclerDataSet(null) {
            @Override
            public int getItemViewType(int position) {

                return 0;
            }
        };
        recyclerViewAdapter.setSource(dataSet);
        mockItemBinder = mock(ItemBinder.class);
        when(mockBinderManager.getItemBinder(0)).thenReturn(mockItemBinder);

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 0);

        // assert
        verifyZeroInteractions(mockItemBinder);
    }

    @Test
    public void onBindViewHolder_can_bind_all_items() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 3);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 4);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 5);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 6);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 7);

        // assert
        verify(mockItemBinder).bindItem(eq("a"), eq(mockViewHolder));
        verify(mockItemBinder).bindItem(eq("b"), eq(mockViewHolder));
        verify(mockItemBinder).bindItem(eq("c"), eq(mockViewHolder));
        verify(mockItemBinder).bindItem(eq("d"), eq(mockViewHolder));
        verify(mockItemBinder).bindItem(eq("e"), eq(mockViewHolder));
    }

    @Test
    public void onBindViewHolder_does_not_bind_items_for_non_item_positions() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 0);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 1);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 2);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 8);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 9);

        // assert
        verify(mockItemBinder, never()).bindItem(anyString(), eq(mockViewHolder));
    }

    @Test
    public void onBindViewHolder_throws_exception_if_no_footer_binder_found() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();
        when(mockBinderManager.getFooterBinding(anyInt())).thenReturn(null);

        // act
        try {
            recyclerViewAdapter.onBindViewHolder(mockViewHolder, 8);
            fail("Exception expected");
        } catch (IllegalStateException e) {
            // assert
            assertThat(e).hasMessage("Missing binder for footer at position 8!");
        }
    }

    @Test
    public void onBindViewHolder_binds_footer_if_binder_found() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 8);

        // assert
        verify(mockFooterBinder).bind(eq(mockViewHolder));
    }

    @Test
    public void onBindViewHolder_can_bind_all_footers() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 8);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 9);

        // assert
        verify(mockFooterBinder, times(2)).bind(eq(mockViewHolder));
    }

    @Test
    public void onBindViewHolder_does_not_bind_footers_for_non_header_positions() {
        // Scenario: 3 headers, 5 items, 2 footers
        // arrange
        RecyclerView.ViewHolder mockViewHolder = mock(RecyclerView.ViewHolder.class);
        arrangeDataset();

        // act
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 0);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 1);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 2);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 3);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 4);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 5);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 6);
        recyclerViewAdapter.onBindViewHolder(mockViewHolder, 7);

        // assert
        verify(mockFooterBinder, never()).bind(eq(mockViewHolder));
    }

    @Test
    public void getItemViewType_returns_header_view_type_for_header_positions() {
        // arrange
        arrangeDataset();

        // act
        final int viewType1 = recyclerViewAdapter.getItemViewType(0);
        final int viewType2 = recyclerViewAdapter.getItemViewType(1);
        final int viewType3 = recyclerViewAdapter.getItemViewType(2);

        // assert
        assertThat(viewType1)
                .isEqualTo(0);
        assertThat(viewType2)
                .isEqualTo(1);
        assertThat(viewType3)
                .isEqualTo(2);
    }

    @Test
    public void getItemViewType_returns_item_view_type_for_item_positions() {
        // arrange
        arrangeDataset();

        // act
        final int viewType1 = recyclerViewAdapter.getItemViewType(3);
        final int viewType2 = recyclerViewAdapter.getItemViewType(4);
        final int viewType3 = recyclerViewAdapter.getItemViewType(5);
        final int viewType4 = recyclerViewAdapter.getItemViewType(6);
        final int viewType5 = recyclerViewAdapter.getItemViewType(7);

        // assert
        assertThat(Arrays.asList(viewType1, viewType2, viewType3, viewType4, viewType5))
                .containsOnly(4);
    }

    @Test
    public void getItemViewType_returns_footer_view_type_for_footer_positions() {
        // arrange
        arrangeDataset();

        // act
        final int viewType1 = recyclerViewAdapter.getItemViewType(8);
        final int viewType2 = recyclerViewAdapter.getItemViewType(9);

        // assert
        assertThat(viewType1)
                .isEqualTo(8);
        assertThat(viewType2)
                .isEqualTo(9);
    }
}
