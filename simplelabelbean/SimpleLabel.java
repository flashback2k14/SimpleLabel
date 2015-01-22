package simplelabelbean;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author skloppe
 */
public class SimpleLabel extends Canvas implements Serializable{
    /**
     * Member
     */
    private Color bgColor = Color.GREEN;
    private Color fgColor = Color.BLACK;
    private String title = "Simple Label";
    private int posX = 8;
    private int posY = 25;
    private int recWidth = 90;
    private int recHeight = 45;
    private boolean isFilledRec = true;
    private boolean isRoundedCornerRec = false;
    private String colorStyle = "normal";
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    
    /**
     * Constructor
     */
    public SimpleLabel() {
        setPreferredSize(new Dimension(90,40));
    }
    
    /**
     * Getter / Setter
     */
    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color color) {
        Color oldBgColor = this.bgColor;
        this.bgColor = color;
        changes.firePropertyChange("bgColor", oldBgColor, this.bgColor);
        repaint();
    }

    public Color getFgColor() {
        return fgColor;
    }

    public void setFgColor(Color fgColor) {
        Color oldFgColor = this.fgColor;
        this.fgColor = fgColor;
        changes.firePropertyChange("fgColor", oldFgColor, this.fgColor);
        repaint();
    }
        
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        String oldTitle = this.title;
        this.title = title;
        changes.firePropertyChange("title", oldTitle, this.title);
        repaint();
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        int oldPosX = this.posX;
        this.posX = posX;
        changes.firePropertyChange("posX", oldPosX, this.posX);
        repaint();
    }
    
    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        int oldPosY = this.posY;
        this.posY = posY;
        changes.firePropertyChange("posY", oldPosY, this.posY);
        repaint();
    }

    public int getRecWidth() {
        return recWidth;
    }

    public void setRecWidth(int recWidth) {
        int oldRecWidth = this.recWidth;
        this.recWidth = recWidth;
        changes.firePropertyChange("recWidth", oldRecWidth, this.recWidth);
        repaint();
    }

    public int getRecHeight() {
        return recHeight;
    }

    public void setRecHeight(int recHeight) {
        int oldRecHeight = this.recHeight;
        this.recHeight = recHeight;
        changes.firePropertyChange("recHeight", oldRecHeight, this.recHeight);
        repaint();
    }

    public boolean isIsFilledRec() {
        return isFilledRec;
    }

    public void setIsFilledRec(boolean isFilledRec) {
        boolean oldIsFilledRec = this.isFilledRec;
        this.isFilledRec = isFilledRec;
        changes.firePropertyChange("isFilledRec", oldIsFilledRec, this.isFilledRec);
        repaint();
    }

    public boolean isIsRoundedCornerRec() {
        return isRoundedCornerRec;
    }

    public void setIsRoundedCornerRec(boolean isRoundedCornerRec) {
        boolean oldIsRoundedCornerRec = this.isRoundedCornerRec;
        this.isRoundedCornerRec = isRoundedCornerRec;
        changes.firePropertyChange("isRoundedCornerRec", oldIsRoundedCornerRec, this.isRoundedCornerRec);
        repaint();
    }           

    public String getColorStyle() {
        return colorStyle;
    }

    public void setColorStyle(String colorStyle) {
        String oldColorStyle = this.colorStyle;
        this.colorStyle = colorStyle;
        changes.firePropertyChange("colorStyle", oldColorStyle, this.colorStyle);
        repaint();
    }
        
    /**
     * Methods
     */
    public void displayDialog() {
        JOptionPane.showMessageDialog(this, 
                                      "Thanks for clicking the Simple Label Bean.", 
                                      "displayDialog Methode", 
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void displayDialogWithPara(String msg) {
        JOptionPane.showMessageDialog(this, 
                                      "Thanks for clicking the Simple Label Bean. Your Message is " + msg, 
                                      "displayDialog Methode", 
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * private Methods
     */
    private Color invertColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }
    
    /**
     * Overridden Methods
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        //Background
        switch(colorStyle) {
            case SimpleLabelConstants.NORMAL:
                g.setColor(this.bgColor);
                break;
                
            case SimpleLabelConstants.INVERT:
                g.setColor(invertColor(this.bgColor));
                break;
                
            default:
                break;
        }
        
        if (isFilledRec) {
            if (isRoundedCornerRec) {
                g.fillRoundRect(0, 0, this.recWidth, this.recHeight, 10, 10);
            } else {
                g.fillRect(0, 0, this.recWidth, this.recHeight);
            }
        } else {
            if (isRoundedCornerRec) {
                g.drawRoundRect(0, 0, this.recWidth, this.recHeight, 10, 10);
            } else {
                g.drawRect(0, 0, this.recWidth, this.recHeight);
            }
        }
        
        //Text
        switch(colorStyle) {
            case SimpleLabelConstants.NORMAL:
                g.setColor(this.fgColor);
                break;
                
            case SimpleLabelConstants.INVERT:
                g.setColor(invertColor(this.fgColor));
                break;
                
            default:
                break;
        }
        
        g.drawString(this.title, this.posX, this.posY);
    }
}