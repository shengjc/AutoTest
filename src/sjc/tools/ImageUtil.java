package sjc.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author shengjc
 * 图片操作工具
 */
public class ImageUtil {
	private static File file = null;
	 /**
     * 从本地文件读取图像的二进制流
     * 
     * @param infile
     * @return
     */
	public static FileInputStream getImageByte(String infile) {
        FileInputStream imageByte = null;
        file = new File(infile);
        try {
            imageByte = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageByte;
    }
	
	 /**
     * 将图片流读出为图片
     * 
     * @param inputStream
     * @param path
     */
    public static void readBlob(InputStream inputStream, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 将图片保存到数据库
     * 
     * @param imagePath	图片地址
     * @param sqlClassName	数据库类型 ，如com.mysql.jdbc.Driver
     * @param username	数据库用户名
     * @param passWord	数据库密码
     * @param sqlUrl	数据库连接url
     */
    public static void saveToSql(String imagePath,String sqlClassName,String username,String passWord,String sqlUrl) {
    	try {
    		//"com.mysql.jdbc.Driver"
            Class.forName(sqlClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String user = username;
        String password = passWord;
        String url = sqlUrl;	//"jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;
        InputStream inputStream = null;
        inputStream = ImageUtil.getImageByte(imagePath);		//"D:\\temp\\photo1.png"
        try {
            String sql = "insert into photo(id,name,photo) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "朱莉");
            preparedStatement.setBinaryStream(3, inputStream,
                    inputStream.available());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    /**
     * 从数据库中获取图片
     * 
     * @param imagePath	图片地址	"D:\\temp\\photo2.png"
     * @param sqlClassName	数据库类型 ，如com.mysql.jdbc.Driver
     * @param username	数据库用户名
     * @param passWord	数据库密码
     * @param sqlUrl	数据库连接url
     * @param sqlStatement	查询图片的SQL语句，如select p.photo from photo p where id = 1
     */
    public static void sqlToImage(String imagePath,String sqlClassName,String username,String passWord,String sqlUrl,String sqlStatement) {
    	 try {
             Class.forName(sqlClassName).newInstance();
         } catch (InstantiationException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
         String user = username;
         String password = passWord;
         String url = sqlUrl;
         Connection connection = null;
         try {
             connection = DriverManager.getConnection(url, user, password);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         Statement statement = null;
         ResultSet resultSet = null;
         InputStream inputStream = null;
         try {
             statement = connection.createStatement();
             String sql = sqlStatement;
             resultSet = statement.executeQuery(sql);
             resultSet.next();
             inputStream = resultSet.getBinaryStream("photo");
             ImageUtil.readBlob(inputStream, imagePath);
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             try {
                 if (inputStream != null)
                     inputStream.close();
             } catch (IOException e) {
                 e.printStackTrace();
             } finally {
                 try {
                     if (resultSet != null)
                         resultSet.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 } finally {
                     if (statement != null)
                         if (statement != null)
                             try {
                                 statement.close();
                             } catch (SQLException e) {
                                 e.printStackTrace();
                             } finally {
                                 if (connection != null)
                                     try {
                                         connection.close();
                                     } catch (SQLException e) {
                                         e.printStackTrace();
                                     }
                             }
                 }
             }
         }
    }
}
