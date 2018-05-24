package com.sample.adapter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

public class HelloWorldManagedConnection implements ManagedConnection {
	private HelloWorldManagedConnectionFactory mcf;

	private PrintWriter logWriter;

	private List<ConnectionEventListener> listeners;

	private Object connection;

	public HelloWorldManagedConnection(HelloWorldManagedConnectionFactory mcf) {
		this.mcf = mcf;
		this.logWriter = null;
		this.listeners = new ArrayList<ConnectionEventListener>(1);
		this.connection = null;
	}

	/**
	 * Creates a new connection handle for the underlying physical connection
	 * represented by the ManagedConnection instance.
	 */
	@Override
	public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
		connection = new HelloWorldConnectionImpl(this, mcf);

		return connection;
	}

	/**
	 * Used by the container to change the association of an application-level
	 * connection handle with a ManagedConneciton instance.
	 */
	@Override
	public void associateConnection(Object connection) throws ResourceException {
		this.connection = connection;
	}

	/**
	 * Application server calls this method to force any cleanup on the
	 * ManagedConnection instance.
	 */
	@Override
	public void cleanup() throws ResourceException {
	}

	/**
	 * Destroys the physical connection to the underlying resource manager.
	 */
	@Override
	public void destroy() throws ResourceException {
		this.connection = null;
	}

	/**
	 * Adds a connection event listener to the ManagedConnection instance.
	 */
	@Override
	public void addConnectionEventListener(ConnectionEventListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("Listener is null");

		listeners.add(listener);
	}

	/**
	 * Removes an already registered connection event listener from the
	 * ManagedConnection instance.
	 */
	@Override
	public void removeConnectionEventListener(ConnectionEventListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("Listener is null");

		listeners.remove(listener);
	}

	/**
	 * Gets the log writer for this ManagedConnection instance.
	 */
	@Override
	public PrintWriter getLogWriter() throws ResourceException {
		return logWriter;
	}

	/**
	 * Sets the log writer for this ManagedConnection instance.
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws ResourceException {
		this.logWriter = out;
	}

	/**
	 * Returns an <code>javax.resource.spi.LocalTransaction</code> instance.
	 */
	@Override
	public LocalTransaction getLocalTransaction() throws ResourceException {
		throw new NotSupportedException("LocalTransaction not supported");
	}

	/**
	 * Returns an javax.transaction.xa.XAresource instance.
	 */
	@Override
	public XAResource getXAResource() throws ResourceException {
		throw new NotSupportedException("GetXAResource not supported");
	}

	/**
	 * Gets the metadata information for this connection's underlying EIS
	 * resource manager instance.
	 */
	@Override
	public ManagedConnectionMetaData getMetaData() throws ResourceException {
		return new HelloWorldManagedConnectionMetaData();
	}

	String helloWorld(String name) {
		return "Hello World, " + name + " !";
	}

	void closeHandle(HelloWorldConnection handle) {
		ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
		event.setConnectionHandle(handle);

		for (ConnectionEventListener cel : listeners) {
			cel.connectionClosed(event);
		}
	}
}