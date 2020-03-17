package com.leafclient.iui;

/**
 * {@link Renderer} are contained by the {@link com.leafclient.iui.theme.Theme} implementation and are associated
 * with a {@link Component}. They're called to draw the {@link Component} on screen.
 *
 * @param <T> Component's type
 */
public interface Renderer<T extends Component> {

    /**
     * Draws specified {@link Component} when specified it needs to be rendered.
     *
     * @param comp Component's instance
     */
    void render(T comp);

    /**
     * You might want to update your {@link Component} before or after a certain amount of time,
     * this can be called to do it.
     *
     * @param comp Component's instance
     */
    default void update(T comp) {}

}
