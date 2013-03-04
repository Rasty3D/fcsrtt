/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcsrtt;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasty
 */
public class OutputGroup {
    public List<OutputParam> outputParams = new ArrayList<>();
    
    public void clear() {
        outputParams.clear();
    }
    
    public void add(char group, int paramId) {
        OutputParam outputParam = new OutputParam(group, paramId);
        outputParams.add(outputParam);
    }
}
