// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Enemy.java

import java.applet.AudioClip;
import java.awt.Graphics;

public class Enemy extends GameObject
{

    public Enemy()
    {
    }

    public void setData(double d, double d1, double d2, double d3, int i, int j, int k, int l, char c)
    {
        super.setData(d, d1, d2, d3, i, j, k, l, c);
        c0 = c1 = c2 = c3 = c4 = c5 = 0.0D;
    }

    public void move()
    {
        if(!exist)
            return;
        if(size == 32)
        {
            if(vx > 1.0D)
                anime = 0;
            else
            if(vx < -1D)
                anime = 2;
            else
                anime = 1;
        } else
        {
            anime = (frame / 5) % 3;
        }
        if(type == 2)
        {
            if(frame % 30 == 0)
            {
                p.enshot[p.wavTarg].play();
                th = playerTh();
                if((tmp = p.bullets.getEmpty()) != null)
                    tmp.setData(px, py, Math.cos(th) * 6D, Math.sin(th) * 6D, 20, 0, 0, 0, 'b');
            }
        } else
        if(type == 3)
        {
            if(frame % 30 == 0)
            {
                p.enshot[p.wavTarg].play();
                c0 = Math.random() * 30D;
                for(int i = 0; i < 360; i += 60)
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData(px, py, Math.cos(Math.toRadians((double)i + c0)) * 6D, Math.sin(Math.toRadians((double)i + c0)) * 6D, 20, 0, 0, 0, 'g');

            }
            if(py > 240D && vy > 0.0D)
                vy *= -1D;
        } else
        if(type == 1)
        {
            if(frame % 40 == 0)
            {
                p.enshot[p.wavTarg].play();
                th = Math.toDegrees(playerTh());
                for(int j = -40; j <= 40; j += 20)
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData(px, py, Math.cos(Math.toRadians(th + (double)j)) * 6D, Math.sin(Math.toRadians(th + (double)j)) * 6D, 20, 0, 0, 0, 'b');

            }
        } else
        if(type == 4)
        {
            if(frame % 40 == 0)
            {
                p.enshot[p.wavTarg].play();
                th = Math.toDegrees(playerTh());
                for(int k = -60; k <= 60; k += 30)
                {
                    for(int k6 = 4; k6 <= 10; k6 += 2)
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(th + (double)k)) * (double)k6, Math.sin(Math.toRadians(th + (double)k)) * (double)k6, 20, 0, 0, 0, 'r');

                }

            }
        } else
        if(type == 5)
        {
            if(frame % 60 == 0)
            {
                p.enshot[p.wavTarg].play();
                c0 = Math.random() * 30D;
                for(int l = 0; l < 360; l += 30)
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData(px, py, Math.cos(Math.toRadians((double)l + c0)) * 8D, Math.sin(Math.toRadians((double)l + c0)) * 8D, 20, 0, 0, 0, 'g');

            }
            if(py > 240D && vy > 0.0D)
                vy *= -1D;
        } else
        if(type == 6)
        {
            if(frame % 40 == 0)
            {
                p.enshot[p.wavTarg].play();
                th = Math.toDegrees(playerTh());
                for(int i1 = -60; i1 <= 60; i1 += 15)
                {
                    for(int l6 = 7; l6 <= 10; l6 += 3)
                    {
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(th + (double)i1)) * 7D, Math.sin(Math.toRadians(th + (double)i1)) * 7D, 16, 0, 0, 0, 'b');
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(th + (double)i1)) * 10D, Math.sin(Math.toRadians(th + (double)i1)) * 10D, 16, 0, 0, 0, 'c');
                    }

                }

            }
        } else
        if(type == 8)
        {
            if(frame % 40 == 0)
            {
                p.enshot[p.wavTarg].play();
                th = Math.toDegrees(playerTh());
                for(int j1 = -75; j1 <= 75; j1 += 25)
                {
                    for(int i7 = 2; i7 <= 12; i7 += 2)
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(th + (double)j1)) * (double)i7, Math.sin(Math.toRadians(th + (double)j1)) * (double)i7, 16, 0, 0, 0, 'r');

                }

            }
        } else
        if(type == 10)
        {
            if(frame % 30 == 0)
            {
                p.enshot[p.wavTarg].play();
                c0 = Math.random() * 30D;
                for(int k1 = 0; k1 < 360; k1 += 30)
                {
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData(px, py, Math.cos(Math.toRadians((double)k1 + c0)) * 8D, Math.sin(Math.toRadians((double)k1 + c0)) * 8D, 16, 0, 0, 0, 'g');
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData(px, py, Math.cos(Math.toRadians((double)(k1 + 15) + c0)) * 6D, Math.sin(Math.toRadians((double)(k1 + 15) + c0)) * 6D, 16, 0, 0, 0, 'm');
                }

            }
            if(py > 240D && vy > 0.0D)
                vy *= -1D;
        } else
        if(type == 15)
        {
            if(frame % 20 == 0)
            {
                p.enshot[p.wavTarg].play();
                th = Math.toDegrees(playerTh());
                for(int l1 = 0; l1 < 5; l1++)
                {
                    c0 = Math.random() * 60D - 30D;
                    c1 = Math.random() * 8D + 2D;
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData(px, py, Math.cos(Math.toRadians(th + c0)) * c1, Math.sin(Math.toRadians(th + c0)) * c1, 16, 0, 0, 0, 'y');
                }

            }
            if(py > 240D && vy > 0.0D)
                vy *= -1D;
        } else
        if(type == 100)
        {
            if(life < 1000)
                bossCrash();
            else
            if(life > 1600)
            {
                if(py < 128D)
                    vy = 1.0D;
                else
                    vy = 0.0D;
                if(frame % 30 == 0)
                {
                    p.enshot[p.wavTarg].play();
                    c0 = Math.toDegrees(playerTh());
                    for(int i2 = -75; i2 <= 75; i2 += 150)
                        if((tmp = p.enemys.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)i2)) * 2D, Math.sin(Math.toRadians(c0 + (double)i2)) * 2D, 32, 0, 2, 20, '\u9752');

                    c0 = Math.random() * 30D;
                    for(int j2 = 0; j2 < 360; j2 += 15)
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians((double)j2 + c0)) * 6D, Math.sin(Math.toRadians((double)j2 + c0)) * 6D, 20, 0, 0, 0, 'r');

                }
            } else
            if(life > 1300)
            {
                if(py < 80D && px <= 400D)
                {
                    vx = 3D;
                    vy = 0.0D;
                } else
                if(px > 400D && py <= 400D)
                {
                    vx = 0.0D;
                    vy = 3D;
                } else
                if(py > 400D && px >= 80D)
                {
                    vx = -3D;
                    vy = 0.0D;
                } else
                {
                    vx = 0.0D;
                    vy = -3D;
                }
                if(frame % 60 == 0)
                {
                    for(int k2 = 90; k2 <= 270; k2 += 180)
                        if((tmp = p.enemys.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(k2)) * 4D, Math.sin(Math.toRadians(k2)) * 4D, 32, 0, 2, 5, '\u9752');

                }
                if(frame % 30 == 0)
                {
                    p.enshot[p.wavTarg].play();
                    c0 = Math.random() * 30D;
                    for(int l2 = 0; l2 < 360; l2 += 30)
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians((double)l2 + c0)) * 6D, Math.sin(Math.toRadians((double)l2 + c0)) * 6D, 20, 0, 0, 0, 'r');

                }
            } else
            {
                if(vx == 0.0D)
                    vx = 3D;
                if(vy == 0.0D)
                    vy = 2D;
                if(py < 80D && vy < 0.0D)
                    vy *= -1D;
                if(py > 400D && vy > 0.0D)
                    vy *= -1D;
                if(px < 80D && vx < 0.0D)
                    vx *= -1D;
                if(px > 400D && vx > 0.0D)
                    vx *= -1D;
                if(frame % 60 == 0)
                {
                    for(int i3 = 90; i3 <= 270; i3 += 180)
                        if((tmp = p.enemys.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(i3)) * 2D, Math.sin(Math.toRadians(i3)) * 2D, 32, 0, 2, 5, '\u9752');

                }
                if(frame % 30 == 0)
                {
                    p.enshot[p.wavTarg].play();
                    c0 = Math.random() * 30D;
                    for(int j3 = 0; j3 < 360; j3 += 20)
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians((double)j3 + c0)) * 6D, Math.sin(Math.toRadians((double)j3 + c0)) * 6D, 20, 0, 0, 0, 'r');

                }
            }
        } else
        if(type == 150)
        {
            if(life < 1000)
                bossCrash();
            else
            if(life > 2000)
            {
                if(py < 128D)
                    vy = 1.0D;
                else
                    vy = 0.0D;
                if(frame % 30 == 0)
                {
                    p.enshot[p.wavTarg].play();
                    c1 = Math.random() * 12D - 6D;
                    for(int k3 = 0; k3 < 480; k3 += 20)
                    {
                        c0 = Math.random() * 20D + 80D;
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(c1 + (double)k3, 0.0D, Math.cos(Math.toRadians(c0)) * 4D, Math.sin(Math.toRadians(c0)) * 4D, 20, 0, 0, 0, 'y');
                    }

                }
            } else
            if(life > 1500)
            {
                if(frame % 14 == 7)
                {
                    for(int l3 = 0; l3 < 360; l3 += 72)
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(frame + l3)) * 4D, Math.sin(Math.toRadians(frame + l3)) * 4D, 24, 0, 0, 0, 'c');

                } else
                if(frame % 14 == 0)
                {
                    for(int i4 = 0; i4 < 360; i4 += 72)
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(frame + i4)) * 4D, Math.sin(Math.toRadians(frame + i4)) * 4D, 16, 0, 1, 0, 'b');

                }
            } else
            {
                if(vy != -3D && vy != 3D)
                    vy = 3D;
                if(py > 400D)
                    vy = -3D;
                if(py < 80D)
                    vy = 3D;
                if(frame % 3 == 0)
                {
                    p.enshot[p.wavTarg].play();
                    for(int j4 = 0; j4 < 360; j4 += 30)
                    {
                        c0 = Math.random() * 360D;
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0)) * 4D, Math.sin(Math.toRadians(c0)) * 4D, 8, 0, 0, 0, randomColor());
                    }

                }
            }
        } else
        if(type == 200)
            if(life < 1000)
                bossCrash();
            else
            if(life > 6000)
            {
                if(py < 128D)
                    vy = 1.0D;
                else
                    vy = 0.0D;
                if(frame % 3 == 0)
                {
                    c0 = (c0 + 0.42731000000000002D) % 480D;
                    c1 = (c1 + c0 * 3D) % 480D;
                    p.enshot[p.wavTarg].play();
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData((c1 + 96D) % 480D, 0.0D, 0.0D, 6D, 12, 0, 0, 0, 'c');
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData((c1 + 192D) % 480D, 0.0D, 0.0D, 6D, 12, 0, 0, 0, 'c');
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData((c1 + 288D) % 480D, 0.0D, 0.0D, 6D, 12, 0, 0, 0, 'c');
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData((c1 + 384D) % 480D, 0.0D, 0.0D, 6D, 12, 0, 0, 0, 'c');
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData((c1 + 480D) % 480D, 0.0D, 0.0D, 6D, 12, 0, 0, 0, 'c');
                }
            } else
            if(life > 5000)
            {
                if(px < 80D && vx < 0.0D)
                    vx *= -1D;
                if(py < 80D && vy < 0.0D)
                    vy *= -1D;
                if(px > 200D && vx > 0.0D)
                    vx *= -1D;
                if(py > 200D && vy > 0.0D)
                    vy *= -1D;
                if(frame % 40 == 0)
                {
                    vx = Math.random() * 12D - 6D;
                    vy = Math.random() * 12D - 6D;
                } else
                if(frame % 40 == 20)
                {
                    vx = 0.0D;
                    vy = 0.0D;
                    c0 = Math.random() * 20D;
                    for(int k4 = 0; k4 < 360; k4 += 30)
                    {
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)k4)) * 4D, Math.sin(Math.toRadians(c0 + (double)k4)) * 4D, 16, -40, 1, 0, 'r');
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)k4)) * 4D, Math.sin(Math.toRadians(c0 + (double)k4)) * 4D, 16, -39, 1, 0, 'r');
                    }

                }
                if(frame % 4 == 0)
                {
                    if(c3 == 0.0D)
                    {
                        c3 = 1.0D;
                        c1 = 0.0D;
                        c2 = 15D;
                    }
                    if(c1 > 140D)
                        c2 = -15D;
                    if(c1 < -140D)
                        c2 = 15D;
                    if(c2 != 15D && c2 != -15D)
                        c2 = 15D;
                    c1 += c2;
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData(px, py, Math.cos(Math.toRadians(270D + c1)) * 8D, Math.sin(Math.toRadians(270D + c1)) * 8D, 64, 0, 0, 0, 'b');
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData(px, py, Math.cos(Math.toRadians(270D - c1)) * 8D, Math.sin(Math.toRadians(270D - c1)) * 8D, 64, 0, 0, 0, 'g');
                }
            } else
            if(life > 4000)
            {
                c0 = c1 = c2 = c3 = 0.0D;
                vx = 0.0D;
                vy = 0.0D;
                px = 240D;
                py = 120D;
                if(frame % 240 == 0)
                {
                    if((tmp = p.enemys.getEmpty()) != null)
                        tmp.setData(40D + Math.random() * 400D, 0.0D, 0.0D, 1.0D, 32, 0, 15, 30, '\u7D2B');
                } else
                if(frame % 240 == 120 && (tmp = p.enemys.getEmpty()) != null)
                    tmp.setData(40D + Math.random() * 400D, 0.0D, 0.0D, 1.0D, 32, 0, 10, 30, '\u7DD1');
                if(frame % 20 == 0)
                {
                    p.enshot[p.wavTarg].play();
                    th = Math.random() * 30D;
                    for(int l4 = 0; l4 < 360; l4 += 10)
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians((double)l4 + th)) * 6D, Math.sin(Math.toRadians((double)l4 + th)) * 6D, 20, 0, 0, 0, 'r');

                }
            } else
            if(life > 3000)
            {
                if(frame % 40 == 20)
                {
                    c0 = Math.random() * 20D;
                    for(int i5 = 0; i5 < 360; i5 += 16)
                    {
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)i5)) * 3D, Math.sin(Math.toRadians(c0 + (double)i5)) * 3D, 16, -20, 0, 0, 'r');
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)i5)) * 3D, Math.sin(Math.toRadians(c0 + (double)i5)) * 3D, 16, -19, 0, 0, 'r');
                    }

                    c0 = Math.random() * 20D;
                    for(int j5 = 0; j5 < 360; j5 += 24)
                    {
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)j5)) * 4D, Math.sin(Math.toRadians(c0 + (double)j5)) * 4D, 16, -20, 0, 0, 'b');
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)j5)) * 4D, Math.sin(Math.toRadians(c0 + (double)j5)) * 4D, 16, -19, 0, 0, 'b');
                    }

                    c0 = Math.random() * 20D;
                    for(int k5 = 0; k5 < 360; k5 += 32)
                    {
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)k5)) * 6D, Math.sin(Math.toRadians(c0 + (double)k5)) * 6D, 16, -20, 0, 0, 'g');
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c0 + (double)k5)) * 6D, Math.sin(Math.toRadians(c0 + (double)k5)) * 6D, 16, -19, 0, 0, 'g');
                    }

                }
            } else
            {
                if(frame % 4 == 0)
                {
                    c0 = (c0 + 0.42731000000000002D) % 480D;
                    c1 = (c1 + c0 * 3D) % 480D;
                    p.enshot[p.wavTarg].play();
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData((c1 + 160D) % 480D, 0.0D, 0.0D, 6D, 12, 0, 0, 0, 'c');
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData((c1 + 320D) % 480D, 0.0D, 0.0D, 6D, 12, 0, 0, 0, 'c');
                    if((tmp = p.bullets.getEmpty()) != null)
                        tmp.setData((c1 + 480D) % 480D, 0.0D, 0.0D, 6D, 12, 0, 0, 0, 'c');
                }
                if(life < 2500 && frame % 40 == 0)
                {
                    vx = 0.0D;
                    vy = 0.0D;
                    c2 = Math.random() * 20D;
                    for(int l5 = 0; l5 < 360; l5 += 60)
                    {
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c2 + (double)l5)) * 4D, Math.sin(Math.toRadians(c2 + (double)l5)) * 4D, 32, -40, 1, 0, 'r');
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c2 + (double)l5)) * 4D, Math.sin(Math.toRadians(c2 + (double)l5)) * 4D, 32, -39, 1, 0, 'r');
                    }

                }
                if(frame % 480 == 0 && life < 2000 && (tmp = p.enemys.getEmpty()) != null)
                    tmp.setData(40D + Math.random() * 400D, 0.0D, 0.0D, 1.0D, 32, 0, 15, 30, '\u7D2B');
                if(frame % 480 == 240 && life < 2000 && (tmp = p.enemys.getEmpty()) != null)
                    tmp.setData(40D + Math.random() * 400D, 0.0D, 0.0D, 1.0D, 32, 0, 10, 30, '\u7DD1');
                if(frame % 4 == 0 && life < 1500)
                {
                    for(int i6 = 0; i6 < 360; i6 += 60)
                    {
                        c3 = Math.random() * 360D;
                        if((tmp = p.bullets.getEmpty()) != null)
                            tmp.setData(px, py, Math.cos(Math.toRadians(c3)) * 6D, Math.sin(Math.toRadians(c3)) * 6D, 16, 0, 0, 0, 'b');
                    }

                }
            }
        for(int j6 = 0; j6 < p.shoots.getArrayMax(); j6++)
        {
            if(!(tmp = p.shoots.getObject(j6)).getExist() || Math.hypot(px - tmp.getPx(), py - tmp.getPy()) >= (double)((size + tmp.getSize()) / 2))
                continue;
            p.enhit[p.wavTarg].play();
            if(size != 64 || life >= 1000)
                life--;
            if(life < 0)
            {
                crash();
                p.encrash[p.wavTarg].play();
                break;
            }
            if(tmp.getSize() < 100)
                tmp.eraseWithEffect();
            p.player.plusScore(1);
        }

        super.move();
    }

    public void crash()
    {
        p.player.plusScore(type * 10);
        p.player.plusPower(type);
        eraseWithEffect();
    }

    public void bossCrash()
    {
        vx = 0.0D;
        vy = 0.0D;
        p.stopBgm();
label0:
        for(int i = 0; i < 10; i++)
        {
            life--;
            if(life % 50 == 0 && life > 199)
            {
                p.encrash[p.wavTarg].play();
                int j = 15;
                do
                {
                    if(j >= 375)
                        continue label0;
                    if((tmp2 = p.effects.getEmpty()) != null)
                        tmp2.setData((px + Math.random() * 32D) - 16D, (py + Math.random() * 32D) - 16D, Math.cos(Math.toRadians(j)) * 16D, Math.sin(Math.toRadians(j)) * 16D, 0, 0, 4, 10, randomColor());
                    j += 30;
                } while(true);
            }
            if(life >= 0)
                continue;
            crash();
            p.bosscrash.play();
            p.player.plusLife(1);
            p.player.plusScore(p.player.getLife() * 3000);
            p.player.plusScore(p.player.getPower() * 10);
            int k = 0;
            do
            {
                if(k >= 360)
                    continue label0;
                for(int l = 8; l < 20; l += 4)
                    if((tmp2 = p.effects.getEmpty()) != null)
                        tmp2.setData(px, py, Math.cos(Math.toRadians(k)) * (double)l, Math.sin(Math.toRadians(k)) * (double)l, 4, 0, 2, 30, randomColor());

                k += 15;
            } while(true);
        }

    }

    public void draw(Graphics g)
    {
        super.draw(g);
    }

    public double playerTh()
    {
        return Math.atan2(p.player.getPy() - py, p.player.getPx() - px);
    }

    double c0;
    double c1;
    double c2;
    double c3;
    double c4;
    double c5;
}
