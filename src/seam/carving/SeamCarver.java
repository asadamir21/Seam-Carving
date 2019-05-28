
package seam.carving;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public final class SeamCarver{
    Picture picture;
    private final double[][] energy;
    int count = 0;
    
    public SeamCarver(Picture picture){ 
        this.picture = picture;
        this.energy = new double[height()][width()];
        
        
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energy[i][j] = energy(i, j);
            }
        }
    }
    
    public Picture picture(){ 
        return picture;
    }
    
    public int width(){ 
        return this.picture.width();
    }
     
    public int height(){ 
        return this.picture.height();
    }

    public double energy(int y, int x) {// energy of pixel at column x and row y
        if (x >= width() || y >= height() || x < 0 || y < 0){
            System.out.println("Boundaries out of Range");
            System.exit(0);
        }
        else if(isBorder(x,y)){
            if(x == 0 && y == 0){
                Color up = new Color(picture.get(x, height() - 1).getRGB());
                Color down = new Color(picture.get(x, y + 1).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);
            
                Color left = new Color(picture.get(width() - 1, y).getRGB());
                Color right = new Color(picture.get(x + 1, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);
            
                return Math.sqrt(deltaX + deltaY);
            }
            else if(x == width() - 1 || y == height() - 1){
                Color up = new Color(picture.get(x, height() - 1).getRGB());
                Color down = new Color(picture.get(x, 0).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);
            
                Color left = new Color(picture.get(width() - 1, y).getRGB());
                Color right = new Color(picture.get(0, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);
            
                return Math.sqrt(deltaX + deltaY);
            }
            else if(x == width() - 1 || y == 0){
                Color up = new Color(picture.get(x, height() - 1).getRGB());
                Color down = new Color(picture.get(x, y + 1).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);
            
                Color left = new Color(picture.get(x - 1, y).getRGB());
                Color right = new Color(picture.get(0, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);
            
                return Math.sqrt(deltaX + deltaY);
            }
            else if(x == 0 || y == height() - 1){
                Color up = new Color(picture.get(x, y - 1).getRGB());
                Color down = new Color(picture.get(0, y - 1).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);
            
                Color left = new Color(picture.get(width() - 1, y).getRGB());
                Color right = new Color(picture.get(x + 1, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);
            
                return Math.sqrt(deltaX + deltaY);
            }
            else if(x == 0){
                Color up = new Color(picture.get(x, y - 1).getRGB());
                Color down = new Color(picture.get(x, y + 1).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);
            
                Color left = new Color(picture.get(width() - 1, y).getRGB());
                Color right = new Color(picture.get(x + 1, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);
            
                return Math.sqrt(deltaX + deltaY);
            }
            else if(y == 0){
                Color up = new Color(picture.get(x, height() - 1).getRGB());
                Color down = new Color(picture.get(x, y + 1).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);

                Color left = new Color(picture.get(x - 1, y).getRGB());
                Color right = new Color(picture.get(x + 1, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);

                return Math.sqrt(deltaX + deltaY);
            }
            else if(x == width() - 1){
                Color up = new Color(picture.get(x, y - 1).getRGB());
                Color down = new Color(picture.get(x, y + 1).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);

                Color left = new Color(picture.get(x - 1, y).getRGB());
                Color right = new Color(picture.get(0, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);
            
                return Math.sqrt(deltaX + deltaY);
            }
            else if(y == height() - 1){
                Color up = new Color(picture.get(x, y - 1).getRGB());
                Color down = new Color(picture.get(x, 0).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);

                Color left = new Color(picture.get(x - 1, y).getRGB());
                Color right = new Color(picture.get(x + 1, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);

                return Math.sqrt(deltaX + deltaY);
            }
        }
        else{
            if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1)
                return (double) 1000;
            
            else{
                Color up = new Color(picture.get(x, y - 1).getRGB());
                Color down = new Color(picture.get(x, y + 1).getRGB());
                double deltaX = Math.pow(up.getRed() - down.getRed(), 2) + Math.pow(up.getBlue()- down.getBlue(), 2) + Math.pow(up.getGreen()- down.getGreen(), 2);

                Color left = new Color(picture.get(x - 1, y).getRGB());
                Color right = new Color(picture.get(x + 1, y).getRGB());
                double deltaY = Math.pow(left.getRed() - right.getRed() , 2) + Math.pow(left.getBlue()- right.getBlue(), 2) + Math.pow(left.getGreen()- right.getGreen(), 2);

                return Math.sqrt(deltaX + deltaY);
            }
        }
        return -1;
    }
    
    public boolean isBorder(int x, int y){
        if(x == 0 || x == width() - 1 || y == 0 || y == height() - 1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int[] findHorizontalSeam() {// sequence of indices for horizontal seam
        
        double min = Double.POSITIVE_INFINITY;
        int index = 0;
       
        for (int i = 0; i < height(); i++) {
            if(min > energy[i][width()-1]){
                min = energy[i][width()-1];
                index = i;
            }
        } 
        int arr[] = new int[width()];
        int arrcount = width() - 1;
        arr[arrcount] = index;
        arrcount--;
       
        
        for(int i = width() - 1; i > 0; i--){
            if(index == 0){
                double a = Math.min(energy[index][i-1], energy[index+1][i-1]);
                if(a == energy[index][i-1]){
                    arr[arrcount] = index;
                    arrcount--;
                }
                else{
                    arr[arrcount] = index + 1;
                    arrcount--;
                    index = index + 1;
                }
            }
            else if(index == height() - 1){
                double a = Math.min(energy[index][i-1], energy[index-1][i-1]);
                if(a == energy[index][i-1]){
                    arr[arrcount] = index;
                    arrcount--;
                }
                else{
                    arr[arrcount] = index - 1;
                    arrcount--;
                    index = index + 1;
                }
            }
            else{
                double a = Math.min(energy[index+1][i-1], Math.min(energy[index][i-1], energy[index-1][i-1]));
                if(a == energy[index-1][i-1]){
                    arr[arrcount] = index - 1;
                    arrcount--;
                    index = index - 1;
                }
                else if(a == energy[index][i-1]){
                    arr[arrcount] = index;
                    arrcount--;
                }
                else{
                    arr[arrcount] = index + 1;
                    arrcount--;
                    index = index + 1;
                }
            }
        }
        return arr;
    }
     
    public int[] findVerticalSeam(){ 
        double min = Double.POSITIVE_INFINITY;
        int index = 0;
        
        
        for (int i = 0; i < width(); i++) {
            if(min > energy[height() - 1][i]){
                min = energy[height() - 1][i];
                index = i;
            }
        } 
        
        int arr[] = new int[height()];
        int arrcount = height() - 1;
        arr[height() - 1] = index;
        arrcount--;
        
        
        for(int i = height() - 1; i > 0; i--){
            if(index == 0){
                double a = Math.min(energy[i-1][index], energy[i-1][index+1]);
                if(a == energy[i-1][index]){
                    arr[arrcount] = index;
                    arrcount--;
                }
                else{
                    arr[arrcount] = index + 1;
                    arrcount--;
                    index = index + 1;
                }
            }
            else if(index == width() - 1){
                double a = Math.min(energy[i-1][index], energy[i-1][index-1]);
                if(a == energy[i-1][index]){
                    arr[arrcount] = index;
                    arrcount--;
                }
                else{
                    arr[arrcount] = index - 1;
                    arrcount--;
                    index = index + 1;
                }
            }
            else{
                double a = Math.min(energy[i-1][index-1], Math.min(energy[i-1][index], energy[i-1][index+1]));
                if(a == energy[i-1][index-1]){
                    arr[arrcount] = index - 1;
                    arrcount--;
                    index = index - 1;
                }
                else if(a == energy[i-1][index]){
                    arr[arrcount] = index;
                    arrcount--;
                }
                else{
                    arr[arrcount] = index + 1;
                    arrcount--;
                    index = index + 1;
                }
            }
        }
        return arr;
    }
    
    public void removeHorizontalSeam(int[] seam){ 
        Picture newpicture = new Picture(width(), height() - 1);
        
        for (int i = 0; i < width(); i++) {      
            for (int j = 0; j < seam[i]; j++) {
                newpicture.set(i, j, new Color (picture.get(i, j).getRGB()));
            }
            for (int j = seam[i]; j < height()-1; j++) {
                newpicture.set(i, j, new Color (picture.get(i, j+1).getRGB()));
            }
	}
        this.picture = newpicture;
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energy[i][j] = energy(i, j);
            }
        }
    }
     
    public void removeVerticalSeam(int[] seam){ 
        Picture newpicture = new Picture(width() - 1, height());
        
        for (int i = 0; i < height(); i++) {      
            for (int j = 0; j < seam[i]; j++) {
                newpicture.set(j, i, new Color (picture.get(j, i).getRGB()));
            }
            for (int j = seam[i]; j < width()-1; j++) {
                newpicture.set(j, i, new Color (picture.get(j+1, i).getRGB()));
            }
	}
        this.picture = newpicture;
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energy[i][j] = energy(i, j);
            }
        }
    }
    
    public static void main(String[] args){
        JFrame frame1 = new JFrame("Seam Carving"); //frame_name("label")
        frame1.setBounds(500,300,500,300); //(x_crd,y_crd,width,height)
        JMenuBar jmb = new JMenuBar();

        JMenu jmFile = new JMenu ("File");
        JMenuItem jmiExit = new JMenuItem ("Exit");
            
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        frame1.add(jmb);
        frame1.setJMenuBar(jmb);
        
        
        final JTextField filepath = new JTextField();  
        filepath.setBounds(150,50, 200,20);  
        
        
        JButton file = new JButton("Choose File");  
        file.setBounds(30,50,100,20);  
        file.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("choosertitle");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    filepath.setText(f.getPath());
                }
            }  
        });
        JLabel hor = new JLabel("No. of Horizontal Seam");  
        hor.setBounds(50,100, 200,30);  
        final JTextField rows = new JTextField();  
        rows.setBounds(280,105, 50,20);  
        
        JLabel vert = new JLabel("No. of Vertical Seam");  
        vert.setBounds(50,150, 200,30);
        final JTextField column = new JTextField();  
        column.setBounds(280,155, 50,20);  
        
        
        JButton b = new JButton("Seam Carv");  
        b.setBounds(150,200,100,20);  
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                if(filepath.getText().equals("")){
                    JFrame parent = new JFrame();
                    JOptionPane.showMessageDialog(parent, "No Image Path");
                }
                else{
                    String path = filepath.getText();
                    boolean ext = false;
                    
                    String extension = "";
                    int k = path.lastIndexOf('.');
                    int p = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));

                    if (k > p) {
                        extension = path.substring(k+1);
                    }
                    
                    if(extension.equalsIgnoreCase("JPG") || extension.equalsIgnoreCase("PNG") || extension.equalsIgnoreCase("BMP") || extension.equalsIgnoreCase("WEBMP") || extension.equalsIgnoreCase("GIF")){
                        ext = true;
                    } 
                    
                    if(ext){
                        Picture picture = new Picture(path);
                        SeamCarver seamcarv = new SeamCarver(picture);

                        int heig = 0;
                        int wid = 0;
                        boolean flag1 = false;
                        boolean flag2 = false;
                        boolean flag3 = false;
                                
                        if(rows.getText().equals("")){
                            flag3 = true;
                            JFrame parent = new JFrame();
                            JOptionPane.showMessageDialog(parent, "Horizontal Seam is Empty");
                        }
                        else{
                            int check = 0;
                            for (int i = 0; i < rows.getText().length(); i++){
                                if(Character.isDigit(rows.getText().charAt(i))){
                                    check++;
                                }
                            }
                            if(check == rows.getText().length()){
                                flag1 = true;
                            }
                        }

                        if(column.getText().equals("")){
                            flag3 = true;
                            JFrame parent = new JFrame();
                            JOptionPane.showMessageDialog(parent, "Vertical Seam is Empty");
                        }    
                        else{
                            int check = 0;
                            for (int i = 0; i < column.getText().length(); i++) {
                                if(Character.isDigit(column.getText().charAt(i))){
                                    check++;
                                }
                            }
                            if(check == column.getText().length()){
                                flag2 = true;
                            }
                        }
                        
                        if(flag1 && flag2){
                            heig = Integer.parseInt(rows.getText());
                            wid = Integer.parseInt(column.getText());
                            
                            if(heig < seamcarv.height() && wid < seamcarv.width()){
                                seamcarv.picture.show();

                                if(flag1 && heig != 0){
                                    for (int i = 0; i < heig; i++) {
                                        seamcarv.removeHorizontalSeam(seamcarv.findHorizontalSeam());
                                    }
                                }
                                if(flag2 && wid != 0){
                                    for (int i = 0; i < wid; i++) {
                                        seamcarv.removeVerticalSeam(seamcarv.findVerticalSeam());
                                    }
                                }
                                seamcarv.picture.show();
                            }
                            else{
                                JFrame parent = new JFrame();
                                JOptionPane.showMessageDialog(parent, "Horizontal and Vertical Seam Cannot be greater than Image wdth and height");
                            }
                        }
                        else if(!flag1 && !flag3){
                            JFrame parent = new JFrame();
                            JOptionPane.showMessageDialog(parent, "Horizontal Seam must be in Integer");
                        }
                        else if(!flag2 && !flag3){
                            JFrame parent = new JFrame();
                            JOptionPane.showMessageDialog(parent, "Vertical Seam must be in Integer");
                        }
                    }
                    else {
                        JFrame parent = new JFrame();
                        JOptionPane.showMessageDialog(parent, "Not a Valid Image File");
                    }
                }
            }  
        });  
        
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame1.add(file);
        frame1.add(filepath);
        frame1.add(hor);
        frame1.add(vert);
        frame1.add(rows);  
        frame1.add(column);  
        frame1.add(b);
        frame1.setSize(380, 350);  
        frame1.setLayout(null);  
         
        jmiExit.addActionListener(new ActionListener(){ //Exit
            public void actionPerformed (ActionEvent ae){
               System.exit(0);
            }
        });
        frame1.setVisible(true); 
    }
}