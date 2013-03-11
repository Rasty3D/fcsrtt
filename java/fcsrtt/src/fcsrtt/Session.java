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
public class Session {
    private static final int SIZE = 26;
    private int size[] = new int[Session.SIZE];
    private int offset[] = new int[Session.SIZE];
    private List<Float> params = new ArrayList<>();
    
    public String timeStart;
    public String timeEnd;
    
    public Session() {
        for (int i = 0; i < SIZE; i++) {
            size[i] = 0;
            offset[i] = 0;
        }
        
        params.clear();
    }
    
    public int getSize(char group) {
       int groupIdx = group - 'A';
        
        if (groupIdx < 0 || groupIdx >= Session.SIZE) {
            return -1;
        }
        else {
            return size[groupIdx];
        }
    }
    
    public int getOffset(char group) {
        int groupIdx = group - 'A';
        
        if (groupIdx < 0 || groupIdx >= Session.SIZE) {
            return -1;
        }
        else {
            return offset[groupIdx];
        }
    }
    
    public float getParam(char group, int index) {
        int groupIdx = group - 'A';
        
        if (groupIdx < 0 || groupIdx >= Session.SIZE) {
            return -1.0f;
        }
        else if (index < 0 || index >= size[groupIdx]) {
            return -1.0f;
        }
        else {
            return params.get(offset[groupIdx] + index);
        }
    }
    
    public boolean getParams(char group, float params[]) {
        int groupIdx = group - 'A';
        
        if (groupIdx < 0 || groupIdx >= Session.SIZE) {
            return false;
        }
        else {
            System.arraycopy(
                this.params.toArray(), offset[groupIdx],
                params, 0,
                size[groupIdx]);
            return true;
        }
    }
    
    public boolean setParam(char group, int index, float value) {    
        int groupIdx = group - 'A';
        
        if (groupIdx < 0 || groupIdx >= Session.SIZE) {
            return false;
        }
        else {
            if (this.offset[groupIdx] == 0) {
                this.offset[groupIdx] = this.params.size();
                this.size[groupIdx] = 0;
            }
            
            while (this.params.size() < this.offset[groupIdx] + index + 1) {
                this.params.add(0.0f);
            }
                
            this.size[groupIdx] = this.params.size() - this.offset[groupIdx];
            this.params.set(this.offset[groupIdx] + index, value);
            return true;
        }
    }
    
    public boolean setParams(char group, float params[]) {
        int groupIdx = group - 'A';
        
        if (groupIdx < 0 || groupIdx >= Session.SIZE) {
            return false;
        }
        else {
            if (this.offset[groupIdx] == 0) {
                this.offset[groupIdx] = this.params.size();
                this.size[groupIdx] = 0;
            }
            
            while (this.params.size() < this.offset[groupIdx] + params.length + 1) {
                this.params.add(0.0f);
            }
                
            this.size[groupIdx] = this.params.size() - this.offset[groupIdx];
            System.arraycopy(
                params, 0,
                this.params.toArray(), offset[groupIdx],
                size[groupIdx]);
            return true;
        }
    }
}
