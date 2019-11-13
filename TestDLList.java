/** 
 * Hochschule Harz
 * Fachbereich Automatisierung und Informatik
 * Prof. Dr. Bernhard Zimmermann
 * 
 * LV "Algorithmen" WiSe 2019
 *
 * Datenstruktur: lineare Liste, doppelt verkettet implementiert
 * Testprogramm
 *
 * @version 1.0,  02.01.2000 - Urfassung
 * @author Bernhard Zimmermann
 * 
 * @version 1.0a, 22.10.2011 - Umstellung auf Autoboxing/Unboxing
 * @author Bernhard Zimmermann
 * 
 */

import Lists.DoublyLinkedList;

/**
 * Datenstruktur: lineare Liste, doppelt verkettet implementiert
 * Testprogramm
 *
 * @author Bernhard Zimmermann
 * @version 1.0a, 22.10.2011
 *
 */
public class TestDLList extends Environment {

  public static void main(String[] args) {
    if (args.length > 0) {
      try {
        System.setIn(new java.io.FileInputStream(args[0]));
      } catch (java.io.FileNotFoundException e) {
        System.out.println("*** Eingabedatei nicht gefunden ***");
        System.exit(1);
      }
    }
    if (args.length > 1) {
      try {
        System.setOut(
          new java.io.PrintStream(
            new java.io.FileOutputStream(args[1])));
      } catch (java.io.FileNotFoundException e) {}
    }
    new TestDLList().mainProgram(args);
  }

  void mainProgram(String[] args) {

    System.out.println(
      "Operationen auf doppelt verketteten linearen Listen");
    System.out.println(
      "===================================================");
    System.out.println();

    DoublyLinkedList l = new DoublyLinkedList();
    // Leere doppelt verkettete (Zahlen-)Liste konstruieren

    System.out.println("::::: leere Liste :::::");
    System.out.println();
    System.out.println("########## Abschnitt 1 ##########");
    l.first();
    l.pred();
    l.next();
    l.last();
    l.next();
    l.pred();
    l.getVal();
    l.getValNext();
    l.getValPred();
    l.setVal(5); // Fehlermeldung
    l.setValNext(6); // Fehlermeldung
    l.setValPred(7); // Fehlermeldung
    System.out.println("Info-Komponenten:");
    l.processListnodes();
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse();
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 2 ##########");
    // erstes Auftreten der Zahl 10 finden und modifizieren 
    l.first();
    l.find(10);
    if (l.hasActListnode()) {
      l.setVal(1000 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes();
    } else
      System.out.println("Zahl 10 nicht gefunden");
    // alle Auftreten der Zahl 55 finden und modifizieren
    l.first();
    for (l.find(55); l.hasActListnode(); l.find(55)) {
      l.setVal(1000 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes();
      l.next();
    }

    // erstes Auftreten der Zahl 11 finden und modifizieren 
    l.last();
    l.findReverse(11);
    if (l.hasActListnode()) {
      l.setVal(1000 + (Integer) l.getVal());
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
    } else
      System.out.println("Zahl 11 nicht gefunden");
    // alle Auftreten der Zahl 66 finden und modifizieren
    l.last();
    for (l.findReverse(66); l.hasActListnode(); l.findReverse(66)) {
      l.setVal(1000 + (Integer) l.getVal());
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
      l.pred();
    }
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("::::: Einfuegen :::::");
    System.out.println();
    System.out.println("########## Abschnitt 3 ##########");
    l.insertAfter(10);
    System.out.println("Info-Komponenten:");
    l.processListnodes(); // 10
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse(); // 10
    // erstes Auftreten der Zahl 10 finden und modifizieren 
    l.find(10);
    if (l.hasActListnode()) {
      l.setVal(1000 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes(); // 1010
    } else
      System.out.println("Zahl 10 nicht gefunden");
    // erstes Auftreten der Zahl 1010 finden und modifizieren 
    l.findReverse(1010);
    if (l.hasActListnode()) {
      l.setVal(1000 + (Integer) l.getVal());
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse(); // 2010
    } else
      System.out.println("Zahl 1010 nicht gefunden");
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 4 ##########");
    l.insertAfter(50);
    l.insertAfter(40);
    l.first();
    l.insertAfter(120);
    l.next();
    l.insertAfter(130);
    l.next();
    l.last();
    l.insertAfter(90);
    l.insertAfter(70);
    l.insertAfter(60);
    l.last();
    l.pred();
    l.insertAfter(80);
    System.out.println("Info-Komponenten:");
    l.processListnodes(); // 2010 120 130 40 50 60 70 80 90
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse(); // 90 80 70 60 50 40 130 120 2010
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 5 ##########");
    l.first();
    l.next();
    l.setVal(20);
    l.setValNext(30);
    System.out.println(l.getVal() + " " + l.getValNext()); // 20 30
    l.last();
    l.setVal(95);
    l.setValPred(85);
    System.out.println(l.getValPred() + " " + l.getVal()); // 85 95
    System.out.println("Info-Komponenten:");
    l.processListnodes(); // 2010 20 30 40 50 60 70 85 95
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse(); // 95 85 70 60 50 40 30 20 2010
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 6 ##########");
    // Keinen Listenknoten als aktuellen Listenknoten definieren
    for (l.first(); l.hasActListnode(); l.next());
    l.insertAfter(999); // Fehlermeldung
    l.insertBefore(999); // Fehlermeldung 
    for (l.last(); l.hasActListnode(); l.pred());
    l.insertAfter(999); // Fehlermeldung
    l.insertBefore(999); // Fehlermeldung
    System.out.println("Info-Komponenten:");
    l.processListnodes(); // 2010 20 30 40 50 60 70 85 95
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse(); // 95 85 70 60 50 40 30 20 2010
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 7 ##########");
    l.first();
    l.insertBefore(7);
    l.insertBefore(5);
    l.last();
    l.insertBefore(90);
    l.next();
    l.insertBefore(90);
    l.next();
    l.insertBefore(90);
    l.next();
    l.insertAfter(95);
    l.findReverse(40);
    if (l.hasActListnode()) {
      l.insertBefore(35);
    } else
      System.out.println("Zahl 40 nicht gefunden");
    l.insertBefore(31);
    System.out.println("Info-Komponenten:");
    l.processListnodes();
    // 5 7 2010 20 30 31 35 40 50 60 70 85 90 90 90 95 95
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse();
    //95 95 90 90 90 85 70 60 50 40 35 31 30 20 2010 7 5
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("::::: Suchen und Modifizieren :::::");
    System.out.println();
    System.out.println("########## Abschnitt 8 ##########");
    // erstes Auftreten der Zahl 95 finden und modifizieren
    l.first();
    l.find(95);
    if (l.hasActListnode()) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes();
      // 5 7 2010 20 30 31 35 40 50 60 70 85 90 90 90 96 95
    } else
      System.out.println("Zahl 95 nicht gefunden");

    // erstes Auftreten der Zahl 95 finden und modifizieren
    l.first();
    l.find(95);
    if (l.hasActListnode()) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes();
      // 5 7 2010 20 30 31 35 40 50 60 70 85 90 90 90 96 96
    } else
      System.out.println("Zahl 95 nicht gefunden");
    l.insertAfter(99);
    System.out.println("Info-Komponenten:");
    l.processListnodes();
    // 5 7 2010 20 30 31 35 40 50 60 70 85 90 90 90 96 96 99
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 9 ##########");
    // erstes Auftreten der Zahl 7 finden und modifizieren
    l.last();
    l.findReverse(7);
    if (l.hasActListnode()) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
      // 99 96 96 90 90 90 85 70 60 50 40 35 31 30 20 2010 8 5
    } else
      System.out.println("Zahl 7 nicht gefunden");

    // erstes Auftreten der Zahl 5 finden und modifizieren
    l.last();
    l.findReverse(5);
    if (l.hasActListnode()) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
      // 99 96 96 90 90 90 85 70 60 50 40 35 31 30 20 2010 8 6 
    } else
      System.out.println("Zahl 5 nicht gefunden");
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 10 ##########");
    // erstes Auftreten der Zahl 6 finden und modifizieren
    l.first();
    l.find(6);
    if (l.hasActListnode()) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes();
      // 7 8 2010 20 30 31 35 40 50 60 70 85 90 90 90 96 96 99
    } else
      System.out.println("Zahl 6 nicht gefunden");
    // erstes Auftreten der Zahl 6 finden und modifizieren
    l.first();
    l.find(6);
    if (l.hasActListnode()) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes();
      // 7 8 2010 20 30 31 35 40 50 60 70 85 90 90 90 96 96 99
    } else
      System.out.println("Zahl 6 nicht gefunden");
    // erstes Auftreten der Zahl 99 finden und modifizieren
    l.last();
    l.findReverse(99);
    if (l.hasActListnode()) {
      l.setVal((Integer) l.getVal() - 1);
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
      // 98 96 96 90 90 90 85 70 60 50 40 35 31 30 20 2010 8 7 
    } else
      System.out.println("Zahl 99 nicht gefunden");
    // erstes Auftreten der Zahl 99 finden und modifizieren
    l.last();
    l.findReverse(99);
    if (l.hasActListnode()) {
      l.setVal((Integer) l.getVal() - 1);
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
      // 98 96 96 90 90 90 85 70 60 50 40 35 31 30 20 2010 8 7 
    } else
      System.out.println("Zahl 99 nicht gefunden");
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 11 ##########");
    // alle Auftreten der Zahl 90 finden und modifizieren
    l.first();
    for (l.find(90); l.hasActListnode(); l.find(90)) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes();
      l.next();
    }
    // 7 8 2010 20 30 31 35 40 50 60 70 85 91 91 91 96 96 98 
    // alle Auftreten der Zahl 90 finden und modifizieren
    l.first();
    for (l.find(90); l.hasActListnode(); l.find(90)) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println("Info-Komponenten:");
      l.processListnodes();
      l.next();
    }
    // 7 8 2010 20 30 31 35 40 50 60 70 85 91 91 91 96 96 98 
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 12 ##########");
    // alle Auftreten der Zahl 91 finden und modifizieren
    l.last();
    for (l.findReverse(91); l.hasActListnode(); l.findReverse(91)) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
      l.pred();
    }
    // 98 96 96 92 92 92 85 70 60 50 40 35 31 30 20 2010 8 7
    // alle Auftreten der Zahl 91 finden und modifizieren
    l.last();
    for (l.findReverse(91); l.hasActListnode(); l.findReverse(91)) {
      l.setVal(1 + (Integer) l.getVal());
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
      l.pred();
    }
    // 98 96 96 92 92 92 85 70 60 50 40 35 31 30 20 2010 8 7
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 13 ##########");
    // alle Info-Komponenten manuell ausgeben
    System.out.println("Info-Komponenten:");
    for (l.first(); l.hasActListnode(); l.next())
      System.out.println((Integer) l.getVal());
    // 7 8 2010 20 30 31 35 40 50 60 70 85 92 92 92 96 96 98     
    // alle Info-Komponenten in umgekehrter Reihenfolge manuell ausgeben
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    for (l.last(); l.hasActListnode(); l.pred())
      System.out.println((Integer) l.getVal());
    // 98 96 96 92 92 92 85 70 60 50 40 35 31 30 20 2010 8 7
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("::::: Loeschen :::::");
    System.out.println();
    System.out.println("########## Abschnitt 14 ##########");
    l.first();
    l.deleteAfter();
    l.find(40);
    l.deleteAfter();
    l.find(60);
    l.deleteAfter();
    l.insertAfter(65);
    l.last();
    l.pred();
    // letzten Listenknoten loeschen
    l.deleteAfter();
    l.deleteAfter(); // Fehlermeldung
    System.out.println("Info-Komponenten:");
    l.processListnodes();
    // 7 2010 20 30 31 35 40 60 65 85 92 92 92 96 96 
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse();
    // 96 96 92 92 92 85 65 60 40 35 31 30 20 2010 7    
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 15 ##########");
    l.first();
    l.deleteFirst();
    if (l.hasActListnode())
      System.out.println("Die Liste hat einen aktuellen Listenknoten.");
    else
      System.out.println(
        "Die Liste hat keinen aktuellen Listenknoten.");
    l.first();
    l.find(40);
    l.deleteFirst();
    System.out.println(
      "Wert des aktuellen Listenknotens: " + (Integer) l.getVal());
    // 40
    System.out.println("Info-Komponenten:");
    l.processListnodes(); // 20 30 31 35 40 60 65 85 92 92 92 96 96  
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse();
    // 96 96 92 92 92 85 65 60 40 35 31 30 20
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 16 ##########");
    l.last();
    l.deleteLast();
    if (l.hasActListnode())
      System.out.println("Die Liste hat einen aktuellen Listenknoten.");
    else
      System.out.println(
        "Die Liste hat keinen aktuellen Listenknoten.");
    l.last();
    l.findReverse(65);
    l.deleteLast();
    System.out.println(
      "Wert des aktuellen Listenknotens: " + (Integer) l.getVal());
    // 65
    System.out.println("Info-Komponenten:");
    l.processListnodes(); // 20 30 31 35 40 60 65 85 92 92 92  
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse(); // 92 92 92 85 65 60 40 35 31 30 20
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();

    System.out.println("########## Abschnitt 17 ##########");
    l.first();
    l.delete();
    l.find(40);
    l.delete();
    l.delete();
    l.find(92);
    l.delete();
    l.last();
    l.pred();
    l.delete();
    l.delete(); // Fehlermeldung
    System.out.println("Info-Komponenten:");
    l.processListnodes(); // 30 31 35 65 85 92 
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse(); // 92 85 65 35 31 30

    System.out.println("########## Abschnitt 18 ##########");
    System.out.println("Loeschen der restlichen Listenknoten");
    System.out.println(
      "+++++ RETURN-Taste druecken, um fortzusetzen ...");
    stdin.readLine();
    l.first();
    while (l.existActListnodeSuccessor()) {
      /* Durch das Loeschen des aktuellen Listenknotens
      wird dessen Nachfolger zum aktuellen Listenknoten. 
      Deshalb darf der aktuelle Listenknoten nicht explizit 
      "weitergesetzt" werden.
      */
      l.delete();
      System.out.println("Info-Komponenten:");
      l.processListnodes();
      System.out.println(
        "Info-Komponenten in umgekehrter Reihenfolge:");
      l.processListnodesReverse();
    }
    // 92 
    /// Loeschen des letzten Listenknotens der 1-elementigen Liste      
    l.deleteLast();
    if (l.hasActListnode())
      System.out.println("Die Liste hat einen aktuellen Listenknoten.");
    else
      System.out.println(
        "Die Liste hat keinen aktuellen Listenknoten.");
    System.out.println("Info-Komponenten:");
    l.processListnodes(); //  
    System.out.println("Info-Komponenten in umgekehrter Reihenfolge:");
    l.processListnodesReverse(); //
    l.deleteFirst();
    l.deleteLast();

  } // mainProgram

} // TestDLList
