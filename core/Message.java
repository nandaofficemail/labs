package com.metlife.investments.cohesion.core;

/** Message is an interface to a generic Cohesion message object
 *
 */
public interface Message
{
    /** sets the text of a Message instance
     * 
     * @param string the text content of message
     */
    void setText(String string);
    
    /** gets the String content of this message
     * 
     * @return the String content
     */
    String getText();
}
