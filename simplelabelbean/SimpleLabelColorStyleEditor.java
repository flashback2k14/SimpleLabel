package simplelabelbean;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author skloppe
 */
public class SimpleLabelColorStyleEditor extends PropertyEditorSupport {
    // These two methods allow the property to be edited in a dropdown list.
    // Return the list of value names for the enumerated type.
    @Override
    public String[] getTags() {
        return new String[] { "normal", "invertiert"};
    }
    
    // Convert each of those value names into the actual value.
    @Override
    public void setAsText(String s) {
        
        switch(s){
            case "normal":
                setValue(SimpleLabelConstants.NORMAL);
                break;
                
            case "invertiert":
                setValue(SimpleLabelConstants.INVERT);
                break;
                
            default:
                throw new IllegalArgumentException(s);
        }
    }
       
    // This is an important method for code generation.
    @Override
    public String getJavaInitializationString() {
        return "\"" + getValue().toString() + "\"";
    }
}