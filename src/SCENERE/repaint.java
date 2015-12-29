package SCENERE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class repaint  implements ActionListener {
		drawbackground bg;
		public repaint(drawbackground bg) {
			this.bg = bg;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			bg.repaint();
		}
}
