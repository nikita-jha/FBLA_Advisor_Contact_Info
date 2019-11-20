package sample;

import javafx.scene.control.CheckBox;

import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private Connection conn;
    private Statement stmt;

    public DBConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\nikit\\FBLAAdvisorContact1.accdb");
            stmt = conn.createStatement();
            conn.setAutoCommit(true);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    public ArrayList<Advisor> readAdvisor(Advisor advisor) {

        ResultSet rest;
        ArrayList<Advisor> advArray = new ArrayList<Advisor>();
        String query = "SELECT AD_ID, AD_NAME, AD_NUMBER, AD_FAX, AD_EMAIL, AD_SECEMAIL, AD_ADDRESS, AD_CITYSTATEZIP, " +
                "AD_PRIMARY, CHAPTER, PASSWORD, ARCHIVE, ADD_DATE FROM ADVISOR AD ";
        try {
            if (advisor == null) {
                query = query + "where AD.ARCHIVE=NO AND AD.CHAPTER=NO";

                //search query
            } else {
                String whereClauseNumber = "";
                String whereClauseName = "";
                String whereClauseArchive = "";
                String whereClause = "";
                if (!(advisor.getAdvisorID() == null || advisor.getAdvisorID().trim().isEmpty())) {
                    whereClauseNumber = "AD.AD_ID = '"+advisor.getAdvisorID()+"'";
                    whereClause = "where  " +  whereClauseNumber;
                }
                if (!(advisor.getName() == null || advisor.getName().trim().isEmpty())) {
                    whereClauseName = "AD.AD_NAME = '"+advisor.getName()+"'";
                    whereClause = "where  " + whereClauseName;
                }
                if (advisor.getArchive().isSelected()) {
                    //whereClauseArchive = "AD.ARCHIVE = YES'";
                    //whereClause = "where  " + whereClauseArchive;
                }
                else {
                    whereClauseArchive = "AD.ARCHIVE = NO";
                    whereClause = "where  " + whereClauseArchive;
                }
                if ((!whereClauseNumber.isBlank() && !whereClauseName.isBlank())) {
                    whereClause = "where  " + whereClauseNumber + " and " + whereClauseName + " and " + whereClauseArchive;
                }
                query = query + whereClause;
            }
            System.out.println("The read query called is : "  + query);
            //search query
            rest = stmt.executeQuery(query);
            while(rest.next()) {
                for (int i=1; i<13; i++) {
                    System.out.println(i + "- > value : " + (rest.getString(i)));
                }
                Advisor adv = new Advisor();
                adv.setAdvisorID(rest.getString(1));
                adv.setName(rest.getString(2));
                adv.setPhoneNumber(rest.getString(3));
                adv.setFax(rest.getString(4));
                adv.setPrimaryEmail(rest.getString(5));
                adv.setSecondaryEmail(rest.getString(6));
                adv.setAddress(rest.getString(7));
                adv.setCityStateZip(rest.getString(8));
                CheckBox primary = new CheckBox();
                primary.setSelected(getBooleanFromString(rest.getString(9)));
                adv.setPrimaryAdvisor(primary);
                CheckBox chapter = new CheckBox();
                chapter.setSelected(getBooleanFromString(rest.getString(10)));
                adv.setChapter(chapter);
                adv.setPassword(rest.getString(11));
                CheckBox archive = new CheckBox();
                archive.setSelected(getBooleanFromString(rest.getString(12)));
                adv.setArchive(archive);
                adv.setDateAdded(rest.getString(13));
                advArray.add(adv);
            }

        }
        catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        return advArray;
    }

    private boolean getBooleanFromString(String str) {
        if (str.equals("TRUE")){
            return true;
        } else {
            return false;
        }
    }


    public void insertAdvisor(Advisor advisor) {
        try
        {
            String queryAdvisor = "INSERT INTO ADVISOR(AD_ID, AD_NAME, AD_NUMBER, AD_FAX, AD_EMAIL, AD_SECEMAIL, AD_ADDRESS," +
                    "AD_CITYSTATEZIP, AD_HOMEPHONE, AD_CELLPHONE, AD_PRIMARY, CHAPTER, PASSWORD, ARCHIVE, ADD_DATE) " +
                    "VALUES('"+advisor.getAdvisorID()+"' , " +
                    "'"+advisor.getName()+"' , " +
                    "'"+advisor.getPhoneNumber()+"' , " +
                    "'"+advisor.getFax()+"' , " +
                    "'"+advisor.getPrimaryEmail()+"' , " +
                    "'"+advisor.getSecondaryEmail()+"' , " +
                    "'"+advisor.getAddress()+"' , " +
                    "'"+advisor.getCityStateZip()+"' , " +
                    "'"+advisor.getHomePhone()+"' , " +
                    "'"+advisor.getCellPhone()+"' , " +
                    (advisor.getPrimaryAdvisor().isSelected()?"YES":"NO")+" , " +
                    (advisor.getChapter().isSelected()?"YES":"NO")+" , " +
                    "'"+advisor.getPassword()+"' , " +
                    (advisor.getArchive().isSelected()?"YES":"NO")+" , " +
                    "'"+advisor.getDateAdded()+"')";

            System.out.println("The insert query called is : "  + queryAdvisor);
            stmt = conn.createStatement();
            stmt.executeUpdate(queryAdvisor);

            System.out.println("successful");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Not connected"+e);
        }

    }

    public void deleteAdvisor(Advisor advisor) {
        try
        {
            String query = "UPDATE ADVISOR SET ARCHIVE = YES  WHERE AD_ID = '"+advisor.getAdvisorID()+"' and ARCHIVE= NO";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            System.out.println("successful");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void updatePrimaryAdvisor(String chapterID) {
        try
        {
            String query = "UPDATE ADVISOR SET AD_PRIMARY = NO";
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            System.out.println("successful");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    public void updateAdvisor(Advisor advisor) {
        try
        {

            String query = "UPDATE ADVISOR SET " +
                    "AD_NAME= '"+ advisor.getName()+"' , " +
                    "AD_NUMBER= '"+advisor.getPhoneNumber()+"' , " +
                    "AD_FAX= '"+advisor.getFax()+"' , " +
                    "AD_EMAIL= '"+advisor.getPrimaryEmail()+"' , " +
                    "AD_SECEMAIL= '"+advisor.getSecondaryEmail()+"' , " +
                    "AD_ADDRESS= '"+advisor.getAddress()+"' , " +
                    "AD_CITYSTATEZIP= '"+advisor.getCityStateZip()+"' , " +
                    "AD_HOMEPHONE= '"+advisor.getHomePhone()+"' , " +
                    "AD_CELLPHONE= '"+advisor.getCellPhone()+"' , " +
                    "AD_PRIMARY= "+(advisor.getPrimaryAdvisor().isSelected()?"YES":"NO")+" , " +
                    "CHAPTER= "+(advisor.getChapter().isSelected()?"YES":"NO")+" , " +
                    "PASSWORD= '"+advisor.getPassword()+"' , " +
                    "ARCHIVE= "+(advisor.getArchive().isSelected()?"YES":"NO")+" , " +
                    "ADD_DATE= '"+advisor.getDateAdded()+
                    "' WHERE AD_ID = '"+advisor.getAdvisorID()+"' and ARCHIVE=NO";
            System.out.println("The update query called is : "  + query);
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.commit();

            System.out.println("successful");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Not connected"+e);
        }

    }


    public void closeConnection() {
        try {
            stmt.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createCSVFile(Advisor advisor,
                                    String filename) throws SQLException, Exception
// Create a statement
    {
        ResultSet rest;
        ArrayList<Advisor> advArray = new ArrayList<Advisor>();
        String query = "SELECT AD_ID, AD_NAME, AD_NUMBER, AD_FAX, AD_EMAIL, AD_SECEMAIL, AD_ADDRESS, AD_CITYSTATEZIP, " +
                "AD_PRIMARY, CHAPTER, PASSWORD, ARCHIVE, ADD_DATE FROM ADVISOR AD ";
        try {
            if (advisor == null) {
                query = query + "where AD.ARCHIVE=NO AND AD.CHAPTER=NO";

                //search query
            } else {
                String whereClauseNumber = "";
                String whereClauseName = "";
                String whereClauseArchive = "";
                String whereClause = "";
                if (!(advisor.getAdvisorID() == null || advisor.getAdvisorID().trim().isEmpty())) {
                    whereClauseNumber = "AD.AD_ID = '" + advisor.getAdvisorID() + "'";
                    whereClause = "where  " + whereClauseNumber;
                }
                if (!(advisor.getName() == null || advisor.getName().trim().isEmpty())) {
                    whereClauseName = "AD.AD_NAME = '" + advisor.getName() + "'";
                    whereClause = "where  " + whereClauseName;
                }
                if (advisor.getArchive().isSelected()) {
                    //whereClauseArchive = "AD.ARCHIVE = YES'";
                    //whereClause = "where  " + whereClauseArchive;
                } else {
                    whereClauseArchive = "AD.ARCHIVE = NO";
                    whereClause = "where  " + whereClauseArchive;
                }
                if ((!whereClauseNumber.isBlank() && !whereClauseName.isBlank())) {
                    whereClause = "where  " + whereClauseNumber + " and " + whereClauseName + " and " + whereClauseArchive;
                }
                query = query + whereClause;
            }
            System.out.println("The read query called is : " + query);
            //search query
            rest = stmt.executeQuery(query);
            FileWriter fw = new FileWriter(filename);
            //String record = null;
            boolean empty = true;
            try {
                ResultSetMetaData metaData = rest.getMetaData();
                int columns = metaData.getColumnCount();
                System.out.println("Entering to write");
                for (int j=1; j<= columns; j++) {
                    fw.append(rest.getMetaData().getColumnName(j));
                    fw.append(',');
                }
                fw.append('\n');
                while (rest.next()) {
                    empty = false;
                    for (int i = 1; i <= columns; i++) {
                        //record = rest.getString(i);
                        fw.append(rest.getString(i));
                        fw.append(',');
                        System.out.println("Writing : " + rest.getString(i));
                    }
                    fw.append('\n');

                }
            } finally {
                fw.flush();
                rest.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Not connected"+e);
        }

    }
}

