/**
 * Hochschule Harz
 * Fachbereich Automatisierung und Informatik
 * Prof. Dr. Bernhard Zimmermann
 * 
 * LV "Algorithmen" WiSe 2019
 *
 * Problem: ...
 * 
 * @author u33873 (Oliver Lindemann)
 * @version 1.0
 * 
 **/
package Lists;

public class DoublyLinkedList extends List {

	// Letzter Listknoten in dieser Liste
	private DLListnode last;

	public DoublyLinkedList() {
		// Konstruiert eine leere doppelt verkettete Liste
	}

	/**
	 * Definiert den letzten Listenknoten als aktuellen Listenknoten
	 */
	public void last() {
		// Den Cursor auf den letzten Knoten setzen
		this.cursor = this.last;
	}

	/**
	 * Definiert den Vorg�nger des aktuellen Listenknotens als aktuellen
	 * Listenknoten. <br>
	 * Wenn die Liste keinen aktuellen Listenknoten besitzt, hat diese Methode
	 * keinen Effekt.
	 */
	public void pred() {
		// Falls der Cursor auf nichts zeigt, wird nichts gemacht
		if (hasActListnode()) {
			// Den Cursor auf den Vorg�nger des aktuellen Knotens zeigen lassen

			// Cursor muss zuerst zu einem DLListnode gecastet werden, da nur ein DLListnode
			// die Variable "pred" enth�lt. Cursor ist aber als der abstraktere Datentyp
			// Listnode gespeichert. Da Cursor aber weiterhin ein DLListnode ist, kann
			// dieser problemlos gecastet werden und dann auf pred zugegriffen werden..
			cursor = ((DLListnode) cursor).pred;
		}
	}

	/**
	 * Liefert die Info-Komponente des Vorg�ngers des aktuellen Listenknotens, falls
	 * dieser Listenknoten existiert, {@code null} sonst
	 * 
	 * @return Info-Komponente des Vorg�ngers; falls dieser nicht existiert wird
	 *         null zur�ckgegeben
	 */
	public Object getValPred() {
		// Falls der Cursor auf nichts zeigt oder der Vorg�nger vom Cursor nicht
		// existiert, dann wird null returned
		if (!existActListnodePredecessor()) {
			return null;
		}
		// Wert des Vorg�ngers holen
		return ((DLListnode) cursor).pred.val;
	}

	/**
	 * Weist der Info-Komponente des Vorg�ngers des aktuellen Listenknotens den Wert
	 * {@code val} zu, falls dieser Listenknoten existiert. Andernfalls wird eine
	 * Meldung auf der Konsole ausgegeben und die Methode hat keinen Effekt.
	 * 
	 * @param val Info-Komponente, die dem Vorg�nger des aktuellen Listenknotens
	 *            zugewiesen werden soll.
	 */
	public void setValPred(Object val) {
		// Falls der Cursor auf nichts zeigt oder der Vorg�nger von dem aktuellen
		// Listknoten null ist, so wird nichts gemacht
		if (!existActListnodePredecessor()) {
			System.out.println("setValPred: Der Vorgaenger des aktuellen Listenknotens existiert nicht.");
			return;
		}
		// Wert des Vorg�ngers setzen
		((DLListnode) cursor).pred.val = val;
	}

	/**
	 * Gibt an, ob der aktuelle Listenknoten existiert und dieser einen Vorg�nger
	 * hat.
	 * 
	 * @return {@code true}, falls die Liste einen aktuellen Knoten hat und dieser
	 *         Listenknoten einen Vorg�nger besitzt; andernfalls {@code false}
	 */
	public boolean existActListnodePredecessor() {
		// Liefert true, falls es einen aktuellen Knoten gibt und dieser einen Vorg�nger
		// besitzt
		return hasActListnode() && ((DLListnode) cursor).pred != null;
	}

	/**
	 * L�scht den letzten Listenknoten einer nichtleeren Liste. <br>
	 * Falls der gel�schte Listenknoten der aktuelle Listenknoten war, hat die Liste
	 * anschlie�end keinen aktuellen Knoten, sonst wird der aktuelle Listenknoten
	 * durch die Methode nicht ver�ndert. <br>
	 * F�r die leere Liste wird eine Meldung auf der Konsole ausgegeben und die
	 * Methode hat keinen Effekt.
	 */
	public void deleteLast() {
		// Falls es keinen letzten Knoten gibt, nichts machen
		if (last == null) {
			System.out.println("deleteLast: Die Liste ist leer.");
			return;
		}
		// Falls der Cursor auf den letzten Knoten zeigt, soll der Cursor auf nichts
		// mehr zeigen
		if (cursor == last) {
			cursor = null;
		}
		// Falls der first den gleichen Listenknoten wie last referenziert hat, so muss
		// auch first auf null gesetzt werden
		if (first == last) {
			first = null;
		}

		// Der Vorg�nger von last soll auf nichts mehr zeigen, da last gel�scht wird
		if (last.pred != null) {
			last.pred.next = null;
		}

		DLListnode del = last;
		// last soll nun auf den Vorg�nger von last zeigen
		last = last.pred;
		// die Referenz von last auf den Vorg�nger l�schen
		del.pred = null;
	}

	/**
	 * Verarbeitet die Info-Komponente aller Listenknoten in der durch die Liste
	 * festgeleten umgekehrten Reihenfolge, d.h. beginnend beim letzten Listenknoten
	 * nacheinander alle anderen bis zum ersten. Der aktuelle Listenknoten wird
	 * dabei nicht ver�ndert.
	 */
	public void processListnodesReverse() {
		// Vom letzten Knoten bis zum ersten Knoten (bis kein Vorg�nger mehr existiert)
		// iterieren
		for (DLListnode act = last; act != null; act = act.pred) {
			act.process();
		}
	}

	/**
	 * Sucht ab dem aktuellen Listenknoten sequentiell in Richtung des ersten
	 * Listenknotens nach dem n�chsten Listenknoten, dessen Info-Komponente aufgrund
	 * der Methode {@code Object#equals} gleich dem Object {@code val} ist und
	 * definiert den gefundenen Listenknoten als aktuellen Listenknoten. <br>
	 * Falls kein solcher Listenknoten oder kein aktueller Listenknoten existiert,
	 * hat die Liste anschlie�end keinen aktuellen Knoten.
	 * 
	 * @param val Info-Komponenten des Listenknotens, der gesucht wird.
	 */
	public void findReverse(Object val) {
		for (DLListnode act = (DLListnode) cursor; act != null; act = act.pred) {
			// Falls dieser Knoten einen Val hat und dieser gleich zu dem gesuchten Wert
			// ist, soll dieser Listknoten als aktueller Listknoten gelten
			if (act.val != null && act.val.equals(val)) {
				cursor = act;
				return;
			}
		}
		// Kein Knoten gefunden oder es existiert kein aktueller Listknoten
		cursor = null;
	}

	// --------------------------------

	@Override
	public void delete() {
		if (existActListnodeSuccessor()) {
			/* Info-Komponente des Nachfolgers dem aktuellen Listenknoten zuordnen */
			cursor.val = cursor.next.val;// Loeschen des Nachfolgers
			Listnode del = cursor.next;
			cursor.next = cursor.next.next;// geloeschten Listenknoten isolieren
			del.next = null;

			// �bern�chsten Knoten mit diesem Knoten verlinken
			if (cursor.next != null) {
				((DLListnode) cursor.next).pred = (DLListnode) cursor;
			} else {
				last = (DLListnode) cursor;
			}
		} else {
			System.out.println("delete: Es gibt keinen aktuellen Listenknoten oder ");
			System.out.println("        dieser Listenknoten hat keinen Nachfolger.");
		}
	}

	@Override
	public void deleteAfter() {
		if (existActListnodeSuccessor()) {
			Listnode del = cursor.next;
			cursor.next = cursor.next.next;// geloeschten Listenknoten isolieren
			del.next = null;

			// Falls der neu gesetzte Nachfolger des Cursors nicht null ist, muss noch pred
			// auf den Cursor gesetzt werden
			if (cursor.next != null) {
				((DLListnode) cursor.next).pred = (DLListnode) cursor;
			} else {
				// Der neu gesetzte Nachfolger existiert nicht. Last muss jetzt auf cursor
				// zeigen.
				last = ((DLListnode) cursor);
			}

		} else {
			System.out.println("deleteAfter: Der Nachfolger des aktuellen " + "Listenknotens existiert nicht.");
		}
	}

	@Override
	public void deleteFirst() {
		if (!isEmpty()) {
			// Falls der Cursor der erste Knoten ist, dann den Cursor auf null setzen
			// (l�schen)
			if (cursor == first) {
				cursor = null;
			}

			Listnode del = first;
			first = first.next;// geloeschten Listenknoten isolieren
			del.next = null;

			// Dieser Knoten soll keinen Vorg�ner mehr haben
			if ((DLListnode) first != null) {
				((DLListnode) first).pred = null;
			}
		} else {
			System.out.println("deleteFirst: Die Liste ist leer.");
		}
	}

	@Override
	public void insertBefore(Object val) {
		if (hasActListnode()) {
			/*
			 * neuen Knoten einfuegen und dabei die Info-Komponenten des aktuellen und
			 * neuenListenknotens vertauschen
			 */
			DLListnode neuerKnoten = newListnode(cursor.val, (DLListnode) cursor, cursor.next);
			cursor.next = neuerKnoten;
			cursor.val = val;

			// Der next-Knoten von dem eingef�gten Knoten soll nun auf den neu
			// eingef�gten Knoten zeigen
			if (neuerKnoten.next != null) {
				((DLListnode) neuerKnoten.next).pred = neuerKnoten;
			} else {
				// Der neue Knoten hat keinen Nachfolger. last muss deshalb auf den neuen
				// Listenknoten zeigen
				last = neuerKnoten;
			}

		} else if (isEmpty()) {
			first = cursor = last = newListnode(val, null, null);
		} else {
			System.out.println("insertBefore: Die Liste ist nicht " + "leer und hat keinen aktuellen Listenknoten.");
		}
	}

	@Override
	public void insertAfter(Object val) {
		if (hasActListnode()) {
			// Einen neuen Listenknoten erzeugen
			DLListnode neuerKnoten = newListnode(val, (DLListnode) cursor, cursor.next);
			cursor.next = neuerKnoten;

			// Der next-Knoten von dem eingef�gten Knoten soll nun auf den neu
			// eingef�gten Knoten zeigen
			if (neuerKnoten.next != null) {
				((DLListnode) neuerKnoten.next).pred = neuerKnoten;
			} else {
				// Der eingef�gte Knoten ist der neue letzte Knoten, last aktualisieren
				last = neuerKnoten;
			}

		} else if (isEmpty()) {
			first = cursor = last = newListnode(val, null, null);
		} else {
			System.out.println("insertAfter: Die Liste ist nicht leer und " + "hat keinen aktuellen Listenknoten.");
		}
	}

	private DLListnode newListnode(Object val, DLListnode pred, Listnode next) {
		return new DLListnode(val, pred, next);
	}

	// --------------------------------

	private class DLListnode extends Listnode {

		// Vorg�nger dieses Listknotens
		private DLListnode pred;

		public DLListnode(Object val, DLListnode pred, Listnode next) {
			super(val, next); // Listnode mit val und next erzeugen
			this.pred = pred; // pred setzen
		}

	}

}
