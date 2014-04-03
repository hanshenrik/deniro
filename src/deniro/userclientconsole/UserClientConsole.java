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
		Button request = new Button("Request");
		request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendToBlock("REQUEST");
			}
		});
		
		Button cancel = new Button("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendToBlock("CANCEL");
			}
		});
		
		frame.getContentPane().add(request);
		frame.getContentPane().add(cancel);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.getContentPane().add(request, BorderLayout.NORTH);
		frame.getContentPane().add(cancel, BorderLayout.SOUTH);
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