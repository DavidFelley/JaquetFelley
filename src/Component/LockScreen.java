package Component;
/**
* ------------------------------------------------------------------------------------------------------ <br/>
* Classe : LockScreen <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Auteur : David Felley et Valentin Jaquet <br/>
* Description de la classe : Cette classe gère le lockscreen <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Remarque : - <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
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
		
		private JPanel timedatePanel = new JPanel();
		// Date
		private LabelPerso date = new LabelPerso("");
		private DateFormat DATEFORMATDATE = new SimpleDateFormat("EEEE d MMM yyyy");
		private Timer timerDate = new Timer(0, new CurrentDate());
		
		// Heure
		private LabelPerso heure = new LabelPerso("");
		private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");
		private Timer timer = new Timer(0, new CurrentTime());
		/**
		 * Constructeur du lockscreen
		 */
		public LockScreen () {
			setLayout(new GridLayout(0,1));
			add(lockScreenPanel, BorderLayout.CENTER);
			lockScreenPanel.setLayout(new BorderLayout());
			lockScreenPanel.setBorder(new EmptyBorder(100,0,50,0));
			
			timedatePanel.setLayout(new BoxLayout(timedatePanel, BoxLayout.PAGE_AXIS));
			timedatePanel.setOpaque(false);
	
			timedatePanel.add(date);
			timedatePanel.add(heure);
			
			timerDate.start();
			date.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			date.setForeground(Color.WHITE);
			date.setFont(new Font(null, Font.BOLD, 40));
			
			timer.start();
			
			heure.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			heure.setForeground(Color.WHITE);
			heure.setFont(new Font(null, Font.BOLD, 40));
			
			lockScreenPanel.add(timedatePanel, BorderLayout.NORTH);
			lockScreenPanel.add(lockButton, BorderLayout.SOUTH);
		}
		
		
		public ButtonCreation getLockButton() {
			return lockButton;
		}


		public void setLockButton(ButtonCreation lockButton) {
			this.lockButton = lockButton;
		}

		/**
		 * ActionListener qui permet de récupérer la date du jour
		 * @author Valentin Jaquet
		 *
		 */
		class CurrentDate implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Calendar now = Calendar.getInstance();
				date.setText(DATEFORMATDATE.format(now.getTime()));
			}
		}
		
		/**
		 * ActionListener qui permet de récupérer l'heure
		 * @author Valentin Jaquet
		 *
		 */
		class CurrentTime implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Calendar now = Calendar.getInstance();
				heure.setText(DATEFORMAT.format(now.getTime()));
			}
		}
}
