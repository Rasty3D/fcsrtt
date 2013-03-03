/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcsrtt;

/**
 *
 * @author rasty
 */
public class Session {
    private static int size[] = new int[26];
    private static int offset[] = new int[26];
    private float params[];
    
    public String timeStart;
    public String timeEnd;
    
    public Session() {
        size[0] = 6;
        size[1] = 10;
        size[2] = 15;
        size[3] = 15;
        size[4] = 15;
        size[5] = 8;
        size[6] = 8;
        size[7] = 8;
        size[8] = 1;
        size[9] = 1;
        size[10] = 2200;
        size[11] = 1;
        size[12] = 6;
        size[13] = 5;
        size[14] = 30;
        size[15] = 1;
        size[16] = 1;
        size[17] = 1;
        size[18] = 1;
        size[19] = 1;
        size[20] = 1;
        size[21] = 1;
        size[22] = 4;
        size[23] = 3;
        size[24] = 1;
        size[25] = 3;
        
        offset[0] = 0;
        
        for (int i = 1; i < 26; i++) {
            offset[i] = offset[i - 1] + size[i - 1];
        }
        
        int totalSize = offset[25] + size[25];
        params = new float[totalSize];
    }
    
    public static int getSize(char group) {
        if (group < 'A' || group > 'Z') {
            return -1;
        }
        else {
            return size[group - 'A'];
        }
    }
    
    public static int getOffset(char group) {
        if (group < 'A' || group > 'Z') {
            return -1;
        }
        else {
            return offset[group - 'A'];
        }
    }
    
    public float getParam(char group, int index) {
        if (group < 'A' || group > 'Z') {
            return -1.0f;
        }
        else if (index < 0 || index >= size[group - 'A']) {
            return -1.0f;
        }
        else {
            return params[offset[group - 'A'] + index];
        }
    }
    
    public boolean getParams(char group, float params[]) {
        if (group < 'A' || group > 'Z') {
            return false;
        }
        else {
            System.arraycopy(
                this.params, offset[group - 'A'], params, 0, size[group - 'A']);
            return true;
        }
    }
    
    public boolean setParam(char group, int index, float value) {
        if (group < 'A' || group > 'Z') {
            return false;
        }
        else if (index < 0 || index >= size[group - 'A']) {
            return false;
        }
        else {
            params[offset[group - 'A'] + index] = value;
            return true;
        }
    }
    
    public boolean setParams(char group, float params[]) {
        if (group < 'A' || group > 'Z') {
            return false;
        }
        else {
            System.arraycopy(
                params, 0, this.params, offset[group - 'A'], size[group - 'A']);
            return true;
        }
    }
}
