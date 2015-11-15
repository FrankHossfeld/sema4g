package org.gwt4e.sema4g.client;

import org.gwt4e.sema4g.client.context.ExecutionContext;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SeMa4g {

  /**
   * <p>Creates a new {@link ExecutionContext}</p>
   * <br><br>
   *
   * @return new {@link ExecutionContext}
   */
  public static ExecutionContext getNewExecutionContext() {
    return new ExecutionContext();
  }
}
