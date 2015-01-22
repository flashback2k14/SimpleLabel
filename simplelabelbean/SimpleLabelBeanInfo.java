package simplelabelbean;

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

/**
 *
 * @author skloppe
 * 
 * http://bip.weizmann.ac.il/course/prog2/tutorial/javabeans/beaninfo/index.html
 */
public class SimpleLabelBeanInfo extends SimpleBeanInfo {
    /**
     * Constants
     */
    private final static Class beanClass = SimpleLabel.class;
    private final static Class titleEditorClass = SimpleLabelTitleEditor.class;
    private final static Class colorStyleEditorClass = SimpleLabelColorStyleEditor.class;
    private final static Class customizerClass = SimpleLabelCustomizer.class;
    
    /** Return an icon for the bean.  We should really check the kind argument
     *  to see what size icon the beanbox wants, but since we only have one
     *  icon to offer, we just return it and let the beanbox deal with it.
     *  @param iconKind
     *  @return  */
    @Override
    public Image getIcon(int iconKind) {
        String imageName = "javabeans-16.png";
        
        if (iconKind == BeanInfo.ICON_COLOR_32x32 || iconKind == BeanInfo.ICON_MONO_32x32) {
            imageName = "javabeans-32.png";
        }
        
        return loadImage(imageName);
    }
    
    /** Return a descriptor for the bean itself.  It specifies a customizer
     *  for the bean class.  We could also add a description string here.
     * @return BeanDescriptor
     */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(beanClass, customizerClass);
    }
    
    /** This method returns an array of PropertyDescriptor objects that specify
     *  additional information about the properties supported by the bean.
     *  By explicitly specifying property descriptors, we are able to provide
     *  simple help strings for each property; these would not be available to
     *  the beanbox through simple introspection.  We are also able to register
     *  special property editors for two of the properties.
     *  @return 
     */
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        
        try{
            
            PropertyDescriptor[] props = {
            
                property("title", "Hier wird der Text des Labels gesetzt.", "01 - Titel"),
                property("bgColor", "Hier wird die Hintergrundfarbe gesetzt.", "02 - Hintergrundfarbe"),
                property("fgColor", "Hier wird die Schriftfarbe gesetzt.", "03 - Schriftfarbe"),
                property("posX", "Hier wird die Position der Schrift gesetzt (X-Koordinate).", "04 - X Koordinate Schriftzug"),
                property("posY", "Hier wird die Position der Schrift gesetzt (Y-Koordinate).", "05 - Y Koordinate Schriftzug"),
                property("recWidth", "Hier wird die Breite des Labels gesetzt.", "06 - Breite Label"),
                property("recHeight", "Hier wird die Höhe des Labels gesetzt.", "07 - Höhe Label"),
                property("isFilledRec", "Wechsel des Füllgrades?", "08 - Ausgefülltes Label"),
                property("isRoundedCornerRec", "Runde Ecken nutzen?", "09 - Runde Ecken"),
                property("colorStyle", "Farben invertieren?", "10 - Farben invertieren")
            
            };
                                       
            props[0].setPropertyEditorClass(titleEditorClass);
            props[9].setPropertyEditorClass(colorStyleEditorClass);
                                   
            return props;
            
        } catch(IntrospectionException e) {
            return super.getPropertyDescriptors();     
        }
    }
    
    /** This is a convenience routine for creating PropertyDescriptor objects.
       * @param name
       * @param description
       * @return 
       * @throws java.beans.IntrospectionException */
    private PropertyDescriptor property(String name, String description, String displayname) throws java.beans.IntrospectionException {
        PropertyDescriptor p = new PropertyDescriptor(name, beanClass);
        p.setShortDescription(description);
        p.setDisplayName(displayname);
        return p;
    }
        
    /** This method returns an array of method descriptors for the supported
     *  methods of a bean. This allows us to provide useful description strings,
     *  but it also allows us to filter out non-useful methods like wait()
     *  and notify() that the bean inherits and which might otherwise be
     *  displayed by the beanbox.
     * @return 
     */
    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            
            MethodDescriptor[] methods = {
                method("displayDialog", "Pop up the a simple Information Dialog."),
                method("displayDialogWithPara", "Pop up the a simple Information Dialog with a Message Parameter.")
            };  
            
            return methods;
            
        } catch (NoSuchMethodException | SecurityException e) {
            return super.getMethodDescriptors();
        }
    }
    
    /** This is a convenience method for creating MethodDescriptors.  Note that
     *  it assumes we are talking about methods with no arguments.
     * @param name
     * @param description
     * @return
     * @throws java.lang.NoSuchMethodException  */
    private MethodDescriptor method(String name, String description) throws NoSuchMethodException, SecurityException {
        Method m = SimpleBeanInfo.class.getMethod(name, new Class[] {});
        MethodDescriptor md = new MethodDescriptor(m);
        md.setShortDescription(description);
        return md;
    }
}