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
import java.util.Arrays;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
public final class Stage3
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
		JFrame frame			= new JFrame("Wireframe");
		
		// create scenario frame
		JFrame secondFrame = new JFrame("Scenarios and Personas");
		
		// create questionnaire frame
		JFrame thirdFrame = new JFrame("Questionnaire");
		
		//wire frame panels
		JPanel centerPanel	= new CenterPanel();
		JPanel westPanel		= new WestPanel();
		JPanel eastPanel		= new EastPanel();
		
		JPanel leftPanel		= new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		centerPanel.add(((CenterPanel) centerPanel).createPanels());
		westPanel.add(((WestPanel) westPanel).createPanels());
		eastPanel.add(((EastPanel) eastPanel).createPanels());
		
		leftPanel.add(centerPanel, BorderLayout.CENTER);
		leftPanel.add(westPanel, BorderLayout.WEST);
		leftPanel.add(eastPanel, BorderLayout.EAST);
		
		//scenario panels
		JPanel scenarioPanel = new ScenarioPanel();
		
		//questionnaire panels
		JPanel questionPanel = new QuestionPanel();
		((QuestionPanel) questionPanel).createQuestions(thirdFrame);
		
		//create layout for the frame
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		frame.setBounds(50, 50, 1300, 1300); //set window size
		
		// create layout for the second frame
		secondFrame.add(scenarioPanel);
		secondFrame.setBounds(50, 50, 1000, 500); // set window size
		
		// create layout for the third frame
		thirdFrame.add(questionPanel);
		thirdFrame.setBounds(50, 50, 1000, 1300); // set window size
		
		frame.setVisible(false);
		secondFrame.setVisible(false);
		thirdFrame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}

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
				    "Warning: No scenario description were found.",
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
	
	// **********************************************************************
		// QuestionPanel -- displays questions for questionnaire
		// **********************************************************************
		private static final class QuestionPanel extends JPanel {
			public QuestionPanel() {
			}
			public void createQuestions(JFrame frame) {
				this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
				boolean[] flags = new boolean[5];
				String[] questions = {"1. Which filtering options should be used in the application?",
				                      "2. On average, how often would you use this application per week?",
				                      "3. How much do you like or dislike K-pop music?",
				                      "4. How long have you been listening to K-pop music?",
				                      "5. Do you have any suggestions on improving the application interface?"};
				
				//create finish Button
				JPanel finishPanel = new JPanel();
		        JButton fbutton = new JButton("Finish");
				
				//Question 1
				JPanel checkPanel = new JPanel();
				JCheckBox[] check = question1(fbutton, checkPanel, flags, questions[0]);
				
			    //Question 2
			    JPanel spinPanel = new JPanel();
				JSpinner spinner = question2(fbutton, spinPanel, flags, questions[1]);
				
		        
		        //Question 3
		        JPanel slidePanel = new JPanel();
		        JSlider slider = question3(fbutton, slidePanel, flags, questions[2]);
		        
		        //Question 4
		        JPanel radioPanel = new JPanel();
		        JRadioButton[] radio = question4(fbutton, radioPanel, flags, questions[3]);
		        
		        //Question 5
		        JPanel textPanel = new JPanel();
		        JTextArea text = question5(fbutton, textPanel, flags, questions[4]);
		        
		        // call finish button
		        finish(frame, fbutton, finishPanel, questions, spinner, slider, text, radio, check);
				
				
				checkPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
				spinPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
				slidePanel.setAlignmentX( Component.LEFT_ALIGNMENT );
				radioPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
				textPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
				
				add(checkPanel);
				add(spinPanel);
				add(slidePanel);
				add(radioPanel);
				add(textPanel);
				add(finishPanel);

			}

			//******************************************************************
			//make the finish button
			private void finish(JFrame frame, JButton fbutton, JPanel finishPanel, String[] questions,
					JSpinner spinner, JSlider slider, JTextArea text, 
					JRadioButton[] radio, JCheckBox[] check)
			{
				//Finish Button
		        fbutton.setEnabled(false);
			    ActionListener factionListener = new ActionListener() {
			        public void actionPerformed(ActionEvent actionEvent) {
			        	
			        	  frame.setVisible(false);
			        	  
			          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();

			          System.out.println(questions[0]);
			          for(int i=0; i<check.length; i++)
			          {
			        	  	//System.out.println(radio[i]);
			        	  	if(check[i].isSelected())
			        	  		System.out.println(check[i].getText());
			          }
			          
			          System.out.println(questions[1]);
			          System.out.println(spinner.getValue());
			          System.out.println(questions[2]);
			          
			          String answer2 = "";
			          switch(slider.getValue())
			          {
			          case 1: answer2 = "Strongly Dislike";
			          	break;
			          case 2: answer2 = "Dislike";
			          	break;
			          case 3: answer2 = "Slightly Dislike";
			          	break;
			          case 4: answer2 = "Neutral";
			          	break;
			          case 5: answer2 = "Slightly Like";
			          	break;
			          case 6: answer2 = "Like";
			          	break;
			          case 7: answer2 = "Strongly Like";
			          	break;
			          }
			          System.out.println(answer2);
			          
			          System.out.println(questions[3]);
			          for(int i=0; i<radio.length; i++)
			          {
			        	  	//System.out.println(radio[i]);
			        	  	if(radio[i].isSelected())
			        	  		System.out.println(radio[i].getText());
			          }
			          
			          System.out.println(questions[4]);
			          System.out.println(text.getText());
			        }
	
			      };
			    fbutton.addActionListener(factionListener);
				finishPanel.add(fbutton);
			}
			
			//******************************************************************
			//make question 1 (check boxes) 
			private JCheckBox[] question1(JButton fbutton, JPanel checkPanel, boolean[] flags, String question)
			{
				checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.PAGE_AXIS));
				checkPanel.setAlignmentX(LEFT_ALIGNMENT);
				Border margin = new EmptyBorder(10,15,10,15);
				checkPanel.setBorder(margin);
				JCheckBox check[] = new JCheckBox[7];
				
				JLabel lab1 = new JLabel(question);
				lab1.setHorizontalAlignment(SwingConstants.LEFT);
				lab1.setHorizontalTextPosition(JLabel.CENTER);
				lab1.setVerticalTextPosition(JLabel.BOTTOM);
				checkPanel.add(lab1);
				ButtonGroup checkButtonGroup = new ButtonGroup();
				
				check[0] = addCheck(check[0], checkPanel, checkButtonGroup, fbutton, flags, "Popular groups");
				check[1] = addCheck(check[1], checkPanel, checkButtonGroup, fbutton, flags, "Year released");
				check[2] = addCheck(check[2], checkPanel, checkButtonGroup, fbutton, flags,"Genre");
				check[3] = addCheck(check[3], checkPanel, checkButtonGroup, fbutton, flags,"Price");
				check[4] = addCheck(check[4], checkPanel, checkButtonGroup, fbutton, flags,"Song length");
				check[5] = addCheck(check[5], checkPanel, checkButtonGroup, fbutton, flags, "Tone (e.g. upbeat, sad, etc.)");
				check[6] = addCheck(check[6], checkPanel, checkButtonGroup, fbutton, flags,"Speed (e.g. fast, slow, etc.)");
			    
				return check;
			}
			
			//******************************************************************
			//make question 2 (spinner) 
			private JSpinner question2(JButton fbutton, JPanel spinPanel, boolean[] flags, String question)
			{
				spinPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
				spinPanel.setAlignmentX(LEFT_ALIGNMENT);
				Border margin = new EmptyBorder(10,15,10,15);
				spinPanel.setBorder(margin);
				
			    int curr = 0;
		        SpinnerModel spinModel = new SpinnerNumberModel(curr, //initial value
		                                       curr, //min
		                                       curr + 100, //max
		                                       1); //step
		        JLabel lab2 = new JLabel(question);
		        lab2.setAlignmentX( Component.LEFT_ALIGNMENT );
		        spinPanel.add(lab2);
		 
		        JSpinner spinner = new JSpinner(spinModel);
		        lab2.setAlignmentX(Component.LEFT_ALIGNMENT);
		        spinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		        
		        spinPanel.add(lab2);
		        spinPanel.add(spinner);
		        
		        ChangeListener spinListener = new ChangeListener() {
		            public void stateChanged(ChangeEvent e) {
			        	  flags[1] = true;
			        	  if(flags[0] && flags[1]&& flags[2]&& flags[3]&& flags[4])
			        		  fbutton.setEnabled(true);
		            }
		          };

		        spinner.addChangeListener(spinListener);
		        
		        return spinner;
			}
			
			//******************************************************************
			//make question 3 (slider)
			private JSlider question3(JButton fbutton, JPanel slidePanel, boolean[] flags, String question)
			{
				slidePanel.setLayout(new BoxLayout(slidePanel, BoxLayout.PAGE_AXIS));
		        slidePanel.setAlignmentX(LEFT_ALIGNMENT);
		        Border margin = new EmptyBorder(10,15,10,15);
				slidePanel.setBorder(margin);
		        
		        JLabel lab3 = new JLabel(question);
		        slidePanel.add(lab3);
		        
		        JSlider slider = new JSlider(1,7,4);
		        Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		        table.put(1, new JLabel("Strongly Dislike"));
		        table.put(2, new JLabel("Dislike"));
		        table.put(3, new JLabel("Slightly Dislike"));
		        table.put(4, new JLabel("Neutral"));
		        table.put(5, new JLabel("Slightly Like"));
		        table.put(6, new JLabel("Like"));
		        table.put(7, new JLabel("Strongly Like"));
		        slider.setLabelTable(table);
		        slider.setPaintLabels(true);
		        slider.setMinorTickSpacing(1);
		        slider.setPaintTicks(true);
		        slider.setSnapToTicks(true);
		        
		        Dimension d = slider.getPreferredSize();
		        slider.setPreferredSize(new Dimension(d.width+500,d.height));
		        
		        slidePanel.add(slider);
		        
		        ChangeListener slideListener = new ChangeListener() {
		            public void stateChanged(ChangeEvent e) {
			        	  flags[2] = true;
			        	  if(flags[0] && flags[1]&& flags[2]&& flags[3]&& flags[4])
			        		  fbutton.setEnabled(true);
		            }
		          };

		          slider.addChangeListener(slideListener);
		          
		          return slider;
			}
			
			//******************************************************************
			//make question 4 (radio buttons)
			private JRadioButton[] question4(JButton fbutton, JPanel radioPanel, boolean[] flags, String question)
			{
				JRadioButton[] radio = new JRadioButton[5];
				radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
		        radioPanel.setAlignmentX(LEFT_ALIGNMENT);
		        Border margin = new EmptyBorder(10,15,10,15);
		        radioPanel.setBorder(margin);
		        
		        JLabel lab4 = new JLabel(question);
		        radioPanel.add(lab4);

		        ButtonGroup buttonGroup = new ButtonGroup();
		        
		        radio[0] = addRadio(radio[0], radioPanel, buttonGroup, fbutton, flags,"I don’t listen to K-pop music.");
		        radio[1] = addRadio(radio[1], radioPanel, buttonGroup, fbutton, flags,"less than a year");
		        radio[2] = addRadio(radio[2], radioPanel, buttonGroup, fbutton, flags,"1-2 years");
		        radio[3] = addRadio(radio[3], radioPanel, buttonGroup, fbutton, flags,"3-4 years");
		        radio[4] = addRadio(radio[4], radioPanel, buttonGroup, fbutton, flags,"longer than 4 years");
		        
		        return radio;
			}
			
			//******************************************************************
			//make question 5 (text)
			private JTextArea question5(JButton fbutton, JPanel textPanel, boolean[] flags, String question)
			{
				JTextArea text = new JTextArea(10, 50);
		        JScrollPane scroll = new JScrollPane(text);
		        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		        
				textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
				textPanel.setAlignmentX(LEFT_ALIGNMENT);
				Border margin = new EmptyBorder(10,15,10,15);
				textPanel.setBorder(margin);
		        
		        JLabel lab5 = new JLabel(question);
		        //lab5.setAlignmentX( Component.LEFT_ALIGNMENT );
		        textPanel.add(lab5);
				
				//set default text
		        text.setAlignmentX( Component.LEFT_ALIGNMENT );
				text.setText("Type response here");
				text.setLineWrap(true);	
				textPanel.add(scroll);
				
		        CaretListener textListener = new CaretListener() {
					@Override
					public void caretUpdate(CaretEvent e) {
			        	  flags[4] = true;
			        	  if(flags[0] && flags[1]&& flags[2]&& flags[3]&& flags[4])
			        		  fbutton.setEnabled(true);
						
					}
		          };

		          text.addCaretListener(textListener);
		          
		          return text;
			}
			
			//******************************************************************
			//create a check box 
			private JCheckBox addCheck(JCheckBox category, JPanel subPanel, ButtonGroup bg, JButton fbutton, boolean[] flags, String text)
			{
				category = new JCheckBox(text);
			    category.setMnemonic(KeyEvent.VK_C); 
			    category.setSelected(false);
			    category.setAlignmentX(Component.LEFT_ALIGNMENT);
			    
			    ActionListener actionListener = new ActionListener() {
			        public void actionPerformed(ActionEvent actionEvent) {
			        	  flags[0] = true;
			        	  if(flags[0] && flags[1]&& flags[2]&& flags[3]&& flags[4])
			        		  fbutton.setEnabled(true);
			        }
			      };
			    category.addActionListener(actionListener);
				
			    
			    //bg.add(category); //uncomment to make mutually exclusive
			    subPanel.add(category);
			    return category;
			    
			}
			//******************************************************************
			//create a radio button 
			private JRadioButton addRadio(JRadioButton category, JPanel subPanel, ButtonGroup bg, JButton fbutton, boolean[] flags, String text)
			{
				category = new JRadioButton(text);
			    category.setMnemonic(KeyEvent.VK_C); 
			    category.setSelected(false);
			    category.setAlignmentX(Component.LEFT_ALIGNMENT);
			    
			    
			    ActionListener actionListener = new ActionListener() {
			        public void actionPerformed(ActionEvent actionEvent) {
			        	  flags[3] = true;
			        	  if(flags[0] && flags[1]&& flags[2]&& flags[3]&& flags[4])
			        		  fbutton.setEnabled(true);
			        	
			        	
			        	  AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
			          String selected = abstractButton.getText();
			          //System.out.println(selected);
			        }

			      };
			    category.addActionListener(actionListener);
				
			    bg.add(category); //uncomment to make mutually exclusive
			    subPanel.add(category);
			    
			    return category;
			}
		}
} // end Stage3 class

//******************************************************************************
