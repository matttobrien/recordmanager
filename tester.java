import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

/**
 * Tester class for Record
 */
public class tester {
    public static record GUIcurrent = null;
    public static int index;
    public static void main(String[] args) throws FileNotFoundException{
        ArrayList<record> list = new ArrayList<record>();
        File fp = new File("src/records.txt");
        Scanner sc = new Scanner(fp);
        while(sc.hasNextLine()){
            record sr = new record(sc.nextLine());
            list.add(sr);
        }
        int pick;
        Scanner sf = new Scanner(System.in);
        System.out.println("------------------");
        System.out.println("Welcome to RECORDS");
        System.out.println("Enter 1 for CLI");
        System.out.println("Enter 2 for GUI");
        System.out.println("------------------");
        pick = sf.nextInt();
        if(pick == 1) {
            CLI(fp, list);
        }
        else {
            GUI(fp, list);
        }
    }

    /**
     * Command Line Interface for RECORDS
     * @param fp
     * @param list
     */
    private static void CLI(File fp, ArrayList<record> list){
        System.out.println("Welcome!");
        while(true) {
            System.out.println("1. Display all records");
            System.out.println("2. Display/edit one record");
            System.out.println("3. Add new record");
            System.out.println("4. Save");
            System.out.println("5. Exit");
            System.out.println("Please enter your selection!");
            int choice;
            Scanner sca = new Scanner(System.in);
            choice = sca.nextInt();
            switch (choice){
                case 1: {
                    Collections.sort(list);
                    Iterator<record> e = list.iterator();
                    while (e.hasNext()) {
                        record temp = e.next();
                        System.out.println(temp);
                    }
                    System.out.println();
                    break;
                }
                case 2: {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Search by Last name:");
                    String s = scan.nextLine();
                    boolean found = false;
                    ListIterator<record> li = list.listIterator();
                    record current = null;
                    int i = 0;
                    while (li.hasNext()) {
                        current = li.next();
                        if (current.getText().getLastName().compareToIgnoreCase(s.toUpperCase()) == 0) {
                            found = true;
                            i = list.indexOf(current);
                            System.out.println(current);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Record not found!");
                        continue;
                    }
                    boolean outerloop = true;
                    while (outerloop) {
                        System.out.println("1. Next");
                        System.out.println("2. Previous");
                        System.out.println("3. Delete");
                        System.out.println("4. Edit");
                        System.out.println("5. Main Menu");
                        System.out.println("Please make a selection!");
                        Scanner ns = new Scanner(System.in);
                        int x = ns.nextInt();
                        ns.nextLine();
                        if (x == 1 && (i + 1) < list.size()) {
                            if(++i < list.size()) {
                                try {
                                    current = list.get(++i);
                                    System.out.println("Next record: " + current);
                                }catch (IndexOutOfBoundsException e){
                                    System.out.println("No more next!");
                                }
                            } else System.out.println("No more next!");
                        } else if (x == 2 && list.indexOf(current) > 0) {
                            if(list.indexOf(current) > 0) {
                                current = list.get(--i);
                                System.out.println("Previous record: " + current);
                            } else System.out.println("No more previous!");
                        } else if (x == 3) {
                            list = removeIndex(current, list);
                            System.out.println("Back to main!");
                            break;
                        } else if (x == 4) {
                            boolean innerloop = true;
                            while(innerloop){
                                System.out.println("1. Edit First name");
                                System.out.println("2. Edit Last name");
                                System.out.println("3. Edit Date of birth");
                                System.out.println("4. Main Menu");
                                System.out.println("Please make a selection!");
                                Scanner scann = new Scanner(System.in);
                                int ch = scann.nextInt();
                                switch (ch) {
                                    case 1: {
                                        Scanner whyy = new Scanner(System.in);
                                        System.out.println("Enter new First name:");
                                        current.text.setFirstName(whyy.nextLine());
                                        break;
                                    }
                                    case 2: {
                                        Scanner whyyy = new Scanner(System.in);
                                        System.out.println("Enter new Last name:");
                                        current.getText().setLastName(whyyy.nextLine());
                                        break;
                                    }
                                    case 3: {
                                        Scanner whyyyy = new Scanner(System.in);
                                        System.out.println("Enter new Date:");
                                        current.getText().setDate(whyyyy.nextLine());
                                        break;
                                    }
                                    case 4: {
                                        innerloop = false;
                                        outerloop = false;
                                        break;
                                    }
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    break;
                }
                case 3:{
                    Scanner why = new Scanner(System.in);
                    System.out.println("Enter Last name: ");
                    String str = why.nextLine();
                    System.out.println("Enter First name: ");
                    String str1 = why.nextLine();
                    String str2 = str1 + "," + str + "," + "01/01/1999";
                    record temp = new record (str2);
                    temp.getDate();
                    list.add(temp);
                    break;
                }
                case 4:{
                    try ( BufferedWriter bw  = new BufferedWriter (new FileWriter (fp)) ) {
                        for (record line : list) {
                            bw.write (line + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 5:{
                    System.out.println("Thank you!\nGoodbye");
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Graphical User Interface for RECORDS
     * @param fp
     * @param list
     */
    private static void GUI(File fp, ArrayList<record> list){
        JFrame frame = new JFrame("Welcome!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200,100,700,200);
        JPanel panel = new JPanel(new GridLayout(1,4));
        JButton b1 = new JButton("Display all Records");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gDisplay(list);
            }
        });
        JButton b2 = new JButton("Display/edit one Record");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gDisplayEdit(list);
            }
        });
        JButton b3 = new JButton("Add a new Record");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*String newRecord = JOptionPane.showInputDialog(null,"Enter new Record: (firstname,lastname,dd/mm/yyyy");
                record temp = new record(newRecord);
                list.add(temp);
                JOptionPane.showMessageDialog(null, "Record Added!");*/
                JFrame addFrame = new JFrame("Add Record");
                JPanel buttonPanel = new JPanel();
                JPanel textPanel = new JPanel();
                JLabel firstNameLabel = new JLabel("First name");
                JLabel lastNameLabel = new JLabel("Last name");
                JLabel dateLabel = new JLabel("Date (mm/dd/yyyy)");
                JTextField firstField = new JTextField("");
                JTextField lastField = new JTextField("");
                JTextField dateField = new JTextField("");
                JButton okay = new JButton("Okay");
                JButton cancel = new JButton("Cancel");
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
                buttonPanel.setLayout(new FlowLayout());
                okay.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newRec = "";
                        String fn = firstField.getText();
                        String ln = lastField.getText();
                        String d = dateField.getText();
                        if(!fn.equals("")) {
                            fn += ",";
                            newRec += fn;
                        }
                        if(!ln.equals("")) {
                            ln += ",";
                            newRec += ln;
                        }
                        if(!d.equals("")) {
                            newRec += d;
                        }
                        if(!(fn.equals("") && ln.equals("") && d.equals(""))) {
                            record temp = new record(newRec);
                            list.add(temp);
                            JOptionPane.showMessageDialog(null, "Record Added!");
                            addFrame.dispose();
                        }else JOptionPane.showMessageDialog(null,"No info entered!");
                    }
                });
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addFrame.dispose();
                    }
                });
                textPanel.add(firstNameLabel);
                textPanel.add(firstField);
                textPanel.add(lastNameLabel);
                textPanel.add(lastField);
                textPanel.add(dateLabel);
                textPanel.add(dateField);
                buttonPanel.add(okay);
                buttonPanel.add(cancel);
                addFrame.add(textPanel, BorderLayout.PAGE_START);
                addFrame.add(buttonPanel, BorderLayout.PAGE_END);
                addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addFrame.setLocationRelativeTo(null);
                addFrame.pack();
                addFrame.setVisible(true);
            }
        });
        JButton b4 = new JButton("Save Records");
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try ( BufferedWriter bw  = new BufferedWriter (new FileWriter (fp)) ) {
                    for (record line : list) {
                        bw.write (line + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Records saved!");
            }
        });
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     *Displays the contents of the Arraylist
     * @param list
     */
    private static void gDisplay(ArrayList<record> list) {
        JFrame displayRecords = new JFrame("Records");
        displayRecords.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayRecords.setBounds(200,100,200,200);
        JPanel displayPanel = new JPanel(new BorderLayout());
        Collections.sort(list);
        JList<record> jist = new JList(list.toArray());
        displayPanel.add(jist);
        displayRecords.getContentPane().add(displayPanel);
        displayRecords.setLocationRelativeTo(null);
        displayRecords.setVisible(true);
    }

    /**
     * Opens a new JFRAME to ask the user how they would like to edit the records
     * @param list
     */
    private static void gDisplayEdit(ArrayList<record> list){
        JFrame displayEditRecords = new JFrame("Records");
        displayEditRecords.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayEditRecords.setBounds(300,200,400,100);
        JPanel displayEditPanel = new JPanel(new BorderLayout());
        ListIterator<record> li = list.listIterator();
        //record current = null;
        boolean found = false;
        index = 0;
        String s = JOptionPane.showInputDialog(null, "Enter Lastname to Search");
        while (li.hasNext()) {
            GUIcurrent = li.next();
            try {
                if (GUIcurrent.getText().getLastName().compareToIgnoreCase(s.toUpperCase()) == 0) {
                    found = true;
                    index = list.indexOf(GUIcurrent);
                    break;
                }
            }
            catch(NullPointerException e){
                System.out.println("");
            }
        }
        if (!found) {
            displayEditRecords.dispose();
            JOptionPane.showMessageDialog(null, "Record not Found!");
        }
        String temp = GUIcurrent.toString();
        JLabel label = new JLabel(temp);
        JButton bt = new JButton("previous");
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list.indexOf(GUIcurrent) > 0) {
                    try {
                        record prev = list.get(--index);
                        JOptionPane.showMessageDialog(null, prev);
                        displayEditRecords.repaint();
                        displayEditRecords.revalidate();
                    } catch(IndexOutOfBoundsException ex){
                        JOptionPane.showMessageDialog(null, "No more previous!");
                    }
                }else JOptionPane.showMessageDialog(null, "No more previous!");
            }
        });
        JButton bt2 = new JButton("edit");
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editRecord(list);
                displayEditRecords.dispose();
            }
        });
        JButton bt3 = new JButton("next");
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(++index < list.size()) {
                    try {
                        record nex = list.get(++index);
                        JOptionPane.showMessageDialog(null, nex);
                        displayEditRecords.repaint();
                        displayEditRecords.revalidate();
                    } catch (IndexOutOfBoundsException exc){
                        JOptionPane.showMessageDialog(null, "Hmm try again");
                    }
                }else JOptionPane.showMessageDialog(null, "No more next!");
            }
        });
        displayEditPanel.add(label, BorderLayout.CENTER);
        displayEditPanel.add(bt, BorderLayout.WEST);
        displayEditPanel.add(bt2, BorderLayout.SOUTH);
        displayEditPanel.add(bt3, BorderLayout.EAST);
        displayEditRecords.getContentPane().add(displayEditPanel);
        displayEditRecords.setLocationRelativeTo(null);
        displayEditRecords.pack();
        displayEditRecords.setVisible(true);
    }

    /**
     * New JFRAME opened for the user to enter in the edited first name, last name, and date
     * @param list
     */
    private static void editRecord(ArrayList<record> list){
        JFrame editFrame = new JFrame("Edit Record");
        JPanel buttonPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JLabel firstNameLabel = new JLabel("First name");
        JLabel lastNameLabel = new JLabel("Last name");
        JLabel dateLabel = new JLabel("Date");
        JTextField firstField = new JTextField("");
        JTextField lastField = new JTextField("");
        JTextField dateField = new JTextField("");
        JButton okay = new JButton("Okay");
        JButton cancel = new JButton("Delete");
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new FlowLayout());
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fn = firstField.getText();
                String ln = lastField.getText();
                String d = dateField.getText();
                if(!fn.equals("")) {
                    GUIcurrent.text.editFirstName(fn);
                }
                if(!ln.equals("")) {
                    GUIcurrent.text.editLastName(ln);
                }
                if(!d.equals("")) {
                    GUIcurrent.text.editDate(d);
                }
                JOptionPane.showMessageDialog(null, "Record Updated!");
                editFrame.dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeIndex(GUIcurrent, list);
                editFrame.dispose();
            }
        });
        textPanel.add(firstNameLabel);
        textPanel.add(firstField);
        textPanel.add(lastNameLabel);
        textPanel.add(lastField);
        textPanel.add(dateLabel);
        textPanel.add(dateField);
        buttonPanel.add(okay);
        buttonPanel.add(cancel);
        editFrame.add(textPanel, BorderLayout.PAGE_START);
        editFrame.add(buttonPanel, BorderLayout.PAGE_END);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setLocationRelativeTo(null);
        editFrame.pack();
        editFrame.setVisible(true);
    }

    /**
     * Method to remove a certain node from the Arraylist
     * @param x
     * @param list
     * @return
     */
    private static ArrayList<record> removeIndex(record x, ArrayList<record> list){
        //int i = list.indexOf(x);
        list.remove(x);
        return list;
    }

}
