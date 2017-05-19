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
package de.gishmo.gwt.sema4g.client;

/**
 * <p>Utilities for sema4g</p>
 */
public class SeMa4gUtils {
  /* last created id */
  private static int id = 0;

  /**
   * <p>Get the next unique id.</p>
   *
   * @return next unique version number
   */
  public static String getNextId() {
    return Integer.toString(++id);
  }

}
