//******************************************************************************
// Copyright (C) 2016-2018 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Feb  9 20:33:16 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160209 [weaver]:	Original file (for CS 4053/5053 homeworks).
// 20180123 [weaver]:	Modified for use in CS 3053 team projects.
// 20180204 [avery]:		Modified for wireframe project
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package ou.cs.hci.stages;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ou.cs.hci.resources.Resources;

//******************************************************************************

/**
 * The <CODE>BuildTest</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Stage2
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************
	// put constants here 


	//**********************************************************************
	// Private Members
	//**********************************************************************
	// State (internal) variables


	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{	
		//create frame and panels 
		JFrame frame			= new JFrame("Personas and Scenarios");
		
		//wireframe panel (left) and scenarios panel (right)
		JPanel leftPanel		= new JPanel();
		leftPanel.setLayout(new BorderLayout());
		JPanel rightPanel	= new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		JPanel centerPanel	= new CenterPanel();
		JPanel westPanel		= new WestPanel();
		JPanel eastPanel		= new EastPanel();
		JPanel listPanel		= new ListPanel();
		
		// create scenario and persona frame and panels
		JFrame secondFrame = new JFrame("Scenarios and Personas");
		JPanel scenarioPanel = new ScenarioPanel();
		
		centerPanel.add(((CenterPanel) centerPanel).createPanels());
		westPanel.add(((WestPanel) westPanel).createPanels());
		eastPanel.add(((EastPanel) eastPanel).createPanels());
		
		listPanel.add(((ListPanel) listPanel).createPanels());

		frame.setBounds(50, 50, 1300, 1300); //set window size
		
		leftPanel.add(centerPanel, BorderLayout.CENTER);
		leftPanel.add(westPanel, BorderLayout.WEST);
		leftPanel.add(eastPanel, BorderLayout.EAST);
		
		// rightPanel.add(listPanel, BorderLayout.CENTER);
		
		//create layout for the frame
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		
		// create layout for the second frame
		secondFrame.add(scenarioPanel);
		secondFrame.setBounds(50, 50, 1000, 500); // set window size
		
		frame.setVisible(true);
		secondFrame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}

	//**********************************************************************
	// Private Inner Classes 
	// RightPanel -- shows scenarios to select and display in text area
	//**********************************************************************
	private static final class ListPanel extends JPanel
	{
		public ListPanel() {}

		public JPanel createPanels()
		{
			//get titles/descriptions
			ArrayList<String> rawScenNames = Resources.getLines("scenarios/titles.txt");
			ArrayList<String> rawPersNames = Resources.getLines("personas/titles.txt");
			
			//rawScenNames.addAll(rawPersNames); //can add personas if desired
			String [] scenNames = (String[]) rawScenNames.toArray(new String[0]);
			String [] persNames = (String[]) rawPersNames.toArray(new String[0]);
			
			ArrayList<String> rawScenDescrip = Resources.getLines(
					"scenarios/descriptions.txt");
			ArrayList<String> rawPersDescrip = Resources.getLines( 
					"personas/descriptions.txt");
			
			//rawScenDescrip.addAll(rawPersDescrip);
			String [] scenDescrip = (String[]) rawScenDescrip.toArray(new String[0]);
			String [] persDescrip = (String[]) rawPersNames.toArray(new String[0]);
			
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new BorderLayout());
			
			//options and text area
			JList jlst = new JList(scenNames);
			JTextArea textArea = new JTextArea(30, 20);
			//if the text files are empty, show nothing
			if(scenDescrip.length > 0) 
				textArea.setText( scenDescrip[0] );
			
			//clicking the options brings up different text
		    jlst.addListSelectionListener(new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent le) {
		          int index = jlst.getSelectedIndex();
		          if (index != -1)
		            textArea.setText( scenDescrip[index] );
		          else
		        	  textArea.setText( "Nothing is selected." );
		        }
		      });
		    //uneditable and wraps text
		    textArea.setEditable(false);
		    textArea.setLineWrap(true);
		    
		    subPanel.add(new JScrollPane(jlst), BorderLayout.NORTH);
		    subPanel.add(new JScrollPane(textArea), BorderLayout.SOUTH);
			
			return subPanel;
		}
		
	} // end center panel class
	
	//**********************************************************************
	// Private Inner Classes 
	// CenterPanel -- shows album covers and descriptions
	//**********************************************************************
	private static final class CenterPanel extends JPanel
	{
		public CenterPanel() {}

		public JPanel createPanels()
		{
			// organize the album pictures into a grid
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new GridLayout(3,3, 17,4));
			subPanel.setAlignmentX(LEFT_ALIGNMENT);
			
			// get album cover pictures
			String picname = System.getProperty("user.dir")+
					"/Build/ou-cs-hci/src/main/java/edu/ou/cs/hci/stages/placeholder.jpeg";
			ImageIcon image = new ImageIcon(picname);
			
			//create album cover and descriptions
			//NOTE: I did not put this in a loop since each will be replaced with its own
			//cover picture and description
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<br/> <html/>", subPanel);
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<br/> <html/>", subPanel);			
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<br/> <html/>", subPanel);
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<html/>", subPanel);
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<html/>", subPanel);
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<html/>", subPanel);
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<html/>", subPanel);
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<html/>", subPanel);
			createAlbum(image, "<html>Album Title <br/> Description <br/> "
					+"More Description<html/>", subPanel);
			
			return subPanel;
		}
		
		//******************************************************************
		//pairs album image with description
		private void createAlbum(ImageIcon image, String text, JPanel subPanel)
		{
			JLabel lab = new JLabel(image);
			lab.setHorizontalAlignment(SwingConstants.LEFT);
		    lab.setText(text);
		    lab.setHorizontalTextPosition(JLabel.CENTER);
		    lab.setVerticalTextPosition(JLabel.BOTTOM);
			subPanel.add(lab);
		}
	} // end center panel class
	
	//**********************************************************************
	// WestPanel -- shows view options and selected album
	//**********************************************************************
	private static final class WestPanel extends JPanel
	{
		public WestPanel() {}
		
		public JPanel createPanels()
		{
			//creates a top and bottom panel
			//top has options and bottom has more information on selected album
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
			subPanel.setAlignmentX(LEFT_ALIGNMENT);
			
			createViewPanel(subPanel);
			createSelectedPanel(subPanel);
			
			return subPanel;
		}
		
		//******************************************************************
		//creates panel with option labels
		private void createViewPanel(JPanel subPanel)
		{
			JPanel viewPanel = new JPanel();
			viewPanel.setLayout(new GridLayout(3,2, 0, 0));
			viewPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			String picname = System.getProperty("user.dir")+
					"/Build/ou-cs-hci/src/main/java/edu/ou/cs/hci/stages/"+
					"small_placeholder.jpeg";
			ImageIcon icon = new ImageIcon(picname);
			
			//icons and option labels
			createIcon(icon, "Artist View", viewPanel,10,30,10,13);
			createIcon(icon, "Featured", viewPanel,10,0,10,0);
			createIcon(icon, "Album View", viewPanel,10,30,10,13);
			createIcon(icon, "Recommended", viewPanel,10,0,10,0);
			createIcon(icon, "List View", viewPanel,10,30,10,13);
			createIcon(icon, "Saved", viewPanel,10,0,10,0);

			subPanel.add(viewPanel);
		}
		
		//******************************************************************
		//creates icon and labels
		private void createIcon(ImageIcon icon, String text, JPanel viewPanel, 
				int top, int left, int bottom, int right)
		{
			JLabel lab = new JLabel(icon);
			lab.setBorder( new EmptyBorder( top,left,bottom,right ) );
		    lab.setText(text);
		    lab.setHorizontalAlignment(SwingConstants.LEFT);
		    lab.setHorizontalTextPosition(JLabel.RIGHT);
		    lab.setVerticalTextPosition(JLabel.CENTER);
		    viewPanel.add(lab);
		}
		
		//******************************************************************
		//panel for selected album cover, description, and songs
		private void createSelectedPanel(JPanel subPanel)
		{
			JPanel selectPanel = new JPanel();
			selectPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			selectPanel.setLayout(new GridLayout(3,1, 0, 0));
			
			//cover image
			String picname = System.getProperty("user.dir")+
					"/Build/ou-cs-hci/src/main/java/edu/ou/cs/hci/stages/"+
					"large_placeholder.jpeg";
			
			ImageIcon icon = new ImageIcon(picname);
			String text = "<html>Selected Album Title<br/>Description<br/>"+
					"More Description<br/>1. Song Name 2. Song Name<br/>"+
					"3. Song Name 4. Song Name<br/>...<html/>";
			
			//attach image to label
			JLabel lab = new JLabel(icon);
			lab.setBorder( new EmptyBorder( 16, 5, 16, 0 ) );
		    lab.setText(text);
		    lab.setHorizontalTextPosition(JLabel.CENTER);
		    lab.setVerticalTextPosition(JLabel.BOTTOM);
		    selectPanel.add(lab);
		    
		    subPanel.add(selectPanel);
		}
	} //end west panel class
	
	//**********************************************************************
	// EastPanel -- filtering options and filler for any more buttons 
	//**********************************************************************
	
	private static final class EastPanel extends JPanel
	{
		public EastPanel() {}

		public JPanel createPanels()
		{
			//creates a panel for filtering options (and filler for more buttons)
			JPanel subPanel = new JPanel();
			subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
			subPanel.setAlignmentX(LEFT_ALIGNMENT);
			Border margin = new EmptyBorder(10,15,10,15);
			subPanel.setBorder(margin);
			
			//filtering check boxes for popular Kpop groups
			JLabel genreLab = new JLabel("Popular Groups");
			genreLab.setHorizontalAlignment(SwingConstants.LEFT);
			genreLab.setHorizontalTextPosition(JLabel.CENTER);
			genreLab.setVerticalTextPosition(JLabel.BOTTOM);
			subPanel.add(genreLab);
			
		    addCheck(subPanel, "SHINee");
		    addCheck(subPanel, "EXO");
		    addCheck(subPanel, "BIGBANG");
		    addCheck(subPanel, "Super Junior");
		    addCheck(subPanel, "BTS");
		    addCheck(subPanel, "BLACKPINK");
		    addCheck(subPanel, "Red Velvet");
		    
		    //filtering check boxes for album year
		    JLabel timeLab = new JLabel("<html><br/>Year<html/>");
		    timeLab.setHorizontalAlignment(SwingConstants.LEFT);
		    timeLab.setHorizontalTextPosition(JLabel.CENTER);
		    timeLab.setVerticalTextPosition(JLabel.BOTTOM);
			subPanel.add(timeLab);
			
		    addCheck(subPanel, "2018");
		    addCheck(subPanel, "2017");
		    addCheck(subPanel, "2016");
		    addCheck(subPanel, "2015");
		    addCheck(subPanel, "2014");
		    addCheck(subPanel, "2013");
		    addCheck(subPanel, "2012");
		    addCheck(subPanel, "2011");
		    
		    //reserve space for other buttons
		    JLabel lab = new JLabel("<html>...<br/>Insert more filtering<br/>"+
		    "options (or other types of<br/>buttons) as necessary<html/>");
		    lab.setHorizontalAlignment(SwingConstants.LEFT);
		    lab.setHorizontalTextPosition(JLabel.CENTER);
		    lab.setVerticalTextPosition(JLabel.BOTTOM);
			subPanel.add(lab);
		    
			return subPanel;
		}
		
		//******************************************************************
		//create a check box for a filtering option
		private void addCheck(JPanel subPanel, String text)
		{
			JCheckBox category = new JCheckBox(text);
		    category.setMnemonic(KeyEvent.VK_C); 
		    category.setSelected(false);
		    category.setAlignmentX(Component.LEFT_ALIGNMENT);
			
		    subPanel.add(category);
		}
		
	} // end east panel class
	
	// **********************************************************************
	// ScenarioPanel -- displays scenarios and scriptions for three types of
	// potential users
	// **********************************************************************
	private static final class ScenarioPanel extends JPanel {
		public ScenarioPanel() {

			this.setLayout(new BorderLayout());

			// creates model to hold list of scenario labels
			DefaultListModel<String> scenarioListModel = new DefaultListModel<>();

			ArrayList<String> scenarios = Resources.getLines("scenarios/titles.txt");
			ArrayList<String> descriptions = Resources.getLines("scenarios/descriptions.txt");

			// warning windows for when there are no scenarios in the text files
			if (scenarios.size() == 0) {
				// give warning of no scenarios in text files
				JFrame noScenarioFrame = new JFrame();
				JOptionPane.showMessageDialog(noScenarioFrame,
				    "Warning No scenario titles were found.",
				    "No Scenario Title Warning",
				    JOptionPane.WARNING_MESSAGE);
			}
			
			if (descriptions.size() == 0) {
				// give warning of no scenarios in text files
				JFrame noScenarioFrame = new JFrame();
				JOptionPane.showMessageDialog(noScenarioFrame,
				    "Earning: No scenario description were found.",
				    "No Scenario Description Warning",
				    JOptionPane.WARNING_MESSAGE);
			}
			
			
			// fills scenario model with labels from text fields
			for (String label : scenarios) {
				scenarioListModel.addElement(label);
			}

			// fills jlist with labels from scenario model
			JList<String> labels = new JList<String>(scenarioListModel);

			labels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			add(labels, BorderLayout.WEST);

			JTextArea description = new JTextArea();
			add(description, BorderLayout.CENTER);
			
			//initially selects a scenario and displays the description
			labels.setSelectedIndex(0);
			description.setText(descriptions.get(labels.getSelectedIndex()));
			description.setLineWrap(true);
			labels.setBorder(BorderFactory.createLineBorder(Color.BLACK));			

			labels.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent le) {
					description.setText(descriptions.get(labels.getSelectedIndex()));
					description.setLineWrap(true);
				}
			});

		}
	}
} // end Stage2 class

//******************************************************************************
