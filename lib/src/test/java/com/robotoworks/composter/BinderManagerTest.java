package com.robotoworks.composter;

import org.junit.Before;
import org.junit.Test;
import com.robotoworks.composter.binders.ItemBinder;
import com.robotoworks.composter.binders.StaticBinder;
import com.robotoworks.composter.mediators.BinderManager;
import com.robotoworks.composter.mediators.ViewHolderFactory;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class BinderManagerTest {

    private BinderManager manager;

    @Before
    public void setUp() {
        manager = new BinderManager();
    }

    @Test
    public void getHeaderBinding_retrieves_binding_after_registering() {
        // Arrange
        StaticBinder binder = mock(StaticBinder.class);

        // Act
        manager.registerHeaderBinder(4, binder);

        // Assert
        assertThat(manager.getHeaderBinding(0).getBinder())
                .isEqualTo(binder);
    }

    @Test
    public void getHeaderBinding_returns_null_for_non_existent_bindings() {
        // Arrange
        StaticBinder binder = mock(StaticBinder.class);

        // Act
        manager.registerHeaderBinder(4, binder);

        // Assert
        assertThat(manager.getHeaderBinding(2))
                .isNull();
    }

    @Test
    public void getHeaderBinding_retrieves_bindings_in_the_order_they_were_added() {
        // Arrange
        StaticBinder binder1 = mock(StaticBinder.class);
        StaticBinder binder2 = mock(StaticBinder.class);
        StaticBinder binder3 = mock(StaticBinder.class);

        // Act
        manager.registerHeaderBinder(4, binder1);
        manager.registerHeaderBinder(5, binder2);
        manager.registerHeaderBinder(6, binder3);
        manager.registerHeaderBinder(6, binder1);

        // Assert
        assertThat(manager.getHeaderBinding(0).getBinder())
                .isEqualTo(binder1);
        assertThat(manager.getHeaderBinding(1).getBinder())
                .isEqualTo(binder2);
        assertThat(manager.getHeaderBinding(2).getBinder())
                .isEqualTo(binder3);
        assertThat(manager.getHeaderBinding(3).getBinder())
                .isEqualTo(binder1);
    }

    @Test
    public void getHeaderBinding_returns_null_for_invalid_positions() {
        // Arrange
        StaticBinder binder1 = mock(StaticBinder.class);
        StaticBinder binder2 = mock(StaticBinder.class);
        StaticBinder binder3 = mock(StaticBinder.class);

        // Act
        manager.registerHeaderBinder(4, binder1);
        manager.registerHeaderBinder(5, binder2);
        manager.registerHeaderBinder(6, binder3);
        manager.registerHeaderBinder(6, binder1);

        // Assert
        assertThat(manager.getHeaderBinding(-1))
                .isNull();
        assertThat(manager.getHeaderBinding(4))
                .isNull();
    }

    @Test
    public void getFooterBinding_retrieves_binding_after_registering() {
        // Arrange
        StaticBinder binder = mock(StaticBinder.class);

        // Act
        manager.registerFooterBinder(4, binder);

        // Assert
        assertThat(manager.getFooterBinding(0).getBinder())
                .isEqualTo(binder);
    }

    @Test
    public void getFooterBinding_returns_null_for_non_existent_bindings() {
        // Arrange
        StaticBinder binder = mock(StaticBinder.class);

        // Act
        manager.registerFooterBinder(4, binder);

        // Assert
        assertThat(manager.getFooterBinding(2))
                .isNull();
    }

    @Test
    public void getFooterBinding_retrieves_bindings_in_the_order_they_were_added() {
        // Arrange
        StaticBinder binder1 = mock(StaticBinder.class);
        StaticBinder binder2 = mock(StaticBinder.class);
        StaticBinder binder3 = mock(StaticBinder.class);

        // Act
        manager.registerFooterBinder(4, binder1);
        manager.registerFooterBinder(5, binder2);
        manager.registerFooterBinder(6, binder3);
        manager.registerFooterBinder(6, binder1);

        // Assert
        assertThat(manager.getFooterBinding(0).getBinder())
                .isEqualTo(binder1);
        assertThat(manager.getFooterBinding(1).getBinder())
                .isEqualTo(binder2);
        assertThat(manager.getFooterBinding(2).getBinder())
                .isEqualTo(binder3);
        assertThat(manager.getFooterBinding(3).getBinder())
                .isEqualTo(binder1);
    }

    @Test
    public void getFooterBinding_returns_null_for_invalid_positions() {
        // Arrange
        StaticBinder binder1 = mock(StaticBinder.class);
        StaticBinder binder2 = mock(StaticBinder.class);
        StaticBinder binder3 = mock(StaticBinder.class);

        // Act
        manager.registerFooterBinder(4, binder1);
        manager.registerFooterBinder(5, binder2);
        manager.registerFooterBinder(6, binder3);
        manager.registerFooterBinder(6, binder1);

        // Assert
        assertThat(manager.getFooterBinding(-1))
                .isNull();
        assertThat(manager.getFooterBinding(4))
                .isNull();
    }

    @Test
    public void clearHeaderBinders_clears_previously_put_in_header_binders() {
        // Arrange
        StaticBinder binder1 = mock(StaticBinder.class);
        StaticBinder binder2 = mock(StaticBinder.class);
        StaticBinder binder3 = mock(StaticBinder.class);
        manager.registerHeaderBinder(4, binder1);
        manager.registerHeaderBinder(5, binder2);
        manager.registerHeaderBinder(6, binder3);

        // Act
        manager.clearHeaderBinders();

        // Assert
        assertThat(manager.getHeaderBinding(0))
                .isNull();
        assertThat(manager.getHeaderBinding(1))
                .isNull();
        assertThat(manager.getHeaderBinding(2))
                .isNull();
    }

    @Test
    public void clearAllBinders_clears_previously_put_in_header_binders() {
        // Arrange
        StaticBinder binder1 = mock(StaticBinder.class);
        StaticBinder binder2 = mock(StaticBinder.class);
        StaticBinder binder3 = mock(StaticBinder.class);
        manager.registerHeaderBinder(4, binder1);
        manager.registerHeaderBinder(5, binder2);
        manager.registerHeaderBinder(6, binder3);

        // Act
        manager.clearAllBinders();

        // Assert
        assertThat(manager.getHeaderBinding(0))
                .isNull();
        assertThat(manager.getHeaderBinding(1))
                .isNull();
        assertThat(manager.getHeaderBinding(2))
                .isNull();
    }

    @Test
    public void clearHeaderBinders_clears_previously_put_in_footer_binders() {
        // Arrange
        StaticBinder binder1 = mock(StaticBinder.class);
        StaticBinder binder2 = mock(StaticBinder.class);
        StaticBinder binder3 = mock(StaticBinder.class);
        manager.registerFooterBinder(4, binder1);
        manager.registerFooterBinder(5, binder2);
        manager.registerFooterBinder(6, binder3);

        // Act
        manager.clearFooterBinders();

        // Assert
        assertThat(manager.getFooterBinding(0))
                .isNull();
        assertThat(manager.getFooterBinding(1))
                .isNull();
        assertThat(manager.getFooterBinding(2))
                .isNull();
    }

    @Test
    public void clearAllBinders_clears_previously_put_in_footer_binders() {
        // Arrange
        StaticBinder binder1 = mock(StaticBinder.class);
        StaticBinder binder2 = mock(StaticBinder.class);
        StaticBinder binder3 = mock(StaticBinder.class);
        manager.registerFooterBinder(4, binder1);
        manager.registerFooterBinder(5, binder2);
        manager.registerFooterBinder(6, binder3);

        // Act
        manager.clearAllBinders();

        // Assert
        assertThat(manager.getFooterBinding(0))
                .isNull();
        assertThat(manager.getFooterBinding(1))
                .isNull();
        assertThat(manager.getFooterBinding(2))
                .isNull();
    }

    @Test
    public void clearItemBinders_clears_item_binders() {
        // Arrange
        ItemBinder binder1 = mock(ItemBinder.class);
        manager.registerItemBinder(31, binder1);

        // Act
        manager.clearItemBinders();

        // Assert
        assertThat(manager.getItemBinder(31))
                .isNull();
    }

    @Test
    public void clearAllBinders_clears_item_binders() {
        // Arrange
        ItemBinder binder1 = mock(ItemBinder.class);
        manager.registerItemBinder(31, binder1);

        // Act
        manager.clearAllBinders();

        // Assert
        assertThat(manager.getItemBinder(31))
                .isNull();
    }

    @Test
    public void getItemBinder_retrieves_item_binder() {
        // Arrange
        ItemBinder binder1 = mock(ItemBinder.class);
        manager.registerItemBinder(31, binder1);

        // Act
        final ItemBinder itemBinder = manager.getItemBinder(31);

        // Assert
        assertThat(itemBinder)
                .isEqualTo(binder1);
    }

    @Test
    public void getViewHolderFactory_retrieves_factory() {
        // Arrange
        ViewHolderFactory expected = mock(ViewHolderFactory.class);
        manager.registerViewHolderFactory(31, expected);

        // Act
        final ViewHolderFactory holderFactory = manager.getViewHolderFactory(31);

        // Assert
        assertThat(holderFactory)
                .isEqualTo(expected);
    }
}
