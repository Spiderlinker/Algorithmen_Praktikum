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

	// --------------------------------

	private DLListnode getCursor() {
		return getAsDL(cursor);
	}

	private DLListnode getFirst() {
		return getAsDL(first);
	}

	private DLListnode getAsDL(Listnode node) {
		return (DLListnode) node;
	}

	// --------------------------------

	public void last() {
		// Den Cursor auf den letzten Knoten setzen
		this.cursor = this.last;
	}

	public void pred() {
		// Falls der Cursor auf nichts zeigt, wird nichts gemacht
		if (hasActListnode()) {
			// Den Cursor auf den Vorgänger des aktuellen Knotens zeigen lassen
			cursor = getCursor().pred;
		}
	}

	public Object getValPred() {
		// Falls der Cursor auf nichts zeigt oder der Vorgänger vom Cursor nicht
		// existiert, dann wird null returned
		if (!existActListnodePredecessor()) {
			return null;
		}
		// Wert des Vorgängers holen
		return getCursor().pred.val;
	}

	public void setValPred(Object val) {
		// Falls der Cursor auf nichts zeigt oder der Vorgänger von dem aktuellen
		// Listknoten null ist, so wird nichts gemacht
		if (!existActListnodePredecessor()) {
			System.out.println("setValPred: Der Vorgaenger des aktuellen Listenknotens existiert nicht.");
			return;
		}
		// Wert des Vorgängers setzen
		getCursor().pred.val = val;
	}

	public boolean existActListnodePredecessor() {
		// Liefert true, falls es einen aktuellen Knoten gibt und dieser einen Vorgänger
		// besitzt
		return hasActListnode() && getCursor().pred != null;
	}

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
		if(first == last) {
			first = null;
		}

		// Der Vorgänger von last soll auf nichts mehr zeigen, da last gelöscht wird
		if(last.pred != null) {
			last.pred.next = null;
		}
		// last zeigt nun auf den Vorgänger von last
		last = last.pred;
	}

	public void processListnodesReverse() {
		DLListnode current = last;
		while (current != null) {
			current.process();
			current = current.pred;
		}
	}

	public void findReverse(Object val) {
		DLListnode current = getCursor();
		while (current != null) {
			// Falls dieser Knoten einen Val hat und dieser gleich zu dem gesuchten Wert
			// ist, soll dieser Listknoten als aktueller Listknoten gelten
			if (current.val != null && current.val.equals(val)) {
				cursor = current;
				return;
			}
			current = current.pred;
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

			// Übernächsten Knoten mit diesem Knoten verlinken
			if (cursor.next != null) {
				getAsDL(cursor.next).pred = getCursor();
			}else {
				last = getCursor();
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

			if (cursor.next != null) {
				getAsDL(cursor.next).pred = getCursor();
				
				if (cursor.next.next == null) {
					last = getAsDL(cursor.next);
				}
			}else {
				last = getCursor();
			}
			
			
		} else {
			System.out.println("deleteAfter: Der Nachfolger des aktuellen " + "Listenknotens existiert nicht.");
		}
	}

	@Override
	public void deleteFirst() {
		if (!isEmpty()) {
			if (cursor == first) {
				cursor = null;
			}
			Listnode del = first;
			first = first.next;// geloeschten Listenknoten isolieren
			del.next = null;

			// Dieser Knoten soll keinen Vorgäner mehr haben
			if (getFirst() != null) {
				getFirst().pred = null;
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
			cursor.next = newListnode(cursor.val, getCursor(), cursor.next);
			cursor.val = val;

			// Der next-Knoten von dem eingefügten Knoten soll nun auf den neu
			// eingefügten Knoten zeigen
			if (cursor.next.next != null) {
				getAsDL(cursor.next.next).pred = getAsDL(cursor.next);
			} else {
				last = getAsDL(cursor.next);
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
			cursor.next = newListnode(val, getCursor(), cursor.next);

			// Der next-Knoten von dem eingefügten Knoten soll nun auf den neu
			// eingefügten Knoten zeigen
			if (cursor.next.next != null) {
				getAsDL(cursor.next.next).pred = getAsDL(cursor.next);
			} else {
				last = getAsDL(cursor.next);
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

		// Vorgänger dieses Listknotens
		private DLListnode pred;

		public DLListnode(Object val, DLListnode pred, Listnode next) {
			super(val, next);
			this.pred = pred;
		}

	}

}
