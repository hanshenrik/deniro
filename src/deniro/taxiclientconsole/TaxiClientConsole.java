package deniro.taxiclientconsole;

import no.ntnu.item.arctis.runtime.Block;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TaxiClientConsole extends Block {
private JFrame frame;
private JTextArea textArea;

		public void show() {
		frame = new JFrame("Taxi Client Console");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)(screenSize.width/4),(int)(screenSize.height/4));
		int x=(int)(frameSize.width/4);
		int y=(int)(frameSize.height/4);
		frame.setBounds(x,y,frameSize.width,frameSize.height);
		
		//frame.getContentPane().setLayout(new GridLayout(4,3,10,10));
		Button button = new Button("Order");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendToBlock("CLICKED");
			}

		});
		frame.getContentPane().add(button);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea),BorderLayout.CENTER);
		frame.getContentPane().add(button,BorderLayout.SOUTH);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sendToBlock("CLOSED");
			}
		});
		frame.setVisible(true);
		//frame.pack();
	}

		public void updateConsole(String arg) {
		System.out.println("### here?");
		textArea.setText(arg);
		}
}