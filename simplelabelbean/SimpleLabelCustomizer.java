package simplelabelbean;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.beans.Customizer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author skloppe
 * 
 * http://bip.weizmann.ac.il/course/prog2/tutorial/javabeans/customization/index.html
 */
public class SimpleLabelCustomizer extends JPanel implements Customizer, TextListener {

    private SimpleLabel simpleLabelBean;
    private TextComponent labelText, posX, posY;
    
    @Override
    public void setObject(Object bean) {
        /**
         * Convert Object to SimpleLabelBean
         */
        simpleLabelBean = (SimpleLabel) bean;
        /**
         * Set Layout to Gridlayout
         */
        GridLayout gl = new GridLayout(4,2,10,10);
        setLayout(gl);
        /**
         * Headline
         */
        add(new JLabel("Customise Labeltext & Position"));
        add(new JLabel());
        /**
         * Labeltext
         */
        add(new JLabel("Labeltext:"));
        
        labelText = new TextField(simpleLabelBean.getTitle());
        labelText.addTextListener(this);
        
        add(labelText);
        /**
         * PosX
         */
        add(new JLabel("Position Text X-Achse:"));
        
        posX = new TextField(String.valueOf(simpleLabelBean.getPosX()));
        posX.addTextListener(this);
        
        add(posX);
        /**
         * PosY
         */
        add(new JLabel("Position Text Y-Achse:"));
        
        posY = new TextField(String.valueOf(simpleLabelBean.getPosY()));
        posY.addTextListener(this);
        
        add(posY);
        /**
         * Dialog Settings
         */
        setSize(new Dimension(300,300));
    }
    
    @Override
    public Insets getInsets() { 
        return new Insets(10, 10, 10, 10);          // Add some space around the outside of the panel.
    }
    
    @Override
    public void textValueChanged(TextEvent e) {
        TextComponent t = (TextComponent)e.getSource();
        String s = t.getText();
        
        if (t == labelText) {
            String oldTitle = simpleLabelBean.getTitle();
            simpleLabelBean.setTitle(s);
            listeners.firePropertyChange("title", oldTitle, s);
        } else if (t == posX) {
            int oldPosX = simpleLabelBean.getPosX();
            simpleLabelBean.setPosX(Integer.parseInt(s));
            listeners.firePropertyChange("posX", oldPosX, Integer.parseInt(s));
        } else if (t == posY) {
            int oldPosY = simpleLabelBean.getPosY();
            simpleLabelBean.setPosY(Integer.parseInt(s));  
            listeners.firePropertyChange("posY", oldPosY, Integer.parseInt(s));
        }
        //listeners.firePropertyChange(null, null, null);
    }
    
    /** This code uses the PropertyChangeSupport class to maintain a list of
     * listeners interested in the edits we make to the bean.
     */
    protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        listeners.addPropertyChangeListener(l);
    }
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        listeners.removePropertyChangeListener(l);
    }
}