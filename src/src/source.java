package src;

import java.applet.*;  // import the java.applet packages
 import java.awt.*; // import the java.awt packages
 import java.awt.event.*;    // import the java.awt.event packages
 import java.util.Arrays;

 import javax.swing.*; // import the javax.swing packages used for messageboxes

 public class source extends Applet implements MouseListener,ActionListener,Runnable {
  
  // Declare data types labels, TextFields, Buttons, Strings, FontMetrics, Threads, Arrays and Integers  variables.
  Label typetxtlbl,txtstyllbl;
  Button Largerbtn, Smallerbtn, Clearbtn, Animate, Pause;     
  TextField typedtxtfield,styletxtfield;
  char sl1 = 'a', sl2 = 'z';
  String typedtextstr,typedstylestr,tempholder;
  int x,y,size,tempxc,tempyc,counter,sizeaverage,fontmitval;
  double circlecp;
  FontMetrics fm;
  Thread animator = new Thread(this);
  // Image backgroundsl1;
  boolean goahead = false;
  boolean pauseanimation = false;
  String retaintextstr[] = new String[999];
  String retainstylestr[] = new String[999];
  int retainxc[] = new int[999];
  int retainyc[] = new int[999];
  int retainsize[] = new int[999];
  int retaincounter[] = new int[999];
  int retainfontmitval[] = new int[999];
   
   // Initialise the sl1 and sl2 coordinates points arrays variable
   int sl1cpx[] = {7,2,0,7,14,12,7,5,9,10,4};
   int sl1cpy[] = {4,14,14,0,14,14,4,8,8,10,10};
   int sl2cpx[] = {14,0,0,12,0,0,14,14,2,14,14};
   int sl2cpy[] = {14,14,11,2,2,0,0,3,12,12,14};
   
   // Initialise the static sl1 and sl2 coordinates points arrays
   int sl1staticcpx[] = {7,2,0,7,14,12,7,5,9,10,4};
   int sl1staticcpy[] = {4,14,14,0,14,14,4,8,8,10,10};
   int sl2staticcpx[] = {14,0,0,12,0,0,14,14,2,14,14};
   int sl2staticcpy[] = {14,14,11,2,2,0,0,3,12,12,14};

  
   // Arranger method for the sl1 x and y coordinate points that get called each alteration to the Applet
   // and it does the calculations for the initials position and size
   public void arrangersl1(int xc,int yc,double sizemod)
   { 
       for (int q=0; q < sl1cpx.length; q++){
       sl1cpx[q] = (int)( (double)sl1staticcpx[q] * (double)sizemod/(sizeaverage * 9.56) ) + xc;
       sl1cpy[q] = (int)( (double)sl1staticcpy[q] * (double)sizemod/(sizeaverage * 9.56) ) + yc;
       }
   }
  
   // Arranger method for the sl2 x and y coordinate points that get called each alteration to the Applet
   // and it does the calculations for the initials position and size
   public void arrangersl2(int xc,int yc,double sizemod)
   { 
       for (int q=0; q < sl2cpx.length; q++){
       sl2cpx[q] = (int)( (double)sl2staticcpx[q] * (double)sizemod/(sizeaverage * 9.56) ) + xc;
       sl2cpy[q] = (int)( (double)sl2staticcpy[q] * (double)sizemod/(sizeaverage * 9.56) ) + yc;
       }
   }
   
   // init function called once the Applet launched and within it the start default values for the variable, button etc has been initialised
     public void init()
     {
      // backgroundsl1 = getImage(getDocumentBase(),"http://www.lywomen.com/dry_erase_board_background.png");
         typedtextstr = " ";
         typedstylestr = " ";
         tempholder = "";
         x = 0;
         y = 0;
         size = 70;
         sizeaverage = 2;
         circlecp = 0;
         counter = 0;
         setSize(900,700);
      // Initialise and add the labels to annotate the Enter Text TextField along with the Text Field.
      typetxtlbl = new Label("Enter Text:");
      add(typetxtlbl);
      typedtxtfield = new TextField(15);
      add(typedtxtfield);
      typedtxtfield.addActionListener(
          new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if (!(typedtxtfield.getText().equals(""))) {
                  if (styletxtfield.getText().toLowerCase().equals("")){
                   typedstylestr = "normal style";
                  }
                  typedtextstr = typedtxtfield.getText().replaceAll("\\s","");
                  repaint();
                  goahead = true;
                  } else {
                    JOptionPane.showMessageDialog(null,
                    "Sorry, you must enter a word or short sentence \n in the TextField first.", "Notification!",
                    JOptionPane.ERROR_MESSAGE);
                    goahead = false;
                    typedtxtfield.setText("");
                  }
             }
           }
        );
      
      // Initialise and add the labels to annotate the Enter Style TextField along with the Text Field.
      txtstyllbl = new Label("Enter style:");
      add(txtstyllbl);
      styletxtfield = new TextField(15);
      add(styletxtfield);
      styletxtfield.addActionListener(
          new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if ((styletxtfield.getText().toLowerCase().equals("d") || styletxtfield.getText().toLowerCase().equals("i")  || styletxtfield.getText().toLowerCase().equals("z") || styletxtfield.getText().toLowerCase().equals("s") || styletxtfield.getText().toLowerCase().equals("c") || styletxtfield.getText().toLowerCase().equals("x"))) {
                     if ((typedtxtfield.getText().equals(""))) {
                             JOptionPane.showMessageDialog(null,
                             "Sorry, you must enter a word or short sentence \n in the TextField first.", "Notification!",
                             JOptionPane.ERROR_MESSAGE);
                     } else {
                         typedstylestr = styletxtfield.getText().toLowerCase();
                         counter = counter - 1;
                         repaint();
                     }
                   } else {
                  counter = counter - 1;
                     typedstylestr = "normal style";
                     repaint();
                     JOptionPane.showMessageDialog(null,
                             "Sorry, This style you have entered is not recognised.\n The working style are the following:-\n1- Diagonal Style Enter i\n-2 Vertical Style Enter d\n-3 Zigzag Style Enter z\n-4 Circle Style Enter c\n-5 Square Style Enter s\n-6 One-Vertex Style Enter x !New\n-7 Click OK for no style", "Notification!",
                             JOptionPane.ERROR_MESSAGE);
                             styletxtfield.setText("");
                        }
             }
           }  
      );
      
      // Initialise and add the Larger and Smaller Buttons function to increase/decrease the size of the text has been entered.
      Largerbtn = new Button("Larger");
      Smallerbtn = new Button("Smaller");

      // Initialise text size modification functions.
      add(Largerbtn);
      Largerbtn.addActionListener(
        new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             size = size + (sizeaverage/sizeaverage);
             counter = counter - 1;
             repaint(); 
             }
           }
        );
      add(Smallerbtn);
      Smallerbtn.addActionListener(
          new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              size = size - (sizeaverage/sizeaverage);
              counter = counter - 1;
              repaint(); 
             }
           }
        
        );
      
      // Initialise and add the Animation buttons pause and animate
      Animate = new Button("Animate");
      Pause = new Button("Pause");
      add(Animate);
      add(Pause);
      Pause.setEnabled(false);
      
      // Initialise the animate and pause button function
      Animate.addActionListener(
          new ActionListener() {
          public void actionPerformed(ActionEvent e) {
         if (goahead){
          Pause.setEnabled(true);
          Animate.setEnabled(false);
          pauseanimation = true;
          start();
          counter = counter - 1;
          repaint();
             }
         }
           }
        );
      Pause.addActionListener(
          new ActionListener() {
          public void actionPerformed(ActionEvent e) {
         Animate.setEnabled(true);
         Pause.setEnabled(false);
         pauseanimation = false;
         stop();
         counter = counter - 1;
         repaint();
             }
           }
        
        );
      
      // Initialise and add the clear button and its function to clear the screen
      Clearbtn = new Button("Clear");
      add(Clearbtn);
      Clearbtn.addActionListener(
          new ActionListener() {
           public void actionPerformed(ActionEvent e) {
            typedtextstr = " ";
            typedstylestr = " ";
            x = 0;
            y = 0;
            size = 70;
            circlecp = 0;
            counter = 0;
            tempxc = 0;
            tempyc = 0;
            Arrays.fill(retaintextstr, null);
            Arrays.fill(retainstylestr, null);
            Arrays.fill(retainxc, 0);
            Arrays.fill(retainyc, 0);
            Arrays.fill(retainsize, 0);
            Arrays.fill(retaincounter, 0);
            Clearbtn.setEnabled(false);
            repaint();
             }
           }
        );    
       addMouseListener(this);
     }
     
     //Called when the applet is ended/destroyed.
     public void destroy(){
     // Good bye message
     }
     
     // Called when the Applet stop function been called within the code
     // Get called automaticlly once the Applet ended
     public void stop() {
     // Stop the animation
  animator = null;
     }
     
     // Called when the Applet start function been called within the code
     // Get called automaticlly once the Applet lauched
     public void start()
     {
      // Set Applet window size along with start animation if button animate been clicked
       setSize(900,700);
       setBackground(Color.white);
      if (pauseanimation) {
           Thread animator = new Thread(this);
     animator.start();  
      }
      
     }
     
     // Set the animation runnar with it function using thread and try and catch
     public void run() {
  while (Thread.currentThread() == animator) {
   if (tempxc < 900) {
   tempxc = tempxc + 1;
   } else if (tempxc > 900) {
   tempxc = -450;
   }
      // Delay with 10 MILISECOND each animation.
      try {
   Thread.sleep(10);
      } catch (InterruptedException e) {
   break;
      }
   repaint();
            // Display the next animation.
      counter = counter - 1;
  }
     }
     
     // Event played each mouse click and does retrieve the mouse location and repaint
     public void mouseClicked(MouseEvent e)
     {
      tempxc = e.getX() - 64;
      tempyc = e.getY() - 104;
      counter = counter - 1;
      repaint();
     }
     
     // Not used Mouse event been squashed altogether to save lines
     public void actionPerformed(ActionEvent e){} public void mousePressed(MouseEvent e){} public void mouseReleased(MouseEvent e){} public void mouseEntered(MouseEvent e){} public void mouseExited(MouseEvent e){}
     
     public void paint(Graphics g)
     {
      
      // Draw a background image for the applet
      // g.drawImage(backgroundsl1, x, y, 900, 700, Color.black,this);
      
         // Function Java 2D Graphics to anti-aliasing the drawn initials and texts for the quality purposes
         Graphics2D g2d = (Graphics2D)g;
         g2d.addRenderingHints(new RenderingHints( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON)); 
         g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         g = g2d;
         
         // Get the accurate position of last mouse click 
         x = x + (tempxc - x);
         y = y + (tempyc - y);
         
         // Initialise the default x and y positions
         x = x + 64;
         y = y + 104;
         
         // Declare and initialise font properties
         Font font = new Font("Arial", Font.PLAIN, size);
         // Get the font metrics to find the Ascent of the font to make the increase and decrease size 
         // algorithm increase the size equally with the draw initials and to change the draw string baseline
         // from bottom top To top bottom which will make the text all togather in the same baseline
         fm = g.getFontMetrics();
   fontmitval = fm.getAscent() + (size - fm.getAscent());
         g.setFont(font);
         g.setColor(Color.black);
         
         // Draw a small rectangle at the top left position to annotate it as a brush
         // which will show how decrease and increase size get increased equally in same position
         g.fillRect(x ,y , 4, 6);
  

         // Check if the Enter text field is not empty if not then apply the draw methods
         if  (!(typedtextstr.equals(""))) {
    
     // Each case enable clear button in case new text been entered and clear button been clicked once before
         Clearbtn.setEnabled(true);
        
        // Arrays variable to store the entered texts and use it to redraw new old 
        // ones including new one on each time you enter new text
      retainsize[counter] = size;
      retainfontmitval[counter] = fontmitval;
      retainxc[counter] = x;
      retainyc[counter] = y;
      retaintextstr[counter] = typedtextstr;
      retainstylestr[counter] = typedstylestr;
      retaincounter[counter] = counter;
        
      // Body for loop go through the counter which holds value of how many times different text have been entered
      for (int k = 0;k <= counter; k++) {
   
   // start with the first array been entered if there was no value it save it and stop the loop
   // start loop again if new value been entered print all old values including the new
   fontmitval = retainfontmitval[k];
   size = retainsize[k];
   x = retainxc[k];
   y = retainyc[k];
   typedtextstr = retaintextstr[k];
   typedstylestr = retainstylestr[k];
   
   // Declare and initialise the char array to hold the entered text and split it into character array
   char splitletter[] = typedtextstr.toCharArray();
  
   // Inside for loop to go through the above declared char array characters and display them accordingly
   // if my initials draw them with fill polygon if not draw them with draw string
   for (int i =0; i < splitletter.length; i++) {
   
   // Inside the loop take the first character to draw
       tempholder = tempholder + splitletter[i];

       // if statement will run if the drawin style is chosen to be circle
       // The function is to determine the x and y position of each character position
       if (typedstylestr.toLowerCase().charAt(0) == 'c') {
           circlecp = circlecp + (2*Math.PI/splitletter.length);
       }
       
       if (tempholder.toLowerCase().charAt(0) == sl1) {
           g.setColor(Color.red);
           arrangersl1(x,y,size);
           g.fillPolygon(sl1cpx, sl1cpy, sl1cpx.length);
          } else if (tempholder.toLowerCase().charAt(0) == sl2) {
           g.setColor(Color.blue);
           arrangersl2(x,y,size);
           g.fillPolygon(sl2cpx, sl2cpy, sl2cpx.length);
          } else {
           g.setColor(Color.black);
           g.drawString(tempholder,x,(int)((y + fontmitval)-((double)size/3.8)));
        } 
       
       // Switch statement for enter style field to determine style to draw text with
       // relying on the entered first character in the field
       switch (typedstylestr.charAt(0)) {
       //  Vertical drawing style
       case 'd':
           y = y + size;
       break;
       // Diagonally drawing style
       case 'i':
           x = x + size;
           y = y + size;
       break;
       // Zigzag drawing style
       case 'z':
          if (i % 2 == 0) {
          y = y + size;
          x = x + size;
          } else {
          x = x - size; 
          y = y + size;
          }
       break;
       // Square drawing style
       case 's':
           if(splitletter.length % 2 == 0){
               if (i < (splitletter.length/4) * 1){
              x = x + size;        
               } else if (i < (splitletter.length/4) * 2){
              y = y + size;
               } else if (i < (splitletter.length/4) * 3){
              x = x - size;
               } else if (i < (splitletter.length/4) * 4){
              y = y - size;
           }        
           } else {
               if (i < (splitletter.length/4) * 1){
              x = x + size;        
               } else if (i <= (splitletter.length/4) * 2){
              y = y + size;
               } else if (i <= (splitletter.length/4) * 3){
              x = x - size;
               } else{
              y = y - size;
               }   
           }
       break;
       // Circle drawing style
       case 'c':
       x = x + (int)((splitletter.length+size) * Math.cos(circlecp));
       y = y + (int)((splitletter.length+size) * Math.sin(circlecp));
       break;
       // One vertex drawing style (My invented one) 
       case 'x': 
          if (i < (splitletter.length/2) * 1){
          x = x + size;        
          } else if (i < (splitletter.length/2) * 2){
          y = y + size;
          } else if (i < (splitletter.length/2) * 3){
          x = x - size;
          } else if (i < (splitletter.length/2) * 4){
          y = y - size;
          }  
       break;
       default: 
        // Normal drawing style - Horizontal
           x = x + size;
       break;
       }
       // Clear the current character variable to make ready for the next character
       tempholder = "";
    }
   // clear the current circle c point to make the circle function ready for any other new circle drawing
  circlecp = 0;
    }
      // Increase the counter to tell that new text been entered to main loop so can loop through each
        // each time you enter new text and draw them inclusive
         counter = counter + 1;
  }  

   }
 }
