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

public class SeMa4gConstants {

  public final static String ERROR_NO_FINAL_COMMAND = "SeMa4g validation: no final command defined";

  public final static String ERROR_CYCLE_DEPENDENCIES_DETECTED  = "SeMa4g validation: cycle dependencies detected";
  public final static String ERROR_CYCLE_DEPENDS_ON_OWN_COMMAND = "SeMa4g validation: a command can not depend on itself";

}
