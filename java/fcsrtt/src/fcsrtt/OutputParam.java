/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcsrtt;

/**
 *
 * @author rasty
 */
public class OutputParam {
    public char group;
    public int paramId;
    
    OutputParam(char group, int paramId) {
        this.group = group;
        this.paramId = paramId;
    }
    
    public void set(char group, int paramId) {
        this.group = group;
        this.paramId = paramId;
    }
}
