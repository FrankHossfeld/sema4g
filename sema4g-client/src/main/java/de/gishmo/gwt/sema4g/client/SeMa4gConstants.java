package de.gishmo.gwt.sema4g.client;

public class SeMa4gConstants {

  public final static String ERROR_NO_COMMAND       = "SeMa4g validation: at least one SeMa4gCommand should be defined";
  public final static String ERROR_NO_FINAL_COMMAND = "SeMa4g validation: no final command defined";
  public final static String ERROR_NO_INIT_COMMAND  = "SeMa4g validation: no init command defined";

  public final static String ERROR_CYCLE_DEPENDENCIES_DETECTED  = "SeMa4g validation: cycle dependencies detected";
  public final static String ERROR_CYCLE_DEPENDS_ON_OWN_COMMAND = "SeMa4g validation: a command can not depend on itself";

}
