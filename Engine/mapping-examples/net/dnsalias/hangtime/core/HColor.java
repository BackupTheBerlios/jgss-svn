package net.dnsalias.hangtime.core;

import java.awt.Color;
import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class HColor implements Serializable {

    /**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/** nullable persistent field */
    private Integer red;

    /** nullable persistent field */
    private Integer green;

    /** nullable persistent field */
    private Integer blue;

    /** full constructor */
    public HColor(Integer red, Integer green, Integer blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /** default constructor */
    public HColor() {
    }

    public Integer getRed() {
        return this.red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }

    public Integer getGreen() {
        return this.green;
    }

    public void setGreen(Integer green) {
        this.green = green;
    }

    public Integer getBlue() {
        return this.blue;
    }

    public void setBlue(Integer blue) {
        this.blue = blue;
    }
    public Color toAWTColor(){
        return new Color(red,green,blue);
    }
}
