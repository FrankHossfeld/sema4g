/*
 * Copyright 2015 Frank Hossfeld
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.gwt4e.sema4g.client.commands;

import java.util.ArrayList;
import java.util.List;

import org.gwt4e.sema4g.client.SeMa4g;
import org.gwt4e.sema4g.client.SeMa4gUtils;
import org.gwt4e.sema4g.client.exceptions.SeMa4gException;

/**
 *<p>AbstractCommand provides base functionality to run a SeMa4g command.</p>
 */
public abstract class AbstractCommand
  implements SeMa4gCommand {

  /* Unique ID of the servercall */
  private final String              id;
  /* State of the server call */
  private       State               state;
  /* execution context of this action */
  private       SeMa4g              executionContext;
  /* dependencies */
  private       List<SeMa4gCommand> dependencies;

  protected AbstractCommand() {
    // set state
    this.state = State.WAITING;
    // create lists
    this.dependencies = new ArrayList<>();
    // get ID
    id = SeMa4gUtils.getNextId();
  }

  String getId() {
    return id;
  }

  /**
   * Returns the list of {@link SeMa4gCommand} for this {@link SeMa4gCommand}
   * which have to be (successfully) executed before this command can be started
   *
   * @return list of depending commands
   */
  public List<SeMa4gCommand> getDependencies() {
    return dependencies;
  }

  /**
   * Returns the {@link SeMa4g} for this command.
   *
   * @return The {@link SeMa4g} for this command
   */
  public SeMa4g getExecutionContext() {
    return this.executionContext;
  }

  /**
   * Returns the current {@link SeMa4gCommand.State}
   *
   * @return current state of the command
   */
  public State getState() {
    return state;
  }

  /**
   * Sets the {@link SeMa4g} for this command.
   *
   * @param executionContext the {@link SeMa4g} for this command
   */
  public void setExecutionContext(SeMa4g executionContext) {
    if (executionContext != null) {
      this.executionContext = executionContext;
    }
  }

  /**
   * Sets the {@link SeMa4gCommand.State}
   * of the command
   *
   * @param state the new state
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * Definies the {@link SeMa4gCommand} (one or more) which have to
   * be finished before this command can be executed.
   *
   * @param dependencies list of commands which have to be finished,
   *                     before this command can be started
   * @return the inistnace of the command
   * @throws SeMa4gException when a cycle dependency is detected
   */
  public SeMa4gCommand dependingOn(SeMa4gCommand... dependencies)
    throws SeMa4gException {
    // add dependencies
    for (SeMa4gCommand dependency : dependencies) {
      this.getDependencies()
          .add(dependency);
    }
    // check weather there are cycle dependencies or not
    startCheckCycleDependencies();
    // return context
    return this;
  }

  /**
   * Checks if there is a cycle dependencies
   *
   * @param usedDependencies all dependencies for this command
   * @throws SeMa4gException when a cycle dependency is detected
   */
  public void checkCycleDependencies(List<SeMa4gCommand> usedDependencies)
    throws SeMa4gException {
    for (int i = 0; i < this.getDependencies()
                            .size(); i++) {
      SeMa4gCommand dependency = this.getDependencies()
                                     .get(i);
      if (usedDependencies.contains(dependency)) {
        throw new SeMa4gException("cycle dependency!");
      } else if (dependency == this) {
        throw new SeMa4gException("you can not depend on your own command!");
      }
      usedDependencies.add(dependency);
      dependency.checkCycleDependencies(usedDependencies);
    }
  }

  /**
   * this method is called to reset the command
   */
  public void reset() {
    this.setState(State.WAITING);
  }

  /**
   * This method is used by the execution context to start the command
   */
  public void run() {
    // update state
    this.state = State.RUNNING;
    // Execute the command
    this.execute();
  }

  /**
   * starts a cycle dependency check
   *
   * @throws SeMa4gException
   */
  public void startCheckCycleDependencies()
    throws SeMa4gException {
    List<SeMa4gCommand> usedDependencies = new ArrayList<>();
    this.checkCycleDependencies(usedDependencies);
  }

  /**
   * This command can be called to interrupt the execution and
   * start the error behavior.
   */
  public void signalError() {
    this.state = State.ERROR;
    executionContext.signalError();
  }

  /**
   * This command can be called to finish an execution and
   * start the next one.
   */
  public void signalFinish() {
    this.state = State.FINISH;
    executionContext.signalFinish();
  }

  /**
   * Use this method to implement the execution code.
   * <br><br>
   * This method is ionvoked by the framework.
   */
  public abstract void execute();

}
