package com.metlife.investments.cohesion.core;

/** Listener is an interface to a specific instance of a object that listens for messages
 *  or events on a queue or a topic
 *  The only thing that can be done to a Listener is to stopListening  
 */

public interface Listener 
{
    void stopListening();
}
