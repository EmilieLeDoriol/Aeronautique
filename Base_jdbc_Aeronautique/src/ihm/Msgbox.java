package ihm;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Msgbox extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Button ok;
	
	public Msgbox(JFrame fr, String msg) {
		
		super(fr, "Message");
		//constructeur hérité

		setModal(true);

		//gestionnaire de positionnement
		setLayout(new BorderLayout());
		//ligne de message
		add(BorderLayout.CENTER,new JLabel(msg,JLabel.CENTER));
		//boutons
		JPanel p=new JPanel();
		p.setLayout(new FlowLayout());
		ok=new Button(" OK ");
		p.add(ok);
		ok.addActionListener(this);
		add(BorderLayout.SOUTH,p);
		//dimensions et positionnement
		pack();
		Dimension d=getToolkit().getScreenSize();
		setLocation(d.width/3,d.height/3);
		//affichage
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ok) {
			setVisible(false);
			dispose();
		}
	}
}
