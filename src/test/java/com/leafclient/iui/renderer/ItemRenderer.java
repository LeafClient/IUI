package com.leafclient.iui.renderer;

import com.leafclient.iui.Renderer;
import com.leafclient.iui.component.Item;

public class ItemRenderer implements Renderer<Item> {

    @Override
    public void render(Item comp) {
        // Draw your stuff
        System.out.println("I have " + comp.name + " in hand!");
    }
}
