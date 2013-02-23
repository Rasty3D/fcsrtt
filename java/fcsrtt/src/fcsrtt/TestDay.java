/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fcsrtt;

/**
 *
 * @author rasty
 */
public class TestDay {
    public String dateStart;
    public String dateEnd;
    public int daySeq;
    public Session session[];
    
    public TestDay() {
        session = new Session[3];
        session[0] = new Session();
        session[1] = new Session();
        session[2] = new Session();
    }
}
