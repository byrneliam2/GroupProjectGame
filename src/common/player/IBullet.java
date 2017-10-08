package common.player;

/*
 * SWEN 222 Group Project
 * Liam Byrne (byrneliam2)
 * 300338518
 */

import player.Bullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface IBullet {

    List<Bullet> bulletList = new ArrayList<>();
    int bulletSize = 20;

    Image getBulletPic();

    double getX();

    double getY();
}
