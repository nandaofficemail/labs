package com.metlife.investments.cohesion.core;

/** MessageHandler is the single method interface used for Cohesion message handling
 *
 */
public interface MessageHandler
{
    void onMessage(EntityMessage em);
}
