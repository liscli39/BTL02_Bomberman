/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities.items;

import bomberman2.entities.barriers.StaticEntity;

/**
 *
 * @author Liscli
 */
public abstract class Item extends StaticEntity{
    public abstract boolean isItemVisible();
    public abstract void show();
    protected boolean visible;
}
