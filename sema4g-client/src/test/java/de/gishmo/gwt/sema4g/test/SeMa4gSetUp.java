package de.gishmo.gwt.sema4g.test;

import org.junit.Test;

import de.gishmo.gwt.sema4g.client.SeMa4g;
import de.gishmo.gwt.sema4g.client.SeMa4gConstants;
import de.gishmo.gwt.sema4g.client.command.SeMa4gCommand;
import de.gishmo.gwt.sema4g.client.exception.SeMa4gException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SeMa4gSetUp
  extends AbstractSeMa4g {

  @Test
  public void noCommands() {
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .build();
      semagContext.run();
      fail("no exception fired!");
    } catch (SeMa4gException e) {
      assertEquals(e.getMessage(),
                   SeMa4gConstants.ERROR_NO_INIT_COMMAND);
    }
  }

  @Test
  public void noInitCommand() {
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .addFinalCommand(super.createFinalCommand())
                                  .build();
      semagContext.run();
      fail("no exception fired!");
    } catch (SeMa4gException e) {
      assertEquals(e.getMessage(),
                   SeMa4gConstants.ERROR_NO_INIT_COMMAND);
    }
  }

  @Test
  public void noFinalCommand() {
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .addInitCommand(super.createInitCommand())
                                  .build();
      semagContext.run();
      fail("no exception fired!");
    } catch (SeMa4gException e) {
      assertEquals(e.getMessage(),
                   SeMa4gConstants.ERROR_NO_FINAL_COMMAND);
    }
  }

  @Test
  public void noCommand() {
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .addInitCommand(super.createInitCommand())
                                  .addFinalCommand(super.createFinalCommand())
                                  .build();
      semagContext.run();
      fail("no exception fired!");
    } catch (SeMa4gException e) {
      assertEquals(e.getMessage(),
                   SeMa4gConstants.ERROR_NO_COMMAND);
    }
  }

  @Test
  public void testCycleDependencies01() {
    SeMa4gCommand command01 = super.createSyncCommand(500);
    SeMa4gCommand command02 = super.createSyncCommand(1250);
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .addInitCommand(super.createInitCommand())
                                  .addFinalCommand(super.createFinalCommand())
                                  .add(command01.dependingOn(command02))
                                  .add(command02.dependingOn(command01))
                                  .build();
      semagContext.run();
      fail("no exception fired!");
    } catch (SeMa4gException e) {
      assertEquals(e.getMessage(),
                   SeMa4gConstants.ERROR_CYCLE_DEPENDENCIES_DETECTED);
    }
  }

  @Test
  public void testCycleDependencies02() {
    SeMa4gCommand command01 = super.createSyncCommand(500);
    SeMa4gCommand command02 = super.createSyncCommand(1250);
    SeMa4gCommand command03 = super.createSyncCommand(750);
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .addInitCommand(super.createInitCommand())
                                  .addFinalCommand(super.createFinalCommand())
                                  .add(command01.dependingOn(command03))
                                  .add(command02)
                                  .add(command03.dependingOn(command01))
                                  .build();
      semagContext.run();
      fail("no exception fired!");
    } catch (SeMa4gException e) {
      assertEquals(e.getMessage(),
                   SeMa4gConstants.ERROR_CYCLE_DEPENDENCIES_DETECTED);
    }
  }

  @Test
  public void testCycleDependencies03() {
    SeMa4gCommand command01 = super.createSyncCommand(500);
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .addInitCommand(super.createInitCommand())
                                  .addFinalCommand(super.createFinalCommand())
                                  .add(command01.dependingOn(command01))
                                  .build();
      semagContext.run();
      fail("no exception fired!");
    } catch (SeMa4gException e) {
      assertEquals(e.getMessage(),
                   SeMa4gConstants.ERROR_CYCLE_DEPENDS_ON_OWN_COMMAND);
    }
  }

  @Test
  public void testOk01() {
    SeMa4gCommand command01 = super.createSyncCommand(500);
    SeMa4gCommand command02 = super.createSyncCommand(1250);
    SeMa4gCommand command03 = super.createSyncCommand(750);
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .addInitCommand(super.createInitCommand())
                                  .addFinalCommand(super.createFinalCommand())
                                  .add(command01)
                                  .add(command02)
                                  .add(command03)
                                  .build();
      semagContext.run();
    } catch (SeMa4gException e) {
      fail("exception fired in a test case, that does not expect an exception!");
    }
  }

  @Test
  public void testOk02() {
    SeMa4gCommand command01 = super.createSyncCommand(500);
    SeMa4gCommand command02 = super.createSyncCommand(1250);
    SeMa4gCommand command03 = super.createSyncCommand(750);
    try {
      SeMa4g semagContext = SeMa4g.builder()
                                  .addInitCommand(super.createInitCommand())
                                  .addFinalCommand(super.createFinalCommand())
                                  .add(command01.dependingOn(command03))
                                  .add(command02)
                                  .add(command03.dependingOn(command02))
                                  .build();
      semagContext.run();
    } catch (SeMa4gException e) {
      fail("exception fired in a test case, that does not expect an exception!");
    }
  }

//  @Test
//  public void noInitCommand() {
//    try {
//      SeMa4g semagContext = SeMa4g.builder().addInitCommand(createInitCommand())
//                                  .addFinalCommand(createFinalCommand())
//                                  .add(createGetRisikoFragenCommand(Collections.singletonList(new RisikoFragenRequestModel(taaClientContext.getAntrag()
//                                                                                                                                           .getBetriebsHaftpflichtVersicherung()
//                                                                                                                                           .getProdukt(),
//                                                                                                                           taaClientContext.getAntrag()
//                                                                                                                                           .getTaetigkeiten()
//                                                                                                                                           .getAlleTaetigkeiten(),
//                                                                                                                           null,
//                                                                                                                           taaClientContext.getAntrag()
//                                                                                                                                           .getBetriebsHaftpflichtVersicherung()
//                                                                                                                                           .getRisikofragen(),
//                                                                                                                           ModelUtilities.getGenerationsBestimmungsDatum(taaClientContext.getAntrag()),
//                                                                                                                           null,
//                                                                                                                           taaClientContext.getAntrag()
//                                                                                                                                           .getGeVoType())))
//                                         .add(createInitApplicationCommand().dependingOn(commandGetApplicationContext));
//      //                                              .add(createCommandGetToolTips().dependingOn(commandGetApplicationContext));
//      if (taaClientContext.getVorgangsKontextIdModel() != null) {
//        semagLoadContext.add(createVorgangsKontextGetCommand().dependingOn(commandGetApplicationContext));
//      } else {
//        // fuer einen Tarifrechner ist ein Vorgangskontext zu erstellen, da
//        // dieser (noch) keinen besitzt.
//        semagLoadContext.add(createVorgangsKontextCreateCommand().dependingOn(commandGetApplicationContext));
//
//        // Im TR Fall können noch diese drei Parameter per URL übergeben worden sein. 'Token' enthält die VD und ggf. eine Email-Adresse.
//        // Diese Werte sollen in den Antrag vorausgefüllt werden, wenn vorhanden.
//        // Das Token muss dafür an den Server übertragen werden, damit dieses decodiert werden kann.
//        String faz   = com.google.gwt.user.client.Window.Location.getParameter("faz");
//        String rave  = com.google.gwt.user.client.Window.Location.getParameter("rave");
//        String token = com.google.gwt.user.client.Window.Location.getParameter("token");
//
//        taaClientContext.getAntrag()
//                        .setFremdaktenzeichen(faz);
//        taaClientContext.getAntrag()
//                        .setRahmenvereinbarung(rave);
//        taaClientContext.getAntrag()
//                        .getVermittler()
//                        .setUrlToken(token);
//
//        semagLoadContext.add(createCommandDecodeToken(token).dependingOn(commandGetApplicationContext));
//
//      }
//      semagLoadContext.add(createCommandGetUserInfo().dependingOn(commandGetApplicationContext));
//      semagLoadContext.build()
//                      .run();
//    } catch (SeMa4gException e) {
//      ExceptionUtils.get()
//                    .showException(UiUtils.createClassMethodString(ApplicationHandler.class,
//                                                                   "onStartApplication"),
//                                   new TaaServerCommunicationException("Response ist null"));
//    }
//  }

}
