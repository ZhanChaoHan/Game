// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Player.java

import java.applet.AudioClip;
import java.awt.Graphics;

public class Player extends GameObject
{

    public Player()
    {
        hiscore = 1000;
    }

    public void setData(double d, double d1, double d2, double d3, int i, int j, int k, int l, char c)
    {
        super.setData(d, d1, d2, d3, i, j, k, l, c);
        shotInterval = 0;
        power = 100;
        guard = 0;
        score = 0;
    }

    public void move(GetKeys getkeys)
    {
        if(!exist)
            return;
        if(vx < 0.0D)
            anime = 0;
        else
        if(vx > 0.0D)
            anime = 2;
        else
            anime = 1;
        if(shotInterval < 1)
        {
            if(getkeys.z)
            {
                p.myshot[p.wavTarg].play();
                shotInterval = 3;
                for(int k = -8; k <= 8; k += 16)
                    if((tmp = p.shoots.getEmpty()) != null)
                        tmp.setData(px + (double)k, py, 0.0D, -24D, 16, 0, 0, 0, color);

                if(color == '\u6731')
                {
                    int i = 21;
                    if(power < 400)
                        i = 15;
                    if(power < 300)
                        i = 9;
                    if(power < 200)
                        i = 3;
                    if(power < 100)
                        i = 0;
                    byte byte0;
                    if(getkeys.shift)
                    {
                        byte0 = 6;
                    } else
                    {
                        i *= 2;
                        byte0 = 12;
                    }
                    for(int l = 90 - i; l <= 90 + i; l += byte0)
                        if((tmp = p.shoots.getEmpty()) != null)
                            tmp.setData(px, py, -Math.cos(Math.toRadians(l)) * 24D, -Math.sin(Math.toRadians(l)) * 24D, 20, 0, 0, 0, color);

                } else
                if(color == '\u8056')
                {
                    int j = 12;
                    if(power < 400)
                        j = 9;
                    if(power < 300)
                        j = 6;
                    if(power < 200)
                        j = 3;
                    if(power < 100)
                        j = 0;
                    byte byte1;
                    if(getkeys.shift)
                    {
                        byte1 = 6;
                    } else
                    {
                        j *= 4;
                        byte1 = 24;
                    }
                    for(int i1 = -j; i1 <= j; i1 += byte1)
                        if((tmp = p.shoots.getEmpty()) != null)
                            tmp.setData(px + (double)i1, py, 0.0D, -18D, 20, 0, 0, 0, color);

                }
            }
        } else
        {
            shotInterval--;
        }
        if(guard == 0 && getkeys.x && power >= 100)
        {
            power -= 100;
            guard = -30;
            p.bombuse.play();
        }
        if(guard > 0)
        {
            for(int j1 = 0; j1 < 4; j1++)
                if((tmp2 = p.effects.getEmpty()) != null)
                    tmp2.setData(px, py, 0.0D, 0.0D, (guard * (5 + j1)) / 2, 0, 0, 1, 'c');

            guard--;
        } else
        if(guard < 0)
        {
            for(int k1 = 0; k1 < 4; k1++)
                if((tmp2 = p.effects.getEmpty()) != null)
                    tmp2.setData(px, py, 0.0D, 0.0D, (-guard * (5 + k1)) / 2, 0, 0, 1, 'c');

            if(guard < -4)
            {
                for(int l1 = 0; l1 < 20; l1++)
                    if((tmp2 = p.effects.getEmpty()) != null)
                        tmp2.setData(Math.random() * 480D, Math.random() * 480D, 0.0D, 0.0D, l1 * 16, 0, 8, 4, randomColor());

            }
            if((tmp = p.shoots.getEmpty()) != null)
                tmp.setData(240D, 240D, 9999D, 9999D, 20000, 0, 0, 0, '\u900F');
            for(int i2 = 0; i2 < p.bullets.getArrayMax(); i2++)
                if((tmp = p.bullets.getObject(i2)).getExist())
                {
                    score += 2;
                    tmp.eraseWithEffect();
                }

            guard++;
        } else
        {
            int j2 = 0;
            do
            {
                if(j2 >= p.bullets.getArrayMax())
                    break;
                if((tmp = p.bullets.getObject(j2)).getExist() && (double)((size + tmp.getSize()) / 2) > Math.hypot(px - tmp.getPx(), py - tmp.getPy()))
                {
                    p.myhit.play();
                    for(int k2 = 15; k2 < 375; k2 += 30)
                        if((tmp2 = p.effects.getEmpty()) != null)
                            tmp2.setData(px, py, Math.cos(Math.toRadians(k2)) * 16D, Math.sin(Math.toRadians(k2)) * 16D, 0, 0, 4, 10, 'y');

                    tmp.eraseWithEffect();
                    guard = 30;
                    life--;
                    power = (power + 499) / 2;
                    if(life < 0)
                    {
                        life = 0;
                        eraseWithEffect();
                    }
                    break;
                }
                j2++;
            } while(true);
        }
        vx = 0.0D;
        vy = 0.0D;
        if(getkeys.up)
            vy -= 8D;
        if(getkeys.down)
            vy += 8D;
        if(getkeys.left)
            vx = -8D;
        if(getkeys.right)
            vx = 8D;
        if(vx != 0.0D && vy != 0.0D)
        {
            vx *= 0.69999999999999996D;
            vy *= 0.69999999999999996D;
        }
        if(getkeys.shift)
        {
            vx *= 0.5D;
            vy *= 0.5D;
            if((tmp2 = p.effects.getEmpty()) != null)
                tmp2.setData(px + vx, py + vy, 0.0D, 0.0D, 8, 0, 0, 0, 'b');
            if((tmp2 = p.effects.getEmpty()) != null)
                tmp2.setData(px + vx, py + vy, 0.0D, 0.0D, 6, 0, 0, 0, 'c');
            if((tmp2 = p.effects.getEmpty()) != null)
                tmp2.setData(px + vx, py + vy, 0.0D, 0.0D, 4, 0, 0, 0, 'w');
            if((tmp2 = p.effects.getEmpty()) != null)
                tmp2.setData(px + vx, py + vy, 0.0D, 0.0D, 2, 0, 0, 0, 'w');
        }
        super.move();
        MainPanel _tmp = p;
        if(px < 32D)
        {
            MainPanel _tmp1 = p;
            px = 32D;
        }
        MainPanel _tmp2 = p;
        if(py < 32D)
        {
            MainPanel _tmp3 = p;
            py = 32D;
        }
        MainPanel _tmp4 = p;
        if(px > 448D)
        {
            MainPanel _tmp5 = p;
            px = 448D;
        }
        MainPanel _tmp6 = p;
        if(py > 448D)
        {
            MainPanel _tmp7 = p;
            py = 448D;
        }
        if(frame < 9999)
        {
            if(frame < 60)
            {
                if(frame == 1)
                {
                    p.stopBgm();
                    p.bgm[0].loop();
                }
            } else
            if(frame < 360)
                st1b_l();
            else
            if(frame < 660)
                st1b_r();
            else
            if(frame >= 700)
                if(frame < 1000)
                {
                    st1b_l();
                    st1b_r();
                } else
                if(frame >= 1050)
                    if(frame < 1300)
                        st1g();
                    else
                    if(frame >= 1350)
                        if(frame < 1600)
                        {
                            st1g();
                            st1b_l();
                        } else
                        if(frame >= 1650)
                            if(frame < 1900)
                            {
                                st1g();
                                st1b_r();
                            } else
                            if(frame < 2200)
                            {
                                st1g();
                                st1b_l();
                                st1b_r();
                            } else
                            if(frame < 2500)
                            {
                                st1g();
                                st1g();
                            } else
                            if(frame < 2700)
                            {
                                st1g();
                                st1g();
                                st1b_l();
                                st1b_r();
                            } else
                            if(frame == 2900)
                            {
                                p.stopBgm();
                                p.bgm[3].loop();
                                p.enemys.allErase();
                                tmp = p.enemys.getObject(0);
                                tmp.setData(240D, 0.0D, 0.0D, 0.0D, 64, 0, 100, 2000, '\u8056');
                            } else
                            if(frame == 2901)
                            {
                                tmp = p.enemys.getObject(0);
                                if(tmp.getExist())
                                    frame--;
                                else
                                    frame = 9900;
                            }
        } else
        if(frame < 19999)
        {
            if(frame < 10050)
            {
                if(frame == 10001)
                {
                    p.stopBgm();
                    p.bgm[1].loop();
                }
            } else
            if(frame < 10350)
                st2b_l();
            else
            if(frame < 10650)
                st2b_r();
            else
            if(frame >= 10700)
                if(frame < 11000)
                    st2r_l();
                else
                if(frame < 11300)
                    st2r_r();
                else
                if(frame >= 11350)
                    if(frame < 11650)
                        st2g();
                    else
                    if(frame >= 11700)
                        if(frame < 12000)
                        {
                            st2b_l();
                            st2b_r();
                        } else
                        if(frame >= 11300)
                            if(frame < 12350)
                            {
                                st2r_l();
                                st2r_r();
                            } else
                            if(frame >= 11400)
                                if(frame < 12700)
                                {
                                    st2g();
                                    st2g();
                                } else
                                if(frame >= 12750)
                                    if(frame < 13000)
                                    {
                                        st2b_l();
                                        st2b_r();
                                        st2g();
                                    } else
                                    if(frame < 13300)
                                    {
                                        st2r_l();
                                        st2r_r();
                                        st2g();
                                    } else
                                    if(frame < 13600)
                                    {
                                        st2r_l();
                                        st2b_r();
                                        st2g();
                                    } else
                                    if(frame < 13900)
                                    {
                                        st2b_l();
                                        st2r_r();
                                        st2g();
                                    } else
                                    if(frame >= 14000)
                                        if(frame < 14500)
                                        {
                                            st2g();
                                            st2g();
                                            st2g();
                                        } else
                                        if(frame >= 14600)
                                            if(frame < 14800)
                                            {
                                                st2b_l();
                                                st2b_r();
                                                st2r_l();
                                            } else
                                            if(frame < 15000)
                                            {
                                                st2b_l();
                                                st2b_r();
                                                st2r_r();
                                            } else
                                            if(frame >= 15100)
                                                if(frame < 15300)
                                                {
                                                    st2r_l();
                                                    st2r_r();
                                                    st2b_l();
                                                } else
                                                if(frame < 15500)
                                                {
                                                    st2r_l();
                                                    st2r_r();
                                                    st2b_r();
                                                } else
                                                if(frame == 15600)
                                                {
                                                    p.stopBgm();
                                                    p.bgm[3].loop();
                                                    p.enemys.allErase();
                                                    tmp = p.enemys.getObject(0);
                                                    tmp.setData(240D, 0.0D, 0.0D, 0.0D, 64, 0, 150, 2500, '\u9752');
                                                } else
                                                if(frame == 15601)
                                                {
                                                    tmp = p.enemys.getObject(0);
                                                    if(tmp.getExist())
                                                        frame--;
                                                    else
                                                        frame = 19900;
                                                }
        } else
        if(frame < 20050)
        {
            if(frame == 20001)
            {
                p.stopBgm();
                p.bgm[2].loop();
            }
        } else
        if(frame < 20300)
            st3b_l();
        else
        if(frame < 20600)
            st3b_r();
        else
        if(frame >= 20700)
            if(frame < 21000)
                st3r_l();
            else
            if(frame < 21300)
                st3r_r();
            else
            if(frame >= 21400)
                if(frame < 21700)
                    st3g();
                else
                if(frame >= 21800)
                    if(frame < 22100)
                        st3y();
                    else
                    if(frame >= 22200)
                        if(frame < 22500)
                        {
                            st3b_l();
                            st3b_r();
                        } else
                        if(frame >= 22600)
                            if(frame < 22900)
                            {
                                st3r_l();
                                st3r_r();
                            } else
                            if(frame >= 23000)
                                if(frame < 23300)
                                {
                                    st3g();
                                    st3g();
                                } else
                                if(frame >= 23400)
                                    if(frame < 23700)
                                    {
                                        st3y();
                                        st3y();
                                    } else
                                    if(frame >= 23800)
                                        if(frame < 24000)
                                        {
                                            st3b_l();
                                            st3g();
                                        } else
                                        if(frame >= 24100)
                                            if(frame < 24300)
                                            {
                                                st3r_r();
                                                st3g();
                                            } else
                                            if(frame >= 24400)
                                                if(frame < 24600)
                                                {
                                                    st3b_l();
                                                    st3y();
                                                } else
                                                if(frame >= 24700)
                                                    if(frame < 24900)
                                                    {
                                                        st3r_r();
                                                        st3y();
                                                    } else
                                                    if(frame == 25000)
                                                    {
                                                        p.stopBgm();
                                                        p.bgm[4].loop();
                                                        p.enemys.allErase();
                                                        tmp = p.enemys.getObject(0);
                                                        tmp.setData(240D, 0.0D, 0.0D, 0.0D, 64, 0, 200, 7000, '\u7DD1');
                                                    } else
                                                    if(frame == 25001)
                                                    {
                                                        tmp = p.enemys.getObject(0);
                                                        if(tmp.getExist())
                                                            frame--;
                                                        else
                                                            frame = 29900;
                                                    } else
                                                    if(frame > 29998)
                                                        p.setMenuMode(3);
    }

    private void st1b_l()
    {
        if(frame % 30 == 0 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(0.0D, Math.random() * 96D + 48D, 2D, 0.0D, 32, 0, 2, 3, '\u9752');
    }

    private void st1b_r()
    {
        if(frame % 30 == 15 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(480D, Math.random() * 96D + 48D, -2D, 0.0D, 32, 0, 2, 3, '\u9752');
    }

    private void st1g()
    {
        if(frame % 30 == 7 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(Math.random() * 400D + 40D, 0.0D, 0.0D, 1.0D, 32, 0, 3, 10, '\u7DD1');
    }

    private void st2b_l()
    {
        if(frame % 30 == 0 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(0.0D, Math.random() * 96D + 48D, 2D, 0.0D, 32, 0, 1, 5, '\u9752');
    }

    private void st2b_r()
    {
        if(frame % 30 == 15 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(480D, Math.random() * 96D + 48D, -2D, 0.0D, 32, 0, 1, 5, '\u9752');
    }

    private void st2r_l()
    {
        if(frame % 40 == 0 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(0.0D, Math.random() * 96D + 48D, 3D, 0.0D, 32, 0, 4, 12, '\u8D64');
    }

    private void st2r_r()
    {
        if(frame % 40 == 20 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(480D, Math.random() * 96D + 48D, -3D, 0.0D, 32, 0, 4, 12, '\u8D64');
    }

    private void st2g()
    {
        if(frame % 60 == 0 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(Math.random() * 400D + 40D, 0.0D, 0.0D, 1.0D, 32, 0, 5, 20, '\u7DD1');
    }

    private void st3b_l()
    {
        if(frame % 40 == 0 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(0.0D, Math.random() * 96D + 48D, 2D, 0.0D, 32, 0, 6, 7, '\u9752');
    }

    private void st3b_r()
    {
        if(frame % 40 == 15 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(480D, Math.random() * 96D + 48D, -2D, 0.0D, 32, 0, 6, 7, '\u9752');
    }

    private void st3r_l()
    {
        if(frame % 60 == 0 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(0.0D, Math.random() * 96D + 48D, 3D, 0.0D, 32, 0, 8, 15, '\u8D64');
    }

    private void st3r_r()
    {
        if(frame % 60 == 20 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(480D, Math.random() * 96D + 48D, -3D, 0.0D, 32, 0, 8, 15, '\u8D64');
    }

    private void st3g()
    {
        if(frame % 80 == 0 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(Math.random() * 400D + 40D, 0.0D, 0.0D, 1.0D, 32, 0, 10, 30, '\u7DD1');
    }

    private void st3y()
    {
        if(frame % 80 == 0 && (tmp = p.enemys.getEmpty()) != null)
            tmp.setData(Math.random() * 400D + 40D, 0.0D, 0.0D, 1.0D, 32, 0, 15, 30, '\u7D2B');
    }

    public void draw(Graphics g)
    {
        super.draw(g);
    }

    public void plusScore(int i)
    {
        score += i;
        if(score > hiscore)
            hiscore = score;
    }

    public void plusPower(int i)
    {
        power += i;
        if(power > 499)
        {
            score += (power - 499) * 5;
            power = 499;
        }
    }

    public void plusLife(int i)
    {
        life += i;
        if(life > 9)
        {
            score += 10000;
            life = 9;
        }
    }

    public int getPower()
    {
        return power;
    }

    public int getScore()
    {
        return score;
    }

    public int getHiscore()
    {
        return hiscore;
    }

    public int getStage()
    {
        if(frame / 10000 < 3)
            return frame / 10000;
        else
            return 2;
    }

    private int shotInterval;
    private int guard;
    private int power;
    private int score;
    private int hiscore;
}
