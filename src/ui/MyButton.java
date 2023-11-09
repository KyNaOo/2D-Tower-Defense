package src.ui;

import java.awt.*;

public class MyButton {
    private int x,y,width,height;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;
    public MyButton(String text, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        initBounds();
    }
    private void initBounds(){
        this.bounds = new Rectangle(x,y,width,height);
    }
    public void draw(Graphics g){
        drawBody(g);
        drawBorder(g);

        drawText(g);
    }
    public void resetBooleans(){
        this.mousePressed = false;
        this.mouseOver = false;
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x,y,width,height);
        if (mousePressed){
            g.drawRect(x+1,y+1,width-2,height-2);
            g.drawRect(x+2,y+2,width-4,height-4);
        }

    }

    private void drawBody(Graphics g) {
        if (mouseOver){
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.white);
        }
        g.fillRect(x,y,width,height);

    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w/2 + width /2 ,y + h/2+ height /2);
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
