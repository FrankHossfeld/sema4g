/*
 * Copyright 2015-2017 Frank Hossfeld
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
package com.github.mvp4g.sema4g.client;

import com.github.mvp4g.sema4g.client.command.FinalCommand;
import com.github.mvp4g.sema4g.client.command.InitCommand;
import com.github.mvp4g.sema4g.client.command.SeMa4gCommand;
import com.github.mvp4g.sema4g.client.command.SyncCommand;
import com.github.mvp4g.sema4g.client.exception.SeMa4gException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>The execution context manages all commands.<br>
 * Use run()-method to start execution.</p>
 */
public class SeMa4g {

  /* state of the context */
  private State state;

  /* list of initial commands to be executed */
  private List<InitCommand>   initCommands;
  /* list of final commands to be executed */
  private List<FinalCommand>  finalCommands;
  /* list auf calls to be executed */
  private List<SeMa4gCommand> seMa4gCommands;

  private SeMa4g() {
  }

  private SeMa4g(Builder builder) {
    this.state = State.WAITING;

    initCommands = new ArrayList<>();
    initCommands.addAll(builder.initialCommands);

    finalCommands = new ArrayList<>();
    finalCommands.addAll(builder.finalCommands);

    seMa4gCommands = new ArrayList<>();
    seMa4gCommands.addAll(builder.seMa4gCommands);
    // set execution context reference
    for (int i = 0; i < seMa4gCommands.size(); i++) {
      seMa4gCommands.get(i)
                    .setExecutionContext(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  public State getState() {
    return state;
  }

  /**
   * <p>Use this method to invoke all commands</p> <p>Make sure, that this method is called only once.</p>
   */
  public void run()
    throws SeMa4gException {
    // runs some validation
    this.validateContext();
    // start only if not running!
    if (State.WAITING.equals(getState())) {
      // set state
      this.state = State.RUNNING;
      // check dependencies
      try {
        this.checkDependencies();
      } catch (SeMa4gException e) {
        e.printStackTrace();
        throw e;
      }
      // execute all init-commands
      this.executeInitCommands();
      // check, if there are any commands to execute.
      // if not, set the state to finish!
      if (this.seMa4gCommands.size() == 0) {
        // no commands to execute ==> set state to finish!
        this.state = State.FINISH;
      }
      // start the coomand calls
      this.executeRun();
    } else {
      assert false : "context already running!";
    }
  }

  private void validateContext()
    throws SeMa4gException {
    // are there at least one FinalCommad?
    if (this.finalCommands.size() < 1) {
      throw new SeMa4gException(SeMa4gConstants.ERROR_NO_FINAL_COMMAND);
    }
  }

  /**
   * <p>This method is used by the framework. After a command is finished,
   * this method will be called by the command to initiate the start commands, which
   * depend on the execution of the currently finished command.</p>
   */
  public void trigger() {
    // update state of execution context
    this.updateState();
    // trigger another command
    executeRun();
  }

  /**
   * This command can be called to interrupt the execution and
   * start the error behavior.
   */
  public void signalError() {
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
   * This command can be called to finish an execution and
   * start the next one.
   */
  public void signalFinish() {
    // set state
    this.state = State.FINISH;
    // trigger another command
    executeRun();
  }

  /**
   * Resets all commands of the execution context
   */
  public void reset() {
    this.state = State.WAITING;
    for (SeMa4gCommand command : this.seMa4gCommands) {
      command.reset();
    }
  }

  private void checkDependencies()
    throws SeMa4gException {
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
    for (InitCommand initialCommand : this.initCommands) {
      initialCommand.onStart();
    }
  }

  /**
   * This methods checks weather a command has dependencies or not.
   *
   * @param command to be executed
   * @return true -> command has no dependies
   */
  private boolean hasNoDependency(SeMa4gCommand command) {
    for (int i = 0; i <
                    command.getDependencies()
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
    if (SeMa4gCommand.State.ERROR.equals(this.state)) {
      return;
    }

    for (SeMa4gCommand seMa4gCommand : this.seMa4gCommands) {
      if (seMa4gCommand.getState()
                       .equals(SeMa4gCommand.State.ERROR)) {
        this.state = State.ERROR;
        return;
      } else if (seMa4gCommand.getState()
                              .equals(SeMa4gCommand.State.WAITING) ||
                 seMa4gCommand.getState()
                              .equals(SeMa4gCommand.State.RUNNING)) {
        this.state = State.RUNNING;
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

  public static final class Builder {
    List<InitCommand>   initialCommands = new ArrayList<>();
    List<FinalCommand>  finalCommands   = new ArrayList<>();
    List<SeMa4gCommand> seMa4gCommands  = new ArrayList<>();

    /**
     * Adds the command to the list of commands, which will be executed, when the context runs. <br></br> if the command is already added to the list of commands, the command will be ignored.
     *
     * @param command command that should be added to the list of executable commands
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

    private boolean isAlreadyAddedToList(SeMa4gCommand command) {
      for (SeMa4gCommand seMa4gCommand : this.seMa4gCommands) {
        if (seMa4gCommand == command) {
          return true;
        }
      }
      return false;
    }

    /**
     * Adds a list of commands to the list of already added commands, which will be executed, when the context runs. <br></br> if the command is already added to the list of commands, the command will be ignored.
     *
     * @param commands list of command that should be added to the list of executable commands
     * @return executionContext
     */
    public Builder add(List<SeMa4gCommand> commands) {
      for (SeMa4gCommand command : commands) {
        seMa4gCommands.add(command);
      }
      // return context
      return this;
    }

    /**
     * <p>Adds a {@link FinalCommand} to the SeMa4g.</p>
     *
     * @param finalCommand FinalCommand
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
     * @param initCommand InitCommand
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
  }
}
