package deniro.userclientconsole;

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

public class UserClientConsole extends Block {
private JFrame frame;
private JTextArea textArea;

	public void show() {
		frame = new JFrame("User Client Console");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)(screenSize.width/4),(int)(screenSize.height/4));
		int x=(int)(frameSize.width/4);
		int y=(int)(frameSize.height/4);
		frame.setBounds(x,y,frameSize.width,frameSize.height);
		
		//frame.getContentPane().setLayout(new GridLayout(4,3,10,10));
		Button requested = new Button("Requested");
		requested.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendToBlock("REQUESTED");
			}
		});
		
		Button cancelled = new Button("Cancelled");
		cancelled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendToBlock("CANCELLED");
			}
		});
		
		Button confirmAddress = new Button("Confirm Address");
		requested.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendToBlock("ADDRESS");
			}
		});
		
		frame.getContentPane().add(requested);
		frame.getContentPane().add(cancelled);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.getContentPane().add(requested, BorderLayout.NORTH);
		frame.getContentPane().add(cancelled, BorderLayout.SOUTH);
		frame.getContentPane().add(confirmAddress, BorderLayout.EAST);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sendToBlock("CLOSED");
			}
		});
		
		frame.setVisible(true);
		//frame.pack();
	}

	public void updateConsole(String arg) {
		textArea.setText(arg);
	}
}