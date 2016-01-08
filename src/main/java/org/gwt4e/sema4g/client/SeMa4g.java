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

package org.gwt4e.sema4g.client;

import java.util.ArrayList;
import java.util.List;

import org.gwt4e.sema4g.client.commands.FinalCommand;
import org.gwt4e.sema4g.client.commands.InitCommand;
import org.gwt4e.sema4g.client.commands.SyncCommand;
import org.gwt4e.sema4g.client.commands.helper.SeMa4gCommand;
import org.gwt4e.sema4g.client.exceptions.SeMa4gException;

import com.google.gwt.core.client.GWT;

/**
 * <p>The execution context manages all commands.<br>Use the run()-method to start execution</p>
 */
public class SeMa4g {

  /* state of the context */
  private State state;

  /* list of initial commands to be executed */
  private List<InitCommand>   initialCommands;
  /* list of final commands to be executed */
  private List<FinalCommand>  finalCommands;
  /* list auf calls to be executed */
  private List<SeMa4gCommand> seMa4gCommands;

  private SeMa4g(Builder builder) {
    this.state = State.WAITING;

    this.initialCommands = builder.initialCommands;
    this.finalCommands = builder.finalCommands;
    this.seMa4gCommands = builder.seMa4gCommands;

    // set execution context reference
    for (int i = 0; i < seMa4gCommands.size(); i++) {
      seMa4gCommands.get(i).setExecutionContext(this);

    }
  }

  public static final Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    static List<InitCommand>   initialCommands = new ArrayList<>();
    static List<FinalCommand>  finalCommands   = new ArrayList<>();
    static List<SeMa4gCommand> seMa4gCommands  = new ArrayList<>();

    /**
     * Adds the command to the list of commands, which will be executed, when the context runs. <br></br> if the command is already added to the list of commands, the command will be ignored.
     *
     * @param command
     *   command that should be added to the list of executable commands
     * @return executionContext
     */
    public Builder add(SeMa4gCommand command) {
      // create server call object
      if (!isAlreadyAddedToList(command)) {
        seMa4gCommands.add(command);
      }
      // return context
      return this;
    }

    /**
     * <p>Adds a {@link FinalCommand} to the SeMa4g.</p>
     *
     * @param finalCommand
     *   FinalCommand
     * @return the SeMa4g
     */
    public Builder addFinalCommand(FinalCommand finalCommand) {
      // add inital command
      this.finalCommands.add(finalCommand);
      // return context
      return this;
    }

    /**
     * <p>Adds a {@link InitCommand} to the SeMa4g.</p>
     *
     * @param initCommand
     *   InitCommand
     * @return the SeMa4g
     */
    public Builder addInitCommand(InitCommand initCommand) {
      // add initial command
      this.initialCommands.add(initCommand);
      // return context
      return this;
    }

    public SeMa4g build() {
      return new SeMa4g(this);
    }

    private boolean isAlreadyAddedToList(SeMa4gCommand command) {
      for (SeMa4gCommand seMa4gCommand : this.seMa4gCommands) {
        if (seMa4gCommand == command) {
          return true;
        }
      }
      return false;
    }
  }

  public State getState() {
    return state;
  }

  /**
   * <p>Use this method to invoke all commands</p> <p>Make sure, that this method is called only once.</p>
   */
  public void run() throws SeMa4gException {
    // start only if not running!
    if (State.WAITING.equals(getState())) {
      // set state
      this.state = State.RUNNING;
      // check dependencies
      try {
        this.checkDependencies();
      } catch (SeMa4gException e) {
        GWT.debugger();
        e.printStackTrace();
        throw e;
      }
      // execute all init-commands
      this.executeInitCommands();
      // start the coomand calls
      this.executeRun();
    } else {
      assert false : "context already running!";
    }
  }

  /**
   * <p>This method is used by the framework. After a command is finished this method will be called by the command to initiate the start of these commands, which depend on the execution of the
   * currently finished command.</p>
   */
  public void trigger() {
    // update state of execution context
    this.updateState();
    // trigger another command
    executeRun();
  }

  /**
   * <p>This method will set the state to 'CANCELED' for all commands, that have not already started (state is 'WAITING').</p>
   */
  public void cancel() {
    // set state
    this.state = State.ERROR;
    // cancel all not already started commands
    for (SeMa4gCommand command : this.seMa4gCommands) {
      if (SeMa4gCommand.State.WAITING.equals(command.getState())) {
        command.setState(SeMa4gCommand.State.CANCELED);
      }
    }
    // trigger another command
    executeRun();
  }

  /**
   * Resets all commands of the execution context
   */
  public void reset() {
    for (SeMa4gCommand command : this.seMa4gCommands) {
      command.reset();
    }
  }

  private void checkDependencies() throws SeMa4gException {
    for (SeMa4gCommand command : this.seMa4gCommands) {
      command.startCheckCycleDependencies();
    }
  }

  private void executeRun() {
    // execeute all final commands
    if (State.FINISH.equals(this.state) || State.ERROR.equals(this.state)) {
      // is there currently a running command?
      // true -> quit!
      for (SeMa4gCommand seMa4gCommand : this.seMa4gCommands) {
        if (seMa4gCommand.getState()
                         .equals(SeMa4gCommand.State.RUNNING)) {
          return;
        }
      }
      this.executeFinalCommands();
    } else {
      // execute all commands with no dependency
      for (SeMa4gCommand command : this.seMa4gCommands) {
        if (SeMa4gCommand.State.WAITING.equals(command.getState())) {
          if (this.hasNoDependency(command)) {
            command.run();
            // in case we have an SeMa4gSyncCommand, we can trigger
            // the updateState()-method
            if (command instanceof SyncCommand) {
              trigger();
            }
          }
        }
      }
    }
  }

  private void executeFinalCommands() {
    for (FinalCommand finalCommand : this.finalCommands) {
      if (State.ERROR.equals(this.state)) {
        finalCommand.onFailure();
      } else {
        finalCommand.onSuccess();
      }
    }
  }

  private void executeInitCommands() {
    for (InitCommand initialCommand : this.initialCommands) {
      initialCommand.onStart();
    }
  }

  /**
   * This methods checks weather a command has dependencies or not.
   *
   * @param command
   *   to be executed
   * @return true -> command has no dependies
   */
  private boolean hasNoDependency(SeMa4gCommand command) {
    for (int i = 0; i < command.getDependencies()
                               .size(); i++) {
      SeMa4gCommand dependency = command.getDependencies()
                                        .get(i);
      if (SeMa4gCommand.State.WAITING.equals(dependency.getState())) {
        return false;
      } else if (SeMa4gCommand.State.RUNNING.equals(dependency.getState())) {
        return false;
      }
    }
    return true;
  }

  private void updateState() {
    for (SeMa4gCommand seMa4gCommand : this.seMa4gCommands) {
      if (seMa4gCommand.getState()
                       .equals(SeMa4gCommand.State.WAITING) || seMa4gCommand.getState()
                                                                            .equals(SeMa4gCommand.State.RUNNING)) {
        this.state = State.RUNNING;
        return;
      } else if (seMa4gCommand.getState()
                              .equals(SeMa4gCommand.State.ERROR)) {
        this.state = State.ERROR;
        return;
      }
    }
    this.state = State.FINISH;
  }

  /**
   * state of the execution context
   */
  public enum State {
    WAITING,
    RUNNING,
    FINISH,
    ERROR
  }
}
