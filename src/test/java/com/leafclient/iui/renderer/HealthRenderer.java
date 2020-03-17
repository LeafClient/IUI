package com.leafclient.iui.renderer;

import com.leafclient.iui.Renderer;
import com.leafclient.iui.component.Health;

public class HealthRenderer implements Renderer<Health> {

    @Override
    public void render(Health comp) {
        // Draw your stuff
        System.out.println("I have " + comp.health + " HP!");
    }
}
