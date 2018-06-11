package Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import View.BackgroundImagePanel;


public class LockScreen extends JPanel{
	
	// Panel principal
		private BackgroundImagePanel lockScreenPanel = new BackgroundImagePanel(new ImageIcon("images/Fond/wallpaper3.jpg"));
		
		private ButtonCreation lockButton = new ButtonCreation("lock", new ImageIcon("images/icones/lock.png"));
		
		// Date
		private LabelPerso date = new LabelPerso("");
		private DateFormat DATEFORMATDATE = new SimpleDateFormat("EEEE d MMM yyyy");
		private Timer timerDate = new Timer(0, new CurrentDate());
		
		public LockScreen () {
			setLayout(new GridLayout(0,1));
			add(lockScreenPanel, BorderLayout.CENTER);
			lockScreenPanel.setLayout(new BorderLayout());
			lockScreenPanel.setBorder(new EmptyBorder(100,0,50,0));
			lockScreenPanel.add(lockButton, BorderLayout.SOUTH);
			timerDate.start();
			lockScreenPanel.add(date, BorderLayout.NORTH);
			date.setHorizontalAlignment(JLabel.CENTER);
			date.setForeground(Color.WHITE);
		}
		
		
		public ButtonCreation getLockButton() {
			return lockButton;
		}


		public void setLockButton(ButtonCreation lockButton) {
			this.lockButton = lockButton;
		}


		class CurrentDate implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Calendar now = Calendar.getInstance();
				date.setText(DATEFORMATDATE.format(now.getTime()));
			}
		}
}
