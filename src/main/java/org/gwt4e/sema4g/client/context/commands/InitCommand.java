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
package org.gwt4e.sema4g.client.context.commands;

/**
 * <p>This command allows to execute statements in case
 * a execution context is started.</p>
 */
public interface InitCommand {

  /**
   * <p>This method will be called in case
   * the related execution context is started</p>
   */
  void onStart();

}