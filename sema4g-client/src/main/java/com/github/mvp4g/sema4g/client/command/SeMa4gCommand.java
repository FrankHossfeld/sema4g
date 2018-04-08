/*
 * Copyright (c) 2017 - 2018 - Frank Hossfeld
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
package com.github.mvp4g.sema4g.client.command;

import com.github.mvp4g.sema4g.client.SeMa4g;
import com.github.mvp4g.sema4g.client.exception.SeMa4gException;

import java.util.List;

/**
 * This interface provide all needed methods to interact with the
 * {@link SeMa4g}
 */
public interface SeMa4gCommand {

  /**
   * Checks if there is a cycle dependencies for this command.
   *
   * @param usedDependencies all dependencies for this command
   * @throws SeMa4gException when a cycle dependency is detected
   */
  void checkCycleDependencies(List<SeMa4gCommand> usedDependencies)
    throws SeMa4gException;

  /**
   * Definies the {@link SeMa4gCommand} (one or more) which have to
   * be finished before this command can be executed.
   *
   * @param dependencies list of commands which have to be finished,
   *                     before this command can be started
   * @return the inistnace of the command
   * @throws SeMa4gException when a cycle dependency is detected
   */
  SeMa4gCommand dependingOn(SeMa4gCommand... dependencies)
    throws SeMa4gException;

  /**
   * Returns the list of {@link SeMa4gCommand} for this {@link SeMa4gCommand}
   * which have to be (successfully) executed before this command can be started
   *
   * @return list of depending commands
   */
  List<SeMa4gCommand> getDependencies();

  /**
   * Returns the {@link SeMa4g} for this command.
   *
   * @return The {@link SeMa4g} for this command
   */
  SeMa4g getExecutionContext();

  /**
   * Sets the {@link SeMa4g} for this command.
   *
   * @param executionContext the {@link SeMa4g} for this command
   */
  void setExecutionContext(SeMa4g executionContext);

  /**
   * Returns the current {@link State}
   *
   * @return current state of the command
   */
  State getState();

  /**
   * Sets the {@link State}
   * of the command
   *
   * @param state the new state
   */
  void setState(State state);

  /**
   * This command can be called to interrupt the execution and
   * start the error behavior.
   */
  void signalError();

  /**
   * This method is used by the execution context to start the command
   */
  void run();

  /**
   * starts a cycle dependency check
   *
   * @throws SeMa4gException in case there is a cycle dependency
   */
  void startCheckCycleDependencies()
    throws SeMa4gException;

  /**
   * this method is called to reset the command
   */
  void reset();

  /**
   * state of the execution context
   */
  enum State {
    WAITING,
    RUNNING,
    CANCELED,
    FINISH,
    ERROR
  }
}
