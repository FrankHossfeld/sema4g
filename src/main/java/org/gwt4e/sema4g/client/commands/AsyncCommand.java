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

/*
 * Copyright 2014 Frank Hossfeld
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

import org.gwt4e.sema4g.client.commands.helper.AbstractCommand;
import org.gwt4e.sema4g.client.commands.helper.SeMa4gCommand;

/**
 * <p>A asynchronous command to use with SeMa4g.</p>
 */
public abstract class AsyncCommand
  extends AbstractCommand {

  public AsyncCommand() {
    super();
  }

  public void onSuccess() {
    // update state
    super.setState(State.FINISH);
    // trigger execution context
    super.getExecutionContext()
         .trigger();
  }

  @SuppressWarnings("unused")
  public void onFailure(Throwable caught) {
    // update state
    super.setState(SeMa4gCommand.State.ERROR);
  }
}
