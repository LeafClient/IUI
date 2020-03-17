package com.leafclient.iui.theme;

import com.google.common.collect.ImmutableMap;
import com.leafclient.iui.Component;
import com.leafclient.iui.Renderer;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;

/**
 * {@link Theme} is an essential tool for IUI. It contains, manage and use
 * the {@link com.leafclient.iui.Renderer} for our {@link com.leafclient.iui.Component}s.
 *
 * Each {@link com.leafclient.iui.Component} instance has a single {@link com.leafclient.iui.Renderer} associated to it yet
 * you can only provide a single {@link com.leafclient.iui.Renderer} class per {@link com.leafclient.iui.Component} class.
 * The theme class is responsible of the instantiation of our {@link com.leafclient.iui.Renderer}.
 */
@SuppressWarnings("unchecked")
public class Theme {

    /**
     * Contains the {@link Renderer} instances. It uses a {@link WeakHashMap} so it
     * doesn't consume too much memory.
     * @see WeakHashMap
     */
    private final Map<Component, Renderer<?>> RENDERER_CACHE = new WeakHashMap<>();

    /**
     * Associates a {@link Renderer} class to a {@link Component} class.
     */
    private final Map<Class<? extends Component>, Class<? extends Renderer<?>>> renderers;

    /**
     * Theme's constructor called by the {@link Builder}.
     *
     * @param renderers Renderers map
     */
    Theme(Map<Class<? extends Component>, Class<? extends Renderer<?>>> renderers) {
        this.renderers = renderers;
    }

    /**
     * Returns a {@link Renderer} instance specific to the {@link Component}.
     *
     * @param component Component's instance
     * @param <T> Component's type
     * @return Renderer's instance
     */
    public <T extends Component> Renderer<T> rendererFor(T component) {
        Renderer<T> renderer = (Renderer<T>) RENDERER_CACHE.get(component);
        if(renderer != null)
            return renderer;

        final Class<Renderer<T>> rendererClass = (Class<Renderer<T>>) renderers.get(component.getClass());

        try {
            renderer = rendererClass.newInstance();
            RENDERER_CACHE.put(component, renderer);
            return renderer;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns a new {@link Builder} instance to create a {@link Theme}.
     *
     * @return Builder's instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        /**
         * A builder used in the builder :0
         */
        private final ImmutableMap.Builder<Class<? extends Component>, Class<? extends Renderer<?>>> renderers
                = ImmutableMap.builder();

        /**
         * Associates specified {@link Component}'s class to {@link Renderer}'s class.
         *
         * @param componentClass Component's class
         * @param rendererClass Renderer's class
         * @param <T> Type
         * @return Builder
         */
        public <T extends Component> Builder associate(Class<T> componentClass, Class<? extends Renderer<T>> rendererClass) {
            renderers.put(componentClass, rendererClass);
            return this;
        }

        /**
         * Builds this {@link Builder} to a {@link Theme} instance.
         *
         * @return Theme instance
         */
        public Theme build() {
            return new Theme(
                    renderers.build()
            );
        }

        /**
         * Builds this {@link Builder} to a {@link Theme} instance and also
         * instantiates {@link Renderer} for {@link Component} inside the specified array.
         *
         * @param components Components array
         * @return Theme instance
         */
        public Theme buildAndPrepare(Component... components) {
            final Theme theme = new Theme(
                    renderers.build()
            );
            for(Component component: components) {
                theme.rendererFor(component);
            }
            return theme;
        }

        /**
         * Builds this {@link Builder} to a {@link Theme} instance and also
         * instantiates {@link Renderer} for {@link Component} inside the {@link Iterator}.
         *
         * @param iterable Components iterable
         * @return Theme instance
         */
        public Theme buildAndPrepare(Iterable<Component> iterable) {
            final Theme theme = new Theme(
                    renderers.build()
            );
            for (Component component : iterable) {
                theme.rendererFor(component);
            }
            return theme;
        }

        /**
         * Builds this {@link Builder} to a {@link Theme} instance and also
         * instantiates {@link Renderer} for {@link Component} inside the specified array.
         *
         * @param components Components array
         * @return Theme instance
         */
        public Theme asyncBuildAndPrepare(Component... components) {
            final Theme theme = new Theme(
                    renderers.build()
            );
            new Thread(() -> {
                for(Component component: components) {
                    theme.rendererFor(component);
                }
            });
            return theme;
        }

        /**
         * Builds this {@link Builder} to a {@link Theme} instance and also
         * instantiates {@link Renderer} for {@link Component} inside the {@link Iterator}.
         *
         * @param iterable Components iterable
         * @return Theme instance
         */
        public Theme asyncBuildAndPrepare(Iterable<Component> iterable) {
            final Theme theme = new Theme(
                    renderers.build()
            );
            new Thread(() -> {
                for (Component component : iterable) {
                    theme.rendererFor(component);
                }
            });
            return theme;
        }
    }

}
