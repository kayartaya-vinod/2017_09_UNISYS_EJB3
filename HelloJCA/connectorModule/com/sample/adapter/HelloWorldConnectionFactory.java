package com.sample.adapter;

import java.io.Serializable;

import javax.resource.Referenceable;
import javax.resource.ResourceException;

public interface HelloWorldConnectionFactory extends Serializable, Referenceable {
	
	public HelloWorldConnection getConnection() throws ResourceException;

}