package com.leafclient.iui;

import com.leafclient.iui.animation.Transition;
import com.leafclient.iui.theme.Theme;
import com.leafclient.iui.component.Health;
import com.leafclient.iui.component.Item;
import org.junit.Test;
import pw.knx.feather.animate.Animation;
import com.leafclient.iui.renderer.HealthRenderer;
import com.leafclient.iui.renderer.ItemRenderer;

public class ExampleOverlay {

    private static final Component[] components = new Component[] {
            new Health(),
            new Item()
    };

    private static final Theme theme = Theme.builder()
            .associate(Health.class, HealthRenderer.class)
            .associate(Item.class, ItemRenderer.class)
            .buildAndPrepare(components);

    @Test
    public void test() {
        for(Component component: components) {
            theme.rendererFor(component)
                    .render(component);
        }
    }

    @Test
    public void animation() throws InterruptedException {
        final Animation animation = new Animation()
                .setDuration(1000)
                .setTransition(Transition.HIGH_EASE)
                .play();

        while (animation.isRunning()) {
            System.out.println(animation.get());
            Thread.sleep(1L);
        }
    }

}
